'use strict';

var employeeService = angular.module('timesheet-app.employees-service', ['ngResource']);

employeeService.factory('Employee', ['$resource', function ($resource) {
    return {
        getAllEmployees: $resource('employees/:pageNumber', {}, {
            query: {method: 'GET', params: {pageNumber: 'pageNumber'}, isArray: false}
        }),
        getEmployeeById: $resource('employee/:id', {}, {
            query: {method: 'GET', params:{id: 'id'}, isArray: false}
        })
    };
}]);
