package com.teste.marlon.puc_crc.evento.Dto;

import com.teste.marlon.puc_crc.evento.Entity.Evento;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter @Setter
public class EventoDto {
    private Integer id;
    private String nome;
    private String descricao;
    private boolean ativo;
    private LocalDate prazoInscricao;
    private LocalDate prazoSubmissao;
    public EventoDto(Evento evento){
        this.id = evento.getId();
        this.nome = evento.getNome();
        this.descricao = evento.getDescricao();
        this.ativo = evento.isAtivo();
        this.prazoInscricao = evento.getPrazoInscricao();
        this.prazoSubmissao = evento.getPrazoSubmissao();
    }

}
