'use strict';

angular.module('refugeesApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('season', {
                parent: 'entity',
                url: '/seasons',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'refugeesApp.season.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/season/seasons.html',
                        controller: 'SeasonController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('season');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('season.detail', {
                parent: 'entity',
                url: '/season/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'refugeesApp.season.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/season/season-detail.html',
                        controller: 'SeasonDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('season');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Season', function($stateParams, Season) {
                        return Season.get({id : $stateParams.id});
                    }]
                }
            })
            .state('season.new', {
                parent: 'season',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/season/season-dialog.html',
                        controller: 'SeasonDialogController',
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
                        $state.go('season', null, { reload: true });
                    }, function() {
                        $state.go('season');
                    })
                }]
            })
            .state('season.edit', {
                parent: 'season',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/season/season-dialog.html',
                        controller: 'SeasonDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Season', function(Season) {
                                return Season.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('season', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('season.delete', {
                parent: 'season',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/season/season-delete-dialog.html',
                        controller: 'SeasonDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Season', function(Season) {
                                return Season.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('season', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
