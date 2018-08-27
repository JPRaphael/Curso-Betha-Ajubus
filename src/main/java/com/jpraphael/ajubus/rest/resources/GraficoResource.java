package com.jpraphael.ajubus.rest.resources;

import com.jpraphael.ajubus.model.Graficos;
import com.jpraphael.ajubus.model.Manutencao;
import com.jpraphael.ajubus.model.Onibus;
import com.jpraphael.ajubus.services.ManutencaoService;
import com.jpraphael.ajubus.services.OnibusService;

import javax.ejb.Local;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("grafico")
public class GraficoResource {

    @Inject
    private ManutencaoService manutencaoService;

    @Inject
    private OnibusService onibusService;

    //TODO REFAZER
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Onibus> onibus = onibusService.findAll(null, null, null, null, null);
        List<Manutencao> manutencao = manutencaoService.findAll(null, null, null, null, null);
        List<Graficos> listGraficos = new ArrayList<Graficos>();

        long somaReceita = 0;

        Graficos graficoJan = new Graficos().setLabel("Jan");
        Graficos graficoFev = new Graficos().setLabel("Fev");
        Graficos graficoMar = new Graficos().setLabel("Mar");
        Graficos graficoAbr = new Graficos().setLabel("Abr");
        Graficos graficoMai = new Graficos().setLabel("Mai");
        Graficos graficoJun = new Graficos().setLabel("Jun");
        Graficos graficoJul = new Graficos().setLabel("Jul");
        Graficos graficoAgo = new Graficos().setLabel("Ago");
        Graficos graficoSet = new Graficos().setLabel("Set");
        Graficos graficoOut = new Graficos().setLabel("Out");
        Graficos graficoNov = new Graficos().setLabel("Nov");
        Graficos graficoDez = new Graficos().setLabel("Dez");

        for (Onibus aReceita : onibus) {
            Long receitaOnibus = aReceita.getReceitaOnibus();
            somaReceita += receitaOnibus;
        }

        for (int i = 0; i < manutencao.size(); i++) {
            Date dataManutencao = manutencao.get(i).getDataManutencao();
            String anoAtual = String.valueOf(LocalDate.now().getYear());
            if (String.valueOf(dataManutencao).substring(0,4).equals(anoAtual)) {
                int month = manutencao.get(i).getDataManutencao().getMonth();
                switch (month) {
                    case 0:
                        long valorJan = manutencao.get(i).getValor();
                        graficoJan.setValue(valorJan);
                        break;
                    case 1:
                        long valorFev = manutencao.get(i).getValor();
                        graficoFev.setValue(valorFev);
                        break;
                    case 2:
                        long valorMar = manutencao.get(i).getValor();
                        graficoMar.setValue(valorMar);
                        break;
                    case 3:
                        long valorAbr = manutencao.get(i).getValor();
                        graficoAbr.setValue(valorAbr);
                        break;
                    case 4:
                        long valorMai = manutencao.get(i).getValor();
                        graficoMai.setValue(valorMai);
                        break;
                    case 5:
                        long valorJun = manutencao.get(i).getValor();
                        graficoJun.setValue(valorJun);
                        break;
                    case 6:
                        long valorJul = manutencao.get(i).getValor();
                        graficoJul.setValue(valorJul);
                        break;
                    case 7:
                        long valorAgo = manutencao.get(i).getValor();
                        graficoAgo.setValue(valorAgo);
                        break;
                    case 8:
                        long valorSet = manutencao.get(i).getValor();
                        graficoSet.setValue(valorSet);
                        break;
                    case 9:
                        long valorOut = manutencao.get(i).getValor();
                        graficoOut.setValue(valorOut);
                        break;
                    case 10:
                        long valorNov = manutencao.get(i).getValor();
                        graficoNov.setValue(valorNov);
                        break;
                    case 11:
                        long valorDez = manutencao.get(i).getValor();
                        graficoDez.setValue(valorDez);
                        break;
                }
            }
        }

        if (graficoJan.getValue() != null) {
            graficoJan.setValue(somaReceita - graficoJan.getValue());
            listGraficos.add(graficoJan);
        }
        if (graficoFev.getValue() != null) {
            graficoFev.setValue(somaReceita - graficoFev.getValue());
            listGraficos.add(graficoFev);
        }
        if (graficoMar.getValue() != null) {
            graficoMar.setValue(somaReceita - graficoMar.getValue());
            listGraficos.add(graficoMar);
        }
        if (graficoAbr.getValue() != null) {
            graficoAbr.setValue(somaReceita - graficoAbr.getValue());
            listGraficos.add(graficoAbr);
        }
        if (graficoMai.getValue() != null) {
            graficoMai.setValue(somaReceita - graficoMai.getValue());
            listGraficos.add(graficoMai);
        }
        if (graficoJun.getValue() != null) {
            graficoJun.setValue(somaReceita - graficoJun.getValue());
            listGraficos.add(graficoJun);
        }
        if (graficoJul.getValue() != null) {
            graficoJul.setValue(somaReceita - graficoJul.getValue());
            listGraficos.add(graficoJul);
        }
        if (graficoAgo.getValue() != null) {
            graficoAgo.setValue(somaReceita - graficoAgo.getValue());
            listGraficos.add(graficoAgo);
        }
        if (graficoSet.getValue() != null) {
            graficoSet.setValue(somaReceita - graficoSet.getValue());
            listGraficos.add(graficoSet);
        }
        if (graficoOut.getValue() != null) {
            graficoOut.setValue(somaReceita - graficoOut.getValue());
            listGraficos.add(graficoOut);
        }
        if (graficoNov.getValue() != null) {
            graficoNov.setValue(somaReceita - graficoNov.getValue());
            listGraficos.add(graficoNov);
        }
        if (graficoDez.getValue() != null) {
            graficoDez.setValue(somaReceita - graficoDez.getValue());
            listGraficos.add(graficoDez);
        }

        return  Response.ok(listGraficos).build();

    }

}

