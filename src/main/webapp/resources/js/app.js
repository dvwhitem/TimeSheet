angular
    .module('app', ['ngResource'])
    .service('EmployeeService', function ($resource) {
        return {
            showEmployees: function () {
                var employeeResource = $resource('employees', {}, {
                    query: {method: 'GET', params: {}, isArray: true}
                });
                return employeeResource.query();
            }
        }
    })
    .controller('EmployeeController', function ($scope, $http, EmployeeService) {
        $scope.employees = EmployeeService.showEmployees();
    });