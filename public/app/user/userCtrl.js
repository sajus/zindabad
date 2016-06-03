'use strict';

define([], function() {
	return ['$scope','appConstants','$http','userService', function($scope, appConstants, $http, userService) {
	
	function getUser() {
        userService.getUser()
            .success(function (dataUser) {
             $scope.user = dataUser;
          
                
            })
            .error(function (error) {
                $scope.status = 'Unable to load customer data: ' + error.message;
            });
    }
	
    getUser();


     $scope.editUser = function(u){
    
             $scope.userName = u.userName; 
             $scope.email = u.email;
             $scope.userRole = u.userRole;
    

               
           }
           

    

	$scope.$apply();
		
	}];
});
