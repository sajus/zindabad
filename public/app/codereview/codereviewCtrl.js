'use strict';

define([], function() {
	return ['$scope','appConstants','$http','codereviewService','userService', function($scope, appConstants, $http, codereviewService, userService) {

  	function init() {
      $scope.closeModal = closeModal;
      $scope.isModalVisible = false;
      $scope.errorMessage = undefined;
      getReviews();
    };

    $scope.showModal = function(review) {
      getUser();
      $scope.review = angular.copy(review) || {};
      $scope.isModalVisible = true;
    }
  
   
    function closeModal() {
      clearErrorMessages();
      $scope.isModalVisible = false;
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

    function getReviews() {
      codereviewService.getReviews()
      .success(function (dataReview) {
       $scope.reviews = dataReview; 
      })
      .error(function (error) {
          $scope.status = 'Unable to process your request: ' + error.message;
      });
    }

    $scope.editReview = function(review){
      codereviewService.editReview(review)
        .success(function () {
            getReviews();
            closeModal();
        }).
        error(function (error) {
            $scope.status = 'Unable to edit Review: ' + error.message;
        });
    }
    
    init();

  $scope.$apply();  
  }];
});
