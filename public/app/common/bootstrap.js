'use strict';
define([
	'login/login',
	'home/app',
	'common/loginService'
	], function(loginView, adminView, loginService) {
	return [adminView, loginView, loginService];
});