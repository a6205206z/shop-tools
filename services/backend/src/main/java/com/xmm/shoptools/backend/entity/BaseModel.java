package com.xmm.shoptools.backend.entity;

import java.io.Serializable;

public class BaseModel implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	/* 标识key */
	public Object id;

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public Object clone() {
		Object o = null;
		try {
			o = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}
}
