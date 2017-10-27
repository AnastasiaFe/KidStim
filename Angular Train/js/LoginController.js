var app = angular.module('app', []);
app.controller('loginController', function ($scope, $http, $location) {
    $scope.login = function () {
        var url = "login";
        var data = {
            email: $scope.email,
            password: $scope.password
        };

        $http.post(url, data).then(function (response) {
            $scope.authenticated = true;
            TokenStorage.store(headers('Authorization'));
        }, function (response) {
            $scope.message = "Failed!";
        });
    }
});
app.factory('TokenAuthInterceptor', function($q, TokenStorage) {
	return {
		request: function(config) {
			var authToken = TokenStorage.retrieve();
			if (authToken) {
				config.headers['Authorization'] = authToken;
			}
			return config;
		},
		responseError: function(error) {
			if (error.status === 401 || error.status === 403) {
				TokenStorage.clear();
			}
			return $q.reject(error);
		}
	};
}).config(function($httpProvider) {
	$httpProvider.interceptors.push('TokenAuthInterceptor');
});