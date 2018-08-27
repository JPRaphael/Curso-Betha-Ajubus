package com.jpraphael.ajubus.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "ONIBUS")
@SequenceGenerator(name = "ONIBUS_SEQ", allocationSize = 1) //valor inicial que a sequencia é iniciada
public class Onibus implements Entidade{

    @Id
    @Column(name = "I_ONIBUS")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ONIBUS_SEQ")
    private Long id;

    @Column(name = "MODELO", length = 20)
    @NotNull(message = "{Onibus.modelo.NotNull}")
    private String modelo;

    @Column(name = "MARCA", length = 20)
    @NotNull(message = "{Onibus.marca.NotNull}")
    private String marca;

    @Column(name = "MOTORISTA")
    private String motorista;

    @Column(name = "TELEFONE", length = 12)
    @Size(min = 8, max = 20, message = "{Onibus.telefone.Size}")
    private String telefoneMotorista;

    @Column(name = "PLACA", length = 8)
    @NotNull(message = "{Onibus.placa.NotNull}")
    @Size(min = 8, max = 8, message = "{Onibus.placa.Size}")
    private String placaOnibus;

    @Column(name = "NUMERO_ONIBUS")
    @NotNull(message = "{Onibus.numero.NotNull}")
    private Integer numeroOnibus;

    @Column(name = "RECEITA")
    @NotNull(message = "{Onibus.receita.NotNull}")
    private Long receitaOnibus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMotorista() {
        return motorista;
    }

    public void setMotorista(String motorista) {
        this.motorista = motorista;
    }

    public String getTelefoneMotorista() {
        return telefoneMotorista;
    }

    public void setTelefoneMotorista(String telefoneMotorista) {
        this.telefoneMotorista = telefoneMotorista;
    }

    public String getPlacaOnibus() {
        return placaOnibus;
    }

    public void setPlacaOnibus(String placaOnibus) {
        this.placaOnibus = placaOnibus;
    }

    public Integer getNumeroOnibus() {
        return numeroOnibus;
    }

    public void setNumeroOnibus(Integer numeroOnibus) {
        this.numeroOnibus = numeroOnibus;
    }

    public Long getReceitaOnibus() {
        return receitaOnibus;
    }

    public void setReceitaOnibus(Long receitaOnibus) {
        this.receitaOnibus = receitaOnibus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Onibus onibus = (Onibus) o;
        return Objects.equals(id, onibus.id) &&
                Objects.equals(modelo, onibus.modelo) &&
                Objects.equals(marca, onibus.marca) &&
                Objects.equals(motorista, onibus.motorista) &&
                Objects.equals(telefoneMotorista, onibus.telefoneMotorista) &&
                Objects.equals(placaOnibus, onibus.placaOnibus) &&
                Objects.equals(numeroOnibus, onibus.numeroOnibus) &&
                Objects.equals(receitaOnibus, onibus.receitaOnibus);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, modelo, marca, motorista, telefoneMotorista, placaOnibus, numeroOnibus, receitaOnibus);
    }

    @Override
    public String toString() {
        return "Onibus{" +
                    "id=" + id +
                    ", modelo='" + modelo + '\'' +
                    ", marca='" + marca + '\'' +
                    ", motorista='" + motorista + '\'' +
                    ", telefoneMotorista=" + telefoneMotorista +
                    ", placaOnibus=" + placaOnibus +
                    ", numeroOnibus=" + numeroOnibus +
                    ", receitaOnibus=" + receitaOnibus +
                '}';
    }
}
