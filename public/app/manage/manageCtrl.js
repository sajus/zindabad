'use strict';

define([], function() {
	return ['$scope','appConstants','$http','manageService', function($scope, appConstants, $http, manageService) {
	
  	function init(){
      $scope.closeModal = closeModal;
      $scope.user = [];
      $scope.editUserSelection = [];
      $scope.errorMessage = undefined;
      $scope.isAddModalVisible = false;
      $scope.isModuleVisible = false;
      $scope.isEditModalVisible = false;
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

    $scope.showEditModal = function(module) {
      $scope.module = angular.copy(module);
      $scope.isEditModalVisible = true;
    }

    function closeModal() {
      $scope.isAddModalVisible = false;
      $scope.isModuleVisible = false;
      $scope.isEditModalVisible = false;

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
           
  init();

	$scope.$apply();
		
	}];

});
