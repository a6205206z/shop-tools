# xmmspider.spiders
# -*- coding: utf-8 -*-
import re
from scrapy import Spider, Request


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
                request = Request(url["url"] %(self.pageSize,0),
                                  callback=self.find_products_htm,
                                  meta={'cookiejar': 1,
                                        'shop_url': url["url"],
                                        'catalog': url["catalog"],
                                        },
                                  )
                yield request

    def find_products_htm(self, response):
        pattern = re.compile('\"totalItem\": \"(.*?)\"', re.S)
        match = re.search(pattern, response.body_as_unicode())
        if match:
            total = int(match.group(1))
            pageNum = total/self.pageSize
            print pageNum


        # pattern = re.compile('\"itemId\":\"(.*?)\"', re.S)
        # itemIds = pattern.findall(response.body_as_unicode())
        # if itemIds != None:
        #     for itemId in itemIds:
        #         print itemId

