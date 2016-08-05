'use strict';

define([], function() {
    return ['$scope','appConstants','$http','sprintService', 'appErrors', function($scope, appConstants, $http, sprintService, appErrors) {

       function init() {
        $scope.closeModal = closeModal;
        $scope.isAddModalVisible = false;
        $scope.isEditModalVisible = false;
        $scope.errorMessage = undefined;
        $scope.addButton = false;
        $scope.sprintStatus = appConstants.getSprintStatus();
        getSprints();
      };
      
      function getSprints() {
        $scope.loading = true;
        sprintService.getSprint()
          .success(function (dataSprint) {
            for(var i=0; i<dataSprint.length; i++){
              if(dataSprint[i].sprintStatus === "OPEN"){
                $scope.addButton = true;
                break;
              }
            }
            var array = _.sortBy(dataSprint, function(sprint) {
              return sprint.sprintStartDate;
          });
           $scope.sprints = array.reverse();
           $scope.loading = false;
          })
          .error(function (error) {
              $scope.status = 'Unable to load customer data: ' + error.message;
              $scope.loading = false;
          });
      }

      $scope.showAddModal = function() {
        $scope.sprint = {};
        $scope.isAddModalVisible = true;
      }
      
      $scope.showEditModal = function(sprint) {
        $scope.editSprint = angular.copy(sprint);
        $scope.isEditModalVisible = true;
      }

      function closeModal() {
        clearErrorMessages();
        $scope.isAddModalVisible = false;
        $scope.isEditModalVisible = false;
      }

      $scope.updateSprint = function(sprint){
        sprintService.updateSprint(sprint)
          .success(function () {
              closeModal();
              init();
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

      init();
      $scope.$apply();
      
  }];
});



