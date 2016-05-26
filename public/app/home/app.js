'use strict';

define([
	'angular',
	'angularRoute',
	'css'
], function(angular) {
	return angular.module('myApp', ['ngRoute'])
	.config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {

		$routeProvider.when('/home', {
			templateUrl: 'home/home.html',
			css: 'home/home.css',
			controller: 'homeCtrl'
		});

	}])
	// We can load the controller only when needed from an external file
	.controller('homeCtrl', ['$scope', '$injector', function($scope, $injector) {
		require(['home/homeCtrl'], function(controller) {
			$injector.invoke(controller, this, {'$scope': $scope});
		});
	}]);
});