package com.aluracursos.screenmatch.model;

public enum Categoria {
    ACCION ("Action"),
    ROMANCE ("Romance"),
    COMEDIA ("Comedy"),
    DRAMA ("Drama"),
    CRIMEN ("Crime");

    private String CategoriaOmdb ;

    Categoria(String categoriaOmdb){
        this.CategoriaOmdb = categoriaOmdb ;
    }
}
