'use strict';

angular.module('refugeesApp')
    .controller('CategoryDetailController', function ($scope, $rootScope, $stateParams, entity, Category, Size) {
        $scope.category = entity;
        $scope.load = function (id) {
            Category.get({id: id}, function(result) {
                $scope.category = result;
            });
        };
        var unsubscribe = $rootScope.$on('refugeesApp:categoryUpdate', function(event, result) {
            $scope.category = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
