app.factory("userData", ["$http", "localStorageService",
    function ($http, localStorageService) {
        var userData = localStorageService.get("userData") | null;

        return {
            "login": function (data, callback) {
                $http({
                    "url": "/login",
                    "method": "POST",
                    "data": data
                }).then(
                    function (response) {
                        if( !response.data.error ) {
                            userData = response.data;
                            localStorageService.set("userData", userData);
                            callback(userData);
                        }
                        else
                            callback( null, response.data.error );
                    },
                    function (error) {
                        console.log(error);
                    }
                );
            },
            "logout": function (callback) {
                $http({
                    "url": "/login",
                    "method": "DELETE"
                }).then(
                    function (response) {
                        userData = null;
                        localStorageService.clearAll();
                        callback();
                    },
                    function (error) {
                        console.log(error);
                    }
                );
            },
            "verify": function (callback) {
                $http({
                    "url": "/login",
                    "method": "GET"
                }).then(
                    function (response) {
                        if( !response.data.error ) {
                            userData = response.data;
                            localStorageService.set("userData", userData);
                            callback(userData);
                        }
                        else
                            callback( null, response.data.error );
                    },
                    function (error) {
                        console.log(error);
                    }
                );
            },
            "getData": function () {
                return userData;
            }
        }
    }
]);