package com.kcb.cxf;

import java.util.HashMap;
import java.util.Map;

import com.wfos.engine.wrapper.model.Msg;

/**
 * 巴枪接口错误返回码
 * @author Matrix.J
 *
 */
public class ErrorMap {
	
	private static Map<String, String> errormsgMap = new HashMap<String, String>();
	
	public static Msg addErrorVal(Msg msg,String varName,String varValue) {
		if(msg == null) {
			msg = new Msg();
		}
		if(msg.mvalue == null) {
			Map<String, Object> map = new HashMap<String, Object>();
			msg.mvalue = map;
		}
		msg.mvalue.put(varName, varValue);
		return msg;
	}
	
	public static String getErrorMsg(String errorCode) {
		return errormsgMap.get(errorCode);
	}
	
	public static String getErrorMsg(String errorCode,Map<String, String> varMap) {
		String errorMsg = errormsgMap.get(errorCode);
		if(errorMsg != null) {
			for(String str : varMap.keySet()) {
				errorMsg = errorMsg.replace(str, varMap.get(str));
			}
		} 
		return errorMsg;
		
	}
	//全局通用
	public static final String SUCCESS ="0000";
	static {errormsgMap.put(SUCCESS, "成功");}
	
	public static final String ACCOUNT_ERROR = "0100";
	static {errormsgMap.put(ACCOUNT_ERROR, "账号或密码错误");}
	
	public static final String DATA_FORMAT_ERROR = "0200";
	static {errormsgMap.put(DATA_FORMAT_ERROR, "数据格式不正确");}
	
	public static final String SYSTEM_ERROR_OR_TIMEOUT = "0500";
	static {errormsgMap.put(SYSTEM_ERROR_OR_TIMEOUT, "系统异常或超时");}	
	

	public static final String CODE_1001 = "11001";
	static {errormsgMap.put(CODE_1001, "拣货单不存在");}
	
	public static final String CODE_1002 = "11002";
	static {errormsgMap.put(CODE_1002, "拣货单已拣货");}
	
	public static final String CODE_1003 = "11003";
	static {errormsgMap.put(CODE_1003, "边拣边分模式篮号为空");}
	
	public static final String CODE_1004 = "11004";
	static {errormsgMap.put(CODE_1004, "商品已拣货上传");}
	
	public static final String CODE_1005 = "11005";
	static {errormsgMap.put(CODE_1005, "还有商品未完成拣货");}
	
	public static final String CODE_1006 = "11006";
	static {errormsgMap.put(CODE_1006, "移库任务单不存在");}
	
	public static final String CODE_1007 = "11007";
	static {errormsgMap.put(CODE_1007, "移库任务单已打印状态才能移库");}
	
	public static final String CODE_1008 = "11008";
	static {errormsgMap.put(CODE_1008, "移出库位不存在");}
	
	public static final String CODE_1009 = "11009";
	static {errormsgMap.put(CODE_1009, "移入库位不存在");}
	
	public static final String CODE_1010 = "11010";
	static {errormsgMap.put(CODE_1010, "移出库位没有库存信息");}
	
	public static final String CODE_1011 = "11011";
	static {errormsgMap.put(CODE_1011, "移动数量超过原库位商品数量");}
	
	public static final String CODE_1012 = "11012";
	static {errormsgMap.put(CODE_1012, "库位不存在");}
	
	public static final String CODE_1013 = "11013";
	static {errormsgMap.put(CODE_1013, "拣货模式已修改");}
	
	//入库单
	public static final String CODE_1301 = "11301";
	static {errormsgMap.put(CODE_1301, "入库单不存在");}
	
	public static final String CODE_1302 = "11302";
	static {errormsgMap.put(CODE_1302, "上架数量不正确");}
	
	public static final String CODE_1303 = "11303";
	static {errormsgMap.put(CODE_1303, "入库单不存在该商品");}
	
	public static final String CODE_1304 = "11304";
	static {errormsgMap.put(CODE_1304, "入库单状态不是待清点");}
	
	public static final String CODE_1305 = "11305";
	static {errormsgMap.put(CODE_1305, "入库单状态不是待上架");}
	
	//商品
	public static final String CODE_1401 = "11401";
	static {errormsgMap.put(CODE_1401, "商品不存在");}
	
	public static void main(String[] args) {
		errormsgMap.put("SALESORDER_STATUS_ERROR", "订单编号为[ordercode]的订单 状态为[status]");
		Map<String, String> mapName = new HashMap<String, String>();
		mapName.put("ordercode", "SO1000123");
		mapName.put("status", "已发货");
		String msg = getErrorMsg("SALESORDER_STATUS_ERROR", mapName);
		System.out.println(msg);
	}
}
