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
	'common/userService'
		], function(loginView, homeView, loginService, userView, header, appConstants, navMenus, tmsTable, storyView, sprintView, sprintService, leaveView, leaveService, userService) {
	return [homeView, loginView, loginService, header, appConstants, navMenus, tmsTable, storyView, sprintView, sprintService, leaveView, leaveService, userView, userService];
});