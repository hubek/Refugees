'use strict';

angular.module('refugeesApp')
    .factory('Donation_condtition', function ($resource, DateUtils) {
        return $resource('api/donation_condtitions/:id', {}, {
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
