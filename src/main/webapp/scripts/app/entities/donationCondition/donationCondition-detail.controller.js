'use strict';

angular.module('refugeesApp')
    .controller('DonationConditionDetailController', function ($scope, $rootScope, $stateParams, entity, DonationCondition) {
        $scope.donationCondition = entity;
        $scope.load = function (id) {
            DonationCondition.get({id: id}, function(result) {
                $scope.donationCondition = result;
            });
        };
        var unsubscribe = $rootScope.$on('refugeesApp:donationConditionUpdate', function(event, result) {
            $scope.donationCondition = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
