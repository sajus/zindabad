'use strict';
define([
    'angular',
], function(angular) {

    return angular.module('myApp.factory', []).factory('navMenus', function() {

        return {

            getMenus: function() {
                return [
                    {label: 'Home', key: 'HOME', accessTo: ['MANAGER', 'LEAD']},
                    {label: 'DashBoard', key: 'DASHBOARD', accessTo: ['LEAD', 'DEVELOPER']},
                    {label: 'Sprint', key: 'SPRINT', accessTo: ['MANAGER, LEAD']},
                    {label: 'Story', key: 'STORY', accessTo: ['LEAD', 'DEVELOPER']},
                    {label: 'Subtask', key: 'SUBTASK', accessTo: ['LEAD', 'DEVELOPER']},
                    {label: 'Efforts', key: 'EFFORTS', accessTo: ['LEAD', 'DEVELOPER']},
                    {label: 'Leaves', key: 'LEAVES', accessTo: ['LEAD', 'DEVELOPER']},
                ]

            } // IF multiple functions user ','

        }

    });    
});
