'use strict';

angular.module('refugeesApp')
    .controller('OpeningHourDetailController', function ($scope, $rootScope, $stateParams, entity, OpeningHour, Branch) {
        $scope.openingHour = entity;
        $scope.load = function (id) {
            OpeningHour.get({id: id}, function(result) {
                $scope.openingHour = result;
            });
        };
        var unsubscribe = $rootScope.$on('refugeesApp:openingHourUpdate', function(event, result) {
            $scope.openingHour = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
