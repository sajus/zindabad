'use strict';

define([], function() {
	return ['$scope', '$rootScope', 'subtaskService', 'appConstants', '$location', function($scope, $rootScope, subtaskService, appConstants, $location) {
	
	$scope.changeTab = function (currentTab) {
    currentTab === 'UNASSIGNED' ? getUnassignedSubtask() : getSubtask();
    $scope.selectedTab = currentTab;
  };

  $scope.changeTab('ASSIGNED');



  function getSubtask() {

      $scope.loading = true;

        subtaskService.getSubtask()
          .success(function (dataSubtask) {
           $scope.subtasks = dataSubtask;
           $scope.loading = false;       
          })
          .error(function (error) {
              $scope.status = 'Unable to process your request: ' + error.message;
              $scope.loading = false;
          });
    }


    function getUnassignedSubtask() {

      subtaskService.getUnassignedSubtask()
        .success(function (dataSubtask) {
         $scope.unassignedTasks = dataSubtask;
         $scope.loading = false;      
        })
        .error(function (error) {
            $scope.status = 'Unable to process your request: ' + error.message;
            $scope.loading = false;
        });

    }
	
    getSubtask();

	$scope.$apply();
		
	}];
});
