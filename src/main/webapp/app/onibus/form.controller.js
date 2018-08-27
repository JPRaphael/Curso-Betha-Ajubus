(function() {

    angular.module('app')
      .controller('OnibusFormController', OnibusFormController);

    OnibusFormController.$inject = ['OnibusService', '$state', '$stateParams', 'DialogBuilder'];

    function OnibusFormController(OnibusService, $state, $stateParams, DialogBuilder) {

        var vm = this;
        vm.registro = {};
        vm.error = {};
        vm.titulo = 'Novo Onibus';

        vm.salvar = salvar;

        if ($stateParams.id) {
            OnibusService.findById($stateParams.id)
                .then(function (data) {
                    vm.registro = data;
                    vm.titulo = 'Editando Ônibus';
                });
        }

        function salvar() {
            if (!vm.registro.id) {
                OnibusService.insert(vm.registro)
                    .then(function (dado) {
                        DialogBuilder.message('Registro inserido com sucesso!');
                        $state.go("onibusList");
                    })
                    .catch(function (error) {
                        vm.error = error.data;
                    });
            } else {
                OnibusService.update(vm.registro)
                    .then(function (dado) {
                        DialogBuilder.message('Registro alterado com sucesso!');
                        $state.go("onibusList");
                    })
                    .catch(function (error) {
                        vm.error = error.data;
                    });
            }
        }
   }

})();