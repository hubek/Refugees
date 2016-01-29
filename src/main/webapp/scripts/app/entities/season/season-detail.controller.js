'use strict';

angular.module('refugeesApp')
    .controller('SeasonDetailController', function ($scope, $rootScope, $stateParams, entity, Season) {
        $scope.season = entity;
        $scope.load = function (id) {
            Season.get({id: id}, function(result) {
                $scope.season = result;
            });
        };
        var unsubscribe = $rootScope.$on('refugeesApp:seasonUpdate', function(event, result) {
            $scope.season = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });