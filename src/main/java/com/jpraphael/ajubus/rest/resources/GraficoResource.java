package com.jpraphael.ajubus.rest.resources;

import com.jpraphael.ajubus.model.Graficos;
import com.jpraphael.ajubus.model.Manutencao;
import com.jpraphael.ajubus.model.Onibus;
import com.jpraphael.ajubus.model.ViagemExtra;
import com.jpraphael.ajubus.services.ManutencaoService;
import com.jpraphael.ajubus.services.OnibusService;
import com.jpraphael.ajubus.services.ViagemExtraService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Path("grafico")
public class GraficoResource {

    @Inject
    private ManutencaoService manutencaoService;

    @Inject
    private OnibusService onibusService;

    @Inject
    private ViagemExtraService viagemExtraService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        long somaReceita = 0;
        List<Graficos> listGraficos = new ArrayList<>();
        List<Onibus> onibus = onibusService.findAll(null, null, null, null, null);
        List<Manutencao> manutencao = manutencaoService.findAll(null, null, null, null, null);
        List<ViagemExtra> viagemExtras = viagemExtraService.findAll(null, null, null, null, null);

        somaReceita = getSomaReceita(somaReceita, onibus);

        Month mes = LocalDate.now().getMonth();
        for (int i = 0; i <= mes.getValue(); i++) {
            switch (i) {
                case 0:
                    calculoGrafico(manutencao, viagemExtras, listGraficos, somaReceita, "Jan", 0);
                    break;
                case 1:
                    calculoGrafico(manutencao, viagemExtras, listGraficos, somaReceita, "Fev", 1);
                    break;
                case 2:
                    calculoGrafico(manutencao, viagemExtras, listGraficos, somaReceita, "Mar", 2);
                    break;
                case 3:
                    calculoGrafico(manutencao, viagemExtras, listGraficos, somaReceita, "Abr", 3);
                    break;
                case 4:
                    calculoGrafico(manutencao, viagemExtras, listGraficos, somaReceita, "Mai", 4);
                    break;
                case 5:
                    calculoGrafico(manutencao, viagemExtras, listGraficos, somaReceita, "Jun", 5);
                    break;
                case 6:
                    calculoGrafico(manutencao, viagemExtras, listGraficos, somaReceita, "Jul", 6);
                    break;
                case 7:
                    calculoGrafico(manutencao, viagemExtras, listGraficos, somaReceita, "Ago", 7);
                    break;
                case 8:
                    calculoGrafico(manutencao, viagemExtras, listGraficos, somaReceita, "Set", 8);
                    break;
                case 9:
                    calculoGrafico(manutencao, viagemExtras, listGraficos, somaReceita, "Out", 9);
                    break;
                case 10:
                    calculoGrafico(manutencao, viagemExtras, listGraficos, somaReceita, "Nov", 10);
                    break;
                case 11:
                    calculoGrafico(manutencao, viagemExtras, listGraficos, somaReceita, "Dez", 11);
                    break;
            }
        }


        return Response.ok(listGraficos).build();

    }

    private long getSomaReceita(long somaReceita, List<Onibus> onibus) {
        for (Onibus aReceita : onibus) {
            Long receitaOnibus = aReceita.getReceitaOnibus();
            somaReceita += receitaOnibus;
        }
        return somaReceita;
    }

    private void calculoGrafico(List<Manutencao> manutencao, List<ViagemExtra> viagemExtras, List<Graficos> listGraficos, long somaReceita, String nomeMes, int i2) {
        Graficos grafico = new Graficos().setLabel(nomeMes);
        Long valorManu = manutencao.stream()
                .filter(a -> a.getDataManutencao().getMonth() == i2 && ((Date) a.getDataManutencao()).toLocalDate().getYear() == LocalDate.now().getYear())
                .map(Manutencao::getValor)
                .findFirst()
                .orElse(null);

        Long valorViagem = viagemExtras.stream()
                .filter(a -> a.getDataViagem().getMonth() == i2 && ((Date) a.getDataViagem()).toLocalDate().getYear() == LocalDate.now().getYear())
                .map(ViagemExtra::getValor)
                .findFirst()
                .orElse(null);

        valorManu = valorManu == null ? 0 : valorManu;
        valorViagem = valorViagem == null ? 0 : valorViagem;

        long valor = (somaReceita + valorViagem) - valorManu;
        grafico.setValue(valor);
        listGraficos.add(grafico);
    }
}