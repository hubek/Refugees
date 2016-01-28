'use strict';

angular.module('refugeesApp')
    .factory('OrganizationType', function ($resource, DateUtils) {
        return $resource('api/organizationTypes/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
