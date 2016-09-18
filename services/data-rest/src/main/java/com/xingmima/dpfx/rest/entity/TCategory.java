package com.xingmima.dpfx.rest.entity;

import java.util.Date;

/**
 * 业务APP：类目信息
 */
public class TCategory extends BaseEntity{
    /**
     * 类目表，标识列
     */
    private String id;

    /**
     * 上级类目，默认0
     */
    private String pid;

    /**
     * 类目名称
     */
    private String title;

    /**
     * 修改时间。格式：yyyy-MM-dd HH:mm:ss
     */
    private Date updated;

    /**
     * 类目表，标识列
     * @return id 类目表，标识列
     */
    public String getId() {
        return id;
    }

    /**
     * 类目表，标识列
     * @param id 类目表，标识列
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 上级类目，默认0
     * @return pid 上级类目，默认0
     */
    public String getPid() {
        return pid;
    }

    /**
     * 上级类目，默认0
     * @param pid 上级类目，默认0
     */
    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    /**
     * 类目名称
     * @return title 类目名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 类目名称
     * @param title 类目名称
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 修改时间。格式：yyyy-MM-dd HH:mm:ss
     * @return updated 修改时间。格式：yyyy-MM-dd HH:mm:ss
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * 修改时间。格式：yyyy-MM-dd HH:mm:ss
     * @param updated 修改时间。格式：yyyy-MM-dd HH:mm:ss
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pid=").append(pid);
        sb.append(", title=").append(title);
        sb.append(", updated=").append(updated);
        sb.append("]");
        return sb.toString();
    }
}