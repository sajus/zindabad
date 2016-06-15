'use strict';
define([
	'angular',
], function(angular) {

  return angular.module('myApp.directive',['$compile']).directive('tmsDatepicker', function ($compile) {
    return {
      
      restrict: 'E',
      require: ['ngModel'],
      replace:true,
      template: '<div class="input-group">'     +
                    '<input type="text"  class="form-control" ngModel required>' +
                    '<span class="input-group-addon" ng-click="openPicker();"><i class="glyphicon glyphicon-calendar"></i></span>' +
                    '</div>',
      scope:{
        ngModel: '='
      },   
      link:function(scope, element, attrs){
        var input = element.find('input');
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

        picker.on('changeDate', function (ev) {
             $(this).datepicker('hide');
        });

        element.bind('blur keyup change', function() {
            scope.ngModel = input.val();
            console.info('date-picker event', input.val(), scope.ngModel);
            //picker.hide();
        });
      }
    }
  });		

});    