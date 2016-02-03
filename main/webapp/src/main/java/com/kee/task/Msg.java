package com.kee.task;

import java.io.Serializable;
import java.util.HashMap;

import com.wfs.task.Msg;


/**
 * 
 * @author Qindebu
 * 该类用于封装返回结果,T表示返回结果的类型；
 * 例如：String，List，实体类，类型必须能够实例化
 *
 * @param <T>
 */
public class Msg<T> implements Serializable {
	
	/**
	 * 用于保存返回该结果的Task的taskid；
	 */
	private String taskID;
	
	/**
	 * 用于保存返回该结果的Task的threadid；
	 */
	private String threadid;
	
	/**
	 * 如果该结果是一个分布式结算的结果，则该ID保存有用于分割task的mapid；
	 */
	private String mapTaskId;

	/**
	 * 保存有请求的paramid
	 */
	private String paramId;
	
	/**
	 * 保存有是由哪个Node请求返回的结果值
	 */
	private String node;
	/**
	 * 保存返回值
	 */
	private HashMap map;
	
	/**
	 * 返回的结果值
	 */
	private T value;

	private boolean result;
	
	
	private boolean rollback;
	
	private int state;
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public boolean isResult() {
		return result;
	}
	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public T getValue() {
		return value;
	}

	public Msg setValue(T value) {
		this.value = value;
		return this;
	}

	public String getThreadid() {
		return threadid;
	}

	public void setThreadid(String threadid) {
		this.threadid = threadid;
	}

	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	public String getMapTaskId() {
		return mapTaskId;
	}

	public void setMapTaskId(String mapTaskId) {
		this.mapTaskId = mapTaskId;
	}

	public String getParamId() {
		return paramId;
	}

	public void setParamId(String paramId) {
		this.paramId = paramId;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}
	public HashMap getMap() {
		return map;
	}
	public void setMap(HashMap map) {
		this.map = map;
	}
	public boolean isRollback() {
		return rollback;
	}
	public void setRollback(boolean rollback) {
		this.rollback = rollback;
	}
	
}
