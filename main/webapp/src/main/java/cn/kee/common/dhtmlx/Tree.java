package cn.kee.common.dhtmlx;

import java.util.ArrayList;

public class Tree {
	
	private String id;
	
	private String text;
	
	private String child;
	
	private Object userdata;
	
	private ArrayList<Tree> item = new ArrayList<Tree>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Object getUserdata() {
		return userdata;
	}

	public void setUserdata(Object userdata) {
		this.userdata = userdata;
	}

	public ArrayList<Tree> getItem() {
		return item;
	}

	public void setItem(ArrayList<Tree> item) {
		this.item = item;
	}
	
	
}
