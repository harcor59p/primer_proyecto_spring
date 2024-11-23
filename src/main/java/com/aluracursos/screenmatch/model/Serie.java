package com.aluracursos.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;
import java.util.OptionalDouble;

@Entity
@Table(name = "series")

public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id ;
    @Column(unique = true)
    private String titulo ;
    private Integer totalDeTemporadas ;
    private Double evaluacion ;
    @Enumerated(EnumType.STRING)
    private Categoria genero ;
    private String sinopsis ;
    private String actores ;
    private String poster ;
    private String aniosEmision ;
    private String fechaEmisionPrimerCapitulo ;
    private String escritores ;
    @OneToMany(mappedBy = "serie" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<Episodio> episodios ;

    public  Serie(){

    }

    public Serie(DatosSerie datosSerie){
        this.titulo = datosSerie.titulo();
        this.totalDeTemporadas = datosSerie.totalDeTemporadas();
        this.evaluacion = OptionalDouble.of(Double.valueOf(datosSerie.evaluacion())).orElse(0);
        this.poster = datosSerie.poster();
        this.genero = Categoria.fromString(datosSerie.genero().split(",")[0].trim());
        this.actores = datosSerie.actores();
        this.sinopsis = datosSerie.sinopsis() ;
    }

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        episodios.forEach(e ->e.setSerie(this));
        this.episodios = episodios;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalDeTemporadas() {
        return totalDeTemporadas;
    }

    public void setTotalDeTemporadas(Integer totalDeTemporadas) {
        this.totalDeTemporadas = totalDeTemporadas;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getActores() {
        return actores;
    }

    public void setActores(String actores) {
        actores = actores;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getAniosEmision() {
        return aniosEmision;
    }

    public void setAniosEmision(String aniosEmision) {
        this.aniosEmision = aniosEmision; 
    }

    public String getFechaEmisionPrimerCapitulo() {
        return fechaEmisionPrimerCapitulo;
    }

    public void setFechaEmisionPrimerCapitulo(String fechaEmisionPrimerCapitulo) {
        this.fechaEmisionPrimerCapitulo = fechaEmisionPrimerCapitulo;
    }

    public String getEscritores() {
        return escritores;
    }

    public void setEscritores(String escritores) {
        this.escritores = escritores;
    }

    @Override
    public String toString() {
        return  "genero=" + genero +
                ", titulo='" + titulo + '\'' +
                ", totalDeTemporadas=" + totalDeTemporadas +
                ", evaluacion=" + evaluacion +
                ", sinopsis='" + sinopsis + '\'' +
                ", Actores='" + actores + '\'' +
                ", poster='" + poster + '\'' +
                ", aniosEmision='" + aniosEmision + '\'' +
                ", fechaEmisionPrimerCapitulo='" + fechaEmisionPrimerCapitulo + '\'' +
                ", escritores='" + escritores + '\'' +
                ", episodios='" + episodios + '\'' ;

    }
}
