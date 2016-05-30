'use strict';
define([
	'login/login',
	'home/app',
	'common/loginService',
	'components/header/header',
	'common/appConstants',
	'components/header/navMenus',
	'components/tmstable/tmsTable',
	'story/story'
	], function(loginView, homeView, loginService, header, appConstants, navMenus, tmsTable, storyView) {
	return [homeView, loginView, loginService, header, appConstants, navMenus, tmsTable, storyView];
});