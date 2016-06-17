'use strict';
define([
	'angular',
], function(angular) {

  return angular.module('myApp.directive',['$compile', '$timeout']).directive('tmsDatepicker', function ($compile, $timeout) {
    return {
      
      restrict: 'E',
      require: ['ngModel'],
      replace:true,
      template: '<div class="form-group">'+
                    '<input type="text" class="form-control" readonly="readonly">' +
                     '<span class="input-group-addon" ng-click="openPicker();"><i class="glyphicon glyphicon-calendar"></i></span>' +
                    '</div>',
      scope:{
        ngModel: '='
      },   
      link:function(scope, element, attrs){
        var input = element.find('input');
        var span = element.find('span');
        var nowTemp = new Date();
        var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(),0,0,0,0);

        var picker = input.datepicker({
           format: "yyyy-mm-dd",
           todayHighlight:'TRUE',
           autoclose: true,
            onRender: function(date) {
                return date.valueOf() < now.valueOf() ? 'disabled' : '';
            }
        });

        element.bind('blur keyup change click', function() {
          $timeout(function () {
            scope.ngModel = input.val();
          }, 0);
        });

        // span.bind('click',function(){
        //     if(element.is(':focus')){
        //         element.trigger('blur');
        //     } else {
        //         element.trigger('focus');
        //     }
        // });

        element.on('$destroy', function () {
          scope.$destroy();
        });
      }
    }
  });		

});    