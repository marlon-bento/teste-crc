package com.teste.marlon.puc_crc.participante.Dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipanteDto {

    private Integer id;
    private String email;
    private String nome;
    private boolean ativo;
}
