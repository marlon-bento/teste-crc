package com.teste.marlon.puc_crc.inscricao.Repository;

import com.teste.marlon.puc_crc.inscricao.Entity.Inscricao;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InscricaoRepository extends JpaRepository<Inscricao, Integer> {
    @Query(value = "SELECT * FROM inscricao WHERE participante_id = :participanteId;", nativeQuery = true)
    List<Inscricao> findAllByParticipanteId(Integer participanteId);
    @Query(value = "SELECT * FROM inscricao WHERE evento_id = :eventoId;", nativeQuery = true)
    List<Inscricao> findAllByEventoId(Integer eventoId);

    //transaction pra falar pra cuidar da transação e modifyng para ele saber que não é pesquisa e que vai ter mudança no banco
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM inscricao WHERE participante_id = :participanteId;", nativeQuery = true)
    void deleteByParticipanteId(Integer participanteId);
    //transaction pra falar pra cuidar da transação e modifyng para ele saber que não é pesquisa e que vai ter mudança no banco
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM inscricao WHERE evento_id = :eventoId;", nativeQuery = true)
    void deleteByEventoId(Integer eventoId);

    //transaction pra falar pra cuidar da transação e modifyng para ele saber que não é pesquisa e que vai ter mudança no banco
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM inscricao WHERE participante_id = :participanteId AND evento_id = :eventoId;", nativeQuery = true)
    void cancelarInscricaoParticipante(Integer participanteId, Integer eventoId);
}
