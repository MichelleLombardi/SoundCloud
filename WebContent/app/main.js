app.controller("MainCtrl", ["$scope", "$http",
    function ($scope, $http) {
    }
]);

app.controller("LoginCtrl", ["$scope", "userData",
    function ($scope, userData) {
        $scope.conectar = data => {
            // Si el formulario es valido lo enviamos
            console.log(data);
            if($scope.loginForm.$valid) {
                userData.login(data, function() {
                    
                });
            }
            // Si no entonces indicamos que hay un error
            else {
                alert("Hay un error en alguno de los campos")
            }
        };

        $scope.verificar = () => {
            userData.verify().then(
                function (e) {
                    $scope.respuesta = e
                },
                function (e) {
                    console.log(e);
                    if( e.status === 401 || e.status === 440  ) {
                        userData.logout();
                    }

                }
            )
        }
    }
]);

app.controller("HomeCtrl", ["$scope", "userData",
    function ($scope, $http) {

    }
]);

app.controller("ProfileCtrl", ["$scope", "userData",
    function ($scope, userData) {
        console.log(userData.getData());
    }
]);



