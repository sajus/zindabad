package com.cyb.tms.util;

public class URIConstants {
	
	/* common uri's */
	
	public static final String GET_BY_ID = "/{id}";
	public static final String GET_ALL = "/list";
	public static final String CREATE = "/create";
	public static final String DELETE = "/delete/{id}";
	public static final String EDIT = "/edit/{id}";
	
	// User
	public static final String USER = "api/user";
	
	// OrgLeaves
	public static final String ORG_LEAVE = "api/org/leave";
	

	public static final String TOKEN_HEADER = "X-Auth-Token";
	public static final String TOKEN_SECRET = "eHTRcUpaSWfGgTIJsC4rM6lPMm8es87pYPrELJUwoNqMXN7zWIzGQGq1hgdPCM5u";
	public static final Long TOKEN_EXPIRE = (long) 86400;
	public static final String ROUTE_AUTH = "api/auth";
	public static final String ROUTE_AUTH_REFRESH = "/refresh";
	
	
}
