package com.teste.marlon.puc_crc.participante.Entity;

import com.teste.marlon.puc_crc.evento.Dto.EventoDtoMini;
import com.teste.marlon.puc_crc.participante.Dto.ParticipanteDtoMini;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "Participante")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Participante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email", nullable= false)
    private String email;

    @Column(name = "nome", nullable= false)
    private String nome;

    @Column(name = "ativo", nullable= false)
    private boolean ativo;

    public Participante(ParticipanteDtoMini part){
        this.nome = part.getNome();
        this.email= part.getEmail();
        this.ativo = part.isAtivo();
    }
}
