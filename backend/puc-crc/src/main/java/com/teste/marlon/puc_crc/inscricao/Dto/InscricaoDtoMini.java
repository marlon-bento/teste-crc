package com.teste.marlon.puc_crc.inscricao.Dto;

import com.teste.marlon.puc_crc.evento.Entity.Evento;
import com.teste.marlon.puc_crc.participante.Entity.Participante;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter @Setter
public class InscricaoDtoMini {

    private Integer id;
    private Integer participanteId;
    private Integer eventoId;

}
