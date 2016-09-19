package com.xingmima.dpfx.rest.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version QueryShopDTO, v 0.1
 * @date 2016/9/19 16:52
 */
public class QueryShopDTO extends BaseDTO {
    private JyDetailDTO shopPv = new JyDetailDTO();
    private JyDetailDTO salesNum = new JyDetailDTO();
    private JyDetailDTO salesMoney = new JyDetailDTO();
    private JyDetailDTO goodsNum = new JyDetailDTO();
    private JyDetailDTO shopFav = new JyDetailDTO();

    private List<HashMap<String, Object>> hotgoods = new ArrayList<HashMap<String, Object>>();
    private List<HashMap<String, Object>> hotfav = new ArrayList<HashMap<String, Object>>();

    public List<HashMap<String, Object>> getHotgoods() {
        return hotgoods;
    }

    public void setHotgoods(List<HashMap<String, Object>> hotgoods) {
        this.hotgoods = hotgoods;
    }

    public List<HashMap<String, Object>> getHotfav() {
        return hotfav;
    }

    public void setHotfav(List<HashMap<String, Object>> hotfav) {
        this.hotfav = hotfav;
    }


}
