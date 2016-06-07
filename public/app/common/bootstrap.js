'use strict';
define([
	'login/login',
	'home/app',
	'common/loginService',
	'components/header/header',
	'common/appConstants',
	'components/header/navMenus',
	'components/tmstable/tmsTable',
	'story/story',
	'sprint/sprint',
	'common/sprintService',
	'leave/leave',
	'common/leaveService',
	'user/user',
	'common/userService',
	'components/loader/loader',
	'common/storyService',
	'subtask/subtask',
	'common/subtaskService'
		], function(
				loginView, 
				homeView, 
				loginService,
				userView, 
				header, 
				appConstants, 
				navMenus, 
				tmsTable,
				storyView, 
				sprintView, 
				sprintService,
				leaveView, 
				leaveService, 
				userService,
				loader,
				storyService,
				subtaskService,
				subtaskView
			) {
	return [
		homeView, 
		loginView, 
		loginService, 
		header, 
		appConstants, 
		navMenus, 
		tmsTable, 
		storyView, 
		sprintView, 
		sprintService, 
		leaveView, 
		leaveService, 
		userView,
		userService,
		loader,
		storyService,
		subtaskService,
		subtaskView
	];
});