#统计某个店铺 七日商品 每日 销量、确认收货数、销售额
set @shopid = 110918969;
select
unix_timestamp(curdate()) as date,
shopid,
numiid,
title,
(max(sold_total_count) - min(sold_total_count)) as sold_count,
(max(confirm_goods_count) - min(confirm_goods_count)) as confirm_goods_count,
(max(total_sales) - min(total_sales)) as total_sales
 from (
select * from d_items where date =  unix_timestamp(curdate()) or date = unix_timestamp(date_sub(curdate(),interval 1 day))
) as t group by t.numiid,t.title,shopid having shopid=@shopid
UNION
select
unix_timestamp(date_sub(curdate(),interval 1 day)) as date,
shopid,
numiid,
title,
(max(sold_total_count) - min(sold_total_count)) as sold_count,
(max(confirm_goods_count) - min(confirm_goods_count)) as confirm_goods_count,
(max(total_sales) - min(total_sales)) as total_sales
 from (
select * from d_items where date =  date = unix_timestamp(date_sub(curdate(),interval 1 day)) or date = unix_timestamp(date_sub(curdate(),interval 2 day))
) as t group by t.numiid,t.title,shopid having shopid=@shopid
UNION
select
unix_timestamp(date_sub(curdate(),interval 2 day)) as date,
shopid,
numiid,
title,
(max(sold_total_count) - min(sold_total_count)) as sold_count,
(max(confirm_goods_count) - min(confirm_goods_count)) as confirm_goods_count,
(max(total_sales) - min(total_sales)) as total_sales
 from (
select * from d_items where date =  date = unix_timestamp(date_sub(curdate(),interval 2 day)) or date = unix_timestamp(date_sub(curdate(),interval 3 day))
) as t group by t.numiid,t.title,shopid having shopid=@shopid
UNION
select
unix_timestamp(date_sub(curdate(),interval 3 day)) as date,
shopid,
numiid,
title,
(max(sold_total_count) - min(sold_total_count)) as sold_count,
(max(confirm_goods_count) - min(confirm_goods_count)) as confirm_goods_count,
(max(total_sales) - min(total_sales)) as total_sales
 from (
select * from d_items where date =  date = unix_timestamp(date_sub(curdate(),interval 3 day)) or date = unix_timestamp(date_sub(curdate(),interval 4 day))
) as t group by t.numiid,t.title,shopid having shopid=@shopid
UNION
select
unix_timestamp(date_sub(curdate(),interval 4 day)) as date,
shopid,
numiid,
title,
(max(sold_total_count) - min(sold_total_count)) as sold_count,
(max(confirm_goods_count) - min(confirm_goods_count)) as confirm_goods_count,
(max(total_sales) - min(total_sales)) as total_sales
 from (
select * from d_items where date =  date = unix_timestamp(date_sub(curdate(),interval 4 day)) or date = unix_timestamp(date_sub(curdate(),interval 5 day))
) as t group by t.numiid,t.title,shopid having shopid=@shopid
UNION
select
unix_timestamp(date_sub(curdate(),interval 5 day)) as date,
shopid,
numiid,
title,
(max(sold_total_count) - min(sold_total_count)) as sold_count,
(max(confirm_goods_count) - min(confirm_goods_count)) as confirm_goods_count,
(max(total_sales) - min(total_sales)) as total_sales
 from (
select * from d_items where date =  date = unix_timestamp(date_sub(curdate(),interval 5 day)) or date = unix_timestamp(date_sub(curdate(),interval 6 day))
) as t group by t.numiid,t.title,shopid having shopid=@shopid
UNION
select
unix_timestamp(date_sub(curdate(),interval 6 day)) as date,
shopid,
numiid,
title,
(max(sold_total_count) - min(sold_total_count)) as sold_count,
(max(confirm_goods_count) - min(confirm_goods_count)) as confirm_goods_count,
(max(total_sales) - min(total_sales)) as total_sales
 from (
select * from d_items where date =  date = unix_timestamp(date_sub(curdate(),interval 6 day)) or date = unix_timestamp(date_sub(curdate(),interval 7 day))
) as t group by t.numiid,t.title,shopid having shopid=@shopid





#统计某个店铺的 七日 每日销售量， 确认收货数，销售额，评价数
set @shopid = 116679011;

select 
shopid,
unix_timestamp(curdate()) as date,
(max(t.sold) - min(t.sold))  as sold,
(max(t.comfirm) - min(t.comfirm))  as comfirm,
(max(t.sales) - min(t.sales))  as sales,
(max(t.rated) - min(t.rated))  as rated
from (
select 
shopid,
sum(sold_total_count) as sold,
sum(confirm_goods_count) as comfirm,
sum(total_sales) as sales,
sum(total_rated_count) as rated
from d_items where date =  unix_timestamp(curdate()) or date = unix_timestamp(date_sub(curdate(),interval 1 day)) group by shopid,date
) as t group by t.shopid having shopid = @shopid
union
select 
shopid,
unix_timestamp(date_sub(curdate(),interval 1 day)) as date,
(max(t.sold) - min(t.sold))  as sold,
(max(t.comfirm) - min(t.comfirm))  as comfirm,
(max(t.sales) - min(t.sales))  as sales,
(max(t.rated) - min(t.rated))  as rated
from (
select 
shopid,
sum(sold_total_count) as sold,
sum(confirm_goods_count) as comfirm,
sum(total_sales) as sales,
sum(total_rated_count) as rated
from d_items where date =  unix_timestamp(date_sub(curdate(),interval 1 day)) or date = unix_timestamp(date_sub(curdate(),interval 2 day)) group by shopid,date
) as t group by t.shopid having shopid = @shopid
union
select 
shopid,
unix_timestamp(date_sub(curdate(),interval 2 day)) as date,
(max(t.sold) - min(t.sold))  as sold,
(max(t.comfirm) - min(t.comfirm))  as comfirm,
(max(t.sales) - min(t.sales))  as sales,
(max(t.rated) - min(t.rated))  as rated
from (
select 
shopid,
sum(sold_total_count) as sold,
sum(confirm_goods_count) as comfirm,
sum(total_sales) as sales,
sum(total_rated_count) as rated
from d_items where date =   unix_timestamp(date_sub(curdate(),interval 2 day)) or date = unix_timestamp(date_sub(curdate(),interval 3 day)) group by shopid,date
) as t group by t.shopid having shopid = @shopid
union
select 
shopid,
unix_timestamp(date_sub(curdate(),interval 3 day)) as date,
(max(t.sold) - min(t.sold))  as sold,
(max(t.comfirm) - min(t.comfirm))  as comfirm,
(max(t.sales) - min(t.sales))  as sales,
(max(t.rated) - min(t.rated))  as rated
from (
select 
shopid,
sum(sold_total_count) as sold,
sum(confirm_goods_count) as comfirm,
sum(total_sales) as sales,
sum(total_rated_count) as rated
from d_items where date =   unix_timestamp(date_sub(curdate(),interval 3 day)) or date = unix_timestamp(date_sub(curdate(),interval 4 day)) group by shopid,date
) as t group by t.shopid having shopid = @shopid
union
select 
shopid,
unix_timestamp(date_sub(curdate(),interval 4 day)) as date,
(max(t.sold) - min(t.sold))  as sold,
(max(t.comfirm) - min(t.comfirm))  as comfirm,
(max(t.sales) - min(t.sales))  as sales,
(max(t.rated) - min(t.rated))  as rated
from (
select 
shopid,
sum(sold_total_count) as sold,
sum(confirm_goods_count) as comfirm,
sum(total_sales) as sales,
sum(total_rated_count) as rated
from d_items where date =   unix_timestamp(date_sub(curdate(),interval 4 day)) or date = unix_timestamp(date_sub(curdate(),interval 5 day)) group by shopid,date
) as t group by t.shopid having shopid = @shopid
union
select 
shopid,
unix_timestamp(date_sub(curdate(),interval 5 day)) as date,
(max(t.sold) - min(t.sold))  as sold,
(max(t.comfirm) - min(t.comfirm))  as comfirm,
(max(t.sales) - min(t.sales))  as sales,
(max(t.rated) - min(t.rated))  as rated
from (
select 
shopid,
sum(sold_total_count) as sold,
sum(confirm_goods_count) as comfirm,
sum(total_sales) as sales,
sum(total_rated_count) as rated
from d_items where date =   unix_timestamp(date_sub(curdate(),interval 5 day)) or date = unix_timestamp(date_sub(curdate(),interval 6 day)) group by shopid,date
) as t group by t.shopid having shopid = @shopid
union
select 
shopid,
unix_timestamp(date_sub(curdate(),interval 6 day)) as date,
(max(t.sold) - min(t.sold))  as sold,
(max(t.comfirm) - min(t.comfirm))  as comfirm,
(max(t.sales) - min(t.sales))  as sales,
(max(t.rated) - min(t.rated))  as rated
from (
select 
shopid,
sum(sold_total_count) as sold,
sum(confirm_goods_count) as comfirm,
sum(total_sales) as sales,
sum(total_rated_count) as rated
from d_items where date =   unix_timestamp(date_sub(curdate(),interval 6 day)) or date = unix_timestamp(date_sub(curdate(),interval 7 day)) group by shopid,date
) as t group by t.shopid having shopid = @shopid

