'use strict';

angular.module('refugeesApp')
    .controller('AppUserDetailController', function ($scope, $rootScope, $stateParams, entity, AppUser, Branch, User) {
        $scope.appUser = entity;
        $scope.load = function (id) {
            AppUser.get({id: id}, function(result) {
                $scope.appUser = result;
            });
        };
        var unsubscribe = $rootScope.$on('refugeesApp:appUserUpdate', function(event, result) {
            $scope.appUser = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
