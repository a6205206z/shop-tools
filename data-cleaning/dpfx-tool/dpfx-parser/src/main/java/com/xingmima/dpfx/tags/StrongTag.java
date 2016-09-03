package com.xingmima.dpfx.tags;

import org.htmlparser.tags.CompositeTag;

public class StrongTag extends CompositeTag {
	private static final long serialVersionUID = 1L;

	private static final String mIds[] = { "strong" };
	private static final String mEndTagEnders[] = { "strong" };

	public StrongTag() {
	}

	public String[] getIds() {
		return mIds;
	}

	public String[] getEndTagEnders() {
		return mEndTagEnders;
	}
}
