'use strict';

angular.module('refugeesApp')
    .controller('DemandDetailController', function ($scope, $rootScope, $stateParams, entity, Demand, Branch, Category, Season, Gender, DonationCondition, Size, Status) {
        $scope.demand = entity;
        $scope.load = function (id) {
            Demand.get({id: id}, function(result) {
                $scope.demand = result;
            });
        };
        var unsubscribe = $rootScope.$on('refugeesApp:demandUpdate', function(event, result) {
            $scope.demand = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
