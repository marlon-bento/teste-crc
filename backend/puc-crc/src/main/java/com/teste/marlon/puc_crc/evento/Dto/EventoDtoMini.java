package com.teste.marlon.puc_crc.evento.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class EventoDtoMini {

    private String nome;
    private String descricao;
    private boolean ativo;
}
