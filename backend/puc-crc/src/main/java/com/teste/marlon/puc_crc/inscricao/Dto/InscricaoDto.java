package com.teste.marlon.puc_crc.inscricao.Dto;


import com.teste.marlon.puc_crc.inscricao.Entity.Inscricao;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
@Getter @Setter
public class InscricaoDto {
    private Integer participanteId;
    private Integer eventoId;
    private LocalDateTime data;

    public InscricaoDto (Inscricao inscricao){
        this.participanteId = inscricao.getParticipante().getId();
        this.eventoId = inscricao.getEvento().getId();
        this.data = inscricao.getData();
    }
}
