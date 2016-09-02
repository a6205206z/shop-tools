# -*- coding: utf-8 -*-

import os
import time
import urllib

from xmmspider.items import TaobaoShopInfoItem, TaobaoShopProductItem
from pykafka import KafkaClient
from xmmspider.helper import Helper


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

        domain_dir_path = date_path + '/' + item['shop_id']
        if not os.path.exists(domain_dir_path):
            os.mkdir(domain_dir_path)

        if isinstance(item, TaobaoShopInfoItem):
            file_path = domain_dir_path + "/shopinfo.html"
            file = open(file_path, 'w')
            file.write(Helper.encode_utf8(item['shop_info_page']))
            file.close()


# on pykafka
# https://github.com/Parsely/pykafka
# pip install pykafka
class PushDataToKafka(object):

    def __init__(self, hosts):
        self.kafka_hosts = hosts

    @classmethod
    def from_crawler(cls, crawler):
        return cls(hosts=crawler.settings.get('KAFKA_HOSTS'))

    def open_spider(self, spider):
        self.client = KafkaClient(hosts=self.kafka_hosts)

    # def close_spider(self, spider):
        # should not close

    def process_item(self, item, spider):
        if isinstance(item, TaobaoShopInfoItem):
            topic = self.client.topics['taobao.shop.info']
            with topic.get_sync_producer() as producer:
                producer.produce('{"runid":"%s","shopid":"%s","shopname":"%s"}|%s' % (item["run_id"], item[
                                 "shop_id"], urllib.quote(Helper.encode_utf8(item["shop_name"])), Helper.encode_utf8(item["shop_info_page"])))

        if isinstance(item, TaobaoShopProductItem):
            topic = self.client.topics['taobao.shop.product']
            with topic.get_sync_producer() as producer:
                producer.produce('{"runid":"%s","shopid":"%s","producturl":"%s"}|%s|%s' % (item["run_id"], item[
                                 "shop_id"], urllib.quote(item["product_url"]), Helper.encode_utf8(item["counter_page"]), Helper.encode_utf8(item["product_page"])))
