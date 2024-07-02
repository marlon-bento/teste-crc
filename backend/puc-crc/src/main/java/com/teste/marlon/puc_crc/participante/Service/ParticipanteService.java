package com.teste.marlon.puc_crc.participante.Service;



import com.teste.marlon.puc_crc.participante.Dto.ParticipanteDtoMini;
import com.teste.marlon.puc_crc.participante.Entity.Participante;
import com.teste.marlon.puc_crc.participante.Repository.ParticipanteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipanteService {
    @Autowired
    private ParticipanteRepository repositorio;
    @Transactional
    public Participante cadastrarParticipante(Participante participante){
        return repositorio.save(participante);
    }
    public List<Participante> mostrarTodosParticipantes(){
        return repositorio.findAll();
    }
    public Participante buscarParticipantePorId(Integer id){
        //retorna um usuário ou lança uma excecao
        return repositorio.findById(id).orElseThrow(() -> new RuntimeException("Participante não encontrado"));
    }
    @Transactional
    public Participante modificarParticipante(Integer id, ParticipanteDtoMini participante){
         /* como esta em estado persistente por ter buscado o id
         o que for alterado sera alterado diretamente no banco automaticamente
         enquanto a requisicao nao terminar o hibernate tem o objeto sob controle*/
        Participante aux = buscarParticipantePorId(id);
        aux.setNome(participante.getNome());
        aux.setEmail(participante.getEmail());
        aux.setAtivo(participante.isAtivo());
        return aux;
    }
    public boolean deletarParticipante(Integer id){
        if(repositorio.existsById(id)){
            //participante existe e foi deletado
            repositorio.deleteById(id);
            return true;

        }else{
            //participante não existe
            return false;
        }
    }
}
