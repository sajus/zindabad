'use strict';

define([
	'angular',
	'angularRoute',
	'components/version/version'
], function(angular) {
	angular.module('myApp.admin', ['ngRoute', 'myApp.version'])
	.config(['$routeProvider', function($routeProvider) {
		$routeProvider.when('/admin', {
			templateUrl: 'admin/admin.html',
			controller: 'adminCtrl',
			css: 'admin/admin.css'
		});
	}])
	// We can load the controller only when needed from an external file
	.controller('adminCtrl', ['$scope', '$injector', function($scope, $injector) {
		require(['admin/adminCtrl'], function(controller) {
			// injector method takes an array of modules as the first argument
			// if you want your controller to be able to use components from
			// any of your other modules, make sure you include it together with 'ng'
			// Furthermore we need to pass on the $scope as it's unique to this controller
			$injector.invoke(controller, this, {'$scope': $scope});
		});
	}]);
});