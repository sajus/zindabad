package com.cyb.tms.util;

public class URIConstants {
	
	public static final String ROUTE_AUTH = "api/auth";
	public static final String ROUTE_AUTH_REFRESH = "api/refresh";

	/* common uri's */
	public static final String GET_BY_ID = "/{id}";
	public static final String GET_ALL = "/list";
	public static final String CREATE = "/create";
	
	
	public static final String DELETE = "/delete/{id}";
	public static final String EDIT = "/edit";
	public static final String GET_ALL_BY_PROJECT = "/list/project";
	public static final String BACKLOG = "/backlog";
	public static final String GET_ALL_BY_USER = "/list/user";
	
	// User
	public static final String USER = "api/user";
	public static final String DASH_BOARD = "/dashboard";
	
	// OrgLeaves
	public static final String ORG_LEAVE = "api/org/leave";
	
	// Leaves
	public static final String LEAVE = "api/leave";
	
	// Sprint
	public static final String SPRINT = "api/sprint";
	
	// Module
	public static final String MODULE = "api/module";
	
	// Project
	public static final String PROJECT = "api/project";
	
	// Story
	public static final String STORY = "api/story";
	public static final String STORIES_BY_SPRINT = "/sprint";
	public static final String USER_STORIES_BY_SPRINT = "/user/sprint";
	
	// Subtask
	public static final String SUBTASK = "api/subtask";
	public static final String SUBTASKS_BY_SPRINT = "/sprint";
	public static final String USER_SUBTASKS_BY_SPRINT = "/user/sprint";
	
	// Efforts
	public static final String EFFORTS = "api/efforts";
	public static final String USER_EFFORTS_BY_SPRINT = "/user/sprint";
	
	//Code Review
	public static final String CODE_REVIEW = "api/review";

	public static final String TOKEN_HEADER = "X-Auth-Token";
	public static final String TOKEN_SECRET = "eHTRcUpaSWfGgTIJsC4rM6lPMm8es87pYPrELJUwoNqMXN7zWIzGQGq1hgdPCM5u";
	public static final Long TOKEN_EXPIRE = (long) 86400;
	
	
}
