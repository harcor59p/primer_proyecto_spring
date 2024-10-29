package com.aluracursos.screenmatch.principal;

import java.util.Arrays;
import java.util.List;

public class EjemploStreams {
    public void muestraEjemplo(){
        List<String> nombres = Arrays.asList("Laura","Stiven","Angelica","Diana","Jeidy","Valentina","Jarvi") ;
        nombres.stream()
                .sorted()
                .limit(4)
                .filter(n -> n.startsWith("D"))
                .map(n -> n.toUpperCase())
                .forEach(System.out::println);

    }
}
