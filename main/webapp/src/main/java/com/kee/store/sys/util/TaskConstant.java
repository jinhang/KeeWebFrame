package com.kee.store.sys.util;

public interface TaskConstant {
	/*
	 * t_store_sys_user_ 任务模块头
	 */
	static final String sys_user_space="t_store_sys_user_";
	static final String queryAllUsersByAction="queryAllUsersByAction";
	static final String queryAllCountUsersByAction="queryAllCountUsersByAction";
	static final String queryAllUsers=sys_user_space+"queryAllUsers";//查询所有用户
	static final String queryAllCountUsers="queryAllCountUsers";//查询所有用户记录总数
	static final String checkNameExists="checkNameExists";//校验用户名是否存在
	static final String checkClientNameExists="checkClientNameExists";//校验公司名称是否存在
	static final String saveClients="saveClients";					//保存客户
	static final String initClientFCAndCET="initClientFCAndCET";	
	static final String savaClientFC="savaClientFC";
	static final String expressTemplateExists="expressTemplateExists";
	static final String savaStaffsAndRP="savaStaffsAndRP";
	static final String saveStaffs="saveStaffs";
	static final String initUserExtendDate="initUserExtendDate";
	static final String saveClientInfos="saveClientInfos";
	static final String queryAllForwardCompany="queryAllForwardCompany";
	static final String setUserStatus="setUserStatus";
	static final String updatePwd="updatePwd";
	static final String getDetailByClient="getDetailByClient";
	static final String getRoleByStaff="getRoleByStaff";
	static final String updateStaffByClient="updateStaffByClient";
	static final String updateClientInfo="updateClientInfo";
	static final String updateStaffPermission="updateStaffPermission";
	
	//用户类型
	static final int USER_TYPE_FORWARDCOMPANY=1;//物流公司
	static final int USER_TYPE_SELLER=2;		//电商卖家
	static final int USER_TYPE_STORAGE=3;		//仓储中心
}
