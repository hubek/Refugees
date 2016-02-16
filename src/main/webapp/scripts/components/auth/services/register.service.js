'use strict';

angular.module('refugeesApp')
    .factory('Register', function ($resource) {
        return $resource('api/appUsers', {}, {
        });
    });


