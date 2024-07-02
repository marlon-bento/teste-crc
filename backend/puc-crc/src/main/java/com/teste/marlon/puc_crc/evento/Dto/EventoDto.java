package com.teste.marlon.puc_crc.evento.Dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter @Setter
public class EventoDto {
    private Integer id;
    private String nome;
    private String descricao;
    private boolean ativo;
    private LocalDateTime prazoInscricao;
    private LocalDateTime prazoSubmissao;

}
