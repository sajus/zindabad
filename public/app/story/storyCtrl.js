'use strict';

define([], function() {
  return ['$scope', '$rootScope', 'storyService', 'manageService', 'statusService', 'subtaskService', 'appErrors','appConstants', '$location', function($scope, $rootScope, storyService, manageService, subtaskService, statusService, appConstants, appErrors, $location) {

    function init() {
      $scope.closeModal = closeModal;
      $scope.story = {};
      $scope.errorMessage = undefined;
      $scope.changeTab('CURRENT');
      $scope.selectedStoryList = [];
      $scope.storySelection = [];
      $scope.editStorySelection = [];
      $scope.isAllSelected = false;
      $scope.currentUser = appConstants.user;
      $scope.assignToId = ($scope.currentUser.userRole !== 'LEAD') ? $scope.currentUser.id : undefined;
      getUser();
      getModule();
      getTaskType();
      getStatus();
      getAllCurrentUserStoriesBySprint();
      $scope.expanded= true;
    };

    $scope.changeTab = function (currentTab) {
      currentTab === 'BACKLOG' ? getBackLogStories() : getStories();
      $scope.selectedTab = currentTab;
    };

    $scope.showAddModal = function() {
      $scope.story = {};
      $scope.isAddModalVisible = true;
    }

    $scope.showEditModal = function(story) {
      $scope.story = angular.copy(story);
      $scope.isEditModalVisible = true;
    }

    $scope.checkAll = function(){
      if($scope.isAllSelected){
        $scope.selectedStoryList = angular.copy($scope.backlogtories);
      }
      else{
        $scope.selectedStoryList = [];
      }
      selectAllItems();
    }

    $scope.onItemSelected = function(story, isSelected, index){
      if (isSelected) {
        $scope.selectedStoryList.push(story);
      }
      else {
        $scope.selectedStoryList.splice(index, 1);
      }
    }

    $scope.addStory = function(story) {
      storyService.addStory(story)
        .success(function () {
          getBackLogStories();
          closeModal();
        })
        .error(function (error) {
          processErrorMessage(error);
          $scope.status = 'Unable to insert Sprint: ' + error.message;
        });
    }

    function processErrorMessage(error) {
      $scope.error = appErrors.getErrorMessage(error.message);
      $scope.errorMessage = "The Jira Id is already present";
      $scope.errorDetails =  error.fieldErrors.toString();
    }

    function selectAllItems() {
      _.each($scope.backlogtories ,function(story, $index) {
        $scope.storySelection[$index] = $scope.isAllSelected;
      });
    }

    function closeModal() {
      clearErrorMessages();
      $scope.isAddModalVisible = false;
      $scope.isEditModalVisible = false;
      $scope.error = false;
    }

    function getStories() {
      $scope.loading = true;
      storyService.getStories()
        .success(function (dataStories) {
         $scope.stories = dataStories;
         $scope.loading = false;
        })
        .error(function (error) {
            $scope.loading = false;
            $scope.status = 'Unable to process your request: ' + error.message;
        });
    }

    function getSubtaskByStory(story) {
      subtaskService.getSubtaskByStory(story)
        .success(function (dataSubtask) {
          $scope.subtasks = dataSubtask;
        })
        .error(function (error) {
          $scope.status = 'Unable to process your request: ' + error.message;
          $scope.loading = false;
        });
    }

    function getAllCurrentUserStoriesBySprint() {
      $scope.loading = true;
      storyService.getAllCurrentUserStoriesBySprint()
        .success(function (allstories) {
         $scope.allstories = allstories;
         $scope.loading = false;
        })
        .error(function (error) {
            $scope.loading = false;
            $scope.status = 'Unable to process your request: ' + error.message;
        });
    }

    function getBackLogStories() {
      $scope.loading = true;
      storyService.getBackLogStories()
        .success(function (backlogtories) {
         $scope.backlogtories = backlogtories;
         $scope.loading = false;
        })
        .error(function (error) {
          $scope.loading = false;
          $scope.status = 'Unable to process your request: ' + error.message;
        });
    }

    function clearErrorMessages() {
      $scope.errorMessage = undefined;
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

    function getModule() {
      manageService.getModule()
        .success(function (dataModule) {
          $scope.modules = dataModule;
        })
        .error(function (error) {
          $scope.status = 'Unable to load customer data: ' + error.message;
        });
    }

    function getTaskType() {
      manageService.getTaskType()
        .success(function (dataTaskType) {
          $scope.tasktypes = dataTaskType;
      })
      .error(function (error) {
        $scope.status = 'Unable to load customer data: ' + error.message;
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

    $scope.assignToSprint = function(){
      storyService.assignToSprint($scope.selectedStoryList, $scope.assignToId)
        .success(function () {
         getBackLogStories();
         $scope.assignToId = '';
         $scope.isAllSelected = false;
         $scope.selectedStoryList = [];
         selectAllItems();
        })
        .error(function (error) {
            $scope.status = 'Unable to process your request: ' + error.message;
        });
    }

    $scope.saveStoryStatus = function(story, index) {
      $scope.editStorySelection[index] = false;
      var storyValues = {
        storyId : story.storyId,
        status : story.userStoryStatus.status,
        projectId : appConstants.user.projectId,
        userId : appConstants.user.id
      }

      storyService.saveStoryStatus(storyValues)
        .success(function () {
         getStories();
        })
        .error(function (error) {
          $scope.errorMessage = 'Unable to process your request';
        });
    }

    $scope.editStory = function(story){
      storyService.editStory(story)
        .success(function () {
         getBackLogStories();
         closeModal();
        })
        .error(function (error) {
            $scope.status = 'Unable to process your request: ' + error.message;
        });
    }

    $scope.showDetails=function(story,$index){
      getSubtaskByStory(story);
      $scope.expanded= false;
      $scope.selected_story=$index;
    }

    $scope.hideDetails = function($index){
      $scope.selected_user=undefined;
    }

    $scope.isSelected=function($index){
      return $scope.selected_user===$index;
      $scope.expanded= false;
    }

    init();
    $scope.$apply();

   }];

 });   
    