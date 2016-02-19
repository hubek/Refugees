'use strict';

angular.module('refugeesApp')
    .controller('OpeningHourController', function ($scope, $state, OpeningHour) {

        $scope.openingHours = [];
        $scope.loadAll = function() {
            OpeningHour.query(function(result) {
               $scope.openingHours = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.openingHour = {
                day: null,
                open: null,
                close: null,
                id: null
            };
        };
    });
