'use strict';

angular.module('refugeesApp')
    .controller('OfferController', function ($scope, $state, Offer, ParseLinks) {

        $scope.offers = [];
        $scope.predicate = 'id';
        $scope.reverse = true;
        $scope.page = 1;
        $scope.loadAll = function() {
            Offer.query({page: $scope.page - 1, size: 20, sort: [$scope.predicate + ',' + ($scope.reverse ? 'asc' : 'desc'), 'id']}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.totalItems = headers('X-Total-Count');
                $scope.offers = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.offer = {
                quantity: null,
                expiration: null,
                id: null
            };
        };
    });
