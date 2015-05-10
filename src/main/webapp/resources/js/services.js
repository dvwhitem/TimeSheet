'use strict';

var employeeServices = angular.module('employeeServices', ['ngResource']);

employeeServices.factory('Employee', ['$resource', function ($resource) {
    return $resource('employees/:pageNumber', {}, {
        query: {method: 'GET', params: {pageNumber: 'pageNumber'}, isArray: false}
    });
}]);
