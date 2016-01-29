'use strict';

angular.module('refugeesApp')
    .controller('Donation_condtitionController', function ($scope, $state, Donation_condtition) {

        $scope.donation_condtitions = [];
        $scope.loadAll = function() {
            Donation_condtition.query(function(result) {
               $scope.donation_condtitions = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.donation_condtition = {
                value: null,
                id: null
            };
        };
    });
