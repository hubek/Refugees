'use strict';

angular.module('refugeesApp')
    .controller('DonationConditionController', function ($scope, $state, DonationCondition) {

        $scope.donationConditions = [];
        $scope.loadAll = function() {
            DonationCondition.query(function(result) {
               $scope.donationConditions = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.donationCondition = {
                value: null,
                id: null
            };
        };
    });
