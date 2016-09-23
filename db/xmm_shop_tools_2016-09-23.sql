# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 192.168.105.252 (MySQL 5.1.73)
# Database: xmm_shop_tools
# Generation Time: 2016-09-23 02:04:18 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table d_activity
# ------------------------------------------------------------

DROP TABLE IF EXISTS `d_activity`;

CREATE TABLE `d_activity` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '标识列',
  `date` bigint(13) NOT NULL COMMENT '数据抓取日期：YYYYMMDDHH',
  `shopid` bigint(20) DEFAULT NULL COMMENT '店铺ID',
  `numiid` bigint(20) NOT NULL COMMENT '商品ID',
  `type` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '活动类型',
  `info` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '活动优惠信息',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



# Dump of table d_dsr
# ------------------------------------------------------------

DROP TABLE IF EXISTS `d_dsr`;

CREATE TABLE `d_dsr` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '标识列',
  `date` bigint(13) DEFAULT NULL COMMENT '数据抓取日期：YYYYMMDDHH',
  `shopid` bigint(20) NOT NULL COMMENT '店铺ID',
  `detail` decimal(10,5) DEFAULT NULL COMMENT '宝贝与描述相符',
  `d_css` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '高、低、持',
  `d_hy` decimal(10,2) DEFAULT NULL COMMENT '高、低、持平行业评分：计算规则：(店铺得分-同行业平均分)/(同行业店铺最高得分-同行业平均分)',
  `d_json` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '动态评分分布情况',
  `seller` decimal(10,5) DEFAULT NULL COMMENT '卖家的服务态度',
  `s_css` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '高、低、持',
  `s_hy` decimal(10,2) DEFAULT NULL COMMENT '高、低、持平行业评分：计算规则：(店铺得分-同行业平均分)/(同行业店铺最高得分-同行业平均分)',
  `s_json` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '动态评分分布情况',
  `rating` decimal(10,5) DEFAULT NULL COMMENT '卖家发货的速度',
  `r_css` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '高、低、持',
  `r_hy` decimal(10,2) DEFAULT NULL COMMENT '高、低、持平行业评分：计算规则：(店铺得分-同行业平均分)/(同行业店铺最高得分-同行业平均分)',
  `r_json` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '动态评分分布情况',
  `created` datetime DEFAULT NULL COMMENT '抓取时间',
  PRIMARY KEY (`id`),
  KEY `ind_date` (`date`),
  KEY `ind_shopid` (`shopid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



# Dump of table d_fans
# ------------------------------------------------------------

DROP TABLE IF EXISTS `d_fans`;

CREATE TABLE `d_fans` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '标识列',
  `date` bigint(13) DEFAULT NULL COMMENT '数据抓取日期：YYYYMMDDHH',
  `shopid` bigint(20) NOT NULL COMMENT '店铺ID',
  `weitao_fans` int(10) DEFAULT NULL COMMENT '微淘粉丝数',
  `updated` datetime NOT NULL COMMENT '最后修改时间。格式：yyyy-MM-dd HH:mm:ss',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



# Dump of table d_item_num
# ------------------------------------------------------------

DROP TABLE IF EXISTS `d_item_num`;

CREATE TABLE `d_item_num` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '标识列',
  `date` bigint(13) NOT NULL COMMENT '数据抓取日期：YYYYMMDDHH',
  `shopid` bigint(20) DEFAULT NULL COMMENT '店铺ID',
  `numiid` bigint(20) NOT NULL COMMENT '商品ID',
  `s_favorite` int(10) DEFAULT NULL COMMENT '店铺收藏数',
  `i_favorite_num` int(10) DEFAULT NULL COMMENT '商品收藏数',
  `i_share_num` int(10) DEFAULT NULL COMMENT '商品分享数',
  `i_pv` int(10) DEFAULT NULL COMMENT '商品浏览量',
  `updated` datetime DEFAULT NULL COMMENT '维护时间',
  PRIMARY KEY (`id`),
  KEY `ind_date` (`date`),
  KEY `ind_shopid` (`shopid`),
  KEY `ind_numiid` (`numiid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



# Dump of table d_items
# ------------------------------------------------------------

DROP TABLE IF EXISTS `d_items`;

CREATE TABLE `d_items` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '标识列',
  `date` bigint(13) NOT NULL COMMENT '数据抓取日期：YYYYMMDDHH',
  `shopid` bigint(20) DEFAULT NULL COMMENT '店铺ID',
  `numiid` bigint(20) NOT NULL COMMENT '商品ID',
  `title` varchar(60) COLLATE utf8_bin DEFAULT NULL COMMENT '商品标题,不能超过60字节',
  `item_url` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '商品URL',
  `pic_url` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '图片链接地址',
  `rcid` bigint(20) DEFAULT NULL COMMENT '一级类目',
  `cid` bigint(20) DEFAULT NULL COMMENT '二级类目',
  `marker_price` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '市场价格:1.0~100',
  `price` decimal(14,2) DEFAULT NULL COMMENT '销售价格',
  `post_fee` decimal(14,2) DEFAULT '0.00' COMMENT '邮费',
  `sold_total_count` int(10) DEFAULT NULL COMMENT '近30天交易成功数',
  `confirm_goods_count` int(10) DEFAULT NULL COMMENT '近30天确认收货数',
  `total_rated_count` int(10) DEFAULT NULL COMMENT '累计评论数',
  `total_sales` decimal(14,2) DEFAULT NULL COMMENT '销售额',
  `stock` int(10) DEFAULT NULL COMMENT '库存',
  `sellable_quantity` int(10) DEFAULT NULL COMMENT '可销售库存****使用stock字段',
  `sku_stock` text COLLATE utf8_bin COMMENT 'sku库存明细',
  `brand_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '品牌',
  `list_time` datetime DEFAULT NULL COMMENT '自动上架时间',
  `delist_time` datetime DEFAULT NULL COMMENT '自动下架时间（跟据上架时间计算+7天）',
  `add_time` datetime DEFAULT NULL COMMENT '商品创建时间',
  `is_delisting` tinyint(1) DEFAULT NULL COMMENT '是否下架 true 正常 false下架',
  `rated` decimal(3,1) DEFAULT '0.0' COMMENT '商品动态评分(天猫)',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `ind_date` (`date`),
  KEY `ind_shopid` (`shopid`),
  KEY `ind_numiid` (`numiid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



# Dump of table d_rated
# ------------------------------------------------------------

DROP TABLE IF EXISTS `d_rated`;

CREATE TABLE `d_rated` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '标识列',
  `date` bigint(10) NOT NULL COMMENT '数据抓取日期：YYYYMMDDHH',
  `shopid` bigint(20) NOT NULL COMMENT '店铺ID',
  `week_good` int(10) DEFAULT NULL COMMENT '最近一周好评总数',
  `week_neutral` int(10) DEFAULT NULL COMMENT '最近一周中评总数',
  `week_bad` int(10) DEFAULT NULL COMMENT '最近一周差评总数',
  `month_good` int(10) DEFAULT NULL COMMENT '最近一月好评总数',
  `month_neutral` int(10) DEFAULT NULL COMMENT '最近一月中评总数',
  `month_bad` int(10) DEFAULT NULL COMMENT '最近一月差评总数',
  `halfyear_good` int(10) DEFAULT NULL COMMENT '最近半年好评总数',
  `halfyear_neutral` int(10) DEFAULT NULL COMMENT '最近半年中评总数',
  `halfyear_bad` int(10) DEFAULT NULL COMMENT '最近半年差评总数',
  `ago_good` int(10) DEFAULT NULL COMMENT '半年以前好评总数',
  `ago_neutral` int(10) DEFAULT NULL COMMENT '半年以前中评总数',
  `ago_bad` int(10) DEFAULT NULL COMMENT '半年以前差评总数',
  `rating` decimal(10,2) DEFAULT NULL COMMENT '好评率',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `ind_date` (`date`),
  KEY `ind_shopid` (`shopid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



# Dump of table d_shop
# ------------------------------------------------------------

DROP TABLE IF EXISTS `d_shop`;

CREATE TABLE `d_shop` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '标识列',
  `date` bigint(13) NOT NULL COMMENT '数据抓取日期：YYYYMMDDHH',
  `shopid` bigint(20) NOT NULL COMMENT '店铺ID',
  `title` varchar(64) COLLATE utf8_bin DEFAULT '' COMMENT '店铺名称',
  `credit_score` bigint(20) DEFAULT '0' COMMENT '信用总分（“好评”加一分，“中评”不加分，“差评”扣一分。分越高，等级越高）',
  `credit_level` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '信用等级（是根据score生成的），信用等级：淘宝会员在淘宝网上的信用度，分为20个级别，级别如：level = 1 时，表示一心；level = 2 时，表示二心',
  `credit_total_num` int(10) DEFAULT '0' COMMENT '收到的评价总条数。取值范围:大于零的整数',
  `credit_good_num` int(10) DEFAULT '0' COMMENT '收到的好评总条数。取值范围:大于零的整数',
  `rating` decimal(10,2) DEFAULT '0.00' COMMENT '好评率',
  `updated` datetime NOT NULL COMMENT '最后修改时间。格式：yyyy-MM-dd HH:mm:ss',
  PRIMARY KEY (`id`),
  KEY `ind_date` (`date`),
  KEY `ind_shopid` (`shopid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



# Dump of table d_tb_services
# ------------------------------------------------------------

DROP TABLE IF EXISTS `d_tb_services`;

CREATE TABLE `d_tb_services` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '标识列',
  `date` bigint(13) NOT NULL COMMENT '数据抓取日期：YYYYMMDDHH',
  `shopid` bigint(20) NOT NULL COMMENT '店铺ID',
  `sh_loc` decimal(10,2) DEFAULT '0.00' COMMENT '平均退款速度：近30天卖家处理完结一笔退款申请平均花费的时长:0.0天',
  `sh_operator` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '相对行业值比较',
  `sh_hy` decimal(10,2) DEFAULT '0.00' COMMENT '行业值：2.68天',
  `shl_loc` decimal(10,2) DEFAULT '0.00' COMMENT '近30天售后率：指卖家在近30天退款成功的笔数（包含售后成立）占近30天支付宝成交笔数的比率:0.0%',
  `shl_operator` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '相对行业值比较',
  `shl_hy` decimal(10,2) DEFAULT '0.00' COMMENT '行业值：9.45%',
  `jf_loc` decimal(10,2) DEFAULT '0.00' COMMENT '近30天纠纷退款：淘宝介入处理且判为卖家责任的退款:0.0%',
  `jf_operator` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '相对行业值比较',
  `jf_hy` decimal(10,2) DEFAULT '0.00' COMMENT '行业值：0.01%',
  `cf_loc` int(5) DEFAULT NULL COMMENT '近30天被处罚总次数:0.0%',
  `cf_operator` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '相对行业值比较',
  `cf_hy` decimal(10,2) DEFAULT NULL COMMENT '行业值：0.7%',
  `cf_json` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '近30天被处罚明细：[]',
  `created` datetime DEFAULT NULL COMMENT '抓取时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



# Dump of table d_tejia_items
# ------------------------------------------------------------

DROP TABLE IF EXISTS `d_tejia_items`;

CREATE TABLE `d_tejia_items` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '标识列',
  `date` bigint(13) NOT NULL COMMENT '数据抓取日期：YYYYMMDDHH',
  `shopid` bigint(20) DEFAULT NULL COMMENT '店铺ID',
  `numiid` bigint(20) NOT NULL COMMENT '商品ID',
  `title` varchar(60) COLLATE utf8_bin DEFAULT NULL COMMENT '商品标题,不能超过60字节',
  `item_url` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '商品URL',
  `pic_url` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '图片链接地址',
  `rcid` bigint(20) DEFAULT NULL COMMENT '一级类目',
  `cid` bigint(20) DEFAULT NULL COMMENT '二级类目',
  `publish_category` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '上活动所发布类目',
  `marker_price` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '市场价格:1.0~100',
  `price` decimal(14,2) DEFAULT NULL COMMENT '销售价格',
  `promo_price` decimal(14,2) DEFAULT NULL COMMENT '优惠价格',
  `post_fee` decimal(14,2) DEFAULT '0.00' COMMENT '邮费',
  `sold_total_count` int(10) DEFAULT NULL COMMENT '近30天交易成功数',
  `confirm_goods_count` int(10) DEFAULT NULL COMMENT '近30天确认收货数',
  `total_rated_count` int(10) DEFAULT NULL COMMENT '累计评论数',
  `total_sales` decimal(14,2) DEFAULT NULL COMMENT '销售额',
  `stock` int(10) DEFAULT NULL COMMENT '库存',
  `sellable_quantity` int(10) DEFAULT NULL COMMENT '可销售库存****使用stock字段',
  `sku_stock` text COLLATE utf8_bin COMMENT 'sku库存明细',
  `brand_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '品牌',
  `list_time` datetime DEFAULT NULL COMMENT '自动上架时间',
  `delist_time` datetime DEFAULT NULL COMMENT '自动下架时间（跟据上架时间计算+7天）',
  `add_time` datetime DEFAULT NULL COMMENT '商品创建时间',
  `is_delisting` tinyint(1) DEFAULT NULL COMMENT '是否下架 true 正常 false下架',
  `rated` decimal(3,1) DEFAULT '0.0' COMMENT '商品动态评分(天猫)',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `ind_date` (`date`),
  KEY `ind_shopid` (`shopid`),
  KEY `ind_numiid` (`numiid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



# Dump of table d_tm_services
# ------------------------------------------------------------

DROP TABLE IF EXISTS `d_tm_services`;

CREATE TABLE `d_tm_services` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '标识列',
  `date` bigint(13) NOT NULL COMMENT '数据抓取日期：YYYYMMDDHH',
  `shopid` bigint(20) NOT NULL COMMENT '店铺ID',
  `tm_tk_loc` decimal(10,2) DEFAULT '0.00' COMMENT '退款完结时长 = 退款完结总时长 / 退款完结总笔数:0.0%',
  `tm_tk_operator` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '相对行业值比较',
  `tm_tk_hy` decimal(10,2) DEFAULT '0.00' COMMENT '行业值：0.7%',
  `tm_tkwjl_loc` decimal(10,2) DEFAULT '0.00' COMMENT '退款自主完结率 = 商家自主完结退款笔数 / 店铺完结退款总笔数:96.65%',
  `tm_tkwjl_operator` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '相对行业值比较',
  `tm_tkwjl_hy` decimal(10,2) DEFAULT '0.00' COMMENT '行业值：0.7%',
  `tm_tkl_loc` decimal(10,4) DEFAULT '0.0000' COMMENT '纠纷退款率 = 判责商家责任笔数 / 支付宝成交笔数:0.0052%',
  `tm_tkl_operator` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '相对行业值比较',
  `tm_tkl_hy` decimal(10,4) DEFAULT '0.0000' COMMENT '行业值：0.7%',
  `created` datetime DEFAULT NULL COMMENT '抓取时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



# Dump of table r_items
# ------------------------------------------------------------

DROP TABLE IF EXISTS `r_items`;

CREATE TABLE `r_items` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '商品天报表数据（今天-昨天）',
  `date` bigint(13) DEFAULT NULL COMMENT '日期:YYYYMMDD',
  `shopid` bigint(20) DEFAULT NULL COMMENT '店铺ID',
  `numiid` bigint(20) NOT NULL COMMENT '商品ID',
  `total_sales` decimal(14,2) DEFAULT '0.00' COMMENT '当天销售总金额',
  `sold_total_count` int(10) DEFAULT NULL COMMENT '当天销量',
  `total_rated_count` int(10) DEFAULT NULL COMMENT '当天评论数',
  `stock` int(10) DEFAULT NULL COMMENT '当天库存量',
  `i_favorite_num` int(10) DEFAULT NULL COMMENT '当天商品收藏数',
  `i_share_num` int(10) DEFAULT NULL COMMENT '当天商品分享数',
  `i_pv` int(10) DEFAULT NULL COMMENT '当天商品浏览量',
  `old_title` varchar(60) COLLATE utf8_bin DEFAULT NULL COMMENT '旧标题',
  `new_title` varchar(60) COLLATE utf8_bin DEFAULT NULL COMMENT '新标题',
  `old_price` varchar(60) COLLATE utf8_bin DEFAULT NULL COMMENT '旧价格',
  `new_price` varchar(60) COLLATE utf8_bin DEFAULT NULL COMMENT '新价格',
  `old_pic` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '旧主图',
  `new_pic` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '新主图',
  `created` datetime DEFAULT NULL COMMENT '统计完成时间',
  PRIMARY KEY (`id`),
  KEY `ind_date` (`date`),
  KEY `ind_shopid` (`shopid`),
  KEY `ind_numiid` (`numiid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



# Dump of table r_shop
# ------------------------------------------------------------

DROP TABLE IF EXISTS `r_shop`;

CREATE TABLE `r_shop` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '标识列（今天-昨天）',
  `date` bigint(13) DEFAULT NULL COMMENT '日期:YYYYMMDD',
  `shopid` bigint(20) NOT NULL COMMENT '店铺ID',
  `sale_goods_num` int(10) DEFAULT '0' COMMENT '当天在售商品数量',
  `on_goods_num` int(10) DEFAULT '0' COMMENT '当天下架商品数量',
  `off_goods_num` int(10) DEFAULT '0' COMMENT '当天上架商品数量',
  `favorite_num` int(10) DEFAULT '0' COMMENT '当天店铺收藏总量',
  `i_favorite_num` int(10) DEFAULT '0' COMMENT '当天商品收藏总量',
  `i_share_num` int(10) DEFAULT '0' COMMENT '当天商品分享数',
  `total_pv` int(10) DEFAULT '0' COMMENT '当天店铺流量',
  `total_wt_fans` int(10) DEFAULT '0' COMMENT '当天微淘粉丝总量',
  `total_sales` decimal(14,2) DEFAULT '0.00' COMMENT '店铺近30天总金额',
  `sold_total_count` int(10) DEFAULT '0' COMMENT '店铺近30天总销量',
  `created` datetime DEFAULT NULL COMMENT '统计完成时间',
  PRIMARY KEY (`id`),
  KEY `ind_date` (`date`),
  KEY `ind_shopid` (`shopid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



# Dump of table t_category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_category`;

CREATE TABLE `t_category` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '类目表，标识列',
  `pid` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '上级类目，默认0',
  `title` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '类目名称',
  `updated` datetime NOT NULL COMMENT '修改时间。格式：yyyy-MM-dd HH:mm:ss',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



# Dump of table t_follow
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_follow`;

CREATE TABLE `t_follow` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '关注表，标识列',
  `uid` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '会员ID',
  `shopid` bigint(20) NOT NULL COMMENT '店铺ID',
  `is_binding` tinyint(1) DEFAULT NULL COMMENT '1:true绑定的自己店铺 0:false关注',
  `status` tinyint(1) DEFAULT NULL COMMENT '1:true正常 0:false取消',
  `updated` datetime NOT NULL COMMENT '修改时间。格式：yyyy-MM-dd HH:mm:ss',
  PRIMARY KEY (`id`),
  KEY `ind_shopid` (`shopid`),
  KEY `ind_uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



# Dump of table t_job
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_job`;

CREATE TABLE `t_job` (
  `runid` int(10) DEFAULT NULL COMMENT '抓取批次ID',
  `spider_node_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '爬虫节点',
  `spider_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '抓取名称',
  `starttime` datetime DEFAULT NULL COMMENT '开始时间',
  `finishtime` datetime DEFAULT NULL COMMENT '完成时间',
  `logfile` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '物理文件名',
  `stats` text COLLATE utf8_bin COMMENT '状态'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



# Dump of table t_shop
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_shop`;

CREATE TABLE `t_shop` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '标识列',
  `shopid` bigint(20) NOT NULL COMMENT '店铺ID',
  `seller_id` bigint(20) DEFAULT NULL COMMENT '卖家会员ID',
  `security_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '加密后的会员ID',
  `nick` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '卖家昵称:店铺主旺旺',
  `cid` bigint(20) DEFAULT NULL COMMENT '店铺所属的类目编号',
  `t_cid` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '内部类目ID',
  `store_url` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '店铺地址',
  `logo_url` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '店铺logo图标',
  `category` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '主营类目',
  `type` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '用户类型。可选值:B(B商家),C(C商家)',
  `location` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '所在地区，如：四川 成都',
  `service_number` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '客服电话',
  `store_date` datetime DEFAULT NULL COMMENT '开店时间',
  `status` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '状态:0正常 1禁用',
  `last_times` int(10) DEFAULT NULL COMMENT '最新抓取批次',
  `created` datetime DEFAULT NULL,
  `updated` datetime NOT NULL COMMENT '修改时间。格式：yyyy-MM-dd HH:mm:ss',
  PRIMARY KEY (`id`),
  KEY `ind_shopid` (`shopid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



# Dump of table t_spider
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_spider`;

CREATE TABLE `t_spider` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'UUID',
  `nodeName` varchar(32) DEFAULT NULL COMMENT '节点名',
  `host` varchar(32) DEFAULT NULL COMMENT '主机地址',
  `descrition` text COMMENT '描述',
  `enable` tinyint(1) DEFAULT NULL COMMENT '是否启用',
  `created` datetime DEFAULT NULL,
  `updated` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
