'use strict';

angular.module('refugeesApp')
    .controller('Donation_condtitionDetailController', function ($scope, $rootScope, $stateParams, entity, Donation_condtition) {
        $scope.donation_condtition = entity;
        $scope.load = function (id) {
            Donation_condtition.get({id: id}, function(result) {
                $scope.donation_condtition = result;
            });
        };
        var unsubscribe = $rootScope.$on('refugeesApp:donation_condtitionUpdate', function(event, result) {
            $scope.donation_condtition = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
