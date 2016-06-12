'use strict';

define([], function() {
	return ['$scope', '$rootScope', 'loginService', 'appConstants', '$location', '$timeout', function($scope, $rootScope, loginService, appConstants, $location, $timeout) {
	
	$scope.authenticate = function() {
		$scope.user = {
			username: $scope.username,
			password: $scope.password
		};

		var promise = loginService.authenticate($scope.user);
		promise.then(
			function(response) {
				storeUserInfo(response.data);
				$timeout(function() {
				  $location.path('/dashboard');
				});
				
			},
			function(status) {
				clearLocalInfo();
				$location.path('/login');
			});
	};

	function storeUserInfo(data) {
	    //if(!localStorageService.isSupported) { return false; }
	    appConstants.setItem('token', data.token);
	    appConstants.setItem('currentUser', data.userDto);
	    $rootScope.$broadcast('loginStatusChanged');
	}

	function clearLocalInfo() {
		appConstants.clearAll();
		$rootScope.$broadcast('loginStatusChanged');
	}	
			

	$scope.$apply();
		
	}];
});