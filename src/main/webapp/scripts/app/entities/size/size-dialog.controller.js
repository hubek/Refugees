'use strict';

angular.module('refugeesApp').controller('SizeDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Size', 'Category',
        function($scope, $stateParams, $uibModalInstance, entity, Size, Category) {

        $scope.size = entity;
        $scope.categorys = Category.query();
        $scope.load = function(id) {
            Size.get({id : id}, function(result) {
                $scope.size = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('refugeesApp:sizeUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.size.id != null) {
                Size.update($scope.size, onSaveSuccess, onSaveError);
            } else {
                Size.save($scope.size, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
