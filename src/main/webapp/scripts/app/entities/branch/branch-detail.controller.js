'use strict';

angular.module('refugeesApp')
    .controller('BranchDetailController', function ($scope, $rootScope, $stateParams, entity, Branch, Organization) {
        $scope.branch = entity;
        $scope.load = function (id) {
            Branch.get({id: id}, function(result) {
                $scope.branch = result;
            });
        };
        var unsubscribe = $rootScope.$on('refugeesApp:branchUpdate', function(event, result) {
            $scope.branch = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
