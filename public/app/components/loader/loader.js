'use strict';
define([
	'angular',
], function(angular) {

return angular.module('myApp.directive',['appConstants', 'navMenus', '$location', '$routeProvider']).directive('appLoader', function (appConstants, navMenus, $location) {
	return {
		restrict: 'E',
		templateUrl: 'components/loader/loader.html',
		$scope: {
			isLoading : "@"
		 },
		replace: true,
		controller: function($scope, $attrs, $element) {

			console.log("scope.isLoading", $scope.isLoading);

			if($attrs.isLoading) {
				$scope.isLoading = true;
			}

			$scope.$watch("isLoading", function () {
				$scope.isLoading = false;
			});
		}
	}
	
});		

});    