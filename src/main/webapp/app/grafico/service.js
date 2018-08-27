(function(){
    'use strict'

    angular.module('app')
      .service('GraficoService', GraficoService);

    GraficoService.$inject = ['$http'];

    function GraficoService($http) {

        function findAll() {
            return $http.get('http://localhost:8080/api/grafico')
                .then(function (response) {
                    return response.data;
                });
        };
        function findAllOnibus() {
            return $http.get('http://localhost:8080/api/onibus')
                .then(function (response) {
                    return response.data;
                });
        };

        function findAllManutencao() {
            return $http.get('http://localhost:8080/api/manutencao')
                .then(function (response) {
                    return response.data;
                });
        };


        return {
            findAll: findAll,
            findAllOnibus:findAllOnibus,
            findAllManutencao:findAllManutencao
        };
    };

})();