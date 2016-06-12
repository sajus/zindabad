'use strict';
define([
	'angular',
], function(angular) {

return angular.module('myApp.directive',['$location', '$http', '$compile']).directive('appLoader', function ($location, $http, $compile) {
	return {
		restrict: 'E',
        replace:true,
        templateUrl: 'components/loader/loader.html',
        link: function (scope, element, attr) {
              scope.$watch('loading', function (val) {
                  if (val)
                      $(element).show();
                  else
                      $(element).hide();
              });
        }
	}
	
});		

});    