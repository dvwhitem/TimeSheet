'use strict';

angular.module('timesheet-app', [
    'ui.router',
    'timesheet-app.login',
    'timesheet-app.login-service',
    'timesheet-app.home',
    'timesheet-app.employees',
    'timesheet-app.employees-service'
])
.config(['$urlRouterProvider', function ($urlRouterProvider) {
        $urlRouterProvider.otherwise('/home');
    }]);