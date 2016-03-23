var app = angular.module("SoundCloudApp", [
    "ngRoute",
    "LocalStorageModule"
]);

// Configuramos nuestra SPA (Single Web Application)
app.config(["$routeProvider", "$httpProvider",
    function ($routeProvider, $httpProvider) {

        // Configuramos las rutas de acceso
        $routeProvider
        // Indicamos que si se escribe una ruta invalida redirecionamos a "/"
            .otherwise({
                redirectTo: "/"
            })
            // Cuando vayamos a "/" cargamos el archivo home.html y usara el controlador "HomeCtrl"
            .when("/", {
                templateUrl: "views/home.html",
                controller: "HomeCtrl"
            })
            // Cuando vayamos a "profile" cargamos el archivo profile.html y usara el controlador "ProfileCtrl"
            .when("/profile", {
                templateUrl: "views/profile.html",
                controller: "ProfileCtrl"
            });

        // Configuramos un nuevo interceptador para las peticiones $http
        $httpProvider.interceptors.push(["localStorageService", "$httpParamSerializerJQLike",
            function (localStorageService, $httpParamSerializerJQLike) {
                return {
                    // Indicamos que cada vez que hagamos una peticion mediante $http
                    // asignaremos a las cabeceras de la peticion la variable de autorizacion
                    "request": function (http) {
                        // Obtenemos la data del usuario del localStorage
                        var data = localStorageService.get("userData");

                        // Asignamos el token a la cabecera de la peticion
                        if( data )
                            http.headers.Authorization = data.token;

                        http.headers["Content-Type"] = "application/x-www-form-urlencoded";

                        // y transformamos de json a urlencoded la data que enviamos si es que la hay
                        http.data = $httpParamSerializerJQLike(http.data);
                        return http;
                    }
                }
            }
        ])
    }
]);