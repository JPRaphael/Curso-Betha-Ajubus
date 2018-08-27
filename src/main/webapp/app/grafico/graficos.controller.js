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


        vm.buscaDataSource();
    }
})();