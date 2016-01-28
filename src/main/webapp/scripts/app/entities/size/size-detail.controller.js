'use strict';

angular.module('refugeesApp')
    .controller('SizeDetailController', function ($scope, $rootScope, $stateParams, entity, Size, Category) {
        $scope.size = entity;
        $scope.load = function (id) {
            Size.get({id: id}, function(result) {
                $scope.size = result;
            });
        };
        var unsubscribe = $rootScope.$on('refugeesApp:sizeUpdate', function(event, result) {
            $scope.size = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
