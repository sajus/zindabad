'use strict';
define([
    'angular',
], function(angular) {

    return angular.module('myApp.factory', []).factory('navMenus', function() {

        return {

            getMenus: function() {
                return [
                    {label: 'DashBoard', key: 'dashboard', accessTo: ['MANAGER', 'LEAD', 'DEVELOPER']},
                    {label: 'Manage', key: 'manage', accessTo: ['MANAGER']},
                    {label: 'Sprint', key: 'sprint', accessTo: ['MANAGER', 'LEAD']},
                    {label: 'Story', key: 'story', accessTo: ['MANAGER','LEAD', 'DEVELOPER']},
                    {label: 'Subtask', key: 'subtask', accessTo: ['LEAD', 'DEVELOPER']},
                    {label: 'Efforts', key: 'efforts', accessTo: ['LEAD', 'DEVELOPER']},
                    {label: 'Leaves', key: 'leave', accessTo: ['LEAD', 'DEVELOPER']},
                    {label: 'CodeReview', key: 'codereview', accessTo: ['LEAD', 'DEVELOPER']}
                ]

            } // IF multiple functions user ','

        }

    });    
});
