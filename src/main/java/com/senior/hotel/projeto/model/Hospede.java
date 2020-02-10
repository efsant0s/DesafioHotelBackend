/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senior.hotel.projeto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Eduardo
 */
@Entity
@Table(name = "hospede")
public class Hospede implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String documento;

    private String telefone;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospede")
    private List<Checkin> listaCheckIn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Checkin> getListaCheckIn() {
        return listaCheckIn;
    }

    public void setListaCheckIn(List<Checkin> listaCheckIn) {
        this.listaCheckIn = listaCheckIn;
    }
    
    @Override
    public String toString() {
        return "Hospede{" + "id=" + id + ", nome=" + nome + ", documento=" + documento + ", telefone=" + telefone + '}';
    }

}
