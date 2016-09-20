# xmmspider.spiders
# -*- coding: utf-8 -*-
import re
from scrapy import Spider, Request
from xmmspider.items import TaobaoTejiaProductItem
from xmmspider.helper import Helper


class TaobaoTejiaProductSpider(Spider):
    name = "TaobaoTejiaProductSpider"
    catalog_urls = [
        {"catalog": "nvzhuang", "url": "http://zhi.taobao.com/json/fantomasItems.htm?t=1474349457152&_input_charset=utf-8&sort=null&appId=9&blockId=901&bucketId=1&extQuery=null&thirdQuery=null&viewId=43376839-9e4b-9720-b0de-9533b0f624bd&requestId=5360d62f-9ce1-ef24-e52a-7893f44991f2&_ksTS=1474349457153_154&callback=jsonp155&pageSize=%d&startRow=%d"}
    ]
    pageSize = 100

    def __init__(self, ri=None, *args, **kwargs):
        super(TaobaoTejiaProductSpider, self).__init__(*args, **kwargs)
        self.run_id = ri

    def start_requests(self):
        if self.catalog_urls != None:
            for url in self.catalog_urls:
                request = Request(url["url"] % (self.pageSize, 0),
                                  callback=self.find_pages,
                                  meta={'cookiejar': 1,
                                        'url': url["url"],
                                        'catalog': url["catalog"],
                                        },
                                  )
                yield request

    def find_pages(self, response):
        pattern = re.compile('\"totalItem\": \"(.*?)\"', re.S)
        match = re.search(pattern, response.body_as_unicode())
        if match:
            total = int(match.group(1))
            totalPage = total / self.pageSize
            if totalPage > 0:
                for page in range(0, totalPage):
                    request = Request(response.meta["url"] % (self.pageSize, page * self.pageSize),
                                      callback=self.find_products_id_htm,
                                      meta={'cookiejar': 1,
                                            'url': response.meta["url"],
                                            'catalog': response.meta["catalog"],
                                            },
                                      )
                    yield request

    def find_products_id_htm(self, response):
        pattern = re.compile('\"itemId\":\"(.*?)\"', re.S)
        itemIds = pattern.findall(response.body_as_unicode())
        if itemIds != None:
            for itemId in itemIds:
                request = Request("https://item.taobao.com/item.htm?id=%s" % (itemId),
                                  callback=self.parse_product_item,
                                  meta={'cookiejar': 1,
                                        'url': url["url"],
                                        'catalog': url["catalog"],
                                        },
                                  )
                yield request


    def parse_product_item(self, response):
        item = TaobaoTejiaProductItem()
        item["shop_id"] = 0
        item["run_id"] = self.run_id
        item['product_url'] = response.url
        item['product_page'] = response.body_as_unicode()

        # find counter
        pattern = re.compile('\'//count.taobao.com/(.*?)\'', re.S)
        match = re.search(pattern, response.body_as_unicode())
        if match:
            counter_url = 'https://count.taobao.com/%s&callback=jsonp86' % match.group(
                1)
            yield Request(counter_url,
                          callback=self.parse_counter,
                          errback=self.errback,
                          headers={"Referer": response.url},
                          meta={'cookiejar': 1,
                                'item': item,
                                },
                          )
        else:
            item['counter_page'] = "0"

        # find stock and selled qty.
        pattern = re.compile(
            '\'//detailskip.taobao.com/service/(.*?)\'', re.S)
        match = re.search(pattern, response.body_as_unicode())
        if match:
            sib_url = 'https://detailskip.taobao.com/service/%s&callback=onSibRequestSuccess' % match.group(
                1)
            yield Request(sib_url,
                          callback=self.parse_sib,
                          errback=self.errback,
                          headers={"Referer": response.url},
                          meta={'cookiejar': 1,
                                'item': item,
                                },
                          )
        else:
            item['sib_page'] = "0"

        # find detail counter
        pattern = re.compile(
            '\'//rate.taobao.com/detailCount.do\?itemId=(.*?)\'', re.S)
        match = re.search(pattern, response.body_as_unicode())
        if match:
            detail_count_url = 'https://rate.taobao.com/detailCount.do?itemId=%s&callback=jsonp100' % match.group(
                1)
            yield Request(detail_count_url,
                          callback=self.parse_comment_count,
                          errback=self.errback,
                          headers={"Referer": response.url},
                          meta={'cookiejar': 1,
                                'item': item,
                                },
                          )

        else:
            item['comment_count'] = "0"

        if self.can_return_item(item):
            yield item

    def parse_counter(self, response):
        item = response.meta['item']
        item['counter_page'] = response.body_as_unicode()
        if self.can_return_item(item):
            return item

    def parse_sib(self, response):
        item = response.meta['item']
        item['sib_page'] = response.body_as_unicode()
        if self.can_return_item(item):
            return item

    def parse_comment_count(self, response):
        item = response.meta['item']
        item['comment_count'] = response.body_as_unicode()
        if self.can_return_item(item):
            return item

    def can_return_item(self, item):
        return 'sib_page' in item and 'counter_page' in item and 'comment_count' in item

    def errback(self, failure):
        response = failure.value.response
        item = response.meta['item']
        if 'sib_page' not in item:
            item['sib_page'] = "0"
        if 'counter_page' not in item:
            item['counter_page'] = "0"
        if 'comment_count' not in item:
            item['comment_count'] = "0"
        return item

