(function(){
    angular.module('app', [
        'ui.router'
    ]);

    angular.module('app').config(AppConfig);
    
    AppConfig.$inject = ['$stateProvider'];

    function AppConfig($stateProvider){
        $stateProvider
        .state({
            name: 'manutencaoList',
            url: '/manutencao',
            templateUrl: '/view/manutencao/list.html',
            controller: 'ManutencaoListController',
            controllerAs: 'vm'
        })
        .state({
            name: 'manutencaoNovo',
            url: '/manutencao/novo',
            templateUrl: '/view/manutencao/form.html',
            controller: 'ManutencaoFormController',
            controllerAs: 'vm'
        })
        .state({
            name: 'manutencaoEditar',
            url: '/manutencao/{id}',
            templateUrl: '/view/manutencao/form.html',
            controller: 'ManutencaoFormController',
            controllerAs: 'vm'
        })
        .state({
            name: 'onibusList',
            url: '/onibus',
            templateUrl: '/view/onibus/list.html',
            controller: 'OnibusListController',
            controllerAs: 'vm'
        })
        .state({
            name: 'onibusNovo',
            url: '/onibus/novo',
            templateUrl: '/view/onibus/form.html',
            controller: 'OnibusFormController',
            controllerAs: 'vm'
        })
        .state({
            name: 'onibusEditar',
            url: '/onibus/{id}',
            templateUrl: '/view/onibus/form.html',
            controller: 'OnibusFormController',
            controllerAs: 'vm'  
        })
        .state({
            name: 'graficoList',
            url: '/graficos',
            templateUrl: '/view/grafico/list.html',
            controller: 'GraficoController',
            controllerAs: 'vm'
        })
        }

})();