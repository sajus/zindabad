'use strict';
define([
	'angular',
], function(angular) {

return angular.module('myApp.directive',['$compile']).directive('tmsAlert', function ($compile) {
	return {
		restrict: 'E',
        replace:true,
        scope: {
            errorMessage: '@',
            type: '@'
        },
        templateUrl: 'components/alerts/tmsalert.html',
        link: function (scope, element, attr) {

            scope.hasErrors = false;

            scope.$watch('errorMessage', function (val) {
                if (val)
                  scope.hasErrors = true;
                else
                  scope.hasErrors = false;
            });
        }
	}

});

});