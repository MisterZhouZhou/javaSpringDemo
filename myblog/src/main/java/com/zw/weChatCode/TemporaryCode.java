package com.zw.weChatCode;

public class TemporaryCode<T> {
	private int expire_seconds;
	private String action_name;
	private ActionInfo<T> action_info;
	public int getExpire_seconds() {
		return expire_seconds;
	}
	public void setExpire_seconds(int expire_seconds) {
		this.expire_seconds = expire_seconds;
	}
	public String getAction_name() {
		return action_name;
	}
	public void setAction_name(String action_name) {
		this.action_name = action_name;
	}
	public ActionInfo<T> getAction_info() {
		return action_info;
	}
	public void setAction_info(ActionInfo<T> action_info) {
		this.action_info = action_info;
	}

	
}
