'use strict';
define([
	'angular',
], function(angular) {

  return angular.module('myApp.directive',['$compile']).directive('modalFooter', function ($compile) {
    return {  
      restrict: 'E',
      replace:true,
      template:'<div class="modal-footer" ng-transclude></div>',
      transclude: true
    }
  });		
}); 