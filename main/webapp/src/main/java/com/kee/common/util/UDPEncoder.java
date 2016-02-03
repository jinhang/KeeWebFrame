package com.kee.common.util;

import org.apache.log4j.Logger;


/**
 * @author mingjin_ding@163.com
 */
public class UDPEncoder {
	static final Logger runLog = Logger.getLogger(UDPEncoder.class);
	
	private static int SEQ=0;
	private int totalLength;
	private int mouleId;
	private int cmdType;
	private int status;
	private int sequenceId;
	private long sessionid;
	
	public static final int HEAD_LENGTH = 11;			
	public static final int MSG_LENGTH_POSITION = 0;
	public static final int MODULE_ID_POSITION = 2;
	public static final int CMD_TYPE_POSITION = 3;
	public static final int STATUS_POSITION = 4;
	public static final int SEQ_ID_POSITION = 5;
	public static final int SESSION_ID_POSITION = 6;
	public static final int MAX_MSG_LENGTH = 4096;
	public static final int MESSAGE_TOTAL_LENGTN_POSITION = 0;
	
	
	public UDPEncoder(int mouleId,int cmdType,int status,long sessionid) {
		this.mouleId=mouleId;
		this.cmdType=cmdType;
		this.status=status;
		this.sequenceId=getSEQ();
		this.sessionid=sessionid;
	}
	
	public byte[] encode(String message) throws Exception{
		byte[] buf = new byte[MAX_MSG_LENGTH];
	    encodeHeader(buf);
	    int bodyLength = encodeBody(buf,HEAD_LENGTH,message);
	    this.totalLength = HEAD_LENGTH + bodyLength;
	    byte[] b1 = ByteTools.shortToByte2((short)(totalLength-2));
	    System.arraycopy(b1, 0, buf, MESSAGE_TOTAL_LENGTN_POSITION, b1.length);
	    byte[] bb = new byte[totalLength];
	    System.arraycopy(buf, 0, bb, 0, totalLength);
	    return bb;
	}
	
	
	
	public void encodeHeader(byte[] buf){
		buf[MODULE_ID_POSITION] = (byte)mouleId;
		buf[CMD_TYPE_POSITION] = (byte)cmdType;
		buf[STATUS_POSITION] = (byte)status;
		buf[SEQ_ID_POSITION] = (byte)sequenceId;
		byte[] sessId = ByteTools.longTobyte5(sessionid);
		System.arraycopy(sessId, 0, buf, SESSION_ID_POSITION, sessId.length);
	}
	
	private int encodeBody(byte[] buf, int offset,String message) throws Exception {
		byte[] by = message.getBytes("UTF-8");
		by=GZipUtil.compress(by);
		int bodyLength = by.length;
		System.arraycopy(by, 0, buf, offset, bodyLength);
		return bodyLength;
	}
	
	
	public static int getSEQ(){
		SEQ++;
		if(SEQ>255){SEQ=1;}
		return SEQ;
	}
	

	public int getTotalLength() {
		return totalLength;
	}

	public void setTotalLength(int totalLength) {
		this.totalLength = totalLength;
	}

	public int getMouleId() {
		return mouleId;
	}

	public void setMouleId(int mouleId) {
		this.mouleId = mouleId;
	}

	public int getCmdType() {
		return cmdType;
	}

	public void setCmdType(int cmdType) {
		this.cmdType = cmdType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(int sequenceId) {
		this.sequenceId = sequenceId;
	}

	public long getSessionid() {
		return sessionid;
	}

	public void setSessionid(long sessionid) {
		this.sessionid = sessionid;
	}
}
