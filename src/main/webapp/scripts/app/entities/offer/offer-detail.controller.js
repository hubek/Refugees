'use strict';

angular.module('refugeesApp')
    .controller('OfferDetailController', function ($scope, $rootScope, $stateParams, entity, Offer, Category, Season, Size, DonationCondition, Gender, Organization) {
        $scope.offer = entity;
        $scope.load = function (id) {
            Offer.get({id: id}, function(result) {
                $scope.offer = result;
            });
        };
        var unsubscribe = $rootScope.$on('refugeesApp:offerUpdate', function(event, result) {
            $scope.offer = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
