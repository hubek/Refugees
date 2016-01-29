'use strict';

angular.module('refugeesApp')
    .factory('DonationCondition', function ($resource, DateUtils) {
        return $resource('api/donationConditions/:id', {}, {
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
