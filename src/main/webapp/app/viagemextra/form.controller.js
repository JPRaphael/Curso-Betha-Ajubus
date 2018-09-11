(function() {

    angular.module('app')
      .controller('ViagemExtraFormController', ViagemExtraFormController);

    ViagemExtraFormController.$inject = ['ViagemExtraService', '$state', '$stateParams', 'DialogBuilder'];

    function ViagemExtraFormController(ViagemExtraService, $state, $stateParams, DialogBuilder) {

      var vm = this;
      vm.registro = {};
      vm.onibus = [];
      vm.error = {};    
      vm.titulo = 'Nova Viagem';

      vm.salvar = salvar;

      if ($stateParams.id) {
        ViagemExtraService.findById($stateParams.id)
            .then(function (data) {
                vm.registro = data;
                vm.registro.dataViagem = new Date(vm.registro.dataViagem);
                vm.onibus.push(vm.registro.onibus);
                vm.titulo = 'Editando Viagem';
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
          ViagemExtraService.insert(vm.registro)
              .then(function (dado) {
                  DialogBuilder.message('Registro inserido com sucesso!');
                  $state.go("viagemExtraList");
              })
              .catch(function (error) {
                  vm.error = error.data;
              });
      } else {
        ViagemExtraService.update(vm.registro)
              .then(function (dado) {
                  DialogBuilder.message('Registro alterado com sucesso!');
                  $state.go("viagemExtraList");
              })
              .catch(function (error) {
                  vm.error = error.data;
              });
            }
        };
        
        vm.setDados = function () {
            if($stateParams.id == undefined){
                ViagemExtraService.getOnibus()
                .then(function (dado) {
                    vm.onibus = dado;
                });
                var data = new Date();
                vm.registro.dataViagem = data;
        }
    };
        
        vm.setDados();
    }

})();