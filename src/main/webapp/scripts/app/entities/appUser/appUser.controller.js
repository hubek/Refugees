'use strict';

angular.module('refugeesApp')
    .controller('AppUserController', function ($scope, $state, AppUser) {

        $scope.appUsers = [];
        $scope.loadAll = function() {
            AppUser.query(function(result) {
               $scope.appUsers = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.appUser = {
                id: null
            };
        };
    });
