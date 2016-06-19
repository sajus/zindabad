'use strict';
define([
	'angular',
], function(angular) {

  return angular.module('myApp.directive',['$compile']).directive('modalHeader', function ($compile) {
    return {  
      restrict: 'E',
      replace:true,
      template:'<div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button><h4 class="modal-title">{{title}}</h4></div>',
      scope: {title:'@'}
    }
  });		
});    