'use strict';

define([], function() {
	return ['$scope','appConstants','$http','leaveService', function($scope, appConstants, $http, leaveService) {

    function init(){
      $scope.leave = {};
      $scope.closeModal = closeModal;
      $scope.loading = true; 
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
          $scope.status = 'Unable to process your request: ' + error.message;
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

    init();

	$scope.$apply();
		
	}];
});