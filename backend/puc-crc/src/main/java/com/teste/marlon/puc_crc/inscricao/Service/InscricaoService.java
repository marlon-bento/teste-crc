package com.teste.marlon.puc_crc.inscricao.Service;

import com.teste.marlon.puc_crc.evento.Entity.Evento;
import com.teste.marlon.puc_crc.evento.Service.EventoService;
import com.teste.marlon.puc_crc.inscricao.Entity.Inscricao;
import com.teste.marlon.puc_crc.inscricao.Repository.InscricaoRepository;
import com.teste.marlon.puc_crc.participante.Entity.Participante;
import com.teste.marlon.puc_crc.participante.Service.ParticipanteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscricaoService {
    @Autowired
    private InscricaoRepository repositorio;
    @Autowired
    private EventoService eventoService;
    @Autowired
    private ParticipanteService participanteService;

    @Transactional
    public Inscricao fazerInscricao (Integer eventoId, Integer participanteId){
        Evento event = eventoService.buscarEventoPorId(eventoId);
        Participante part = participanteService.buscarParticipantePorId(participanteId);
        if(event != null && part != null){

            return repositorio.save(new Inscricao(event, part));
        }else {
            return null;
        }
    }
    public List<Inscricao> listarTodosQueTemIdParticipante(Integer participanteId){
        return repositorio.findAllByParticipanteId(participanteId);
    }
    public List<Inscricao> listarParticipantesInscritoEmEvento(Integer eventoId){
        return repositorio.findAllByEventoId(eventoId);
    }
    public List<Inscricao>  listarTodasInscricoes(){
        return repositorio.findAll();
    }
    public void deletaPorParticipanteId(Integer participanteId){
         repositorio.deleteByParticipanteId(participanteId);
    }
    public void cancelarInscricaoParticipante(Integer participanteId, Integer eventoId){
        repositorio.cancelarInscricaoParticipante(participanteId, eventoId);
    }
}
