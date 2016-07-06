'use strict';
define([
	'angular',
	'angularRoute',
	'css'
], function(angular) {
	return angular.module('myApp', ['ngRoute'])
	.controller('passwordCtrl', ['$scope', '$injector', function($scope, $injector) {
		require(['password/passwordCtrl'], function(controller) {
			$injector.invoke(controller, this, {'$scope': $scope});

		});
	}]);


    



});