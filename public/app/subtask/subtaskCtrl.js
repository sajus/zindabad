'use strict';

define([], function() {
	return ['$scope', '$rootScope', 'subtaskService', 'userService', 'appConstants', '$location', function($scope, $rootScope, subtaskService, userService, appConstants, $location) {
	
	$scope.changeTab = function (currentTab) {
    currentTab === 'UNASSIGNED' ? getUnassignedSubtasks() : getSubtasks();
    $scope.selectedTab = currentTab;
  };

  $scope.closeModal = closeModal;
  $scope.errorMessage = undefined;
  $scope.changeTab('ASSIGNED');
  $scope.selectedSubtaskList = [];
  $scope.subtaskList = [];
  $scope.isAllSelected = false;

  $scope.showModal = function(id, subtask) {
    $scope.getStories();
    $scope.subtask = angular.copy(subtask) || {};
    var element = angular.element(id);
    element.modal('show');
  }

  $scope.addSubtask = function(subtask) {
    subtaskService.addSubtask(subtask)
      .success(function () {
        getUnassignedSubtasks();
        closeModal('#add');
      })
      .error(function (error) {
        $scope.errorMessage = 'Unable to process your request';
      });
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

    function closeModal(id) {
      clearErrorMessages();
      var element = angular.element(id);
      element.modal('hide');
    }

    function clearErrorMessages() {
      $scope.errorMessage = undefined;
    }

    function getUser() {
      userService.getUser()
        .success(function (dataUser) {
          $scope.users = dataUser;
          
                
        })
        .error(function (error) {
          $scope.status = 'Unable to load customer data: ' + error.message;
        });
    }
  
    getUser();

    $scope.user =  appConstants.getItem('currentUser');

    $scope.isSelectAll = function(){

     if($scope.isAllSelected){
        
        $scope.selectedSubtaskList = angular.copy($scope.unassignedTasks);
      }
      else{
        $scope.selectedSubtaskList = [];
      }
    }

  $scope.isLabelChecked = function(subtask, isSelected, index){

    if (isSelected) {
      $scope.selectedSubtaskList.push(subtask);
    } else {
      $scope.selectedSubtaskList.splice(index, 1);
    }
  } 


   $scope.assignToSprint = function(){

    subtaskService.assignToSprint($scope.selectedSubtaskList, $scope.assignToId)
      .success(function () {
       getUnassignedSubtasks();
      })
      .error(function (error) {
          $scope.status = 'Unable to process your request: ' + error.message;
      });
  }

	$scope.$apply();
		
	}];
});
