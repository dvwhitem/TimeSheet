'use strict';

var employeeControllers = angular.module('employeeControllers', []);
var taskControllers = angular.module('taskControllers', []);

employeeControllers.controller('EmployeeListController', ['$scope', 'Employee',
    function ($scope, Employee) {
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
        $scope.getAllRecords = function (pageNumber) {
            $scope.data = Employee.getAllEmployees.query({pageNumber: pageNumber}, function (employee) {
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

employeeControllers.controller('EmployeeDetailController', ['$scope', '$routeParams', 'Employee',
    function($scope, $routeParams, Employee) {
        $scope.employee = Employee.getEmployeeById.get({id: $routeParams.id});
    }
]);





// Task Controller start

taskControllers.controller('TaskListController', ['$scope', 'Task',
    function ($scope, Task) {
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
        $scope.getAllRecords = function (pageNumber) {
            $scope.data = Task.getAllEmployees.query({pageNumber: pageNumber}, function (task) {
                $scope.records = task.content;
                $scope.totalPages = task.totalPages;
                $scope.currentPage = task.number + 1;
                $scope.totalRecords = task.totalElements;

                var pages = [];
                for (var i = 1; i <= task.totalPages; i++) {
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


    }]);


taskControllers.controller('TaskDetailController', ['$scope', '$routeParams', 'Task',
    function($scope, $routeParams, Task) {
        $scope.employee = Task.getEmployeeById.get({id: $routeParams.id});
    }
]);