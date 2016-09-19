package com.xingmima.dpfx.rest.dto;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version JyDetailDTO, v 0.1
 * @date 2016 /9/19 17:11
 */
public class JyDetailDTO {
    private String val;
    private String flag;
    private String diff;

    public JyDetailDTO() {
    }

    public JyDetailDTO(String val, String flag, String diff) {
        this.val = val;
        this.flag = flag;
        this.diff = diff;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }
}
