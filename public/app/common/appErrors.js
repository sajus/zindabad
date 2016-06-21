'use strict';
define([
    'angular',
], function(angular) {

    return angular.module('myApp.factory', []).factory('appErrors', function() {

         var self = {};

            self.getErrorMessages = function() {
                return [
                    {code: 'TMS-0001', message: 'Unable to create'},
                    {code: 'TMS-0002', message: 'Unable to update'},
                    {code: 'TMS-0003', message: 'Unable to delete'},
                    {code: 'TMS-0004', message: 'Unable to close'}
                ]

            } ,

            self.getErrorMessage = function(errorCode) {
                var found = _.findWhere(self.getErrorMessages(), {code: errorCode});
                if (found) {
                    return found.message;
                }
            }
         return self;

    });
});