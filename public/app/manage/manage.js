'use strict';
define([
	'angular',
	'angularRoute',
	'css'
], function(angular) {
	return angular.module('myApp', ['ngRoute'])
	.controller('manageCtrl', ['$scope', '$injector', function($scope, $injector) {
		require(['manage/manageCtrl'], function(controller) {
			$injector.invoke(controller, this, {'$scope': $scope});

		});
	}]);


    



});

