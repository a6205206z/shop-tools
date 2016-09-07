package com.xingmima.dpfx.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xingmima.dpfx.dao.DdsrDao;
import com.xingmima.dpfx.dao.ShopDao;
import com.xingmima.dpfx.entity.DDsr;
import com.xingmima.dpfx.entity.DShop;
import com.xingmima.dpfx.inter.TaobaoParser;
import com.xingmima.dpfx.util.Constant;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;

/**
 * The type Tmall shop info.
 */
public class TmallShopInfo extends TaobaoParser {
    /**
     * Instantiates a new Tmall shop info.
     *
     * @param resource the resource
     */
    public TmallShopInfo(String resource) {
        super(resource);
    }

    /**
     * Call tmall shop info.
     *
     * @return the tmall shop info
     */
    public TmallShopInfo call() {
        boolean isOk = this.initSpiderShop();
        if (!isOk) {
            log.error("===format error===", Constant.HTML_FORMAT_ERROR);
            return null;
        }
        return this;
    }

    /**
     * Handle shop info
     *
     * @return the d shop
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * @throws ParserException              the parser exception
     */
    public DShop handleShopInfo() throws UnsupportedEncodingException, ParserException {
        DShop info = this.getDefaultShop();
        NodeList list = null;

        /*店名*/
        JSONObject obj = (JSONObject) JSON.parse(this.getParam());
        try {
            info.setTitle(URLDecoder.decode(obj.getString("shopname"), UTF8));
        } catch (UnsupportedEncodingException e) {
            throw e;
        }

        //设置默认值
        info.setRating(BigDecimal.ZERO);
        info.setCreditGoodNum(0);
        info.setCreditTotalNum(0);
        info.setRating(BigDecimal.ZERO);
        info.setCreditLevel("");
        info.setCreditScore(0L);

        list = null;
        log.info(info.toString());
        return info;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        try {
            String res = readHtmlFile(TmallShopInfo.class.getClassLoader().getResource("").getPath() + "/file/tmall_shopinfo.html", "GBK");

            TmallShopInfo dox = new TmallShopInfo(res).call();

            DShop obj = dox.handleShopInfo();
            new ShopDao().insert(obj);

            DDsr obj3 = dox.handelDsr();
            new DdsrDao().insert(obj3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
