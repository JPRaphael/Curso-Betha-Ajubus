(function(){
    'use strict'

    angular.module('app')
      .service('ManutencaoService', ManutencaoService);

    ManutencaoService.$inject = ['$http'];

    function ManutencaoService($http) {

        function findAllOver() {
            return $http.get('http://localhost:8080/api/manutencao/all?order=placaOnibus')
                .then(function (response) {
                    return response.data;
                });
        };

        function getOnibus() {
            return $http.get('http://localhost:8080/api/onibus')
                .then(function (response) {
                    return response.data;
                });
        };


        function findAll(filtro, page) {
            return $http.get('http://localhost:8080/api/manutencao?page=' + page.number +
                    '&size=' + page.size + '&filterField=descricao&filterValue=' + filtro)
                .then(function (response) {
                    return {
                        registros: response.data,
                        total: response.headers('X-Total-Lenght'),
                        pageSize: response.headers('X-Page-Size'),
                        pages: _calcPage(response.headers('X-Total-Lenght'), response.headers('X-Page-Size')),
                        currentPage: response.headers('X-Current-Page')
                    }
                });
        };

        function _calcPage(totalRegistros, tamanhoPagina) {
            var pages = [];
            var num = totalRegistros / tamanhoPagina;
            var actual = 1;

            while (num > 0) {
                pages.push(actual++)
                num -= 1;
            };

            return pages;
        };

        function findById(id) {
            return $http.get('http://localhost:8080/api/manutencao/' + id)
                .then(function (response) {
                    return response.data;
                });
        };

        function insert(registro) {
            return $http.post('http://localhost:8080/api/manutencao', registro)
                .then(function (response) {
                    return response.data;
                });
        };

        function update(registro) {
            return $http.put('http://localhost:8080/api/manutencao/' + registro.id, registro)
                .then(function (response) {
                    return response.data;
                });
        };

        function remove(id) {
            return $http.delete('http://localhost:8080/api/manutencao/' + id)
                .then(function (response) {
                    return response.data;
                });
        };

        return {
            findAll: findAll,
            findAllOver: findAllOver,
            findById: findById,
            insert: insert,
            update: update,
            remove: remove,
            getOnibus: getOnibus
        };
    };

})();