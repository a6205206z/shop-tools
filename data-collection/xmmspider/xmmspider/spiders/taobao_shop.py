# xmmspider.spiders
# -*- coding: utf-8 -*-

import re

from scrapy import Spider, Request
from xmmspider.items import TaobaoShopInfoItem


class TaobaoShopSpider(Spider):
    name = "TaobaoShopSpider"
    shop_urls = []

    def __init__(self, ri=None, *args, **kwargs):
        super(TaobaoShopSpider, self).__init__(*args, **kwargs)
        self.run_id = ri

    def start_requests(self):
        if self.shop_urls != None:
            for url in self.shop_urls:
                item = TaobaoShopInfoItem()
                item["shop_id"] = url["shop_id"]
                item["run_id"] = self.run_id
                request = Request(url["url"],
                                  callback=self.find_shop_rate_page,
                                  meta={'cookiejar': 1, 'item': item},
                                  )
                yield request

    def find_shop_rate_page(self, response):
        # find shop name
        item = response.meta['item']
        item["shop_name"] = response.xpath(
            "//a[@class='shop-name']/span/text()").extract()

        pattern = re.compile('//rate.taobao.com/(.*?).htm', re.S)
        match = re.search(pattern, response.body_as_unicode())
        if(match):
            rate_page_url = 'https://rate.taobao.com/%s.htm' % match.group(1)
            request = Request(rate_page_url,
                              callback=self.extract_shop_rate_data,
                              meta={'cookiejar': response.meta[
                                  'cookiejar'], 'item': item},
                              # cna from https://log.mmstat.com/eg.js
                              cookies={'cna': '4RdLEIipQlECAWXM93goJEgl'},
                              )
            return request

    def extract_shop_rate_data(self, response):
        item = response.meta['item']
        item["shop_info_page"] = response.body_as_unicode()
        return item
