'use strict';
define([
	'angular',
	'angularRoute',
	'css'
], function(angular) {
	return angular.module('myApp', ['ngRoute'])
	.controller('leaveCtrl', ['$scope', '$injector', function($scope, $injector) {
		require(['leave/leaveCtrl'], function(controller) {
			$injector.invoke(controller, this, {'$scope': $scope});

		});
	}]);


    



});
