'use strict';

angular.module('refugeesApp')
    .controller('GenderController', function ($scope, $state, Gender) {

        $scope.genders = [];
        $scope.loadAll = function() {
            Gender.query(function(result) {
               $scope.genders = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.gender = {
                value: null,
                id: null
            };
        };
    });
