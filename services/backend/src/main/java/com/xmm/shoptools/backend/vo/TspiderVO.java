package com.xmm.shoptools.backend.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xmm.shoptools.backend.utils.CustomDateSerializer;

public class TspiderVO extends BaseVo {
    private String id;
	//columns START
	private java.lang.String nodeName;
	private java.lang.String host;
	private java.lang.String descrition;
	private java.lang.Boolean enable;
	private java.util.Date created;
    private java.util.Date updated;
	//columns END
	
	public void setNodeName(java.lang.String value) {
		this.nodeName = value;
	}
	public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public java.lang.String getNodeName() {
		return this.nodeName;
	}
	public void setHost(java.lang.String value) {
		this.host = value;
	}
	public java.lang.String getHost() {
		return this.host;
	}
	public void setDescrition(java.lang.String value) {
		this.descrition = value;
	}
	public java.lang.String getDescrition() {
		return this.descrition;
	}
	public void setEnable(java.lang.Boolean value) {
		this.enable = value;
	}
	public java.lang.Boolean getEnable() {
		return this.enable;
	}
	
	public void setCreated(java.util.Date value) {
        this.created = value;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public java.util.Date getCreated() {
        return this.created;
    }
    public void setUpdated(java.util.Date value) {
        this.updated = value;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public java.util.Date getUpdated() {
        return this.updated;
    }
    @Override
    public String toString() {
        return "TspiderVO [id=" + id + ", nodeName=" + nodeName + ", host=" + host
               + ", descrition=" + descrition + ", enable=" + enable + ", created=" + created
               + ", updated=" + updated + "]";
    }
	
    
}

