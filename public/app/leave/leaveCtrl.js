'use strict';

define([], function() {
	return ['$scope','appConstants','$http','leaveService', function($scope, appConstants, $http, leaveService) {

	$scope.newLeave = {};
	$scope.closeModal = closeModal;
    $scope.loading = true;

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

    $scope.addLeave = function(newLeave) {
      leaveService.addLeave(newLeave)
        .success(function() {
          getLeaves();
          closeModal('#add');
        })
        .error(function(error) {
          $scope.status = 'Unable to process your request: ' + error.message;
        });

    }

    $scope.updateLeave = function(leave) {
      leaveService.updateLeave(leave)
        .success(function() {
          getLeaves();
          closeModal('#edit');
        })
        .error(function(error) {
          $scope.status = 'Unable to process your request: ' + error.message;
        });

    }

    $scope.deleteLeave = function(leaveId) {
      leaveService.deleteLeave(leaveId)
        .success(function() {
            getLeaves();
            closeModal('#delete');
        })
        .error(function(error) {
           closeModal('#delete');
           $scope.status = 'Unable to process your request';
        });

    }

    $scope.showModal = function(id, leave) {
        $scope.hasErrors = false;
        $scope.leave = leave || {};
        var element = angular.element(document.querySelectorAll(id));
        element.modal('show');
    }

    $scope.closeAlert = function() {
        angular.element('.alert-error').alert('hide')
    }

    function closeModal(id) {
        var element = angular.element(document.querySelectorAll(id));
        element.modal('hide');
    }

    getLeaves();

	$scope.$apply();
		
	}];
});