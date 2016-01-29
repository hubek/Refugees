'use strict';

angular.module('refugeesApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('branch', {
                parent: 'entity',
                url: '/branchs',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'refugeesApp.branch.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/branch/branchs.html',
                        controller: 'BranchController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('branch');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('branch.detail', {
                parent: 'entity',
                url: '/branch/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'refugeesApp.branch.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/branch/branch-detail.html',
                        controller: 'BranchDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('branch');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Branch', function($stateParams, Branch) {
                        return Branch.get({id : $stateParams.id});
                    }]
                }
            })
            .state('branch.new', {
                parent: 'branch',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/branch/branch-dialog.html',
                        controller: 'BranchDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    address: null,
                                    phone: null,
                                    email: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('branch', null, { reload: true });
                    }, function() {
                        $state.go('branch');
                    })
                }]
            })
            .state('branch.edit', {
                parent: 'branch',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/branch/branch-dialog.html',
                        controller: 'BranchDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Branch', function(Branch) {
                                return Branch.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('branch', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('branch.delete', {
                parent: 'branch',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/branch/branch-delete-dialog.html',
                        controller: 'BranchDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Branch', function(Branch) {
                                return Branch.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('branch', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
