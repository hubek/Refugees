'use strict';

angular.module('refugeesApp')
    .factory('AppUser', function ($resource, DateUtils) {
        return $resource('api/appUsers/:id', {}, {
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
