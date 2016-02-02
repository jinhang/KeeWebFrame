package com.wfs.common.dhtmlx;

/**
 * 任何需要实现树显示的数据都需要实现该接口
 * @author Administrator
 *
 */
public interface ITree {
	public String getId();
	public String getParentid();
	public String getName();
}
