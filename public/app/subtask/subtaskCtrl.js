'use strict';

define([], function() {
	return ['$scope', '$rootScope', 'subtaskService', 'userService', 'appConstants', '$location', '$timeout', function($scope, $rootScope, subtaskService, userService, appConstants, $location, $timeout) {

    function init() {
      $scope.closeModal = closeModal;
      $scope.isModalVisible = false;
      $scope.errorMessage = undefined;
      $scope.changeTab('ASSIGNED');
      $scope.selectedSubtaskList = [];
      $scope.subtaskSelection = [];
      $scope.editStorySelection = [];
      $scope.isAllSelected = false;
      $scope.availableOptions = appConstants.getStatusList();
      $scope.currentUser = appConstants.user;
      $scope.assignToId = ($scope.currentUser.userRole !== 'LEAD') ? $scope.currentUser.id : undefined;
      $scope.getStories();
      getUser();
    };

    $scope.changeTab = function (currentTab) {
      currentTab === 'UNASSIGNED' ? getUnassignedSubtasks() : getSubtasks();
      $scope.selectedTab = currentTab;
    };

    $scope.showModal = function(subtask) {
      $scope.subtask = angular.copy(subtask) || {};
      $scope.isModalVisible = true;
    }

    $scope.checkAll = function(){
      if($scope.isAllSelected){
        $scope.selectedSubtaskList = angular.copy($scope.unassignedTasks);
      }
      else{
        $scope.selectedSubtaskList = [];
      }
      selectAllItems();
    }

    $scope.onItemSelected = function(subtask, isSelected, index){
      if (isSelected) {
        $scope.selectedSubtaskList.push(subtask);
      } else {
        $scope.selectedSubtaskList.splice(index, 1);
      }
    }

    function closeModal() {
      clearErrorMessages();
      $scope.isModalVisible = false;
    }

    function clearErrorMessages() {
      $scope.errorMessage = undefined;
    }

    function selectAllItems() {
      _.each($scope.unassignedTasks ,function(subtask, $index) {
        $scope.subtaskSelection[$index] = $scope.isAllSelected;
      });
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

    $scope.saveSubtaskStatus = function(subtask, index) {
      $scope.editStorySelection[index] = false;
      var subtaskValues = {
        subtaskId : subtask.subtaskId,
        status : subtask.userStoryStatus.status,
        projectId : appConstants.user.projectId,
        userId : appConstants.user.id
      }

      subtaskService.saveSubtaskStatus(subtaskValues)
        .success(function () {
         getSubtasks();
        })
        .error(function (error) {
          $scope.errorMessage = 'Unable to process your request';
        });
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

    init();
    $scope.$apply();
  }];
});
