'use strict';

angular.module('refugeesApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('appUser', {
                parent: 'entity',
                url: '/appUsers',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'refugeesApp.appUser.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/appUser/appUsers.html',
                        controller: 'AppUserController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('appUser');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('appUser.detail', {
                parent: 'entity',
                url: '/appUser/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'refugeesApp.appUser.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/appUser/appUser-detail.html',
                        controller: 'AppUserDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('appUser');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'AppUser', function($stateParams, AppUser) {
                        return AppUser.get({id : $stateParams.id});
                    }]
                }
            })
            .state('appUser.new', {
                parent: 'appUser',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/appUser/appUser-dialog.html',
                        controller: 'AppUserDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('appUser', null, { reload: true });
                    }, function() {
                        $state.go('appUser');
                    })
                }]
            })
            .state('appUser.edit', {
                parent: 'appUser',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/appUser/appUser-dialog.html',
                        controller: 'AppUserDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['AppUser', function(AppUser) {
                                return AppUser.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('appUser', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('appUser.delete', {
                parent: 'appUser',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/appUser/appUser-delete-dialog.html',
                        controller: 'AppUserDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['AppUser', function(AppUser) {
                                return AppUser.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('appUser', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
