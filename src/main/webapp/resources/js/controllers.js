'use strict';

var employeeControllers = angular.module('employeeControllers', []);

employeeControllers.controller('EmployeeController', ['$scope', 'Employee', function($scope, Employee) {
    $scope.employees = Employee.query();
}]);