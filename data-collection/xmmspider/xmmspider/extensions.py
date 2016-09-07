# -*- coding: utf-8 -*-
import logging
import time

from scrapy import signals
from xmmspider.db import TaobaoShopMySQLAccess
from xmmspider.spiders.taobao_shop import TaobaoShopSpider
from xmmspider.spiders.taobao_shop_product import TaobaoShopProductSpider
from xmmspider.spiders.tmall_shop import TmallShopSpider
from xmmspider.spiders.tmall_shop_product import TmallShopProductSpider

logger = logging.getLogger(__name__)


class TaobaoShopDataMySQLLoader(object):

    def __init__(self, db_host, db_port, db_user, db_passwd, db_name):
        self.access = TaobaoShopMySQLAccess(
            db_host, db_port, db_user, db_passwd, db_name)

    @classmethod
    def from_crawler(cls, crawler):

        ext = cls(
            db_host=crawler.settings.get('DB_HOST'),
            db_user=crawler.settings.get('DB_USER'),
            db_passwd=crawler.settings.get('DB_PASSWD'),
            db_name=crawler.settings.get('DB_NAME'),
            db_port=crawler.settings.get('DB_PORT'),
        )

        crawler.signals.connect(
            ext.spider_opened, signal=signals.spider_opened)

        return ext

    def spider_opened(self, spider):
        logger.info("load shop urls")
        if isinstance(spider, TaobaoShopSpider) or isinstance(spider, TaobaoShopProductSpider):
            spider.shop_urls = self.access.load_shop_urls("C")
        elif isinstance(spider, TmallShopSpider) or isinstance(spider, TmallShopProductSpider):
            spider.shop_urls = self.access.load_shop_urls("B")


class SpiderMonitor(object):

    def __init__(self, db_host, db_port, db_user, db_passwd, db_name, stats):
        self.access = TaobaoShopMySQLAccess(
            db_host, db_port, db_user, db_passwd, db_name)
        self.stats = stats

    @classmethod
    def from_crawler(cls, crawler):

        ext = cls(
            db_host=crawler.settings.get('DB_HOST'),
            db_user=crawler.settings.get('DB_USER'),
            db_passwd=crawler.settings.get('DB_PASSWD'),
            db_name=crawler.settings.get('DB_NAME'),
            db_port=crawler.settings.get('DB_PORT'),
            stats=crawler.stats
        )

        crawler.signals.connect(
            ext.spider_opened, signal=signals.spider_opened)
        crawler.signals.connect(
            ext.spider_closed, signal=signals.spider_closed)

        return ext

    def spider_opened(self, spider):
        self.access.inster_spider_job(
            spider.run_id, spider.name, "%s-%s.log" % (spider.name, spider.run_id))

    def spider_closed(self, spider):
        self.access.update_spider_job(
            spider.run_id, spider.name, self.stats.get_stats())


class SpiderRequestInterval(object):

    def __init__(self, interval):
        self.interval = interval

    @classmethod
    def from_crawler(cls, crawler):
        ext = cls(
            interval=int(crawler.settings.get('DOWNLOAD_DELAY')),
        )

        crawler.signals.connect(ext.request_scheduled,
                                signal=signals.request_scheduled)
        return ext

    def request_scheduled(self, request, spider):
        time.sleep(self.interval)
