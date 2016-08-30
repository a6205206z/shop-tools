# -*- coding: utf-8 -*-

import os
import time

from xmmspider.items import TaobaoShopInfoItem

class SaveDataAsFilePipeline(object):
	def __init__(self, dir_path):
		self.base_path = dir_path

	@classmethod
	def from_crawler(cls, crawler):
		return cls(dir_path=crawler.settings.get('FILE_OUTPUT_BASE_PATH'))

	def process_item(self, item, spider):
		date_path = self.base_path + item['run_id']
		if not os.path.exists(date_path):
			os.mkdir(date_path)

		domain_dir_path = date_path + '/' + item['shop_domain']
		if not os.path.exists(domain_dir_path):
			os.mkdir(domain_dir_path)

		if isinstance(item, TaobaoShopInfoItem):
			file_path = domain_dir_path + "/shopinfo.html"
			file = open(file_path, 'w')
			file.write(item['shop_info_page'].encode('utf8'))
			file.close()