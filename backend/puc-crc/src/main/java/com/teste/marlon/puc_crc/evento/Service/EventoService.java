package com.teste.marlon.puc_crc.evento.Service;


import com.teste.marlon.puc_crc.evento.Dto.EventoDtoMini;
import com.teste.marlon.puc_crc.evento.Entity.Evento;
import com.teste.marlon.puc_crc.evento.Repository.EventoRepository;
import com.teste.marlon.puc_crc.inscricao.Repository.InscricaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class EventoService {

    @Autowired
    private EventoRepository repositorio;
    @Autowired
    private InscricaoRepository inscricaoRepository;
    @Transactional
    public Evento cadastrarEvento(Evento evento){
        return repositorio.save(evento);
    }
    public List<Evento> mostrarTodosEventos(){
        return repositorio.findAll();
    }
    public Evento buscarEventoPorId(Integer id){
        //retorna um usuário ou null se não achar
        return repositorio.findById(id).orElse(null);
    }
    @Transactional
    public Evento modificarEvento(Integer id, EventoDtoMini evento){
        /* como esta em estado persistente por ter buscado o id
         o que for alterado sera alterado diretamente no banco automaticamente
         enquanto a requisicao nao terminar o hibernate tem o objeto sob controle*/
        Evento aux = buscarEventoPorId(id);
        aux.setNome(evento.getNome());
        aux.setDescricao(evento.getDescricao());
        aux.setAtivo(evento.isAtivo());

        return aux;
    }
    @Transactional
    public boolean deletarEvento(Integer id){
        if (repositorio.existsById(id)) {
            //deletar todas inscrições relacionadas a esse id
            inscricaoRepository.deleteByEventoId(id);
            //participante existe e foi deletado
            repositorio.deleteById(id);
            return true;
        } else {
            //participante não existe
            return false;
        }
    }
    public List<Evento> filtrarEventosAtivos(){
        return repositorio.findAllByAtivo();
    }
    public List<Evento> filtrarEventosPorPrazoSubmissao(LocalDate prazo){
        return repositorio.findAllByPrazoSubmissao(prazo);
    }
    public List<Evento> filtrarEventosPorPrazoInscricao(LocalDate prazo){
        return repositorio.findAllByPrazoInscricao(prazo);
    }
    public List<Evento> filtrarEventosInativos(){
        return repositorio.findAllByInativo();
    }
}

