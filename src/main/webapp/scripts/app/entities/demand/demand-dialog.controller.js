'use strict';

angular.module('refugeesApp').controller('DemandDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Demand', 'Branch', 'Category', 'Season', 'Gender', 'DonationCondition', 'Size',
        function($scope, $stateParams, $uibModalInstance, entity, Demand, Branch, Category, Season, Gender, DonationCondition, Size) {

        $scope.demand = entity;
        $scope.branchs = Branch.query();
        $scope.categorys = Category.query();
        $scope.seasons = Season.query();
        $scope.genders = Gender.query();
        $scope.donationconditions = DonationCondition.query();
        $scope.sizes = Size.query();
        $scope.load = function(id) {
            Demand.get({id : id}, function(result) {
                $scope.demand = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('refugeesApp:demandUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.demand.id != null) {
                Demand.update($scope.demand, onSaveSuccess, onSaveError);
            } else {
                Demand.save($scope.demand, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
