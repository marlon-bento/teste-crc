package com.teste.marlon.puc_crc.evento.Entity;

import com.teste.marlon.puc_crc.evento.Dto.EventoDtoMini;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Evento")
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
    private LocalDateTime prazoInscricao;

    @Column(name = "prazo_submissao")
    private LocalDateTime prazoSubmissao;

    public Evento(){

    }
    public Evento(EventoDtoMini event){
        this.nome = event.getNome();
        this.descricao= event.getDescricao();
        this.ativo = event.isAtivo();


    }

}
