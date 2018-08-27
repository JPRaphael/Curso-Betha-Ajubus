(function(){
    'use strict'

    angular.module('app')
      .service('OnibusService', OnibusService);

    OnibusService.$inject = ['$http'];
    
    function OnibusService($http) {

        function findAllOver() {
            return $http.get('http://localhost:8080/api/onibus/all?order=numeroOnibus')
                .then(function (response) {
                    return response.data;
                });
        }

        function findAll(filtro, page) {
            return $http.get('http://localhost:8080/api/onibus?page=' + page.number +
                    '&size=' + page.size + '&filterField=numeroOnibus&filterValue=' + filtro)
                .then(function (response) {
                    return {
                        registros: response.data,
                        total: response.headers('X-Total-Lenght'),
                        pageSize: response.headers('X-Page-Size'),
                        pages: _calcPage(response.headers('X-Total-Lenght'), response.headers('X-Page-Size')),
                        currentPage: response.headers('X-Current-Page')
                    }
                });
        }

        function _calcPage(totalRegistros, tamanhoPagina) {
            var pages = [];
            var num = totalRegistros / tamanhoPagina;
            var actual = 1;

            while (num > 0) {
                pages.push(actual++)
                num -= 1;
            }

            return pages;
        }

        function findById(id) {
            return $http.get('http://localhost:8080/api/onibus/' + id)
                .then(function (response) {
                    return response.data;
                });
        }

        function insert(registro) {
            return $http.post('http://localhost:8080/api/onibus', registro)
                .then(function (response) {
                    return response.data;
                });
        }

        function update(registro) {
            return $http.put('http://localhost:8080/api/onibus/' + registro.id, registro)
                .then(function (response) {
                    return response.data;
                });
        }

        function remove(id) {
            return $http.delete('http://localhost:8080/api/onibus/' + id)
                .then(function (response) {
                    return response.data;
                });
        }

        return {
            findAll: findAll,
            findAllOver: findAllOver,
            findById: findById,
            insert: insert,
            update: update,
            remove: remove
        }
    }

})();