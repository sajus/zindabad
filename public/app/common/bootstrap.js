'use strict';
define([
	'login/login',
	'home/app',
	'common/loginService',
	'components/header/header',
	'common/appConstants',
	'components/header/navMenus'
	], function(loginView, adminView, loginService, header, appConstants, navMenus) {
	return [adminView, loginView, loginService, header, appConstants, navMenus];
});