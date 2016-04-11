app.controller("MainCtrl", ["$scope", "$http",
    function ($scope, $http) {
        
    }
]);

app.controller("LoginCtrl", ["$scope", "userData",
    function ($scope, userData) {
        $scope.conectar = function(data) {
            // Si el formulario es valido lo enviamos
            if($scope.loginForm.$valid) {
                userData.login(data, function( usuario, error ) {
                    $scope.usuario = usuario;
                    
                    if( error ) {

                    }
                    else {
                    	
                    }
                    $scope.loginError = error;

                    $scope.conectarLoading = false;
                });
                $scope.conectarLoading = true;
            }
            // Si no entonces indicamos que hay un error
            else {
                alert("Hay un error en alguno de los campos")
            }
        };

        ($scope.verificar = function() {
            userData.verify(function (usuario, error) {
                console.log(error);
                $scope.usuario = usuario;
            })
        })();

        $scope.desconectar = function() {
            userData.logout(function () {
                $scope.usuario = null;
            });
        };

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



