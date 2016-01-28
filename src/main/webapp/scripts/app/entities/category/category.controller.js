'use strict';

angular.module('refugeesApp')
    .controller('CategoryController', function ($scope, $state, Category) {

        $scope.categorys = [];
        $scope.loadAll = function() {
            Category.query(function(result) {
               $scope.categorys = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.category = {
                type: null,
                name: null,
                id: null
            };
        };
    });
