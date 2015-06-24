angular.module('hello', [])
	.controller('home', function($scope, $http) {
	$http.get('book/hello').success(function(data) {
		$scope.greeting = data;
	})
});
