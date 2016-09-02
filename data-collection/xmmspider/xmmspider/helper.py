# xmmspider.spiders
# coding=utf-8


class Helper(object):

    @staticmethod
    def formart_text(text):
        if type(text) == list:
            for c in text:
                c = c.replace(' ', '')\
                    .replace('\n', '')\
                    .replace('\t', '')\
                    .replace('\r', '')
        else:
            text = text.replace(' ', '')\
                .replace('\n', '')\
                .replace('\t', '')\
                .replace('\r', '')
        return text

    @staticmethod
    def encode_utf8(text):
        result = ""
        if type(text) == list:
            for c in text:
                result += (c.encode('utf8') + ",")
        else:
            result = text.encode('utf8')

        return result.rstrip(',')

    @staticmethod
    def encode_gbk(text):
        result = ""
        if type(text) == list:
            for c in text:
                result += (c.encode('gbk') + ",")
        else:
            result = text.encode('gbk')
        return result.rstrip(',')
