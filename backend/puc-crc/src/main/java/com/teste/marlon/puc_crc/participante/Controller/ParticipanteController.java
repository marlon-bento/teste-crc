package com.teste.marlon.puc_crc.participante.Controller;

import com.teste.marlon.puc_crc.participante.Dto.ParticipanteDtoMini;
import com.teste.marlon.puc_crc.participante.Entity.Participante;
import com.teste.marlon.puc_crc.participante.Service.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/participantes")
public class ParticipanteController {
    @Autowired
    private ParticipanteService participanteService;

    @PostMapping
    public ResponseEntity<Participante> cadastrarParticipante(@RequestBody ParticipanteDtoMini participante){
        Participante aux = participanteService.cadastrarParticipante(new Participante(participante));
        //status 201 CREATED
        return ResponseEntity.status(201).body(aux);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Participante> buscarParticipantePorId(@PathVariable("id") Integer idPassado){
        Participante aux = participanteService.buscarParticipantePorId(idPassado);
        //status 200 ok e o evento que foi buscado
        return ResponseEntity.status(200).body(aux);
    }
    @GetMapping
    public List<Participante> mostrarTodosParticipantes(){
        return participanteService.mostrarTodosParticipantes();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Participante> modificarParticipante(@PathVariable("id") Integer idPassado, @RequestBody ParticipanteDtoMini participante){
        Participante aux = participanteService.modificarParticipante(idPassado, participante);
        //status 200 ok e o evento que foi alterado
        return ResponseEntity.status(200).body(aux);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarEvento(@PathVariable("id") Integer idPassado){
        boolean verifica = participanteService.deletarParticipante(idPassado);
        if(verifica == true){
            //status 204 no content
            return ResponseEntity.status(204).build();
        }else{
            //status 404 not found (não existe o id passado)
            return ResponseEntity.status(404).body("Participante não existe");
        }
    }



}
