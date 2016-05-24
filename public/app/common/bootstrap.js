'use strict';
define([
	'login/login',
	'admin/admin',
	'common/loginService'
	], function(loginView, adminView, loginService) {
	return [adminView, loginView, loginService];
});