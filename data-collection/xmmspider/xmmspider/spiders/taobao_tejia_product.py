# xmmspider.spiders
# -*- coding: utf-8 -*-
import re
from scrapy import Spider, Request


class TaobaoTejiaProductSpider(Spider):
    name = "TaobaoTejiaProductSpider"
    catalog_urls = []

    def __init__(self, ri=None, *args, **kwargs):
        super(TaobaoTejiaProductSpider, self).__init__(*args, **kwargs)
        self.run_id = ri

    def start_requests(self):
        if self.catalog_urls != None:
            for url in self.catalog_urls:
                print url
