'use strict';

define([], function() {
	return ['$scope', '$rootScope', 'loginService', 'appConstants', '$location', function($scope, $rootScope, loginService, appConstants, $location) {
	
	console.log($scope);

	$scope.$apply();
		
	}];
});