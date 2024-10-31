package com.example.pokersistem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;

import java.io.Serializable;
@Entity
@Table (name = "TIPO_USUARIO", schema = "seguranca")
public class TipoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="genTipoUsuario", allocationSize = 1,  sequenceName = "seguranca.tipo_usuario_id_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genTipoUsuario")
    private Integer id;

    @Column(name="TIPO", nullable=false)
    private String tipo;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
