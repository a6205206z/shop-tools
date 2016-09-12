# xmmspider.spiders
# -*- coding: utf-8 -*-
import re
import time
import urlparse

from scrapy import Spider, Request
from xmmspider.items import TmallShopProductItem
from xmmspider.helper import Helper


class TmallShopProductSpider(Spider):
    name = "TmallShopProductSpider"
    shop_urls = []

    def __init__(self, ri=None, *args, **kwargs):
        super(TmallShopProductSpider, self).__init__(*args, **kwargs)
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
                                  dont_filter=True,
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
        item = TmallShopProductItem()
        item["shop_id"] = response.meta['shop_id']
        item["run_id"] = self.run_id
        item['product_url'] = response.url
        item['product_page'] = response.body_as_unicode()

        # find selled qty.
        pattern = re.compile(
            '\'//mdskip.taobao.com/core/initItemDetail.htm\?(.*?)\'', re.S)
        match = re.search(pattern, response.body_as_unicode())
        if match:
            sib_url = 'https://mdskip.taobao.com/core/initItemDetail.htm?%s&callback=setMdskip' % match.group(
                1)
            yield Request(sib_url,
                          callback=self.parse_sib,
                          errback=self.errback,
                          headers={
                          "Referer": response.url,
                          "Host":"mdskip.taobao.com",
                          },
                          meta={'cookiejar': 1,
                                'item': item,
                                },
                          )
        else:
            item['sib_page'] = "0"

        url = urlparse.urlparse(response.url)
        params = urlparse.parse_qs(url.query, True)
        if 'id' in params:
            counter_url = "https://count.taobao.com/counter3?callback=jsonp225&keys=ICCP_1_%s,DFX_200_1_%s,ICVT_7_%s,ICCP_1_%s,SCCP_2_%s" % (
                params['id'][0], params['id'][0], params['id'][0], params['id'][0], response.meta['shop_id'])
            yield Request(counter_url,
                          callback=self.parse_counter,
                          errback=self.errback,
                          headers={"Referer": response.url},
                          meta={'cookiejar': 1,
                                'item': item,
                                },
                          )
            detail_count_url = "https://dsr-rate.tmall.com/list_dsr_info.htm?itemId=%s&callback=jsonp197" % params[
                'id'][0]
            yield Request(detail_count_url,
                          callback=self.parse_comment_count,
                          errback=self.errback,
                          headers={"Referer": response.url},
                          meta={'cookiejar': 1,
                                'item': item,
                                },
                          )
        else:
            item['counter_page'] = "0"
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
