'use strict';
define([
	'angular',
	'angularRoute',
	'css'
], function(angular) {
	return angular.module('myApp', ['ngRoute'])
	.controller('userCtrl', ['$scope', '$injector', function($scope, $injector) {
		require(['user/userCtrl'], function(controller) {
			$injector.invoke(controller, this, {'$scope': $scope});

		});
	}]);


    



});

