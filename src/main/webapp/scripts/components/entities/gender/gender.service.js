'use strict';

angular.module('refugeesApp')
    .factory('Gender', function ($resource, DateUtils) {
        return $resource('api/genders/:id', {}, {
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
