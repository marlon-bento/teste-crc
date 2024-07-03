package com.teste.marlon.puc_crc.evento.Entity;

import com.teste.marlon.puc_crc.evento.Dto.EventoDtoMini;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "evento")
@Getter @Setter
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "ativo")
    private boolean ativo;

    @Column(name = "prazo_inscricao")
    private LocalDate prazoInscricao;

    @Column(name = "prazo_submissao")
    private LocalDate prazoSubmissao;

    public Evento(){

    }
    public Evento(EventoDtoMini event){
        this.nome = event.getNome();
        this.descricao= event.getDescricao();
        this.ativo = event.isAtivo();
        this.prazoInscricao = event.getPrazoInscricao();
        this.prazoSubmissao = event.getPrazoSubmissao();

    }

}
