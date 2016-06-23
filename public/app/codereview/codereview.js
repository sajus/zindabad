'use strict';
define([
	'angular',
	'angularRoute',
	'css'
], function(angular) {
	return angular.module('myApp', ['ngRoute'])
	.controller('codereviewCtrl', ['$scope', '$injector', function($scope, $injector) {
		require(['codereview/codereviewCtrl'], function(controller) {
			$injector.invoke(controller, this, {'$scope': $scope});
		});
	}]);
});
