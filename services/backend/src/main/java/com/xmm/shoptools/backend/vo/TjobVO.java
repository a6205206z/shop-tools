package com.xmm.shoptools.backend.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xmm.shoptools.backend.utils.CustomDateSerializer;

public class TjobVO extends BaseVo {

	//columns START
	private java.lang.Integer runid;
	private java.lang.String spiderName;
	private java.util.Date starttime;
	private java.util.Date finishtime;
	private java.lang.String logfile;
	private java.lang.String stats;
//	完成状态：1表示已完成，0表示进行中
	private String completedStatus ;
	//columns END
	
	public void setRunid(java.lang.Integer value) {
		this.runid = value;
	}
	public String getCompletedStatus() {
        return completedStatus;
    }
    public void setCompletedStatus(String completedStatus) {
        this.completedStatus = completedStatus;
    }
    public java.lang.Integer getRunid() {
		return this.runid;
	}
	public void setSpiderName(java.lang.String value) {
		this.spiderName = value;
	}
	public java.lang.String getSpiderName() {
		return this.spiderName;
	}
	public void setStarttime(java.util.Date value) {
		this.starttime = value;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public java.util.Date getStarttime() {
		return this.starttime;
	}
	public void setFinishtime(java.util.Date value) {
		this.finishtime = value;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public java.util.Date getFinishtime() {
		return this.finishtime;
	}
	public void setLogfile(java.lang.String value) {
		this.logfile = value;
	}
	public java.lang.String getLogfile() {
		return this.logfile;
	}
	public void setStats(java.lang.String value) {
		this.stats = value;
	}
	public java.lang.String getStats() {
		return this.stats;
	}
    @Override
    public String toString() {
        return "TjobVO [runid=" + runid + ", spiderName=" + spiderName + ", starttime=" + starttime
               + ", finishtime=" + finishtime + ", logfile=" + logfile + ", stats=" + stats
               + ", completedStatus=" + completedStatus + "]";
    }
	
}

