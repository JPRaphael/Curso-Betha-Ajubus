(function() {

    angular.module('app')
      .controller('ManutencaoFormController', ManutencaoFormController);

    ManutencaoFormController.$inject = ['ManutencaoService', '$state', '$stateParams', 'DialogBuilder'];

    function ManutencaoFormController(ManutencaoService, $state, $stateParams, DialogBuilder) {

      var vm = this;
      vm.registro = {};
      vm.onibus = [];
      vm.error = {};    
      vm.titulo = 'Nova Manutenção';

      vm.salvar = salvar;

      if ($stateParams.id) {
        ManutencaoService.findById($stateParams.id)
            .then(function (data) {
                vm.registro = data;
                vm.registro.dataManutencao = new Date(vm.registro.dataManutencao);
                vm.onibus.push(vm.registro.onibus);
                vm.titulo = 'Editando Manutenção';
            });
    };

    function pegaOnibus(numeroOnibus){
        var result = {};
        vm.onibus.forEach(function(item) {
            if(item.numeroOnibus == parseInt(numeroOnibus)){
                result = item;
            }
        });
        return result;
    }

    function salvar() {
      if (!vm.registro.id) {
          vm.registro.onibus = pegaOnibus(vm.registro.onibus);
          ManutencaoService.insert(vm.registro)
              .then(function (dado) {
                  DialogBuilder.message('Registro inserido com sucesso!');
                  $state.go("manutencaoList");
              })
              .catch(function (error) {
                  vm.error = error.data;
              });
      } else {
        ManutencaoService.update(vm.registro)
              .then(function (dado) {
                  DialogBuilder.message('Registro alterado com sucesso!');
                  $state.go("manutencaoList");
              })
              .catch(function (error) {
                  vm.error = error.data;
              });
            }
        };
        
        vm.setDados = function () {
            if($stateParams.id == undefined){
                ManutencaoService.getOnibus()
                .then(function (dado) {
                    vm.onibus = dado;
                });
                var data = new Date();
                vm.registro.dataManutencao = data;
        }
    };
        
        vm.setDados();
    }

})();