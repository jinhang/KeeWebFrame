package cn.kee.common;

public class ValidateMessage {
	public boolean result;
	public String memo;
	
	public ValidateMessage(boolean result,String memo){
		this.result = result;
		this.memo = memo;
	}
	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
}
