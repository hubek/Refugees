'use strict';

angular.module('refugeesApp')
    .controller('BranchController', function ($scope, $state, Branch) {

        $scope.branchs = [];
        $scope.loadAll = function() {
            Branch.query(function(result) {
               $scope.branchs = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.branch = {
                address: null,
                phone: null,
                email: null,
                lng: null,
                lat: null,
                id: null
            };
        };
    });
