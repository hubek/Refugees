'use strict';

angular.module('refugeesApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('gender', {
                parent: 'entity',
                url: '/genders',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'refugeesApp.gender.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/gender/genders.html',
                        controller: 'GenderController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('gender');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('gender.detail', {
                parent: 'entity',
                url: '/gender/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'refugeesApp.gender.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/gender/gender-detail.html',
                        controller: 'GenderDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('gender');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Gender', function($stateParams, Gender) {
                        return Gender.get({id : $stateParams.id});
                    }]
                }
            })
            .state('gender.new', {
                parent: 'gender',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/gender/gender-dialog.html',
                        controller: 'GenderDialogController',
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
                        $state.go('gender', null, { reload: true });
                    }, function() {
                        $state.go('gender');
                    })
                }]
            })
            .state('gender.edit', {
                parent: 'gender',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/gender/gender-dialog.html',
                        controller: 'GenderDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Gender', function(Gender) {
                                return Gender.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('gender', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('gender.delete', {
                parent: 'gender',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/gender/gender-delete-dialog.html',
                        controller: 'GenderDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Gender', function(Gender) {
                                return Gender.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('gender', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
