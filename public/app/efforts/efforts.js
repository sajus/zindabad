'use strict';
define([
	'angular',
	'angularRoute',
	'css'
], function(angular) {
	return angular.module('myApp', ['ngRoute'])
	.controller('effortsCtrl', ['$scope', '$injector', function($scope, $injector) {
		require(['efforts/effortsCtrl'], function(controller) {
			$injector.invoke(controller, this, {'$scope': $scope});

		});
	}]);
});
