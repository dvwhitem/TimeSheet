'use strict';

var loginService = angular.module('timesheet-app.login-service', ['ngResource']);

loginService.factory('Auth', ['$resource', function($resource) {
    return {
        login: $resource('login', {}, {
            charge: {
                method:'POST', params:{charge:true}}
        })
    };
}]);