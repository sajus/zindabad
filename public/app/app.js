'use strict';

define([
	'angular',
	'angularRoute',
	'login/app',
	'admin/app'
], function(angular, angularRoute, login, admin) {
	// Declare app level module which depends on views, and components
	return angular.module('myApp', [
		'ngRoute',
		'angularCSS',
		'myApp.login',
		'myApp.admin'
	]).
	config(['$routeProvider', function($routeProvider) {
		$routeProvider.otherwise({redirectTo: '/login'});
	}]);
});

