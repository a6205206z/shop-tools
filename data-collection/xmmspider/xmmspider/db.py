# -*- coding: utf-8 -*-
import MySQLdb
import logging

logger = logging.getLogger(__name__)

class TaobaoShopMySQLAccess:
    def __init__(self,db_host,db_port,db_user,db_passwd,db_name):
        self.db_host=db_host
        self.db_port=db_port
        self.db_user=db_user
        self.db_passwd=db_passwd
        self.db_name=db_name

    def loadShopUrlandShopID(self):
        results = []
        conn=MySQLdb.connect(
            host=self.db_host,
            user=self.db_user,
            passwd=self.db_passwd,
            db=self.db_name,
            port=self.db_port,
            )
        try:
            cursor=conn.cursor()
            cursor.execute('select shopid,store_url from t_shop where status = 0')
            table = cursor.fetchall()
            for row in table:
                results.append({'shop_id':str(row[0]),'url':str(row[1])})
            cursor.close()
        except MySQLdb.Error,e:
            logger.fatal("%d:%s" % (e.args[0], e.args[1]))
        conn.close()
        return results