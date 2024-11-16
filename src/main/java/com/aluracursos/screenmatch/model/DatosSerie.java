package com.aluracursos.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosSerie(
        @JsonAlias("Title")
        String titulo ,
        @JsonAlias("totalSeasons")
        Integer totalDeTemporadas ,
        @JsonAlias("imdbRating")
        String evaluacion ,
        @JsonAlias("Genre")
        String genero ,
        @JsonAlias("Plot")
        String sinopsis ,
        @JsonAlias("Actors")
        String Actores ,
        @JsonAlias("Poster")
        String poster ,
        @JsonAlias("Year")
        String aniosEmision ,
        @JsonAlias("Released")
        String fechaEmisionPrimerCapitulo ,
        @JsonAlias("Writer")
        String escritores



) {
}
