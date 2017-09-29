var app=angular.module('app',[]);
app.controller('loginController',function($scope,$http,$location){
    $scope.login=function(){
        var url="login";
        var data={
email:$scope.email,
password:$scope.password
        };

        $http.post(url,data).then(function(response)
    {
        $scope.message="Successful!"+response.headers("Authorization");
    }, function(response){

        $scope.message="Failed!";
    });
    }
});
angular.module('app').factory('httpRequestInterceptor',
['$rootScope', function($rootScope)
 {
  return {
   request: function($config) {
    if( $rootScope.user.loginticket )
    {
     $config.headers['your-auth-ticket'] = $rootScope.user.loginticket;
    }
  return $config;
  }
 };
}]);