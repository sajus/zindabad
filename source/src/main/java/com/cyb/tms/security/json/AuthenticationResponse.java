package com.cyb.tms.security.json;

import com.cyb.tms.dto.TmsUsersDTO;
import com.cyb.tms.security.model.ModelBase;

public class AuthenticationResponse extends ModelBase {


	private static final long serialVersionUID = -8899688124401146318L;
	
	private String token;
	private TmsUsersDTO currentUser;
	
	public AuthenticationResponse() {
		super();
	}

	
	public AuthenticationResponse(String token, TmsUsersDTO currentUser) {
		super();
		this.token = token;
		this.currentUser = currentUser;
	}

	

	public TmsUsersDTO getUserDto() {
		return currentUser;
	}


	public void setUserDto(TmsUsersDTO userDto) {
		this.currentUser = userDto;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}	

}
