'use strict';

angular.module('refugeesApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('size', {
                parent: 'entity',
                url: '/sizes',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'refugeesApp.size.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/size/sizes.html',
                        controller: 'SizeController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('size');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('size.detail', {
                parent: 'entity',
                url: '/size/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'refugeesApp.size.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/size/size-detail.html',
                        controller: 'SizeDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('size');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Size', function($stateParams, Size) {
                        return Size.get({id : $stateParams.id});
                    }]
                }
            })
            .state('size.new', {
                parent: 'size',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/size/size-dialog.html',
                        controller: 'SizeDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    value: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('size', null, { reload: true });
                    }, function() {
                        $state.go('size');
                    })
                }]
            })
            .state('size.edit', {
                parent: 'size',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/size/size-dialog.html',
                        controller: 'SizeDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Size', function(Size) {
                                return Size.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('size', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('size.delete', {
                parent: 'size',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/size/size-delete-dialog.html',
                        controller: 'SizeDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Size', function(Size) {
                                return Size.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('size', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
