'use strict';
define([
	'angular',
], function(angular) {

return angular.module('myApp.directive',['appConstants', 'navMenus', '$location']).directive('appHeader', function (appConstants, navMenus, $location) {
	return {
		restrict: 'EA',
		templateUrl: 'components/header/header.html',
		scope: { },
		replace: true,
		controller: function($scope, $attrs) {

			function init() {
				$scope.isAuthenticated = appConstants.isAuthenticated();
				$scope.user = appConstants.user;
				$scope.selectedTab = 0;
				$scope.menus = navMenus.getMenus();//[];

				// angular.forEach(navMenus.getMenus(), function(menu) {
				// 	if ($scope.user && (menu.accessTo).indexOf($scope.user.userRole) > -1) {
				// 		$scope.menus.push(menu);
				// 	} 
				// });					
					
			};
		
			$scope.logout = function() {
				$scope.isAuthenticated = false;
				appConstants.clearAll();
				//$location.replace();
				$location.path('/login');
			}
			

		    $scope.$on('loginStatusChanged', function (loggedIn) {
		        $scope.isAuthenticated = loggedIn; 
		    });

		    init();


		}
	};
});    	


});    