package com.zw.weChatCode;

public class PermanentCode<T> {
	private String action_name;
	private ActionInfo<T> action_info;
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
