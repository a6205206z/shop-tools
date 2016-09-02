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
                item = TaobaoShopProductItem()
                item["shop_id"] = url["shop_id"]
                item["run_id"] = self.run_id
                request = Request(url["url"] + 'search.htm',
                                  callback=self.find_products_htm,
                                  meta={'cookiejar': 1,
                                        'item': item,
                                        'shop_url': url["url"],
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
                                 'item': response.meta['item'],
                                 'shop_url': response.meta['shop_url'],
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
                                        'item': response.meta['item'],
                                        'shop_url': response.meta['shop_url'],
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
                                    'item': response.meta['item'],
                                    'shop_url': response.meta['shop_url'],
                                    },
                              )
            yield request

    def parse_product_item(self, response):
        item = response.meta['item']
        item['product_url'] = response.url
        item['product_page'] = response.body_as_unicode()

        pattern = re.compile('\'//count.taobao.com/(.*?)\'', re.S)
        match = re.search(pattern, response.body_as_unicode())
        if match:
            counter_url = 'https://count.taobao.com/%s&callback=jsonp86' % match.group(1)
            return Request(counter_url,
                           callback=self.parse_counter,
                           meta={'cookiejar': 1,
                                 'item': item,
                                 'shop_url': response.meta['shop_url'],
                                 },
                           )
        else:
            item['counter_page'] = "nothing"
            return item

    def parse_counter(self, response):
        item = response.meta['item']
        item['counter_page'] = response.body_as_unicode()
        return item
