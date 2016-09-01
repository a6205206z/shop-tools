# -*- coding: utf-8 -*-
import logging
from scrapy import signals
from xmmspider.db import TaobaoShopMySQLAccess

logger = logging.getLogger(__name__)

class TaobaoShopDataMySQLLoader(object):
    def __init__(self,db_host,db_port,db_user,db_passwd,db_name):
    	self.access = TaobaoShopMySQLAccess(db_host,db_port,db_user,db_passwd,db_name)

    @classmethod
    def from_crawler(cls, crawler):

        ext = cls(
            db_host=crawler.settings.get('DB_HOST'),
            db_user=crawler.settings.get('DB_USER'),
            db_passwd=crawler.settings.get('DB_PASSWD'),
            db_name=crawler.settings.get('DB_NAME'),
            db_port=crawler.settings.get('DB_PORT'),
            )

        crawler.signals.connect(ext.spider_opened, signal=signals.spider_opened)
        crawler.signals.connect(ext.spider_closed, signal=signals.spider_closed)
        # crawler.signals.connect(ext.item_scraped, signal=signals.item_scraped)

        return ext

    def spider_opened(self, spider):
    	logger.info("load shop urls")
        spider.shop_urls = self.access.loadShopUrlandShopID()

    def spider_closed(self, spider):
    	logger.info("close")


