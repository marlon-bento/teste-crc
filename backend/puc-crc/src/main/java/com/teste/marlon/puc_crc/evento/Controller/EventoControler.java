package com.teste.marlon.puc_crc.evento.Controller;

import com.teste.marlon.puc_crc.evento.Dto.EventoDtoMini;
import com.teste.marlon.puc_crc.evento.Entity.Evento;
import com.teste.marlon.puc_crc.evento.Service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("api/v1/eventos")
public class EventoControler {

    @Autowired
    private EventoService eventoService;

    @PostMapping
    public ResponseEntity<Evento> cadastrarEvento(@RequestBody EventoDtoMini evento){
        Evento aux =  eventoService.cadastrarEvento(new Evento(evento));
        //status 201 CREATED
        return ResponseEntity.status(201).body(aux);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarEventoPorId(@PathVariable("id") Integer idPassado){
        Evento aux = eventoService.buscarEventoPorId(idPassado);
        //status 200 ok e o evento que foi buscado
        return ResponseEntity.status(200).body(aux);
    }
    @GetMapping
    public List<Evento> mostrarTodosEventos(){
        return eventoService.mostrarTodosEventos();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Evento> modificarEvento(@PathVariable("id") Integer idPassado, @RequestBody EventoDtoMini evento){
        Evento aux = eventoService.modificarEvento(idPassado, evento);
        //status 200 ok e o evento que foi alterado
        return ResponseEntity.status(200).body(aux);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarEvento(@PathVariable("id") Integer idPassado ){
        boolean verifica = eventoService.deletarEvento(idPassado);
        if(verifica == true){
            //status 204 no content
            return ResponseEntity.status(204).build();
        }else{
            //status 404 not found (não existe o id passado)
            return ResponseEntity.status(404).body("Evento não existe");
        }

    }
}
