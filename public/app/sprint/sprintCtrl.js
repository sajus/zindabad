'use strict';

define([], function() {
	return ['$scope','appConstants','$http','sprintService', function($scope, appConstants, $http, sprintService) {
	
	function getSprint() {
        sprintService.getSprint()
            .success(function (dataSprint) {
             $scope.sprint = _.filter(dataSprint, function(sprint){ return sprint.sprintStatus !== 'CLOSED'});
          
                
            })
            .error(function (error) {
                $scope.status = 'Unable to load customer data: ' + error.message;
            });
    }
	
    getSprint();


    $scope.editSprint = function(s){
    
             $scope.sprintName = s.sprintName; 
             $scope.sprintStartDate = s.sprintStartDate;
             $scope.sprintEndDate = s.sprintEndDate;
             $scope.sprintHours = s.sprintHours;
             $scope.sprintVelocity = s.sprintVelocity;  

               
            }
           
     $scope.addSprint = function(){
    
        sprintService.addSprint()
             $scope.sprintName = s.sprintName; 
             $scope.sprintStartDate = s.sprintStartDate;
             $scope.sprintEndDate = s.sprintEndDate;
             $scope.sprintHours = s.sprintHours;
             $scope.sprintVelocity = s.sprintVelocity;  

               
            }
    

	$scope.$apply();
		
	}];
});



    