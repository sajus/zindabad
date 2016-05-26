package com.cyb.tms.security.json;

import com.cyb.tms.security.model.CybUsers;
import com.cyb.tms.security.model.ModelBase;

public class AuthenticationResponse extends ModelBase {


	private static final long serialVersionUID = -8899688124401146318L;
	
	private String token;
	private CybUsers user;
	
	public AuthenticationResponse() {
		super();
	}

	
	public AuthenticationResponse(String token, CybUsers user) {
		this.token = token;
		this.user = user;
	}


	public CybUsers getUser() {
		return user;
	}


	public void setUser(CybUsers user) {
		this.user = user;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
