'use strict';

angular.module('refugeesApp')
    .controller('GenderDetailController', function ($scope, $rootScope, $stateParams, entity, Gender) {
        $scope.gender = entity;
        $scope.load = function (id) {
            Gender.get({id: id}, function(result) {
                $scope.gender = result;
            });
        };
        var unsubscribe = $rootScope.$on('refugeesApp:genderUpdate', function(event, result) {
            $scope.gender = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
