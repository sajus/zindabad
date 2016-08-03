'use strict';

define([], function() {
	return ['$scope','appConstants','manageService', function($scope, appConstants, manageService) {
	
  	function init(){
      $scope.closeModal = closeModal;
      $scope.user = [];
      $scope.editUserSelection = [];
      $scope.errorMessage = undefined;
      $scope.isAddModalVisible = false;
      $scope.isModuleVisible = false;
      $scope.isTaskModuleVisible = false;
      $scope.isEditModalVisible = false;
      $scope.isEditTaskModalVisible = false;
      $scope.availableOptions = appConstants.getStatus();
      $scope.Options = appConstants.getRole();
      $scope.changeTab('USERS');
    };

    function getUser() {
      manageService.getUser()
        .success(function (dataUser) {
          $scope.users = dataUser;
        })
        .error(function (error) {
          $scope.status = 'Unable to load customer data: ' + error.message;
        });
    }
	
    getUser();

    $scope.changeTab = function (currentTab) {
      currentTab === 'MODULE' ? getUser() : getUser();
      $scope.selectedTab = currentTab;
    };

    $scope.showAddModal = function() {
      $scope.user = {};
      $scope.isAddModalVisible = true;
    }

    $scope.showModule = function() {
      $scope.module = {};
      $scope.isModuleVisible = true;
    }

    $scope.showTaskModule = function() {
      $scope.task = {};
      $scope.isTaskModuleVisible = true;
    }

    $scope.showEditModal = function(module) {
      $scope.module = angular.copy(module);
      $scope.isEditModalVisible = true;
    }

    $scope.showEditTaskModal = function(task) {
      $scope.task = angular.copy(task);
      $scope.isEditTaskModalVisible = true;
    }

    function closeModal() {
      $scope.isAddModalVisible = false;
      $scope.isModuleVisible = false;
      $scope.isTaskModuleVisible = false;
      $scope.isEditModalVisible = false;
      $scope.isEditTaskModalVisible = false;
    }

    $scope.saveUserStatus = function(user, index) {
      $scope.editUserSelection[index] = false;
      var userValues = {
        userRole : user.userRole,
        isActive : user.isActive,
        id : user.id
      }

      manageService.saveUserStatus(userValues)
        .success(function () {
         getUser();
        })
        .error(function (error) {
          $scope.errorMessage = 'Unable to process your request';
        });
    }

    $scope.addUser = function(user) {
      manageService.addUser(user)
      .success(function () {
        getUser();
        closeModal();
      })
      .error(function (error) {
        $scope.errorMessage = 'Unable to process your request';
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

    getModule();

    $scope.addModule = function(module) {
      manageService.addModule(module)
      .success(function () {
        getModule();
        closeModal();
      })
      .error(function (error) {
        $scope.errorMessage = 'Unable to process your request';
      });
    }

    $scope.editModule = function(module){
      manageService.editModule(module)
        .success(function () {
            getModule();
            closeModal();
        }).
        error(function (error) {
            $scope.status = 'Unable to edit module: ' + error.message;
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

    getTaskType();

    $scope.addTaskType = function(task) {
      manageService.addTaskType(task)
      .success(function () {
        getTaskType();
        closeModal();
      })
      .error(function (error) {
        $scope.errorMessage = 'Unable to process your request';
      });
    }

    $scope.editTaskType = function(task){
      manageService.editTaskType(task)
        .success(function () {
            getTaskType();
            closeModal();
        }).
        error(function (error) {
            $scope.status = 'Unable to edit tasks: ' + error.message;
        });
    }
           
  init();

	$scope.$apply();
		
	}];

});
