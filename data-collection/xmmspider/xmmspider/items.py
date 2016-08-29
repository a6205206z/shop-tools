# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# http://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class XmmspiderItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    pass

class TaobaoShopRateItem(scrapy.Item):
	saler_name = scrapy.Field()
	shop_main_category = scrapy.Field()
	shop_city = scrapy.Field()
	saler_credit = scrapy.Field()
	buyer_credit = scrapy.Field()

	#rate 
	rate_product_description = scrapy.Field()
	rate_saler_service = scrapy.Field()
	rate_logistics_service = scrapy.Field()

	#percent over
	percent_over_product_description = scrapy.Field()
	percent_over_saler_service = scrapy.Field()
	percent_over_logistics_service = scrapy.Field()

	#star for product description
	five_star_for_product_description = scrapy.Field()
	four_star_for_product_description = scrapy.Field()
	three_star_for_product_description = scrapy.Field()
	tow_star_for_product_description = scrapy.Field()
	one_star_for_product_description = scrapy.Field()

	#star for saler service
	five_star_for_saler_service = scrapy.Field()
	four_star_for_saler_service = scrapy.Field()
	three_star_for_saler_service = scrapy.Field()
	tow_star_for_saler_service = scrapy.Field()
	one_star_for_saler_service = scrapy.Field()

	#star for logistics service
	five_star_for_logistics_service = scrapy.Field()
	four_star_for_logistics_service = scrapy.Field()
	three_star_for_logistics_service = scrapy.Field()
	tow_star_for_logistics_service = scrapy.Field()
	one_star_for_logistics_service = scrapy.Field()

	pass

