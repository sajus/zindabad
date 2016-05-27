'use strict';

define([], function() {
	return ['$scope', 'loginService', 'localStorageService', '$location', function($scope, loginService, localStorageService, $location) {
	
	$scope.authenticate = function() {
		$scope.user = {
			username: $scope.username,
			password: $scope.password
		};

		var promise = loginService.authenticate($scope.user);
		promise.then(
			function(response) {
				storeUserInfo(response.data);
				$location.path('/admin');
			},
			function(status) {

			});
		//console.log("status::", $scope.status);
	}
		function storeUserInfo(data) {
		    if(!localStorageService.isSupported) { return false; }
		    localStorageService.set('token', data.token);
		    localStorageService.set('currentUser', data.user);''
		}
			

		$scope.$apply();
	}];
});