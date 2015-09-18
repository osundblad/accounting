var app = angular.module('accounting', ['ngRoute'])
    .config(function ($routeProvider, $httpProvider) {

        $routeProvider.when('/', {
            templateUrl: 'partials/books.html',
            controller: 'books'
        }).when('/book/:bookId', {
            templateUrl: 'partials/book-details.html',
            controller: 'book'
        }).when('/login', {
            templateUrl: 'partials/login.html',
            controller: 'navigation'
        }).otherwise('/');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    });

app
    .controller('navigation', function ($rootScope, $scope, $http, $location) {

        var authenticate = function (credentials, callback) {
            var headers = credentials ? {authorization: "Basic " + btoa(credentials.username + ":" + credentials.password) } : {};
            $http.get('user', {headers: headers}).success(function (data) {
                $rootScope.authenticated = !!data.name;
                callback && callback();
            }).error(function () {
                $rootScope.authenticated = false;
                callback && callback();
            });
        };

        authenticate();

        $scope.credentials = {};

        $scope.login = function () {
            authenticate($scope.credentials, function () {
                if ($rootScope.authenticated) {
                    $location.path("/");
                    $scope.error = false;
                } else {
                    $location.path("/login");
                    $scope.error = true;
                }
            });
        };

        $scope.logout = function () {
            $http.post('logout', {}).success(function () {
                $rootScope.authenticated = false;
                $location.path("/");
            }).error(function (data) {
                $rootScope.authenticated = false;
            });
        };
    })

    .controller('books', function ($scope, $http) {
        $http.get('/book').success(function (data) {
            $scope.books = data;
        });
    })

    .controller('book', function ($scope, $routeParams, $http) {
        $scope.bookId = $routeParams.bookId;
        $http.get('/book/' + $scope.bookId).success(function (data) {
            $scope.book = data;
        });
        $http.get('/bookyear/' + $scope.bookId).success(function (data) {
            $scope.bookYears = data;
        });

        $scope.addBookYear = function($scope, $http) {
            console.log('button pressed');
            $http.get('/bookyear/' + $scope.bookId + '/next').
                success(function (data) {
                    $scope.bookYears.push(data);
            });
        }
    })
;
