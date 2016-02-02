package com.kcb.common.constant;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import com.wfos.engine.cache.Cache;

public class Constants {
	
	public static String AUTO_OPEN_RDS="false";
	public static String RDS_NAME="";
	public static String TOP_APPKEY = "";
	public static String OPEN_TOP_APPKEY = "";
	public static String STO_IP = "";
	public static String GTO_URL = "";
	public static String IMAGE_PATH = "";
	public static String IMAGE_URL = "";
	public static String EXPORT_URL = "";
	public static String ENABLE_AUTO_FETCH_TRACK = "false";
	public static String ALIBABA_APPKEY = "";
	public static String ALIBABA_APPSECRET = "";
	
	public static String PP_APPOAUTHID = "";
	public static String PP_SECRETOAUTHKEY = "";
	
	public static String NULL_STR_JDP="_1_0_1_0_1_0_1_";
	
	
	
	public  String getRDS_NAME() {
		return RDS_NAME;
	}
	public  void setRDS_NAME(String rDS_NAME) {
		RDS_NAME = rDS_NAME;
	}
	public  String getPP_APPOAUTHID() {
		return PP_APPOAUTHID;
	}
	public  void setPP_APPOAUTHID(String pP_APPOAUTHID) {
		PP_APPOAUTHID = pP_APPOAUTHID;
	}
	public  String getPP_SECRETOAUTHKEY() {
		return PP_SECRETOAUTHKEY;
	}
	public  void setPP_SECRETOAUTHKEY(String pP_SECRETOAUTHKEY) {
		PP_SECRETOAUTHKEY = pP_SECRETOAUTHKEY;
	}
	public static String ENABLE_TRANSPORT_DOMAIN = "false";
	
	public static String JD_APPKEY = "";
	public static String JD_APPSECRET = "";
	public static String JD_CHARSET = "";

	public void setJD_CHARSET(String charset){
		JD_CHARSET = charset;
	}
	public void setJD_APPKEY(String jD_APPKEY) {
		JD_APPKEY = jD_APPKEY;
	}

	public void setJD_APPSECRET(String jD_APPSECRET) {
		JD_APPSECRET = jD_APPSECRET;
	}
	
	public  String getALIBABA_APPKEY() {
		return ALIBABA_APPKEY;
	}


	public  void setALIBABA_APPKEY(String aLIBABA_APPKEY) {
		ALIBABA_APPKEY = aLIBABA_APPKEY;
	}


	public  String getALIBABA_APPSECRET() {
		return ALIBABA_APPSECRET;
	}


	public  void setALIBABA_APPSECRET(String aLIBABA_APPSECRET) {
		ALIBABA_APPSECRET = aLIBABA_APPSECRET;
	}
	public static String APPSECRET = "";
	public static String OPEN_APPSECRET = "";
	public static String REDIRECT_URI = "";
	public static String REDIRECT_OPEN_URI = "";
	public static String FILE_PATH="";
	public static String LUCENE_PATH = "";
	public static Map<String,Object> FORWARD_OUTLETS_MAP = new HashMap<String, Object>();
	public final static String CLIENT_ROLE="client_role";
	public final static String EXPRESS_STO = "402881ec33c024850133c025918d0000";
	public final static String EXPRESS_YWP = "YWP20e1055944b20a2128d352416af72";
	public final static String EXPRESS_BC = "BC85eb0ef04e488db5a9af1560f4103e";
	public final static String EXPRESS_GTO = "d47b6158ffbb47f9af915d3c30e96b25";	
	public final static String TOP_SIGN = "top_sign";

	public final static String TOP_PARAMETERS = "top_parameters";

	public final static String TOP_SESSION = "top_session";
	public final static String TOP_REFRESH_TOKEN = "refresh_token";
	public final static String TOP_VISITOR_NICK = "visitor_nick";
	public final static String TOP_SUB_NICK = "sub_taobao_user_nick";
	public final static String TBSANDBOX_URL = "http://gw.api.tbsandbox.com/router/rest";
	public  static String PROXY_TOP_URL = "http://gw.api.taobao.com/router/rest";
	public  static String TOP_URL = "http://gw.api.taobao.com/router/rest";
	public final static String JD_SERVER_URL = "http://gw.api.360buy.com/routerjson";
	
	// user session
	public final static String SESSION_USER = "session_user";
	// client session
	public final static String SESSION_CLIENT = "session_client";
	//登录随机码
	public final static String SESSION_RANDOM = "session_random";
	public final static String SESSION_APPKEY = "session_appkey";
	public final static String SESSION_APPSECRET = "session_appsecret";
	
	
	// nick session
	public final static String SESSION_NICK = "session_nick";
	// shop session
	public final static String SESSION_SHOP = "session_shop";
	
	public final static String SESSION_TOP_PARAMETERS = "session_top_parameters";
	
	// 当前登录的员工号
	public final static String SESSION_CURRENT_USER_ID = "session_current_user_id";
	//员工当前登录的用户的公司
	public final static String SESSION_CURRENT_COMPANNY_ID = "session_current_company_id";
	
	//员工当前登录的用户的的客户类型
	public final static String SESSION_CURRENT_TYPE = "session_current_type";
	/**
	 * 当前登录用户所属系统版本（B  标准版   H  豪华版   Q  企业版   C  仓储版   K   酷仓宝 )
	 */
	public final static String SESSION_CURRENT_CLIENT_SYSTEMTYPE = "session_current_client_systemtype";
	//员工当前登录的用户
	public final static String SESSION_CURRENT_CLIENT_ID = "session_current_client_id";
	//当前登陆用户类型
	public final static String SESSION_CURRENT_CLIENT_TYPE = "session_current_client_type";
	public final static String SESSION_CURRENT_CLIENT_KDTTYPE = "session_current_client_kdttype";
	
	//员工当前操作的仓库
	public final static String SESSION_CURRENT_WAREHOUSE_ID = "session_current_warehouse_id";
	//客户默认的操作仓库
	public final static String SESSION_DEFAULT_WAREHOUSE_ID = "session_default_warehouse_id";
	//客户默认发货快递公司
	public final static String SESSION_DEFAULT_FORWARD_ID = "session_default_forward_id";
	//员工当前操作的店铺
	public final static String SESSION_CURRENT_SHOP_ID = "session_current_shop_id";
	//淘宝员工
	public final static String SESSION_CURRENT_TAOBAOSTAFF = "session_current_taobaostaff";
	//员工可操作的仓库数量
	public final static String SESSION_CURRENT_STAFF_WAREHOUSE_COUNT = "session_current_staff_warehouse_count";
	//员工可操作的店铺数量
	public final static String SESSION_CURRENT_STAFF_SHOP_COUNT = "session_current_staff_shop_count";
	/**
	 * 当前客户启用的物流公司
	 */
	public final static String SESSION_CLIENT_FC="session_client_fc";
	/**
	 * 订单类型_淘宝下载
	 */
	public static final int ORDER_TYPE_DOWNLOAD_FROM_TAOBAO = 1;
	/**
	 * 开放平台类型_淘宝
	 */
	public static final int OPEN_TYPE_TAOBAO = 0;
	
	/**
	 * 开放平台类型_阿里巴巴
	 */
	public static final int OPEN_TYPE_ALIBABA = 8;
	
	
	/**
	 * 开放平台类型_淘宝天猫
	 */
	public static final int OPEN_TYPE_TMALL = 1;
	
	/**
	 * 开放平台类型_淘宝沙箱
	 */
	public static final int OPEN_TYPE_TAOBAO_SANDBOX = -1;	
	/**
	 * 开放平台类型_拍拍
	 */
	public static final int OPEN_TYPE_PAIPAI = 2;
	/**
	 * 开放平台类型_京东
	 */
	public static final int OPEN_TYPE_JD =3;
	/**
	 * Excle的缺省宽度
	 */
	public static final int EXCLE_DEFAULT_WIDTH = 100;
	
	/**
	 * 订单状态_没有创建支付宝交易
	 */
	public static final String TRADE_NO_CREATE_PAY = "TRADE_NO_CREATE_PAY";
	/**
	 * 订单状态_等待买家付款
	 */
	public static final String TRADE_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
	
	/**
	 * 订单状态_等待卖家发货,即:买家已付款
	 */
	public static final String TRADE_WAIT_SELLER_SEND_GOODS = "WAIT_SELLER_SEND_GOODS";
	

	/**
	 * 订单状态_等待买家确认收货,即:卖家已发货
	 */
	public static final String TRADE_WAIT_BUYER_CONFIRM_GOODS = "WAIT_BUYER_CONFIRM_GOODS";

	/**
	 * 订单状态_买家已签收,货到付款专用
	 */
	public static final String TRADE_BUYER_SIGNED = "TRADE_BUYER_SIGNED";
	/**
	 * 订单状态_交易成功
	 */
	public static final String TRADE_FINISHED = "TRADE_FINISHED";
	/**
	 * 订单状态_付款以后用户退款成功，交易自动关闭
	 */
	public static final String TRADE_CLOSED = "TRADE_CLOSED";
	
	
	/**
	 * 订单状态_买家已经申请退款，等待卖家同意
	 */
	public static final String WAIT_SELLER_AGREE = "WAIT_SELLER_AGREE";
	
	/**
	 * 订单状态_退款成功
	 */
	public static final String  REFUND_SUCCESS = "SUCCESS";
	
	/**
	 * 订单状态_退款关闭
	 */
	public static final String REFUND_CLOSED = "CLOSED";
	
	/**
	 * 订单状态_卖家已经同意退款，等待买家退货
	 */
	public static final String WAIT_BUYER_RETURN_GOODS = "WAIT_BUYER_RETURN_GOODS";
	
	/**
	 * 订单状态_买家已经退货，等待卖家确认收货
	 */
	public static final String WAIT_SELLER_CONFIRM_GOODS = "WAIT_SELLER_CONFIRM_GOODS";
	
	/**
	 * 订单状态_卖家拒绝退款
	 */
	public static final String SELLER_REFUSE_BUYER = "SELLER_REFUSE_BUYER";
	
	
	
	
	/**
	 * 订单状态_付款以前，卖家或买家主动关闭交易
	 */
	public static final String TRADE_CLOSED_BY_TAOBAO = "TRADE_CLOSED_BY_TAOBAO";
	/**
	 * 错误类型_登录_随机验证码验证失败
	 */
	public static final int STATE_ERROR_VALIDATE_RANDOM_CODE = -201;
	/**
	 * 错误类型_淘宝session过期
	 */
	public static final int STATE_ERROR_TOP_SESSION_EXPIRED = 27;
	/**
	 * isp.remote-service-error
	 * top平台API调用远程连接错误
	 */
	public static final String TOP_REMOTE_SERVICE_ERROR = "isp.remote-service-error";
	/**
	 * isp.remote-connection-error
	 * top平台API调用远程连接错误
	 */
	public static final String TOP_REMOTE_CONNECTION_ERROR = "isp.remote-connection-error";
	
	/**
	 * isp.top-remote-connection-timeout
	 * top平台连接后端服务超时
	 */
	public static final String TOP_REMOTE_CONNECTION_TIMEOUT = "isp.top-remote-connection-timeout";
	
	
	/**
	 * accesscontrol.limited-by-api-access-count
	 * top访问次数受限
	 */
	public static final String TOP_LIMITED_BY_API_ACCESS_COUNT = "7";
	
	/**
	 * 错误类型_订单状态被改变
	 */
	public static final int STATE_ERROR_ORDER_STATUS_CHANGE = -102;
	
	/**
	 * 错误类型_同步失败
	 */
	public static final int STATE_ERROR_SYN = -103;
	/**
	 * 执行成功
	 */
	public static final int STATE_SUCCESS = 1;
	/**
	 * 执行失败
	 */
	public static final int STATE_FAILURE = -1;
	/***开始  出库单单据状态***/

	public static final int OUTSTOCK_STATUS_DEFAULT=0;   //初始状态  =待审核
	public static final int OUTSTOCK_STATUS_CHECK=1;   //已审核 = 待发货单打印 待处理
	public static final int OUTSTOCK_STATUS_PRINT=2;   //发货单已打印=待拣货
	public static final int OUTSTOCK_STATUS_PICKING=3;   //拣货完成=待质检扫描
	
	public static final int OUTSTOCK_STATUS_SCAN=4;   //已扫描完成=待包装
	
	public static final int OUTSTOCK_STATUS_PACKING=5;   //包装完成=待交接
	
	public static final int OUTSTOCK_STATUS_HANDOVER=6;   //已交接 	
	
	public static final int OUTSTOCK_STATUS_DEL=-1;   //已删除	
	
	/** 入库单单据状态**/
	
	public static final int  INSTOCK_STATUS_APPOINT = 1;  //预约
	
	public static final int  INSTOCK_STATUS_COUNT = 2;  //清点
	
	public static final int  INSTOCK_STATUS_CHECK = 3;  //检验
	
	public static final int  INSTOCK_STATUS_TAG = 4;  //贴标签
	
	public static final int  INSTOCK_STATUS_ON = 5;  //上架
	public static final int  INSTOCK_STATUS_FINISH = 8;  //入库完成
	/**库存盘点 状态**/
	
	public static final int INVENTORY_STATUS_DEFULT = 0;   //未盘点/没盘点完成
	public static final int INVENTORY_STATUS_FINISH = 1;    //盘点完成
	public static final int INVENTORY_STATUS_FORM = 2;    //报表处理
	public static final int INVENTORY_DETAIL_STATUS_DEFAULT = 0;    //未盘点
	
	public static final int INVENTORY_DETAIL_STATUS_FIRST = 1;    //初盘完成
	
	public static final int INVENTORY_DETAIL_STATUS_FINISH = 2;    //复盘完成
	
	public static final int INVENTORY_APP_EXSTATUS_CANCLE = -1;    //已取消
	
	public static final int INVENTORY_APP_EXSTATUS_DEFAULT = 0;    //暂不处理
	
	public static final int INVENTORY_APP_EXSTATUS_AGREE = 1;    //同意
	
	public static final int INVENTORY_APP_EXSTATUS_FIRST = 2;    //初盘
	
	public static final int INVENTORY_APP_EXSTATUS_FINISH = 3;    //盘点完成
	
	public static final String MEMORY_LOGIN_TAOBAOSTAFF = "get_login_taobaostaff";
	//同步时间
	public static final String MEMORY_SYN_TAOBAO_DATE = "syn_taobao_date";
	//补单时间
	public static final String MEMORY_SYN_TAOBAO_FILL_DATE = "syn_taobao_fill_date";
	
	//增量时间间隔
	public static final String MEMORY_SYS_TAOBAO_TIME_INTERVAL="memory_sys_taobao_time_interval";
	
	public static final String MEMORY_DEFAULT_FORWARD_TYPE = "default_forward_type";
	
	//创建订单
	public static  int CREATE_OUTSTOCK_BATCH_NUMS=50;
	
	//下载批次
	public static  int DOWN_BATCH_NUMS=100;
	
	public static final String MEMORY_SYS_DOWN_BATCH_NUMS="memory_sys_down_batch_nums";
	public static final String MEMORY_SYS_CREATE_OUTSTOCK_BATCH_NUMS="memory_sys_create_batch_nums";
	
	/**
	 * 存放被中断下载的店铺
	 */
	public static Map<String,String> DOWNLOAD_STOPING_SHOP = new HashMap<String, String>();
	
	//下商品时间
	public static final String MEMORY_SYS_DOWNLOAD_GOODS="sys_download_goods";
	
	public static final String CLIENT_HAS_DOWNLOAD="client_has_download";
	
	//发货时自动发送短信提醒
	public static final String MEMORY_AUTO_SEND_SMS = "auto_send_sms";
	public  static final String JAVA_NET_CONNECT_EXCEPTION = "java.net.ConnectException: Connection timed out: connect";
	public  static final String JAVA_NET_SOCKET_EXCEPTION = "java.net.SocketTimeoutException: connect timed out";
	public  static final String JAVA_NET_SOCKET_READ_EXCEPTION = "com.taobao.api.ApiException: java.net.SocketTimeoutException: Read timed out";
	/**
	 * 其他用户登录
	 */
	public static final int LOGINED_BY_OTHER_USER = 9999;
	public static final String IMAGE_EXTENSION = null;
	public static final String SMALL_IMAGE_PATH = null;
	
	
	/**
	 * 存放正在下载的店铺
	 */
	public static Map<String,String> DWONLOADING_SHOP = new HashMap<String, String>();
	
	
	/**
	 * 存放正在审核的商品id 防止并发引起库存不对或订单审核多次
	 */
	public static Map<String,String> AUDIT_GOODS_LOCK = new HashMap<String, String>();
	

	/***结束  出库单单据状态***/
	/**
	 * 短信变量_客户姓名
	 */
	public static String SMS_VAR_CLIENT_NAME = "【客户姓名】";
	
	/**
	 * 短信变量_收件人姓名
	 */
	public static String SMS_VAR_RECEIVER_NAME = "【收件人姓名】";
	
	/**
	 * 短信变量_快递单号
	 */
	public static String SMS_VAR_SEND_CODE = "【快递单号】";
	
	/**
	 * 短信变量_快递重量
	 */
	public static String SMS_VAR_WIGHT = "【重量】";
	
	/**
	 * 短信变量_异常原因
	 */
	public static String SMS_VAR_FAILE_TYPE = "【异常原因】";
	
	/**
	 * 短信变量_店铺名称
	 */
	public static String SMS_VAR_SHOP_NAME = "【店铺名】";

	/**
	 * 短信变量_发送时间
	 */
	public static String SMS_VAR_SEND_TIME = "【发送时间】";

	/**
	 * 短信变量_物流公司订单号
	 */
	public static String SMS_VAR_FORWARD_AND_ORDER_NUMBER = "【物流公司,订单号】";

	/**
	 * 短信变量_买家昵称
	 */
	public static String SMS_VAR_BUYER = "【买家昵称】";

	/**
	 * 短信变量_评论等级
	 */
	public static String SMS_VAR_LEVEL = "【评论等级】";

	/**
	 * 短信变量_评论内容
	 */
	public static String SMS_VAR_CONTENT = "【评论内容】";
	
	/**
	 * 短信变量_淘宝订单号
	 */
	public static String SMS_VAR_ORDER_NUMBER = "【订单号】";

	/**
	 * 短信变量_卖家昵称
	 */
	public static String SMS_VAR_SELLER_NICK = "【卖家昵称】";    
	
	public static String NO_NEED_INVOICE = "不需要开具发票";    
	
	
	/**
	 * 仓库分区代码
	 * 默认五个分区代码是拣货区:JHQ ; 发货区:FHQ ; 存储区:CCQ ; 质检区:ZJQ ; 异常区:YCQ ; 
	 */
	public static String WHAREA_CODE_JHQ = "JHQ";
	public static String WHAREA_CODE_FHQ = "FHQ";
	public static String WHAREA_CODE_CCQ = "CCQ";
	public static String WHAREA_CODE_ZJQ = "ZJQ";
	public static String WHAREA_CODE_YCQ = "YCQ";
	
	
	/**
	 * 客户启用的参数
	 */
	private  static Map<String,Map>  clientParamsMap=new HashMap<String,Map> (); 
	
	/**
	 * staff启用的个性化参数
	 */
	private  static Map<String,Map>  staffParamsMap=new HashMap<String,Map> (); 
	
	/**
	 * 物流匹配卖家备注或买家备注启用的正则表达式模板
	 */
	private  static Map<Integer,String>  regexPatternMap=null; 
	
	
	public static String KCB_BUY_URL="";
	
	public static String CACHE_DATASTORE="kcb";
	
	public static String ENABLE_DOWNLOADSHOP_DOMAIN="false";
	
	public static String SHOP_LOCK_SIGN="Lock";   //是否正在手动下载标记
	
	public static String SHOP_STOPMANUALDOWNLOAD_SIGN="StopManualDownload"; //是否需要中断正在的手动下载
	
	public static String SHOP_MESSAGESERVER_SIGN="MessageServer"; //淘宝店铺的订单消息服务
	
	public static String AUTO_TAOBAO_MESSAGESERVER="false"; //自动开启淘宝消息服务		
	
	//泓丰国际
	public static String HF_APPKEY = "";  
	
	public static String HF_APPSECRET = "";
	
	public static String HF_url = "";
	
	public static String hf_localfile_url = "";
	
	public static String HF_certificate = "";
	
	public static String HF_oldsystem_url = "";
	
	public static String HF_newsystem_url = "";
	
	public static String HF_signature_url = "";
	
	public void setHF_signature_url(String hF_signature_url) {
		HF_signature_url = hF_signature_url;
	}
	public void setHF_newsystem_url(String hF_newsystem_url) {
		HF_newsystem_url = hF_newsystem_url;
	}
	public void setHF_oldsystem_url(String hF_oldsystem_url) {
		HF_oldsystem_url = hF_oldsystem_url;
	}
	public void setHF_certificate(String hF_certificate) {
		HF_certificate = hF_certificate;
	}
	public void setHf_localfile_url(String hf_localfile_url) {
		Constants.hf_localfile_url = hf_localfile_url;
	}
	public String getKCB_BUY_URL() {
		return KCB_BUY_URL;
	}


	public void setKCB_BUY_URL(String kCB_BUY_URL) {
		KCB_BUY_URL = kCB_BUY_URL;
	}




	public static Map<String, Map> getClientParamsMap() {
		return clientParamsMap;
	}


	public static void setClientParamsMap(Map<String, Map> clientParamsMap) {
		Constants.clientParamsMap = clientParamsMap;
	}


	public static Map<String, Map> getStaffParamsMap() {
		return staffParamsMap;
	}


	public static void setStaffParamsMap(Map<String, Map> staffParamsMap) {
		Constants.staffParamsMap = staffParamsMap;
	}


	public static Map<Integer,String> getRegexPatternMap() {	
		if(regexPatternMap==null){
	        String regCharset1 = "不[\\s]*发[\\s]*[\\S]{2,4}[\\s]*发[\\s]*[\\S]{2,4}";    
	        String regCharset2 = "不[\\s]*用[\\s]*[\\S]{2,4}[\\s]*用[\\s]*[\\S]{2,4}";         
	        String regCharset3 = "不[\\s]*要[\\s]*发[\\s]*[\\S]{2,4}[\\s]*发[\\s]*[\\S]{2,4}";    
	        String regCharset4 = "不[\\s]*要[\\s]*用[\\s]*[\\S]{2,4}[\\s]*用[\\s]*[\\S]{2,4}";   	        	        
	        String regCharset5 = "拒[\\s]*绝[\\s]*用[\\s]*[\\S]{2,4}[\\s]*用[\\s]*[\\S]{2,4}";    
	        String regCharset6 = "拒[\\s]*用[\\s]*[\\S]{2,4}[\\s]*用[\\s]*[\\S]{2,4}";    
	        String regCharset7 = "拒[\\s]*发[\\s]*[\\S]{2,4}[\\s]*发[\\s]*[\\S]{2,4}";    
	        String regCharset8 = "拒[\\s]*绝[\\s]*发[\\s]*[\\S]{2,4}[\\s]*发[\\s]*[\\S]{2,4}";     
	        String regCharset9 = "别[\\s]*发[\\s]*[\\S]{2,4}[\\s]*发[\\s]*[\\S]{2,4}";    
	        String regCharset10 = "别[\\s]*用[\\s]*[\\S]{2,4}[\\s]*用[\\s]*[\\S]{2,4}";
	        String regCharset11 = "勿[\\s]*发[\\s]*[\\S]{2,4}[\\s]*发[\\s]*[\\S]{2,4}"; 
	        String regCharset12 = "勿[\\s]*用[\\s]*[\\S]{2,4}[\\s]*用[\\s]*[\\S]{2,4}"; 
	        
	        String regCharset101 = "不[\\s]*发[\\s]*[\\S]{2,4}[\\s]*";    
	        String regCharset102 = "不[\\s]*要[\\s]*发[\\s]*[\\S]{2,4}[\\s]*";    
	        String regCharset103 = "不[\\s]*用[\\s]*[\\S]{2,4}[\\s]*";    
	        String regCharset104= "不[\\s]*要[\\s]*用[\\s]*[\\S]{2,4}[\\s]*";  
	        String regCharset105 = "拒[\\s]*绝[\\s]*发[\\s]*[\\S]{2,4}[\\s]*";    
	        String regCharset106 = "拒[\\s]*发[\\s]*[\\S]{2,4}[\\s]*";  
	        String regCharset107 = "拒[\\s]*绝[\\s]*用[\\s]*[\\S]{2,4}[\\s]*";    
	        String regCharset108 = "拒[\\s]*用[\\s]*[\\S]{2,4}[\\s]*";  
	        String regCharset109 = "别[\\s]*发[\\s]*[\\S]{2,4}[\\s]*";    
	        String regCharset110= "不[\\s]*要[\\s]*[\\S]{2,4}[\\s]*";  
	        String regCharset111= "勿[\\s]*发[\\s]*[\\S]{2,4}[\\s]*";
	        String regCharset112= "勿[\\s]*用[\\s]*[\\S]{2,4}[\\s]*";
	        
	       
	        String regCharset201 = "[\\S]{2,4}[\\s]*不到";    
	                
	        regexPatternMap=new LinkedHashMap<Integer,String>();
	        regexPatternMap.put(1, regCharset1);
	        regexPatternMap.put(2, regCharset2);
	        regexPatternMap.put(3, regCharset3);
	        regexPatternMap.put(4, regCharset4);
	        regexPatternMap.put(5, regCharset5);
	        regexPatternMap.put(6, regCharset6);
	        regexPatternMap.put(7, regCharset7);
	        regexPatternMap.put(8, regCharset8);	       
	        regexPatternMap.put(9, regCharset9);
	        regexPatternMap.put(10, regCharset10);
	        regexPatternMap.put(11, regCharset11);
	        regexPatternMap.put(12, regCharset12);
 
	        regexPatternMap.put(101, regCharset101);
	        regexPatternMap.put(102, regCharset102);
	        regexPatternMap.put(103, regCharset103);
	        regexPatternMap.put(104, regCharset104);
	        regexPatternMap.put(105, regCharset105);
	        regexPatternMap.put(106, regCharset106);
	        regexPatternMap.put(107, regCharset107);
	        regexPatternMap.put(108, regCharset108);	        
	        regexPatternMap.put(109, regCharset109);	        
	        regexPatternMap.put(110, regCharset110);
	        regexPatternMap.put(111, regCharset111);
	        regexPatternMap.put(112, regCharset112);
	        
	        regexPatternMap.put(201, regCharset201);
		}	
		return regexPatternMap;
	}


	public static void setRegexPatternMap(Map<Integer,String> regexPatternMap) {
		Constants.regexPatternMap = regexPatternMap;
	}


	public String getAPPSECRET() {
		return APPSECRET;
	}
	public  String getFILE_PATH() {
		return FILE_PATH;
	}
	public String getIMAGE_PATH() {
		return IMAGE_PATH;
	}
	public String getIMAGE_URL() {
		return IMAGE_URL;
	}
	public  String getOPEN_APPSECRET() {
		return OPEN_APPSECRET;
	}
	public  String getOPEN_TOP_APPKEY() {
		return OPEN_TOP_APPKEY;
	}
	public String getREDIRECT_OPEN_URI() {
		return REDIRECT_OPEN_URI;
	}
	public String getREDIRECT_URI() {
		return REDIRECT_URI;
	}
	public String getTOP_APPKEY() {
		return TOP_APPKEY;
	}
	
	public String getEXPORT_URL() {
		return EXPORT_URL;
	}


	public void setEXPORT_URL(String eXPORT_URL) {
		EXPORT_URL = eXPORT_URL;
	}


	public void setAPPSECRET(String aPPSECRET) {
		APPSECRET = aPPSECRET;
	}

	public  void setFILE_PATH(String fILE_PATH) {
		FILE_PATH = fILE_PATH;
	}
	

	public String getLUCENE_PATH() {
		return LUCENE_PATH;
	}


	public void setLUCENE_PATH(String lUCENE_PATH) {
		LUCENE_PATH = lUCENE_PATH;
	}


	public void setIMAGE_PATH(String iMAGE_PATH) {
		IMAGE_PATH = iMAGE_PATH;
	}

	public void setIMAGE_URL(String iMAGE_URL) {
		IMAGE_URL = iMAGE_URL;
	}

	public  void setOPEN_APPSECRET(String oPEN_APPSECRET) {
		OPEN_APPSECRET = oPEN_APPSECRET;
	}

	public  void setOPEN_TOP_APPKEY(String oPEN_TOP_APPKEY) {
		OPEN_TOP_APPKEY = oPEN_TOP_APPKEY;
	}

	public void setREDIRECT_OPEN_URI(String rEDIRECT_OPEN_URI) {
		REDIRECT_OPEN_URI = rEDIRECT_OPEN_URI;
	}

	public void setREDIRECT_URI(String rEDIRECT_URI) {
		REDIRECT_URI = rEDIRECT_URI;
	}

	public void setTOP_APPKEY(String tOP_APPKEY) {
		TOP_APPKEY = tOP_APPKEY;
	}

	/**
	 * 
	 * @param shopid
	 * @param key   Lock  解锁锁    StopManualDownload 停止下载  
	 * @param value Lock ( on 锁定  off 解锁    )StopManualDownload  (on 停止下载  off 不停止下载)
	 * @return 
	 */
	public static synchronized boolean setKeyvalueByShopid(String shopid,String key,String value) {
		if(ENABLE_DOWNLOADSHOP_DOMAIN.equals("true")){
			String cacheKey="downloadshop";
			Ehcache cache=Cache.manager.getEhcache(CACHE_DATASTORE);		    
			Element cacheValue =  cache.get(cacheKey);
			if("on".equals(value)){
				if(cacheValue==null){
					Map<String,Map<String,String>> map=new HashMap<String,Map<String,String>>();
					Map childMap=new HashMap<String,String>();
					childMap.put(key, "on");
					map.put(shopid, childMap);
					updateCache(cache,cacheKey,map);
					return true;
				}else{
					Map<String,Map<String,String>> map=(Map)cacheValue.getObjectValue();
					Map<String,String> childMap=map.get(shopid);
					 if("on".equals(childMap.get(key))){
						 return false;
					 }else{
					   childMap.put(key, "on");
					   map.put(shopid, childMap);
					   updateCache(cache,cacheKey,map);
					   return true;
					 }
				}
			}else{
				if(cacheValue==null){
					return true;
				}else{
					Map<String,Map<String,String>> map=(Map)cacheValue.getObjectValue();
					Map<String,String> childMap=map.get(shopid);
					childMap.put(key, "off");
					map.put(shopid, childMap);
					updateCache(cache,cacheKey,map);
					return true;
				}
			}
		}		
		
		if(Constants.SHOP_LOCK_SIGN.equals(key)){
			if("on".equals(value)){
				if (!Constants.DWONLOADING_SHOP.containsKey(shopid)) {
					Constants.DWONLOADING_SHOP.put(shopid, null);
					return true;
				} else {
					System.out.println("系统正在为该店铺执行同步中...");
					return false;
				}
			}else{
				Constants.DWONLOADING_SHOP.remove(shopid);
				return true;
			}
		}else if(Constants.SHOP_STOPMANUALDOWNLOAD_SIGN.equals(key)){
		if("off".equals(value)){
			if (!Constants.DOWNLOAD_STOPING_SHOP.containsKey(shopid)) {
				Constants.DOWNLOAD_STOPING_SHOP.put(shopid, null);
				return true;
			} else {
				System.out.println("已正在停止");
				return false;
			}
		}else{
			Constants.DOWNLOAD_STOPING_SHOP.remove(shopid);
			return true;
			}
		}else{
			return true;
		}
	}
	
	public static void updateCache(Ehcache cache,String cacheKey,Map map){
		 cache.acquireWriteLockOnKey(cacheKey);
			try{
			cache.put(new Element(cacheKey,map));
			}finally{
				cache.releaseWriteLockOnKey(cacheKey);
	      }
	}
	
	/*
	 *  当前店铺是否正在下载
	 *  Lock ( on 锁定  off 解锁    )StopManualDownload  (on 停止下载  off 不停止下载)
	 */
	public static boolean isKeyvalueByShopid(String shopid,String key){
	
		if(ENABLE_DOWNLOADSHOP_DOMAIN.equals("true")){
			String cacheKey="downloadshop";
			Ehcache cache=Cache.manager.getEhcache(CACHE_DATASTORE);		    
			Element cacheValue =  cache.get(cacheKey);
				if(cacheValue==null){
					return false;
				}else{
					Map<String,Map<String,String>> map=(Map)cacheValue;
					Map<String,String> childMap=map.get(shopid);
					 if("on".equals(childMap.get(key))){
						 return true;
					 }else{
						 return false;
					 }
				}
		}			
		if (Constants.SHOP_LOCK_SIGN.equals(key) && Constants.DWONLOADING_SHOP.containsKey(shopid)) {
			return true;
		} else if (Constants.SHOP_STOPMANUALDOWNLOAD_SIGN.equals(key) && Constants.DOWNLOAD_STOPING_SHOP.containsKey(shopid)) {
			return true;
		}  else {
			return false;
		}
	 }
	
	
	public static void setObjectValue(Object o, String prop, Object val)
			throws Exception {
		String stringLetter = prop.substring(0, 1).toUpperCase();
		// 获得相应属性的setXXX
		String getName = "set" + stringLetter + prop.substring(1);
		// 获取相应的方法
		Method getMethod;
		Class clasz = String.class;
		String clazz = clasz.getName();
		getMethod = o.getClass().getMethod(getName, new Class[] { clasz });
		Object arg[] = new Object[] { val };
		getMethod.invoke(o, arg);

	}
	
	public static Object getObjectValue(Object o, String prop)
			throws Exception {
		String stringLetter = prop.substring(0, 1).toUpperCase();
		// 获得相应属性的setXXX
		String getName = "get" + stringLetter + prop.substring(1);
		// 获取相应的方法
		Method getMethod;
		getMethod = o.getClass().getMethod(getName);
		return getMethod.invoke(o);
	}

	public String getENABLE_TRANSPORT_DOMAIN() {
		return ENABLE_TRANSPORT_DOMAIN;
	}

	public void setENABLE_TRANSPORT_DOMAIN(String eNABLE_TRANSPORT_DOMAIN) {
		ENABLE_TRANSPORT_DOMAIN = eNABLE_TRANSPORT_DOMAIN;
	}
	public String getSTO_IP() {
		return STO_IP;
	}
	public void setSTO_IP(String sTO_IP) {
		STO_IP = sTO_IP;
	}
	
	public String getGTO_URL() {
		return GTO_URL;
	}
	public void setGTO_URL(String gTO_URL) {
		GTO_URL = gTO_URL;
	}
	
	public  String getPROXY_TOP_URL() {
		return PROXY_TOP_URL;
	}
	

	public String getENABLE_AUTO_FETCH_TRACK() {
		return ENABLE_AUTO_FETCH_TRACK;
	}
	public void setENABLE_AUTO_FETCH_TRACK(String eNABLE_AUTO_FETCH_TRACK) {
		ENABLE_AUTO_FETCH_TRACK = eNABLE_AUTO_FETCH_TRACK;
	}
	public  void setPROXY_TOP_URL(String pROXY_TOP_URL) {
		PROXY_TOP_URL = pROXY_TOP_URL;
	}

	public  String getTOP_URL() {
		return TOP_URL;
	}

	public  void setTOP_URL(String tOP_URL) {
		TOP_URL = tOP_URL;
	}
	public  String getCACHE_DATASTORE() {
		return CACHE_DATASTORE;
	}
	public  void setCACHE_DATASTORE(String cACHE_DATASTORE) {
		CACHE_DATASTORE = cACHE_DATASTORE;
	}
	public  String getENABLE_DOWNLOADSHOP_DOMAIN() {
		return ENABLE_DOWNLOADSHOP_DOMAIN;
	}
	public  void setENABLE_DOWNLOADSHOP_DOMAIN(
			String eNABLE_DOWNLOADSHOP_DOMAIN) {
		ENABLE_DOWNLOADSHOP_DOMAIN = eNABLE_DOWNLOADSHOP_DOMAIN;
	}
	public  String getAUTO_TAOBAO_MESSAGESERVER() {
		return AUTO_TAOBAO_MESSAGESERVER;
	}
	public  void setAUTO_TAOBAO_MESSAGESERVER(String aUTO_TAOBAO_MESSAGESERVER) {
		AUTO_TAOBAO_MESSAGESERVER = aUTO_TAOBAO_MESSAGESERVER;
	}
	
	public static String JDP_WSDL;
	
	public void setJDP_WSDL(String JDP_WSDL){
		Constants.JDP_WSDL = JDP_WSDL;
	}
	public  String getAUTO_OPEN_RDS() {
		return AUTO_OPEN_RDS;
	}
	public  void setAUTO_OPEN_RDS(String aUTO_OPEN_RDS) {
		AUTO_OPEN_RDS = aUTO_OPEN_RDS;
	}
	
	public void setHF_APPKEY(String hF_APPKEY) {
		HF_APPKEY = hF_APPKEY;
	}
	
	public void setHF_APPSECRET(String hF_APPSECRET) {
		HF_APPSECRET = hF_APPSECRET;
	}
	public void setHF_url(String hF_url) {
		HF_url = hF_url;
	}
	
	//E网配
	public static String EWP_LOCAL_URL = "";  
	
	public static String EWP_PICTURE_URL = "";
	
	public static String EWP_SERVER_AK = "";
	
	public static String EWP_AROUND_RADIUS = "";

	public  void setEWP_LOCAL_URL(String eWP_LOCAL_URL) {
		EWP_LOCAL_URL = eWP_LOCAL_URL;
	}
	public  void setEWP_PICTURE_URL(String eWP_PICTURE_URL) {
		EWP_PICTURE_URL = eWP_PICTURE_URL;
	}
	public  void setEWP_SERVER_AK(String eWP_SERVER_AK) {
		EWP_SERVER_AK = eWP_SERVER_AK;
	}
	public void setEWP_AROUND_RADIUS(String eWP_AROUND_RADIUS) {
		EWP_AROUND_RADIUS = eWP_AROUND_RADIUS;
	}
	
	public static String PUSH_ANDROID_APIKEY = "";
	
	public static String PUSH_ANDROID_SECRETKEY = "";

	public void setPUSH_ANDROID_APIKEY(String pUSH_ANDROID_APIKEY) {
		PUSH_ANDROID_APIKEY = pUSH_ANDROID_APIKEY;
	}
	public void setPUSH_ANDROID_SECRETKEY(String pUSH_ANDROID_SECRETKEY) {
		PUSH_ANDROID_SECRETKEY = pUSH_ANDROID_SECRETKEY;
	}
	
	public static String ZM_URL = "";	
	
	public static String UPLOAD_ZM_DATA = "";
	
	public void setZM_URL(String zM_URL) {
		ZM_URL = zM_URL;
	}
	public void setUPLOAD_ZM_DATA(String uPLOAD_ZM_DATA) {
		UPLOAD_ZM_DATA = uPLOAD_ZM_DATA;
	}
	
	public static String PUSH_IOS_APIKEY = "";
	
	public static String PUSH_IOS_SECRETKEY = "";

	public void setPUSH_IOS_APIKEY(String pUSH_IOS_APIKEY) {
		PUSH_IOS_APIKEY = pUSH_IOS_APIKEY;
	}
	public void setPUSH_IOS_SECRETKEY(String pUSH_IOS_SECRETKEY) {
		PUSH_IOS_SECRETKEY = pUSH_IOS_SECRETKEY;
	}
}