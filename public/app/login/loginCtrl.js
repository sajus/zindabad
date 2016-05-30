'use strict';

define([], function() {
	return ['$scope', '$rootScope', 'loginService', 'appConstants', '$location', function($scope, $rootScope, loginService, appConstants, $location) {
	
	$scope.authenticate = function() {
		$scope.user = {
			username: $scope.username,
			password: $scope.password
		};

		var promise = loginService.authenticate($scope.user);
		promise.then(
			function(response) {
				storeUserInfo(response.data);
				$location.path('/home');
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