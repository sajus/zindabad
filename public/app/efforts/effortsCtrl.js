'use strict';

define([], function() {
	return ['$scope','appConstants','$http','effortsService', function($scope, appConstants, $http, effortsService) {
  $scope.loading = true;
	
	function getEfforts() {
    $scope.loading = true;
    effortsService.getEfforts()
    .success(function (dataEffort) {
     $scope.efforts = dataEffort; 
     $scope.loading = false;
    })
    .error(function (error) {
      $scope.loading = false;
      $scope.status = 'Unable to process your request: ' + error.message;
    });
  }

  $scope.saveEfforts = function(efforts){
    effortsService.saveEfforts(efforts)
      .success(function () {
        getEfforts();
         
      }).
      error(function (error) {
        $scope.status = 'Unable to insert Sprint: ' + error.message;
      });
  };
  
  getEfforts();

	$scope.$apply();	
	}];
});
