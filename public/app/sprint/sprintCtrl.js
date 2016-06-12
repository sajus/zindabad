'use strict';

define([], function() {
    return ['$scope','appConstants','$http','sprintService', 'appErrors', function($scope, appConstants, $http, sprintService, appErrors) {


    $scope.closeModal = closeModal;
    $scope.sprint = {};

    function getSprints() {
      $scope.loading = true;
      sprintService.getSprint()
        .success(function (dataSprint) {
        var array = _.sortBy(dataSprint, function(sprint) {
            return sprint.sprintName;
        });
         $scope.sprints = array.reverse();//_.filter(dataSprint, function(sprint){ return sprint.sprintStatus !== 'CLOSED'});
         $scope.loading = false;
        })
        .error(function (error) {
            $scope.status = 'Unable to load customer data: ' + error.message;
            $scope.loading = false;
        });
    }

    $scope.updateSprint = function(sprint){
      sprintService.updateSprint(sprint)
        .success(function () {
            getSprints();
            closeModal('#edit');
        }).
        error(function (error) {
            processErrorMessage(error);
            $scope.status = 'Unable to insert Sprint: ' + error.message;
        });
    }

    $scope.addSprint = function(sprint){
      sprintService.addSprint(sprint)
        .success(function () {
           getSprints();
           closeModal('#add');
        }).
        error(function (error) {
            $scope.status = 'Unable to insert Sprint: ' + error.message;
        });
    };

    $scope.showModal = function(id, sprint) {
      $scope.sprint = angular.copy(sprint) || {};
      var element = angular.element(id);
      element.modal('show');
    }

    function closeModal(id) {
      clearErrorMessages();
      var element = angular.element(id);
      element.modal('hide');
    }

    function processErrorMessage(error) {
      $scope.error = appErrors.getErrorMessage(error.message);
      $scope.errorMessage = "Please close below user stories";
      $scope.errorDetails =  error.fieldErrors.toString();
    }

    function clearErrorMessages() {
      $scope.error = undefined;
      $scope.errorMessage = undefined;
      $scope.errorDetails = undefined;
    }

    getSprints();

    $scope.$apply();


    }];
});



