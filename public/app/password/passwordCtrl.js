'use strict';

define([], function() {
	return ['$scope', '$location', 'appConstants','$http', 'passwordService', function($scope, $location, appConstants, $http, passwordService) {
  	$scope.error = false;
    $scope.successMessage = false;
    $scope.user = {};

    $scope.updatePassword = function(newPassword) {
      if($scope.confirmPassword === $scope.newPassword){
        $scope.user.password = newPassword;
        passwordService.updatePassword($scope.user)
        .success(function () {
          $scope.successMessage = true;
          $scope.newPassword = null;
          $scope.confirmPassword = null;
       }).
        error(function (error) {
            $scope.status = 'Unable to update password: ' + error.message;
        });
    }
    else{
      $scope.error = true;
    }
  }

	$scope.$apply();
		
	}];

});
