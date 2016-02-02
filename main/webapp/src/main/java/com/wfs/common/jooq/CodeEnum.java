package com.wfs.common.jooq;

import org.jooq.CaseValueStep;
import org.jooq.CaseWhenStep;
import org.jooq.Field;
import org.jooq.impl.DSL;

public class CodeEnum {
	
	public static enum OrderState {
		DJD("WAIT_EXPRESS_ACCEPT", "待接单"), DQJ("WAIT_EXPRESS_PICKUP", "待取件"), DZF("WAIT_USER_PAY", "待支付"), 
		PSZ("EXPRESS_DELIVERY_PACKAGE", "配送中"), DQS("WAIT_USER_SIGNED", "待签收"), YQS("TRADE_FINISHED", "已签收"),
		QXZD("TRADE_CANCEL", "取消订单");

		public String key;
		public String value;

		OrderState(String key, String value) {
			this.value = value;
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}
	}

	public static enum SignedWay {
		DQS("INSTEAD_SIGNED", "代签收"), BRQS("OWN_SIGNED", "本人签收"), JQS("REFUSE_SIGNED", "拒签收");

		public String key;
		public String value;

		SignedWay(String key, String value) {
			this.value = value;
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}
		
		public static String chageKeyToValue(String signedway) {
			SignedWay[] sws = SignedWay.values();
			for (int i = 0; i < sws.length; i++) {
				String name = sws[i].key;
				String value = sws[i].value;
				if(name.equals(signedway)){
					return value;
				}
			}
			return "error";
		}
	}
	
	public static enum SendType {
		UTE("USER_SEND_TO_EXPRESS"), ETU("EXPRESS_SEND_TO_USER"), STUE("SYSTEM_SEND");

		public String value;

		SendType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
	
	public static enum MessageType {
		SM("SYSTEM_MESSAGE"), BM("BUSINESS_MESSAGE"),IMM("IMO_MESSAGE");

		public String value;

		MessageType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
	
	/**
	 * 锁类型
	 */
	public enum RedisLockType {

		MESSAGE("MESSAGE", "消息表"),ORDER("ORDER", "订单表");

		public String key;
		public String value;

		RedisLockType(String key, String value) {
			this.value = value;
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}
	}
	
	public static enum PayWay {
		WX("WX", "微信支付"), ZFB("ZFB", "支付宝支付"), XXZF("OFFLINE", "线下支付");

		public String key;
		public String value;

		PayWay(String key, String value) {
			this.value = value;
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}
	}
	
	public static enum FollowType {
		UFS("USER_FOLLOW_STAFF"), SFU("STAFF_FOLLOW_USER");

		public String value;

		FollowType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
	
	public static enum EvaType {
		SO("SIGNED_ORDER"), OR("ONLINE_ORDER");

		public String value;

		EvaType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
	
	public static enum ScanType {
		PU("PU", "收件"), AF("AF", "到件"), OD("OD", "派件"),YN("YN", "问题件"), OK("OK", "签收");

		public String key;
		public String value;

		ScanType(String key, String value) {
			this.value = value;
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}
	}
	
	public static enum PushMesType {
		NSPU(1, "接单通知"), SRM(2, "回复消息"),NSCO(3, "取消订单通知"),
		NSPAYED(4, "用户支付通知"), NSE(5, "评价业务员"),NSAS(6, "预约签收通知"),
		NPS(7, "包裹签收通知"), NUCP(8, "取件通知"),NUTO(9, "揽件通知"),NUSO(10,"派件通知");

		public int key;
		public String value;

		PushMesType(int key, String value) {
			this.value = value;
			this.key = key;
		}

		public int getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}
		
		public static String chageKeyToValue(int pushtype) {
			PushMesType[] sws = PushMesType.values();
			for (int i = 0; i < sws.length; i++) {
				int key = sws[i].key;
				String value = sws[i].value;
				if(key==pushtype){
					return value;
				}
			}
			return "error";
		}
	}
}
