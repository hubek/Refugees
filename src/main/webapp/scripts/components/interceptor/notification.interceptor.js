 'use strict';

angular.module('refugeesApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-refugeesApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-refugeesApp-params')});
                }
                return response;
            }
        };
    });
