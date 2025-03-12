package com.pedro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Xogador")
public class Xogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_xogador")
    private int id_xogador;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "apelidos", length = 100)
    private String apellidos;

    @Column(name = "posicion", length = 100)
    private String posicion;

    @Column(name = "data_nacemento")
    private Date data_nacemento;

    @Column(name = "nacionalidade", length = 100)
    private String nacionalidade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_equipo", foreignKey = @ForeignKey(name = "xogador_id_equipo_fkey"))
    @JsonIgnore // Ignora el objeto Equipo durante la serialización
    private Equipo idequipo;

    // Getters y Setters
    public int getId_xogador() {
        return id_xogador;
    }

    public void setId_xogador(int id_xogador) {
        this.id_xogador = id_xogador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Date getData_nacemento() {
        return data_nacemento;
    }

    public void setData_nacemento(Date data_nacemento) {
        this.data_nacemento = data_nacemento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public Equipo getIdequipo() {
        return idequipo;
    }

    public void setIdequipo(Equipo idequipo) {
        this.idequipo = idequipo;
    }

    // Metodo para obtener solo el ID del equipo
    @JsonProperty("id_equipo")
    public Integer getIdEquipo() {
        return (idequipo != null) ? idequipo.getId_equipo() : null;
    }

    // Metodo para establecer el ID del equipo
    public void setIdEquipo(Integer idEquipo) {
        if (idEquipo != null) {
            this.idequipo = new Equipo();
            this.idequipo.setId_equipo(idEquipo);
        } else {
            this.idequipo = null;
        }
    }
}