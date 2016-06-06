'use strict';

define([], function() {
	return ['$scope','appConstants','$http','leaveService', function($scope, appConstants, $http, leaveService) {
	
	function getLeaves() {
        leaveService.getLeaves()
            .success(function (dataLeave) {
             $scope.leave = dataLeave; 
            })
            .error(function (error) {
                $scope.status = 'Unable to process your request: ' + error.message;
            });
    }
    
    getLeaves();

	$scope.$apply();
		
	}];
});