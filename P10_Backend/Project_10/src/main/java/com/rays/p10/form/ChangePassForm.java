package com.rays.p10.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ChangePassForm {

	@NotEmpty
	String loginId;
	
	@NotEmpty
	@Size(min = 2, max = 10)
	private String oldPassword;

	@NotEmpty
	@Size(min = 2, max = 10)
	private String newPassword;

	
	
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
