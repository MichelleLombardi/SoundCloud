app.controller("MainCtrl", ["$scope", "$http",
    function ($scope, $http) {
        
    }
]);

app.controller("LoginCtrl", ["$scope", "userData",
    function ($scope, userData) {
        $scope.conectar = data => {
            // Si el formulario es valido lo enviamos
            if($scope.loginForm.$valid) {
                userData.login(data, function( usuario, error ) {
                    $scope.usuario = usuario;
                    
                    if( error ) {
                    	setTimeout(function(){ 
	                        loading.style.display="none";
	                        signinEmail.value= vacio; 
	                        signinPass.value= vacio;
	                        anuncio.style.display="block";
	                        setTimeout(function(){
	                        	anuncio.style.display="none";
	                        },3000);
                        }, 5000);
                    }
                    else {
                    	setTimeout(function(){ 
                            loading.style.display="none";
                            modal1.style.display="none";
                            }, 5000);
                    	
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

        ($scope.verificar = () => {
            userData.verify(function (usuario, error) {
                console.log(error);
                $scope.usuario = usuario;
            })
        })();

        $scope.desconectar = () => {
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



