'use strict';

var employeeServices = angular.module('employeeServices', ['ngResource']);

var taskServices = angular.module('taskServices', ['ngResource']);


employeeServices.factory('Employee', ['$resource', function ($resource) {
    return {
        getAllEmployees: $resource('employees/:pageNumber', {}, {
            query: {method: 'GET', params: {pageNumber: 'pageNumber'}, isArray: false}
        }),
        getEmployeeById: $resource('employee/:id', {}, {
            query: {method: 'GET', params:{id: 'id'}, isArray: false}
        })
    };
}]);


taskServices.factory('Task', ['$resource', function ($resource) {
    return {
        getAllTasks: $resource('tasks/:pageNumber', {}, {
            query: {method: 'GET', params: {pageNumber: 'pageNumber'}, isArray: false}
        }),
        getTaskById: $resource('task/:id', {}, {
            query: {method: 'GET', params:{id: 'id'}, isArray: false}
        })
    };
}]);
