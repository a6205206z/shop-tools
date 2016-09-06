# xmmspider.spiders
# -*- coding: utf-8 -*-
import re
import time

from scrapy import Spider, Request
from xmmspider.items import TaobaoShopProductItem
from xmmspider.helper import Helper


class TaobaoShopProductSpider(Spider):
    name = "TaobaoShopProductSpider"
    shop_urls = []

    def __init__(self, ri=None, *args, **kwargs):
        super(TaobaoShopProductSpider, self).__init__(*args, **kwargs)
        self.run_id = ri

    def start_requests(self):
        if self.shop_urls != None:
            for url in self.shop_urls:
                request = Request(url["url"] + 'search.htm',
                                  callback=self.find_products_htm,
                                  meta={'cookiejar': 1,
                                        'shop_url': url["url"],
                                        'shop_id': url["shop_id"],
                                        },
                                  )
                yield request

    def find_products_htm(self, response):
        products_htm = response.xpath(
            "//input[@id='J_ShopAsynSearchURL']/@value").extract()
        if products_htm != None:
            return Request(response.meta['shop_url'] + Helper.encode_utf8(products_htm),
                           callback=self.page_process,
                           meta={'cookiejar': 1,
                                 'shop_url': response.meta['shop_url'],
                                 'shop_id': response.meta['shop_id'],
                                 },
                           )

    def page_process(self, response):
        product_urls = response.xpath(
            "//a[@class='\\\"J_TGoldData\\\"']/@href").extract()
        if len(product_urls) > 0:
            for p_url in product_urls:
                p_url = p_url.replace('\\"', '')
                p_url = p_url.replace('//', 'https://')
                request = Request(p_url,
                                  callback=self.parse_product_item,
                                  meta={'cookiejar': 1,
                                        'shop_url': response.meta['shop_url'],
                                        'shop_id': response.meta['shop_id'],
                                        },
                                  )
                yield request

        next_urls = response.xpath(u"//a[contains(.,'下一页')]/@href").extract()
        if len(next_urls) > 0:
            url = next_urls[0].replace('\\"', '')
            url = url.replace('//', 'https://')
            request = Request(url,
                              callback=self.find_products_htm,
                              meta={'cookiejar': 1,
                                    'shop_url': response.meta['shop_url'],
                                    'shop_id': response.meta['shop_id'],
                                    },
                              )
            yield request

    def parse_product_item(self, response):
        item = TaobaoShopProductItem()
        item["shop_id"] = response.meta['shop_id']
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
