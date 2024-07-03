package com.teste.marlon.puc_crc.evento.Repository;

import com.teste.marlon.puc_crc.evento.Entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
    @Query(value = "SELECT * FROM evento WHERE ativo = true", nativeQuery = true)
    List<Evento> findAllByAtivo();
    @Query(value = "SELECT * FROM evento WHERE ativo = false;", nativeQuery = true)
    List<Evento> findAllByInativo();

    @Query(value= "SELECT * FROM evento WHERE prazo_submissao = :prazoSubimissao;", nativeQuery = true)
    List<Evento> findAllByPrazoSubmissao(LocalDate prazoSubimissao);
    @Query(value= "SELECT * FROM evento WHERE prazo_inscricao = :prazoInscricao;", nativeQuery = true)
    List<Evento> findAllByPrazoInscricao(LocalDate prazoInscricao);


}
