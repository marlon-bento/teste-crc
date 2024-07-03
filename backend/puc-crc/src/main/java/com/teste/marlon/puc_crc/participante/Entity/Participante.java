package com.teste.marlon.puc_crc.participante.Entity;

import com.teste.marlon.puc_crc.participante.Dto.ParticipanteDtoMini;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;




@Entity
@Table(name = "participante")
@Getter @Setter
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

    public Participante(){

    }
    public Participante(ParticipanteDtoMini part){
        this.nome = part.getNome();
        this.email= part.getEmail();
        this.ativo = part.isAtivo();
    }
}
