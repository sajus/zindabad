'use strict';
define([
	'angular',
	'angularRoute',
	'css'
], function(angular) {
	return angular.module('myApp', [])
	.controller('storyCtrl', ['$scope', '$injector', function($scope, $injector) {
		require(['story/storyCtrl'], function(controller) {
			$injector.invoke(controller, this, {'$scope': $scope});
		});
	}]);
	
});

