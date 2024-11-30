package com.aluracursos.screenmatch.controller;

import com.aluracursos.screenmatch.dto.EpisodioDTO;
import com.aluracursos.screenmatch.dto.SerieDTO;
import com.aluracursos.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/series")
public class SerieController {
    @Autowired
    private SerieService servicio ;

    @GetMapping()
    public List<SerieDTO> obtenerTodasLasSerires(){
        return servicio.obtenerTodasLasSerires();
    }

    @GetMapping("/top5")
    public List<SerieDTO> obtenerTop5(){
        return servicio.obtenerTop5();
    }

    @GetMapping("/lanzamientos")
    public List<SerieDTO> obtenerLanzamintosMasRecientes(){
        return servicio.obtenerLanzamientosMasRecientes();
    }

    @GetMapping("/{id}")
    public SerieDTO obtenerPorID(@PathVariable Long id){
        return servicio.obtenerPorId(id) ;
    }

    @GetMapping("/{id}/temporadas/todas")
        public List<EpisodioDTO> obtenerTodasLasTemporadas(@PathVariable Long id){
        return servicio.obtenerTodasLasTemporadas(id);

    }

    @GetMapping("/{id}/temporadas/{numeroTemporada}")
    public List<EpisodioDTO> obtenerTemporadasPorNumero(@PathVariable Long id , @PathVariable Integer numeroTemporada){
        return servicio.obtrenerTemporadasPorNumero(id , numeroTemporada) ;
    }

    @GetMapping("/categoria/{nombreGenero}")
    public List<SerieDTO> obtenerSeriesPorCategoria(@PathVariable String nombreGenero){
        return  servicio.obtenerSeriesPorCategoria(nombreGenero) ;
    }


}
