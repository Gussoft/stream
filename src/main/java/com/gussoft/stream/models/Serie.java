package com.gussoft.stream.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String sinopsis;
    private String episodios;
    private String banner;
    private String thumbs;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "serie")
    private List<Detalle> detalles;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "serie")
    private List<Comentario> comentarios;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date fechae;

    public Serie() {
    }

    public Serie(Long id, String nombre, String sinopsis, String episodios, String banner,
                 String thumbs, Date fechae, List<Detalle> detalles, List<Comentario> comentarios) {
        this.id = id;
        this.nombre = nombre;
        this.sinopsis = sinopsis;
        this.episodios = episodios;
        this.banner = banner;
        this.thumbs = thumbs;
        this.fechae = fechae;
        this.detalles = detalles;
        this.comentarios = comentarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getEpisodios() {
        return episodios;
    }

    public void setEpisodios(String episodios) {
        this.episodios = episodios;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getThumbs() {
        return thumbs;
    }

    public void setThumbs(String thumbs) {
        this.thumbs = thumbs;
    }

    public Date getFechae() {
        return fechae;
    }

    public void setFechae(Date fechae) {
        this.fechae = fechae;
    }

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles;
    }

    public void addDetalle(Detalle detalle){
        detalles.add(detalle);
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
