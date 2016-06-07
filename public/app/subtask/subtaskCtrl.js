'use strict';

define([], function() {
	return ['$scope', '$rootScope', 'subtaskService', 'appConstants', '$location', function($scope, $rootScope, subtaskService, appConstants, $location) {
	
	function getSubtask() {

      $scope.loading = true;

        subtaskService.getSubtask()
            .success(function (dataSubtask) {
             $scope.subtasks = dataSubtask;
             $scope.loading = false;
          
                
            })
            .error(function (error) {
                $scope.status = 'Unable to load customer data: ' + error.message;
                $scope.loading = false;
            });
    }
	
    getSubtask();

	$scope.$apply();
		
	}];
});
