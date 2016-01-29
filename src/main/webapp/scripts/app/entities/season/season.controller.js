'use strict';

angular.module('refugeesApp')
    .controller('SeasonController', function ($scope, $state, Season) {

        $scope.seasons = [];
        $scope.loadAll = function() {
            Season.query(function(result) {
               $scope.seasons = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.season = {
                value: null,
                id: null
            };
        };
    });
