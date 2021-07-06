package com.gussoft.stream.models;

import javax.persistence.*;

@Entity
public class Detalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String capitulo;
    private String temporada;
    private String estreno;
    private String duracion;
    private String link1;
    private String link2;

    @ManyToOne(fetch = FetchType.LAZY)
    private Serie serie;

    public Detalle() {
    }

    public Detalle(Long id, String capitulo, String temporada, String estreno, String duracion, String link1, String link2, Serie serie) {
        this.id = id;
        this.capitulo = capitulo;
        this.temporada = temporada;
        this.estreno = estreno;
        this.duracion = duracion;
        this.link1 = link1;
        this.link2 = link2;
        this.serie = serie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(String capitulo) {
        this.capitulo = capitulo;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public String getEstreno() {
        return estreno;
    }

    public void setEstreno(String estreno) {
        this.estreno = estreno;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getLink1() {
        return link1;
    }

    public void setLink1(String link1) {
        this.link1 = link1;
    }

    public String getLink2() {
        return link2;
    }

    public void setLink2(String link2) {
        this.link2 = link2;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }
}
