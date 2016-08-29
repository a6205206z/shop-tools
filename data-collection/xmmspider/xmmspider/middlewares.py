#xmmspider.spiders
#coding=utf-8

import random

class RandomUserAgent(object):
    def __init__(self, agents):
        self.agents = agents

    @classmethod
    def from_crawler(cls, crawler):
        return cls(crawler.settings.getlist('USER_AGENTS'))

    def process_request(self, request, spider):
        request.headers.setdefault('User-Agent', random.choice(self.agents))

class ProxyMiddleware(object):
    def __init__(self, proxy_list):
        self.proxy_list = proxy_list

    @classmethod
    def from_crawler(cls, crawler):
        return cls(
            proxy_list=crawler.settings.get('PROXY_LIST')
        )
    def process_request(self, request, spider):
        proxy = random.choice(self.proxy_list)
        

        ip = proxy.split(' ')[0]
        port = proxy.split(' ')[1]
        http_method = proxy.split(' ')[2]

        http_method = http_method.lower()
        request.meta['proxy'] = "%s://%s:%s" % (http_method,ip,port)
        
        #if user_pass is not None:
        #    request.meta['proxy'] = "%s://%s:%s" % (http_method,ip,port)
        #    encoded_user_pass = base64.encodestring(user_pass)
        #    request.headers[
        #        'Proxy-Authorization'] = 'Basic ' + encoded_user_pass
        #else:
        #    request.meta['proxy'] = "%s://%s:%s" % (http_method,ip,port)