'use strict';

define([], function() {
	return ['$scope','appConstants','$http','userService', function($scope, appConstants, $http, userService) {
	
	function init(){
        $scope.closeModal = closeModal;
      $scope.user = [];
        $scope.editUserSelection = [];
        $scope.errorMessage = undefined;
        $scope.isAddModalVisible = false;
        $scope.isEditModalVisible = false;
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

    
    $scope.showAddModal = function() {
      $scope.user = {};
      $scope.isAddModalVisible = true;
    }

    $scope.showEditModal = function(user) {
      $scope.user = angular.copy(user);
      $scope.isEditModalVisible = true;
    }

    function closeModal() {
      $scope.isAddModalVisible = false;
      $scope.isEditModalVisible = false;
    }

     $scope.saveUserStatus = function(user, index) {
      $scope.editUserSelection[index] = false;
      var userValues = {
        userRole : user.userRole,
        isActive : user.isActive,
        id : user.id
      }

      userService.saveUserStatus(userValues)
        .success(function () {
         getUser();
        })
        .error(function (error) {
          $scope.errorMessage = 'Unable to process your request';
        });
    }

    $scope.addUser = function(user) {
        userService.addUser(user)
        .success(function () {
          getUser();
          closeModal();
        })
        .error(function (error) {
          $scope.errorMessage = 'Unable to process your request';
        });
    }
           

    init();

	$scope.$apply();
		
	}];
});
