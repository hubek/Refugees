'use strict';

angular.module('refugeesApp')
    .controller('SizeController', function ($scope, $state, Size) {

        $scope.sizes = [];
        $scope.loadAll = function() {
            Size.query(function(result) {
               $scope.sizes = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.size = {
                value: null,
                id: null
            };
        };
    });
