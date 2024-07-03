package com.teste.marlon.puc_crc.participante.Dto;


import com.teste.marlon.puc_crc.participante.Entity.Participante;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipanteDto {

    private Integer id;
    private String email;
    private String nome;
    private boolean ativo;

    public ParticipanteDto(Participante participante){
        this.id = participante.getId();
        this.email = participante.getEmail();
        this.nome = participante.getNome();
        this.ativo = participante.isAtivo();
    }
}
