package com.jpraphael.ajubus.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "MANUTENCAO")
@SequenceGenerator(name = "MANUTENCAO_SEQ", allocationSize = 1) //valor inicial que a sequencia é iniciada
public class Manutencao implements Entidade {

    @Id
    @Column(name = "I_MANUTENCAO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MANUTENCAO_SEQ")
    private Long id;

    @Column(name = "DESCRICAO")
    @NotNull(message = "{Manutencao.descricao.NotNull}")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "I_ONIBUS")
    @NotNull(message = "{Manutencao.onibus.NotNull}")
    private Onibus onibus;

    @Column(name = "VALOR")
    @NotNull(message = "{Manutencao.valor.NotNull}")
    private Long valor;

    @Column(name = "DATA_MANUTENCAO")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "{Manutencao.data.NotNull}")
    private Date dataManutencao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Onibus getOnibus() {
        return onibus;
    }

    public void setOnibus(Onibus onibus) {
        this.onibus = onibus;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public Date getDataManutencao() {
        return dataManutencao;
    }

    public void setDataManutencao(Date dataManutencao) {
        this.dataManutencao = dataManutencao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manutencao that = (Manutencao) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(descricao, that.descricao) &&
                Objects.equals(onibus, that.onibus) &&
                Objects.equals(valor, that.valor) &&
                Objects.equals(dataManutencao, that.dataManutencao);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, descricao, onibus, valor, dataManutencao);
    }

    @Override
    public String toString() {
        return "Manutencao{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", onibus=" + onibus +
                ", valor=" + valor +
                ", dataManutencao=" + dataManutencao +
                '}';
    }
}
