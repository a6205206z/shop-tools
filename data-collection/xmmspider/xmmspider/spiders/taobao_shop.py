#xmmspider.spiders
# -*- coding: utf-8 -*-

import re

from scrapy import Spider,Request


class TaobaoShopSpider(Spider):
	name = "TaobaoShopSpider"

	shop_urls=[]

	def __init__(self, *args, **kwargs):
		super(TaobaoShopSpider, self).__init__(*args, **kwargs)
		self.load_shop_urls()

	def load_shop_urls(self):
		self.shop_urls.append('https://shop34685656.taobao.com/')

	def start_requests(self):
		for url in self.shop_urls:
			yield Request(url,
				callback = self.find_shop_rate_page)

	def find_shop_rate_page(self, response):
		pattern = re.compile('//rate.taobao.com/(.*?).htm',re.S)
		match = re.search(pattern,response.body_as_unicode)
		if(match):
			rate_page_url = 'https://rate.taobao.com/%s.htm' % match.group(1)
			return Request(rate_page_url,
				callback = self.extract_shop_rate_data)

	def extract_shop_rate_data(self, response):
		print response.body_as_unicode
