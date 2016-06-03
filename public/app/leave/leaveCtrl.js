'use strict';

define([], function() {
	return ['$scope','appConstants','$http','leaveService', function($scope, appConstants, $http, leaveService) {
	
	function getLeave() {
        leaveService.getLeave()
            .success(function (dataLeave) {
             $scope.leave = dataLeave;
          
                
            })
            .error(function (error) {
                $scope.status = 'Unable to load customer data: ' + error.message;
            });
    }
    
    getLeave();

	$scope.$apply();
		
	}];
});