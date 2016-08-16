'use strict';

define([], function() {
	return ['$scope', '$rootScope', 'subtaskService', 'manageService', 'statusService', 'appConstants', 'appErrors', '$location', '$timeout', function($scope, $rootScope, subtaskService, manageService, statusService, appConstants, appErrors, $location, $timeout) {

    function init() {
      $scope.closeModal = closeModal;
      $scope.closeCodeReviewModal = closeCodeReviewModal;
      $scope.isCodeReviewModalVisible = false;
      $scope.isAddModalVisible = false;
      $scope.isEditModalVisible = false;
      $scope.errorMessage = undefined;
      $scope.changeTab('ASSIGNED');
      $scope.selectedSubtaskList = [];
      $scope.subtaskSelection = [];
      $scope.editStorySelection = [];
      $scope.isAllSelected = false;
      $scope.options = appConstants.getList();
      $scope.reviewer = appConstants.getReviewerType();
      $scope.scopes = appConstants.getScope();
      $scope.types = appConstants.getSubtaskType();
      $scope.currentUser = appConstants.user;
      $scope.assignToId = ($scope.currentUser.userRole !== 'LEAD') ? $scope.currentUser.id : undefined;
      $scope.getStories();
      getUser();
      getStatus();
    };

    $scope.changeTab = function (currentTab) {
      currentTab === 'UNASSIGNED' ? getUnassignedSubtasks() : getSubtasks();
      $scope.selectedTab = currentTab;
    };

    $scope.showAddModal = function() {
      $scope.subtask = {};
      $scope.isAddModalVisible = true;
    }

    $scope.showEditModal = function(subtask) {
      $scope.subtask = angular.copy(subtask);
      $scope.isEditModalVisible = true;
    }

    $scope.showCodeReviewModal = function(subtask) {
      $scope.codeReview = {};
      $scope.codeReview.jiraId = subtask.jiraId;
      $scope.isCodeReviewModalVisible = true;
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
      $scope.isAddModalVisible = false;
      $scope.isEditModalVisible = false;
    }

    function closeCodeReviewModal() {
      clearErrorMessages();
      $scope.isCodeReviewModalVisible = false;
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
      manageService.getUser()
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

    function getStatus() {
      statusService.getStatus()
        .success(function (dataStatus) {
          $scope.statuslist = dataStatus;
        })
        .error(function (error) {
          $scope.status = 'Unable to load customer data: ' + error.message;
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
        $scope.assignToId = '';
        $scope.isAllSelected = false;
        $scope.selectedSubtaskList = [];
        selectAllItems();
        })
        .error(function (error) {
          $scope.status = 'Unable to process your request: ' + error.message;
        });
    }

     $scope.addReview = function(review){
      review.developerId= appConstants.user.id;
      subtaskService.addReview(review)
        .success(function () {
          closeCodeReviewModal();
        }).
        error(function (error) {
          processErrorMessage(error);
          $scope.status = 'Unable to insert Sprint: ' + error.message;
        });
    };

    $scope.editSubtask = function(subtask){
      subtaskService.editSubtask(subtask)
        .success(function () {
        getUnassignedSubtasks();
        closeModal();
        })
        .error(function (error) {
          $scope.status = 'Unable to process your request: ' + error.message;
        });
    }

    function processErrorMessage(error) {
      $scope.error = appErrors.getErrorMessage(error.message);
      $scope.errorMessage = "Check review date and fixed date.";  
      $scope.errorDetails =  error.fieldErrors.toString();   
   }

    init();
    $scope.$apply();
    
  }];
});
