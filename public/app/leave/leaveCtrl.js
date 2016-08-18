'use strict';

define([], function() {
	return ['$scope','appConstants','$http','leaveService','appErrors', function($scope, appConstants, $http, leaveService,appErrors) {

    function init(){
      $scope.leave = {};
      $scope.closeModal = closeModal;
      $scope.loading = true; 
      $scope.errorMessage = undefined;
      getLeaves();  
    }

    $scope.showAddModal = function() {
      $scope.leave = {};
      $scope.isAddModalVisible = true;
    }

    $scope.showEditModal = function(leave) {
      $scope.leave = angular.copy(leave);
      $scope.isEditModalVisible = true;
    }

    $scope.showDeleteModal = function(leave) {
      $scope.leave = angular.copy(leave);
      $scope.isDeleteModalVisible = true;
    }

    function closeModal() {
      $scope.isAddModalVisible = false;
      $scope.isEditModalVisible = false;
      $scope.isDeleteModalVisible = false;
    }

  	function getLeaves() {
      $scope.loading = true;        
      leaveService.getLeaves()
          .success(function (dataLeave) {
           $scope.leaves = dataLeave; 
           $scope.loading = false;
          })
          .error(function (error) {
            $scope.loading = false;
            $scope.status = 'Unable to process your request: ' + error.message;
          });
      }

    $scope.addLeave = function(leave) {
      leaveService.addLeave(leave)
        .success(function() {
          getLeaves();
          closeModal();
        })
        .error(function(error) {
           processErrorMessage(error);
            $scope.status = 'Unable to insert Sprint: ' + error.message;
        });

    }

    $scope.editLeave = function(leave) {
      leaveService.editLeave(leave)
        .success(function() {
          getLeaves();
          closeModal();
        })
        .error(function(error) {
          $scope.status = 'Unable to process your request: ' + error.message;
        });

    }

    $scope.deleteLeave = function(leaveId) {
      leaveService.deleteLeave(leaveId)
        .success(function() {
            getLeaves();
            closeModal();
        })
        .error(function(error) {
           closeModal();
           $scope.status = 'Unable to process your request';
        });

    }

    function processErrorMessage(error) {
        $scope.error = appErrors.getErrorMessage(error.message);
        $scope.errorMessage = "Please enter valid start date and end date. End date greater than start date!";   
      }

      function clearErrorMessages() {
        $scope.error = undefined;
        $scope.errorMessage = undefined;  
      }

    init();

	$scope.$apply();
		
	}];
});