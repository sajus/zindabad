'use strict';

define([], function() {
	return ['$scope', '$rootScope', 'subtaskService', 'appConstants', '$location', '$timeout', function($scope, $rootScope, subtaskService, appConstants, $location, $timeout) {
	
	$scope.changeTab = function (currentTab) {
    currentTab === 'UNASSIGNED' ? getUnassignedSubtasks() : getSubtasks();
    $scope.selectedTab = currentTab;
  };

  $scope.closeModal = closeModal;
  $scope.isModalVisible = false;
  $scope.errorMessage = undefined;
  $scope.changeTab('ASSIGNED');
  $scope.dateOptions = {format: 'dd/mm/yyyy'};

  $scope.showModal = function(subtask) {
    $scope.getStories();
    $scope.subtask = angular.copy(subtask) || {};
    $scope.isModalVisible = true;
  }

  $scope.addSubtask = function(subtask) {
    $timeout(function() {
      subtaskService.addSubtask(subtask)
      .success(function () {
        getUnassignedSubtasks();
        closeModal();
      })
      .error(function (error) {
        $scope.errorMessage = 'Unable to process your request';
      });
    }, 0);
    
  }

  $scope.getStories = function() {
    subtaskService.getStories()
      .success(function (data) {
        $scope.stories = data;
      })
      .error(function (error) {
        $scope.errorMessage = 'Unable to fetch user stories';
      });
  }

  function getSubtasks() {
    $scope.loading = true;
    subtaskService.getSubtasks()
      .success(function (dataSubtask) {
         $scope.subtasks = dataSubtask;
         $scope.loading = false;
      })
      .error(function (error) {
        $scope.status = 'Unable to process your request: ' + error.message;
        $scope.loading = false;
      });
  }


    function getUnassignedSubtasks() {
      $scope.loading = true;
      subtaskService.getUnassignedSubtasks()
        .success(function (dataSubtask) {
         $scope.unassignedTasks = dataSubtask;
         $scope.loading = false;      
        })
        .error(function (error) {
            $scope.status = 'Unable to process your request: ' + error.message;
            $scope.loading = false;
        });

    }

    function closeModal() {
      clearErrorMessages();
      $scope.isModalVisible = false;
    }

    function clearErrorMessages() {
      $scope.errorMessage = undefined;
    }

	$scope.$apply();
		
	}];
});
