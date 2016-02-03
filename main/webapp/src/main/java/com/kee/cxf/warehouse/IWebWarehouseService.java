package com.kee.cxf.warehouse;

import javax.jws.WebMethod;

public interface IWebWarehouseService {
	
	/**
	 * @Desc:登录
	 * @param reqXml：XML格式报文
	 * @return resXml： XML格式报文
	 * @Author:yeyanying@kucangbao.com
	 */
	@WebMethod
	public abstract String login(String reqXml);
	
	/**
	 * @Desc:签收
	 * @param reqXml：XML格式报文
	 * @return resXml： XML格式报文
	 * @Author:wangsen@kucangbao.com
	 */
	@WebMethod
	public abstract String siginpackage(String reqXml);
	
	/**
	 * @Desc:签收查询
	 * @param reqXml：XML格式报文
	 * @return resXml： XML格式报文
	 * @Author:wangsen@kucangbao.com
	 */
	@WebMethod
	public abstract String getsiginpackageinfo(String reqXml);
		
	
	/**
	 * @Desc:盘点
	 * @param reqXml：XML格式报文
	 * @return resXml： XML格式报文
	 * @Author:wangsen@kucangbao.com
	 */
	@WebMethod
	public abstract String checkorderinfo(String reqXml);
	
	
	/**
	 * @Desc:上架验证订单
	 * @param reqXml：XML格式报文
	 * @return resXml： XML格式报文
	 * @Author:wangsen@kucangbao.com
	 */
	@WebMethod
	public abstract String shelfofcheckoutstock(String reqXml);
	
	/**
	 * @Desc:上架操作验证库位并完成上架操作
	 * @param reqXml：XML格式报文
	 * @return resXml： XML格式报文
	 * @Author:wangsen@kucangbao.com
	 */
	@WebMethod
	public abstract String shelfofcheckwhitem(String reqXml);
	
	/**
	 * @Desc:下架操作
	 * @param reqXml：XML格式报文
	 * @return resXml： XML格式报文
	 * @Author:wangsen@kucangbao.com
	 */
	@WebMethod
	public abstract String hfUnderCarriage(String reqXml);
	
	
	/**
	 * @Desc:出库操作
	 * @param reqXml：XML格式报文
	 * @return resXml： XML格式报文
	 * @Author:yeyanying@kucangbao.com
	 */
	@WebMethod
	public abstract String hfOutstockInfo(String reqXml);	
	
	/**
	 * @Desc:国际快递交接
	 * @param reqXml：XML格式报文
	 * @return resXml： XML格式报文
	 * @Author:yeyanying@kucangbao.com
	 */
	@WebMethod
	public abstract String handover(String reqXml);	
	
	
	/**
	 * @Desc:国际快递交接查询
	 * @param reqXml：XML格式报文
	 * @return resXml： XML格式报文
	 * @Author:yeyanying@kucangbao.com
	 */
	@WebMethod
	public abstract String gethandoverinfo(String reqXml);	
	
	/**
	 * @Desc:登出
	 * @param reqXml：XML格式报文
	 * @return resXml： XML格式报文
	 * @Author:yeyanying@kucangbao.com
	 */
	@WebMethod
	public abstract String loginout(String reqXml);
}
