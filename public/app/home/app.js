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
			// injector method takes an array of modules as the first argument
			// if you want your controller to be able to use components from
			// any of your other modules, make sure you include it together with 'ng'
			// Furthermore we need to pass on the $scope as it's unique to this controller
			$injector.invoke(controller, this, {'$scope': $scope});
		});
	}]);
});