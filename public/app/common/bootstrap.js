'use strict';
define([
	'login/login',
	'admin/admin'
	], function(loginView, adminView) {
	return [adminView, loginView];
});