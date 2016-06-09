'use strict';

define([], function() {
    return ['$scope','appConstants','$http','sprintService', function($scope, appConstants, $http, sprintService) {
  
    function getSprint() {

      $scope.loading = true;

        sprintService.getSprint()
            .success(function (dataSprint) {
             $scope.sprints = dataSprint;//_.filter(dataSprint, function(sprint){ return sprint.sprintStatus !== 'CLOSED'});
             $scope.loading = false;     
            })
            .error(function (error) {
                $scope.status = 'Unable to load customer data: ' + error.message;
                $scope.loading = false;
            });
    }
    
    getSprint();


    $scope.editSprint = function(sprint){
       $scope.sprint = sprint;

    }

    $scope.updateSprint = function(sprint){
        sprintService.updateSprint(sprint)
            .success(function () {
                getSprint();
            }).
            error(function (error) {
                $scope.status = 'Unable to insert Sprint: ' + error.message;
            });

        
    }

    $scope.addSprint = function(newSprint){

        sprintService.addSprint(newSprint)
            .success(function () {
                $scope.sprints.push(newSprint);
            }).
            error(function (error) {
                $scope.status = 'Unable to insert Sprint: ' + error.message;
            });

             $scope.newSprint = '';
       };


    $scope.$apply();

        
    }];
});



    