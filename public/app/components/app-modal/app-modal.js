'use strict';
define([
	'angular',
], function(angular) {

  return angular.module('myApp.directive',['$compile', '$timeout', '$uibModal']).directive('tmsDatepicker', function ($compile, $timeout, $uibModal) {
    return {
      
      restrict: 'E',
      require: ['ngModel'],
      replace:true,
      template: '<div class="form-groupform-group">'     +
                    '<input type="text"  class="form-control" readonly="readonly">' +
                    // '<span class="input-group-addon" ng-click="openPicker();"><i class="glyphicon glyphicon-calendar"></i></span>' +
                    '</div>',
      scope:{
        ngModel: '='
      },   
      link:function(scope, element, attrs){
        
      }
    }
  });		

});    