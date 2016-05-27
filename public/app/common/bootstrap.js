'use strict';
define([
	'login/login',
	'home/app',
	'common/loginService',
	'components/header/header',
	'common/appConstants'
	], function(loginView, adminView, loginService, header, appConstants) {
	return [adminView, loginView, loginService, header, appConstants];
});