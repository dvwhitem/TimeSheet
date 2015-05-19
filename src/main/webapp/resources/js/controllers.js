'use strict';

var controllers = angular.module('controllers', []);

controllers.controller('WelcomeController', ['$scope', function ($scope) {
    $scope.welcome = 'Welcome to Timesheet';
}]);

controllers.controller('EmployeeListController', ['$scope', '$routeParams', 'Employee',
    function ($scope, $routeParams, Employee) {

        if(!$routeParams.pageNumber) $routeParams.pageNumber = 1;

        /* page properties */
        $scope.records = [];
        $scope.totalPages = 0;
        $scope.currentPage = 1;
        $scope.range = [];
        $scope.totalRecords = 0;
        $scope.data = {};

        /**
         * Get all records with arguments
         * @param pageNumber
         */
        $scope.getAllRecords = function () {
            $scope.data = Employee.getAllEmployees.query({pageNumber: $routeParams.pageNumber}, function (employee) {
                $scope.records = employee.content;
                $scope.totalPages = employee.totalPages;
                $scope.currentPage = employee.number + 1;
                $scope.totalRecords = employee.totalElements;

                var pages = [];
                for (var i = 1; i <= employee.totalPages; i++) {
                    pages.push(i);
                }
                $scope.range = pages;
            });
        };
        /* if don't there previous element return start page */
        $scope.noPrevious = function () {
            return $scope.currentPage == 1;
        };
        /* if there previous element return it */
        $scope.selectPrevious = function () {
            if (!$scope.noPrevious()) {
                $scope.getAllRecords($scope.currentPage - 1);
            }
        };
        /* if don't there next element return start page */
        $scope.noNext = function () {
            return $scope.currentPage == $scope.totalPages;
        };
        /* if there next element return it */
        $scope.selectNext = function () {
            if (!$scope.noNext()) {
                $scope.getAllRecords($scope.currentPage + 1);
            }
        }
        /* return is active page */
        $scope.isActive = function (page) {
            return $scope.currentPage === page;

        };
        /* get default all records */
        $scope.getAllRecords(1);
        $scope.initialized = true;

    }]);

controllers.controller('EmployeeDetailController', ['$scope', '$routeParams', 'Employee',
    function($scope, $routeParams, Employee) {
        $scope.employee = Employee.getEmployeeById.get({id: $routeParams.id});
    }
]);