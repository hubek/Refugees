'use strict';

angular.module('refugeesApp').controller('SeasonDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Season',
        function($scope, $stateParams, $uibModalInstance, entity, Season) {

        $scope.season = entity;
        $scope.load = function(id) {
            Season.get({id : id}, function(result) {
                $scope.season = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('refugeesApp:seasonUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.season.id != null) {
                Season.update($scope.season, onSaveSuccess, onSaveError);
            } else {
                Season.save($scope.season, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
