'use strict';

angular.module('refugeesApp')
    .factory('Size', function ($resource, DateUtils) {
        return $resource('api/sizes/:id', {}, {
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
