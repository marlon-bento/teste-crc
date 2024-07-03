package com.teste.marlon.puc_crc.inscricao.Controller;


import com.teste.marlon.puc_crc.inscricao.Dto.InscricaoDto;
import com.teste.marlon.puc_crc.inscricao.Dto.InscricaoDtoMini;
import com.teste.marlon.puc_crc.inscricao.Entity.Inscricao;
import com.teste.marlon.puc_crc.inscricao.Service.InscricaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/inscricoes")
public class InscricaoController {
    @Autowired
    private InscricaoService inscricaoService;
    @PostMapping
    public ResponseEntity<?> fazerInscricao(@RequestBody InscricaoDtoMini inscricao){
        Inscricao aux = inscricaoService.fazerInscricao(inscricao.getEventoId(), inscricao.getParticipanteId());
        if(aux == null){
            //status 404 Not Found
            return ResponseEntity.status(404).body("O id de evento ou participante não existe");
        }else{
            //status 201 CREATED
            return ResponseEntity.status(201).body(aux);
        }

    }
    @GetMapping
    public List<InscricaoDto> listarTodasInscricoes(){
        return listInscricaoParaInscricaoDto(inscricaoService.listarTodasInscricoes());
    }
    @GetMapping("/buscar/participantes/{id}")
    public List<InscricaoDto> listarTodosQueTemIdParticipante(@PathVariable("id") Integer idPassado ){
        return listInscricaoParaInscricaoDto(inscricaoService.listarTodosQueTemIdParticipante(idPassado));
    }
    @GetMapping("/listar/participantes/evento/{id}")
    public List<Inscricao> listarParticipantesInscritoEmEvento(@PathVariable("id") Integer idPassado){
        return inscricaoService.listarParticipantesInscritoEmEvento(idPassado);
    }
    public List<InscricaoDto> listInscricaoParaInscricaoDto(List<Inscricao> listaInscricoes){
        List<InscricaoDto> aux = new ArrayList<>();
        for(int i = 0; i< listaInscricoes.size(); i++ ){
            aux.add(new InscricaoDto(listaInscricoes.get(i)));
        }
        return aux;
    }
    @DeleteMapping("/deletar/participantes/{id}")
    public ResponseEntity<?> deletaPorParticipanteId(@PathVariable("id") Integer participanteId){
        inscricaoService.deletaPorParticipanteId(participanteId);
        //status 204 no content
        return ResponseEntity.status(204).build();
    }
    @DeleteMapping("/cancelar/inscricao/evento")
    public ResponseEntity<?> cancelarInscricaoParticipante(@RequestBody InscricaoDtoMini inscricao){
        inscricaoService.cancelarInscricaoParticipante(inscricao.getParticipanteId(), inscricao.getEventoId());
        //status 204 no content
        return ResponseEntity.status(204).build();
    }

}
