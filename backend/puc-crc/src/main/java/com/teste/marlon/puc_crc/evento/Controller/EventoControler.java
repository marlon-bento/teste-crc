package com.teste.marlon.puc_crc.evento.Controller;

import com.teste.marlon.puc_crc.evento.Dto.EventoDto;
import com.teste.marlon.puc_crc.evento.Dto.EventoDtoMini;
import com.teste.marlon.puc_crc.evento.Dto.PrazoIns;
import com.teste.marlon.puc_crc.evento.Dto.PrazoSub;
import com.teste.marlon.puc_crc.evento.Entity.Evento;
import com.teste.marlon.puc_crc.evento.Service.EventoService;
import com.teste.marlon.puc_crc.participante.Entity.Participante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/v1/eventos")
public class EventoControler {

    @Autowired
    private EventoService eventoService;
    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<Evento> cadastrarEvento(@RequestBody EventoDtoMini evento){
        Evento aux =  eventoService.cadastrarEvento(new Evento(evento));
        //status 201 CREATED
        return ResponseEntity.status(201).body(aux);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarEventoPorId(@PathVariable("id") Integer idPassado){
        Evento aux = eventoService.buscarEventoPorId(idPassado);
        if(aux == null){
            //404 not found id não encontrado
            return ResponseEntity.status(404).body("id não foi encontrado");
        }else{
            //status 200 ok e o evento que foi buscado
            return ResponseEntity.status(200).body(aux);
        }


    }
    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Evento> mostrarTodosEventos(){
        return eventoService.mostrarTodosEventos();
    }
    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ResponseEntity<Evento> modificarEvento(@PathVariable("id") Integer idPassado, @RequestBody EventoDtoMini evento){
        Evento aux = eventoService.modificarEvento(idPassado, evento);
        //status 200 ok e o evento que foi alterado
        return ResponseEntity.status(200).body(aux);
    }
    @CrossOrigin(origins = "*")
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
    @CrossOrigin(origins = "*")
    @GetMapping("/filtrar/ativos")
    public List<Evento> filtrarEventosAtivos(){
        return eventoService.filtrarEventosAtivos();
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/filtrar/inativos")
    public List<Evento> filtrarEventosInativos(){

        return eventoService.filtrarEventosInativos();
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/filtrar/prazo-submicao")
    public List<Evento> filtrarEventosPorPrazoSubmissao(@RequestBody PrazoSub prazo){

        return eventoService.filtrarEventosPorPrazoSubmissao(prazo.getPrazoSubmissao());
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/filtrar/prazo-inscricao")
    public List<Evento> filtrarEventosPorPrazoInscricao(@RequestBody PrazoIns prazo){

        return eventoService.filtrarEventosPorPrazoInscricao(prazo.getPrazoInscricao());
    }
    public List<EventoDto> listEventoParaEventoDto(List<Evento> listaEventos){
        List<EventoDto> aux = new ArrayList<>();
        for(int i = 0; i < listaEventos.size(); i++ ){
            aux.add(new EventoDto(listaEventos.get(i)));
        }
        return aux;
    }
}
