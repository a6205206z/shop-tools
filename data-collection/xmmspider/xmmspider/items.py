# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# http://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class TaobaoShopInfoItem(scrapy.Item):
    run_id = scrapy.Field()
    shop_id = scrapy.Field()
    shop_name = scrapy.Field()
    shop_info_page = scrapy.Field()
    pass


class TaobaoShopProductItem(scrapy.Item):
    run_id = scrapy.Field()
    shop_id = scrapy.Field()
    product_url = scrapy.Field()
    product_page = scrapy.Field()
    counter_page = scrapy.Field()
    sib_page = scrapy.Field()
    comment_count = scrapy.Field()
    pass
