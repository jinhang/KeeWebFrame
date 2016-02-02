package com.kcb.store.report.domain.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.kcb.common.constant.Constants;
import com.taobao.api.internal.util.WebUtils;
import com.wfos.engine.transfer.Tasks;
import com.wfos.engine.wrapper.model.Msg;

public class AddressCheckReportAction extends Tasks {

	@Override
	public Msg execute(Map _mParam, Map<String, Msg> _mReturnParam, Msg _oMsg)
			throws Exception {

		Msg expresses = this.executeTask("t_get_express_in_specific_date", _mParam);
		
		Iterator it = expresses.getLvalue().iterator();
		while(it.hasNext()) {
			Object[] original = (Object[])it.next();
			//String sendCode = (String)original[0];
			String netCompany = (String)original[1];	/* 目的网点公司 */
			String actualNetCompany = (String)original[2];	/* 实际网点公司 */
			
			/* 过滤相同的公司 */
			
			/**
			 * 情况1
			 * 目的网点公司和实际网点公司名字一模一样
			 * 如河南郑州公司vs.河南郑州公司
			 */
			if(netCompany.equals(actualNetCompany)) {
				it.remove();
				continue;
			}
			
			/**
			 * 情况2 
			 * 实际网点公司包含目的网点公司
			 * 如河南郑州公司vs.河南郑州   (无公司)
			 */
			if(netCompany.contains("公司")) {
				netCompany = netCompany.replace("公司", "");
				if(actualNetCompany.indexOf(netCompany) != -1) {
					it.remove();
					continue;
				}
			}
			
			/**
			 * 情况3
			 * ...
			 */
			
		}

		return expresses;
	}

	/**
	 * 根据物流信息找到网点公司
	 * @param wuliuXML 物流信息(xml格式)
	 * @return 网点公司(若找不到已签收，返回未签收)
	 * @author Tsw
	 * 该物流信息由STO提供
	 * @see http://202.75.220.167:999/sto?billcode=sendCode
	 */
	private String findNetCompanyInWuliu(String wuliuXML) {
		String hasSigned = "已签收";
		String notSigned = "未签收";
		String prefix = "<scantype>派件</scantype><memo>";
		String suffix = "的派件员";
		String delivery = "派件";
		String receivePart = "到件";
		String arrived = "快件已到";
		String arrivedX = "快件已到达";
		String detail = "detail";
		// 申通物流接口可能返回纯数字
		
		if(!wuliuXML.contains(detail)) {
			return "没有物流信息";
		}
		
			if(wuliuXML.contains(delivery)) {
				int start = wuliuXML.indexOf(prefix) + prefix.length();
				int end = wuliuXML.indexOf(suffix);
				try {
						String netCompany = wuliuXML.substring(start,end).trim();
						return netCompany;
				}catch(IndexOutOfBoundsException e) {
					return "解析出错";
				}

			} else if (wuliuXML.contains(receivePart)){
				if(wuliuXML.contains(arrivedX)) {

					String tempWuLiu = wuliuXML.substring(wuliuXML.lastIndexOf(arrivedX));// 可能存在"多个快件"已到关键字
					int end = tempWuLiu.indexOf("</memo>");
					String netCompany = tempWuLiu.substring(5,end).trim();
					return netCompany;						
				} else if(wuliuXML.contains(arrived)) {
					String tempWuLiu = wuliuXML.substring(wuliuXML.lastIndexOf(arrived));// 可能存在"多个快件"已到关键字
					int end = tempWuLiu.indexOf("</memo>");
					String netCompany = tempWuLiu.substring(0,end).trim();
					return netCompany;					
				} else {
					return "未找到";//"无快件到(达)字样";
				}

			} else {
				return "未找到";//"无派件和到件字样";
			}
		}
	
	/**
	 * 
	 * @param wuliuXML 该物流确保含有已签收字样和签收时间
	 * @return
	 */
	private String getNetCompanyByWuliuInfo(String wuliuXML) {
		String prefix = "<scantype>派件</scantype><memo>";
		String suffix = "的派件员";
		String delivery = "派件";
		String receivePart = "到件";
		String arrived = "快件已到";
		String arrivedX = "快件已到达";
		if(wuliuXML.contains(delivery)) {	// 优先检查是否存在派件字样
			int deliverySpot = wuliuXML.lastIndexOf(prefix);	// 从最后一个找，可能存在多个派件
			if(deliverySpot == -1) {
				// 未找到该字样
				return "派件已找到，解析网点公司出错";
			}
			int start = deliverySpot + prefix.length();
			int end = wuliuXML.lastIndexOf(suffix);
			if(end == -1) {
				return "派件已找到，解析网点公司出错";
			}
			try {
				String netCompany = wuliuXML.substring(start,end).trim();
				return netCompany;
			}catch(IndexOutOfBoundsException e) {
				return "派件已找到，解析网点公司出错";
			}

		} else if (wuliuXML.contains(receivePart)){
			if(wuliuXML.contains(arrivedX)) {
				int arrivedXSpot = wuliuXML.lastIndexOf(arrivedX);
				if(arrivedXSpot == -1) {
					return "到件已找到，解析网点公司出错";
				}
				String tempWuLiu = wuliuXML.substring(arrivedXSpot);// 可能存在"多个快件"已到关键字
				
				int end = tempWuLiu.indexOf("</memo>");
				if(end == -1) {
					return "到件已找到，解析网点公司出错";					
				}
				String netCompany = tempWuLiu.substring(5,end).trim();
				return netCompany;						
			} else if(wuliuXML.contains(arrived)) {
				int arrivedSpot = wuliuXML.lastIndexOf(arrived);
				if(arrivedSpot == -1) {
					return "到件已找到，解析网点公司出错";					
				}
				String tempWuLiu = wuliuXML.substring(arrivedSpot);// 可能存在"多个快件"已到关键字
				int end = tempWuLiu.indexOf("</memo>");
				if(end == -1) {
					return "到件已找到，解析网点公司出错";					
				}
				String netCompany = tempWuLiu.substring(0,end).trim();
				return netCompany;					
			} else {
				return "未找到到件";//"无快件到(达)字样";
			}

		} else {
			return "未找到派件和到件";//"无派件和到件字样";
		}		
	}
	
	/**
	 * 根据快递单号查找物流信息
	 * @param sendCode 快递单号
	 * @return 物流信息(xml格式)
	 * 出错返回空字符串-""
	 */
	private String getWuliuBySendcode(String sendCode) {
		String xml = "";
		String url = Constants.STO_IP + sendCode;
		try {
			xml = WebUtils.decode(WebUtils.doPost(url, null, 5000, 5000));
			if (xml == null) {
				xml = "";
			}
			} catch (IOException e) {
				e.printStackTrace();
				xml = "";
			} catch (Exception e) {
				e.printStackTrace();
				xml = "";
			}
		return xml;
	}
	
	/**
	 * 获取签收时间
	 * @param wuliuXML
	 * @return 签收时间,若出错返回字符串"error"
	 */
	private String getSigningDate(String wuliuXML) {
		String error = "error";
		if(wuliuXML.contains("已签收")) {
			int timeSpot = wuliuXML.lastIndexOf("<time>");
			if(timeSpot == -1) {	// 如果未找到<time>标签
				return error;
			}
			int start = timeSpot + "<time>".length();
			String temp = wuliuXML.substring(start);
			int endTimeSpot = temp.indexOf("</time>");
			if(endTimeSpot == -1) {	// 如果未找到</time>标签
				return error;
			}			
			String signingDate = temp.substring(0, endTimeSpot);
			return signingDate;			
		} else {
			return error;
		}
	}

}
