app.factory("userData", ["$http", "localStorageService",
    function ($http, localStorageService) {
        var userData = localStorageService.get("userData") | null;

        return {
            "login": function (data, callback) {
                console.log(data);
                $http({
                    "url": "/login",
                    "method": "POST",
                    "data": data,
                    "headers": {
                        'Content-Type': "application/x-www-form-urlencoded"
                    }
                }).then(
                    function (response) {
                        userData = response.data;
                        localStorageService.set("userData", userData);
                        callback(userData);
                    },
                    function (error) {
                        console.log(error);
                    }
                );
            },
            "logout": function () {
                localStorageService.clearAll();
                userData = null;
            },
            "verify": function () {
                return $http({
                    "url": "/login",
                    "method": "GET"
                });
            },
            "getData": function () {
                return userData;
            }
        }
    }
]);