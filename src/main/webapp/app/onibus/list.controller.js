(function(){
  'use strict'

  angular.module('app')
    .controller('OnibusListController', OnibusListController);
  
  OnibusListController.$inject = ['OnibusService', 'DialogBuilder']
  
  function OnibusListController(OnibusService, DialogBuilder) {
    var vm = this;
    vm.data = {};
    vm.filtro = '';
    vm.page = {
        number: 1,
        size: '15'
    }

    vm.atualizar = load;
    vm.resetFiltro = function () {
        vm.filtro = '';
        load();
    }

    vm.goToPage = function (page) {
        vm.page.number = page;
        load();
    }

    function load() {
      OnibusService.findAll(vm.filtro, vm.page)
            .then(function (dados) {
                vm.data = dados
            });
    }

    vm.excluir = function (item) {
        DialogBuilder.confirm('Tem certeza que deseja remover o registro?')
            .then(function (result) {
                if (result.value) {
                  OnibusService.remove(item.id)
                        .then(function () {
                            load();
                            DialogBuilder.message('Registro excluído com sucesso!');
                        });
                } else {
                    DialogBuilder.message({
                        title: 'Exclusão cancelada pelo usuário!',
                        type: 'error'
                    });
                }
            });
    };
    load();
  }
})();

