'use strict';

angular.module('refugeesApp')
    .factory('OpeningHour', function ($resource, DateUtils) {
        return $resource('api/openingHours/:id', {}, {
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
