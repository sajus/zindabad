'use strict';
define([
	'angular',
], function(angular) {
	return angular.module('myApp.service', ['$http', 'appConstants']).service("loginService", function($http, appConstants) {

		var self = this;

		self.authenticate = function(user) {
			return $http.post(appConstants.url+"api/auth", user);
		};

	});
	
	
});
