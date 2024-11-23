package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.model.*;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in) ;
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://www.omdbapi.com?t=" ;
    private final String API_KEY = "&apikey=4f54209a" ;
    private ConvierteDatos conversor = new ConvierteDatos() ;
    private List<DatosSerie> datosSeries = new ArrayList<>() ;



    public void muestraElMenu(){
        var opcion = -1;
        while(opcion != 0){
            var menu = """
                    1- Buscar series
                    2- Buscar episodios
                    3- Mostrar series buscadas
                    
                    
                    0- Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSeerie();
                    break;
                case 3:
                    mostrarSeriesBuscadas();
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción invalida");
            }

        }

    }


    private DatosSerie getDatosSerie() {
        System.out.println("Por favor escribe el nombre de la serie que deseas buscar: ");
        // Trae la información basica de la serie indicada
        var nombreSerie = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + nombreSerie.replace(" " , "+") + API_KEY) ;
        System.out.println(json);
        DatosSerie datos = conversor.obtenerDatos(json , DatosSerie.class);
        return datos;
    }

    private void buscarEpisodioPorSeerie(){
        DatosSerie datosSerie = getDatosSerie();
        // Trae la información de los episodios de todas las temportadas de la serie indicada
        List<DatosTemporadas> temporadas = new ArrayList<>();

        for (int i = 1; i < datosSerie.totalDeTemporadas() ; i++) {
            var nombreSerie = teclado.nextLine();
            var json = consumoAPI.obtenerDatos(URL_BASE + nombreSerie.replace(" " , "+") + "&Season="+i+API_KEY) ;
            DatosTemporadas datosTemporadas = conversor.obtenerDatos(json , DatosTemporadas.class);
            temporadas.add(datosTemporadas);
        }
        temporadas.forEach(System.out::println);
    }

    private void buscarSerieWeb(){
        DatosSerie datos = getDatosSerie();
        datosSeries.add(datos);
        System.out.println(datos);
    }

    private void mostrarSeriesBuscadas() {
        List<Serie> series = new ArrayList<>();
        series = datosSeries.stream()
                .map(d -> new Serie(d))
                .collect(Collectors.toList());

        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }
}





//        var datos = conversor.obtenerDatos(json , DatosSerie.class) ;
//        System.out.println(datos);
//        List<DatosTemporadas> temporadas = new ArrayList<>() ;
//        for (int i = 1; i <= datos.totalDeTemporadas(); i++) {
//            json = consumoAPI.obtenerDatos(URL_BASE + nombreSerie.replace(" " , "+") + "&Season="+i+API_KEY) ;
//            var datosTemporadas = conversor.obtenerDatos(json , DatosTemporadas.class) ;
//            temporadas.add(datosTemporadas);
//
//        } temporadas.forEach(System.out::println);
//        //Mosstrar solo el titulo de los episodios para las temporadas
////        for (int i = 0; i < datos.totalDeTemporadas(); i++) {
////            List<DatosEpisodio> episodiosTemporada = temporadas.get(i).episodios() ;
////            for (int j = 0; j < episodiosTemporada.size() ; j++) {
////                System.out.println(episodiosTemporada.get(j).titulo());
////            }
////        }
//        // temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
//
//        // Convertir todos las informaciones a una lista de tipo DatosEpisodio
//
//        List<DatosEpisodio> datosEpisodios = temporadas.stream()
//                .flatMap(t -> t.episodios().stream())
//                .collect(Collectors.toList()) ;
//
//        // Top 5 mejores episodios
//
//
////        System.out.println("Top 5 mejores episodios");
////        datosEpisodios.stream()
////                .filter(e -> !e.evaluacion().equalsIgnoreCase("N/A"))
////                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
////                .limit(5)
////                .forEach(System.out::println);
//
//
//        // Convirtiendo los datos a una lista del tipo Episodio
//
//        List<Episodio> episodios = temporadas.stream()
//                .flatMap(t -> t.episodios().stream()
//                        .map(d -> new Episodio(t.numero() , d)))
//                .collect(Collectors.toList());
//
////        episodios.forEach(System.out::println);
//
//        // Busqueda de episodios a partir de un año
////        System.out.println("Por favor ingrese el año a partir del cual deseas ver los episodios");
////        var fecha = teclado.nextInt();
////        teclado.nextLine() ;
////
////        LocalDate fechaBusqueda = LocalDate.of(fecha , 1 , 1) ;
////
////        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
////        episodios.stream()
////                .filter(e -> e.getFechaDeLanzamiento() != null && e.getFechaDeLanzamiento().isAfter(fechaBusqueda))
////                .forEach(e -> System.out.println(
////                        "Temporada: " + e.getTemporada() +
////                                " Episodio: " + e.getTitulo() +
////                                " Fecha de Lanzamiento: " + e.getFechaDeLanzamiento().format(dtf)
////                ));
//
//        // Busca episodio por parte del titulo
////        System.out.println("Por favro escriba el titulo del episodio ver:");
////        var pedazoTitulo = teclado.nextLine();
////        Optional<Episodio> episodioBuscado = episodios.stream()
////                .filter(e -> e.getTitulo().toUpperCase().contains(pedazoTitulo.toUpperCase()))
////                .findFirst();
////        if (episodioBuscado.isPresent()){
////            System.out.println("Episodio encontrado");
////            System.out.println("Los datos son: " + episodioBuscado.get());
////        }else {
////            System.out.println("Episodio no encontrado");
////        }
//
//        // evaluacion de promedio de temporadas
//
//        Map<Integer , Double> evaluacionesPorTemporada = episodios.stream()
//                .filter(e -> e.getEvaluacion() > 0.0)
//                .collect(Collectors.groupingBy(Episodio::getTemporada , Collectors.averagingDouble(Episodio::getEvaluacion))) ;
//        System.out.println(evaluacionesPorTemporada);
//
//        DoubleSummaryStatistics est = episodios.stream()
//                .filter(e -> e.getEvaluacion() > 0.0)
//                .collect(Collectors.summarizingDouble(Episodio::getEvaluacion));
//        System.out.println("Total de episodios evaluados: " + est.getCount());
//        System.out.println("El episodio mejor valorado obtuvo: " + est.getMax());
//        System.out.println("El espisodio peor valorado obtuvo: " + est.getMin());
//        System.out.println("El valor promedio de los episodio es: " + est.getAverage());
//    }
//}
