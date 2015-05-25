'use strict';


var employeecontrollers = angular.module('employeeControllers', []);
var taskcontrollers = angular.module('taskControllers', []);
var timesheetcontrollers = angular.module('timesheetControllers', []);

employeecontrollers.controller('WelcomeController', ['$scope', function ($scope) {
    $scope.welcome = 'Стартовая страница';
}]);





employeecontrollers.controller('EmployeeListController', ['$scope', '$routeParams', 'Employee',
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
                return $scope.currentPage - 1;
            }
            return $scope.currentPage;
        };
        /* if don't there next element return start page */
        $scope.noNext = function () {
            return $scope.currentPage == $scope.totalPages;
        };
        /* if there next element return it */
        $scope.selectNext = function () {
            if (!$scope.noNext()) {
                return $scope.currentPage + 1;
            }
            return $scope.currentPage;
        }
        /* return is active page */
        $scope.isActive = function (page) {
            return $scope.currentPage === page;
        };
        /* get default all records */
        $scope.getAllRecords(1);
        $scope.initialized = true;

    }]);


employeecontrollers.controller('EmployeeDetailController', ['$scope', '$routeParams', 'Employee',
    function($scope, $routeParams, Employee) {
        $scope.employee = Employee.getEmployeeById.get({id: $routeParams.id});
    }
]);




    /*  TaskListController*/

taskcontrollers.controller('TaskListController', ['$scope', '$routeParams', 'Task',
            function ($scope, $routeParams, Task) {
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
                    $scope.data = Task.getAllTasks.query({pageNumber: $routeParams.pageNumber}, function (task) {
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
                        return $scope.currentPage - 1;
                    }
                    return $scope.currentPage;
                };
                /* if don't there next element return start page */
                $scope.noNext = function () {
                    return $scope.currentPage == $scope.totalPages;
                };
                /* if there next element return it */

                $scope.selectNext = function () {
                    if (!$scope.noNext()) {
                        return $scope.currentPage + 1;
                    }
                    return $scope.currentPage;
                }



                /* return is active page */
                $scope.isActive = function (page) {
                    return $scope.currentPage === page;
                };
                /* get default all records */
                $scope.getAllRecords(1);


    }]);



taskcontrollers.controller('TaskDetailController', ['$scope', '$routeParams', 'Task',
    function($scope, $routeParams, Task) {
        $scope.task = Task.getTaskById.get({id: $routeParams.id});
    }
]);





/* Create  Edit Delete Tasks */

taskcontrollers.controller('TaskDeleteController', ['$scope', '$routeParams', 'Task',
function ($scope, Task) {

    $scope.newsEntries = Task.query();

    $scope.deleteEntry = function(taskEntry) {
        taskEntry.$remove(function() {
            $scope.newsEntries = Task.query();
        });
    };
}
]);



taskcontrollers.controller('TaskEditController', ['$scope', '$routeParams', 'Task',
    function($scope, $routeParams, Task) {
        $scope.task = Task.getTaskById.get({id: $routeParams.id});
    }

    /*  $scope.save = function() {
     $scope.task.$save(function() {
     $location.path('/');
     });
     };*/
]);





taskcontrollers.controller('TaskAddController', ['$scope', '$routeParams', 'Task',
    function ($scope, $location, Task) {

        $scope.newsEntry = new Task();

        $scope.save = function() {
            $scope.newsEntry.$save(function() {
                $location.path('/');
            });
        };
    }
]);











/*  TimesheetListController*/

timesheetcontrollers.controller('TimesheetListController', ['$scope', '$routeParams', 'Timesheet',
    function ($scope, $routeParams, Timesheet) {
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
            $scope.data = Timesheet.getAllTimesheets.query({pageNumber: $routeParams.pageNumber}, function (timesheet) {
                $scope.records = timesheet.content;
                $scope.totalPages = timesheet.totalPages;
                $scope.currentPage = timesheet.number + 1;
                $scope.totalRecords = timesheet.totalElements;
                var pages = [];
                for (var i = 1; i <= timesheet.totalPages; i++) {
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
                return $scope.currentPage - 1;
            }
            return $scope.currentPage;
        };
        /* if don't there next element return start page */
        $scope.noNext = function () {
            return $scope.currentPage == $scope.totalPages;
        };
        /* if there next element return it */

        $scope.selectNext = function () {
            if (!$scope.noNext()) {
                return $scope.currentPage + 1;
            }
            return $scope.currentPage;
        }

        /* return is active page */
        $scope.isActive = function (page) {
            return $scope.currentPage === page;
        };
        /* get default all records */
        $scope.getAllRecords(1);


    }]);



timesheetcontrollers.controller('TimesheetDetailController', ['$scope', '$routeParams', 'Timesheet',
    function($scope, $routeParams, Timesheet) {
        $scope.timesheet = Timesheet.getTimesheetById.get({id: $routeParams.id});
    }
]);



