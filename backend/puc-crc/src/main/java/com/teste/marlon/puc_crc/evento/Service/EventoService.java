package com.teste.marlon.puc_crc.evento.Service;


import com.teste.marlon.puc_crc.evento.Dto.EventoDtoMini;
import com.teste.marlon.puc_crc.evento.Entity.Evento;
import com.teste.marlon.puc_crc.evento.Repository.EventoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EventoService {

    @Autowired
    private EventoRepository repositorio;
    @Transactional
    public Evento cadastrarEvento(Evento evento){
        return repositorio.save(evento);
    }
    public List<Evento> mostrarTodosEventos(){
        return repositorio.findAll();
    }
    public Evento buscarEventoPorId(Integer id){
        //retorna um usuário ou lança uma excecao
        return repositorio.findById(id).orElseThrow(() -> new RuntimeException("Evento não encontrado"));
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
    public boolean deletarEvento(Integer id){
        if (repositorio.existsById(id)) {
            //participante existe e foi deletado
            repositorio.deleteById(id);
            return true;
        } else {
            //participante não existe
            return false;
        }
    }
}

