package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.model.DatosSerie;
import com.aluracursos.screenmatch.model.DatosTemporadas;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in) ;
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://www.omdbapi.com?t=" ;
    private final String API_KEY = "&apikey=4f54209a" ;
    private ConvierteDatos conversor = new ConvierteDatos() ;



    public void muestraElMenu(){
        System.out.println("Por favor escribe el nombre de la serie que deseas buscar: ");
        // Trae la información basica de la serie indicada
        var nombreSerie = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + nombreSerie.replace(" " , "+") + API_KEY) ;
        var datos = conversor.obtenerDatos(json , DatosSerie.class) ;
        System.out.println(datos);
        // Trae la información de los episodios de todas las temportadas de la serie indicada
        List<DatosTemporadas> temporadas = new ArrayList<>() ;
        for (int i = 1; i <= datos.totalDeTemporadas(); i++) {
            json = consumoAPI.obtenerDatos(URL_BASE + nombreSerie.replace(" " , "+") + "&Season="+i+API_KEY) ;
            var datosTemporadas = conversor.obtenerDatos(json , DatosTemporadas.class) ;
            temporadas.add(datosTemporadas);

        } temporadas.forEach(System.out::println);

    }
}
