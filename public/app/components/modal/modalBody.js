'use strict';
define([
	'angular',
], function(angular) {

  return angular.module('myApp.directive',['$compile']).directive('modalBody', function ($compile) {
    return {  
      restrict: 'E',
      replace:true,
      template:'<div class="modal-body" ng-transclude></div>',
      transclude: true
    }
  });		
});    