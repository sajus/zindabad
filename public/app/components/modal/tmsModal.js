'use strict';
define([
	'angular',
], function(angular) {

  return angular.module('myApp.directive',['$compile']).directive('tmsModal', function ($compile) {
    return {
      
      restrict: 'E',
      transclude: true,
      replace:true,
      templateUrl: 'components/modal/tms-modal.html',
      scope:{
        modalVisible:'=', 
        onSown:'&?', 
        onHide:'&'
      },   
      link:function(scope, element, attrs){
          
          $(element).modal({
              show: false, 
              keyboard: attrs.keyboard, 
              backdrop: attrs.backdrop
          });
          
          scope.$watch('modalVisible', function (value) {
              
              if(value == true){
                  $(element).modal('show');
              }else{
                  $(element).modal('hide');
              }
          });
          
          $(element).on('shown.bs.modal', function(){
            scope.$apply(function(){
              scope.$parent[attrs.modalVisible] = true;
            });
          });
          
          $(element).on('shown.bs.modal', function(){
            scope.$apply(function(){
                scope.onSown({});
            });
          });

          $(element).on('hidden.bs.modal', function(){
            scope.$apply(function(){
              scope.$parent[attrs.modalVisible] = false;

            });
          });
          
          $(element).on('hidden.bs.modal', function(){
            scope.$apply(function(){
                scope.onHide({});
            });
          });
      }
    
    }
  });		

});    