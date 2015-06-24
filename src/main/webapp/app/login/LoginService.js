'use strict';

var loginService = angular.module('timesheet-app.login-service', ['ngResource']);

loginService.factory('Auth', ['$resource', function($resource) {
    return {
        login: $resource('login', {}, {
            method:'POST', params:{charge:true}
        }),
        user: $resource('user', {}, {
            query: { method: "GET", isArray: false}
        })
    };
}]);