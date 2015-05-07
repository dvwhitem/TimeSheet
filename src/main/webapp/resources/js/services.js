'use strict';

var employeeServices = angular.module('employeeServices', ['ngResource']);

employeeServices.factory('Employee', ['$resource', function($resource) {
    return $resource('employees', {}, {
    query: {method: 'GET', params: {}, isArray: true}
    });
}]);
