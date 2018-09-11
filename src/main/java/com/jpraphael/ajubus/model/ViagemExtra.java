package com.jpraphael.ajubus.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "VIAGENS_EXTRAS")
@SequenceGenerator(name = "VIAGENS_EXTRAS_SEQ", allocationSize = 1) //valor inicial que a sequencia é iniciada
public class ViagemExtra implements Entidade {

    @Id
    @Column(name = "I_VIAGENS_EXTRAS")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VIAGENS_EXTRAS_SEQ")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "I_ONIBUS")
    @NotNull(message = "{ViagemExtra.onibus.NotNull}")
    private Onibus onibus;

    @Column(name = "VALOR")
    @NotNull(message = "{ViagemExtra.valor.NotNull}")
    private Long valor;

    @Column(name = "DESCRICAO")
    @NotNull(message = "{ViagemExtra.descricao.NotNull}")
    private String descricao;

    @Column(name = "DATAS_VIAGENS")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "{ViagemExtra.data.NotNull}")
    private Date dataViagem;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDataViagem() {
        return dataViagem;
    }

    public void setDataViagem(Date dataViagem) {
        this.dataViagem = dataViagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViagemExtra that = (ViagemExtra) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(onibus, that.onibus) &&
                Objects.equals(valor, that.valor) &&
                Objects.equals(descricao, that.descricao) &&
                Objects.equals(dataViagem, that.dataViagem);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, onibus, valor, descricao, dataViagem);
    }

    @Override
    public String toString() {
        return "ViagemExtra{" +
                "id=" + id +
                ", onibus=" + onibus +
                ", valor=" + valor +
                ", descricao='" + descricao + '\'' +
                ", dataViagem=" + dataViagem +
                '}';
    }
}
