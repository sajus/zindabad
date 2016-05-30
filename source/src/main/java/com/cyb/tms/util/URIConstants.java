package com.cyb.tms.util;

public class URIConstants {
	
	public static final String ROUTE_AUTH = "api/auth";
	public static final String ROUTE_AUTH_REFRESH = "api/refresh";

	/* common uri's */
	public static final String GET_BY_ID = "/{id}";
	public static final String GET_ALL = "/list";
	public static final String CREATE = "/create";
	public static final String DELETE = "/delete/{id}";
	public static final String EDIT = "/edit/{id}";
	
	// User
	public static final String USER = "api/user";
	public static final String DASH_BOARD = "/dashboard";
	
	// OrgLeaves
	public static final String ORG_LEAVE = "api/org/leave";
	
	
	// Sprint
	public static final String SPRINT = "api/sprint";
	
	// Module
	public static final String MODULE = "api/module";
	
	// Project
	public static final String PROJECT = "api/project";
	
	// Story
	public static final String STORY = "api/story";
	public static final String STORIES_BY_SPRINT = "/sprint";
	
	// Subtask
	public static final String SUBTASK = "api/subtask";
	
	// Efforts
	public static final String EFFORTS = "api/efforts";


	public static final String TOKEN_HEADER = "X-Auth-Token";
	public static final String TOKEN_SECRET = "eHTRcUpaSWfGgTIJsC4rM6lPMm8es87pYPrELJUwoNqMXN7zWIzGQGq1hgdPCM5u";
	public static final Long TOKEN_EXPIRE = (long) 86400;
	
	
}
