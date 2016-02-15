'use strict';

angular.module('refugeesApp')
    .factory('Demand', function ($resource, DateUtils) {
        return $resource('api/demands/:id', {}, {
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
