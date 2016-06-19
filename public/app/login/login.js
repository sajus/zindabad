'use strict';
define([
	'angular',
	'angularRoute',
	'css'
], function(angular) {
	return angular.module('myApp', ['ngRoute', 'LocalStorageModule'])
	.config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
		//$locationProvider.html5Mode(true);
		$routeProvider.when('/login', {
			templateUrl: 'login/login.html',
			css: 'login/login.css',
			controller: 'loginCtrl'
		});
	}])
	.controller('loginCtrl', ['$scope', '$injector', function($scope, $injector) {
		require(['login/loginCtrl'], function(controller) {
			// injector method takes an array of modules as the first argument
			// if you want your controller to be able to use components from
			// any of your other modules, make sure you include it together with 'ng'
			// Furthermore we need to pass on the $scope as it's unique to this controller
			$injector.invoke(controller, this, {'$scope': $scope});
		});
	}]);
	
});

