(function () {

    angular.module('app')
        .controller('GraficoController', GraficoController);

    GraficoController.$inject = ['GraficoService', '$state', '$stateParams', 'DialogBuilder'];

    function GraficoController(GraficoService, $state, $stateParams, DialogBuilder) {

        var vm = this;
        vm.receitaBus = 0;
        vm.despesasManutencao = 0;
        vm.dado = [];
        vm.dataSource = {
            "chart": {
                "caption": "Receita mensal",
                "subCaption": "JK Transporte e Turismo",
                "xAxisName": "Mês",
                "yAxisName": "Receitas (Em Real)",
                "numberPrefix": "R$",
                "paletteColors": "#0075c2",
                "bgColor": "#ffffff",
                "borderAlpha": "20",
                "canvasBorderAlpha": "0",
                "usePlotGradientColor": "0",
                "plotBorderAlpha": "10",
                "placevaluesInside": "1",
                "rotatevalues": "1",
                "valueFontColor": "#ffffff",
                "showXAxisLine": "1",
                "xAxisLineColor": "#999999",
                "divlineColor": "#999999",
                "divLineIsDashed": "1",
                "showAlternateHGridColor": "0",
                "subcaptionFontBold": "0",
                "subcaptionFontSize": "14"
            },
            "trendlines": [{
                "line": [{
                    "startvalue": "0",
                    "color": "#1aaf5d",
                    "valueOnRight": "1"
                }]
            }],
            "data": []
        };

        vm.buscaDataSource = function () {
            GraficoService.findAll()
                .then(function (dado) {
                    vm.dataSource.data = dado;
                    FusionCharts.ready(function () {
                        var revenueChart = new FusionCharts({
                            type: 'column2d',
                            renderAt: 'chart-container',
                            width: '550',
                            height: '350',
                            dataFormat: 'json',
                            dataSource: vm.dataSource
                        }).render();
                    });
                });

        };

        google.charts.load('current', {
            'packages': ['corechart']
        });
        google.charts.setOnLoadCallback(drawChart);
        google.charts.setOnLoadCallback(drawChart3d);

        function drawChart() {
            GraficoService.findAllOnibus()
                .then(function (dado) {
                    dado.forEach(function (item) {
                        vm.receitaBus += item.receitaOnibus;
                    });
                    var mesAtual = new Date().getMonth() + 1;
                    var valorAno = mesAtual * vm.receitaBus
                    var onibus = ['Receita Ônibus', valorAno];

                    GraficoService.findAllManutencao()
                        .then(function (dado) {
                            dado.forEach(function (item) {
                                var dataManutencao = new Date(item.dataManutencao).getFullYear();
                                var dataAtual = new Date().getFullYear();
                                if (dataManutencao === dataAtual) {
                                    vm.despesasManutencao += item.valor;
                                }
                            });
                            var manutencao = ['Despesas Manutenção', vm.despesasManutencao];

                            var data = google.visualization.arrayToDataTable([
                                ['Task', 'Hours per Day'],
                                onibus,
                                manutencao
                            ]);

                            var options = {
                                'title': 'Média do ano',
                                'width': 600,
                                'height': 500
                            };

                            var chart = new google.visualization.PieChart(document.getElementById('piechart'));
                            chart.draw(data, options);
                        });
                });
        }


        function drawChart3d() {
            GraficoService.findAllOnibus()
                .then(function (dado) {
                    vm.onibus = dado;
                    GraficoService.findAllManutencao()
                        .then(function (dado) {
                            vm.manutencao = dado;
                            GraficoService.findAllViagemExtra()
                                .then(function (dado) {
                                    vm.viagemExtra = dado;
                                    grafico(vm.onibus, vm.manutencao, vm.viagemExtra);
                                })
                        })
                })

        }

        function grafico(listOnibus, listManutencao, listViagem) {
            vm.despesa = 0;
            vm.viagem = 0;
            var list = [];
            var anoAtual = new Date().getFullYear();
            listOnibus.forEach(function (dado) {
                listManutencao.forEach(function (item) {
                    if (item.onibus.numeroOnibus === dado.numeroOnibus && new Date(item.dataManutencao).getFullYear() === anoAtual) {
                        vm.despesa += item.valor;
                    } else {
                        vm.despesa = 0;
                    }
                });
                listViagem.forEach(function (item) {
                    if (item.onibus.numeroOnibus === dado.numeroOnibus && new Date(item.dataViagem).getFullYear() === anoAtual) {
                        vm.viagem += item.valor;
                    } else {
                        vm.viagem = 0;
                    }
                })
                var data = google.visualization.arrayToDataTable([
                    ['Task', 'Hours per Day'],
                    ['Receita', (dado.receitaOnibus * (new Date().getMonth() + 1)) + vm.viagem],
                    ['Manutenção', vm.despesa]
                ]);

                var options = {
                    title: 'Ônibus nº' + dado.numeroOnibus,
                    is3D: true,
                };

                $('#linha').append('<div id="piechart_3d' + dado.numeroOnibus + '" style="width: 300px; height: 200px;"></div>')
                var chart = new google.visualization.PieChart(document.getElementById('piechart_3d' + dado.numeroOnibus));
                chart.draw(data, options);

            });
        }


        vm.buscaDataSource();
    }
})();