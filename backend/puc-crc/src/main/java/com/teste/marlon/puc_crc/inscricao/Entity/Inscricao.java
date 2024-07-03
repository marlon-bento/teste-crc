package com.teste.marlon.puc_crc.inscricao.Entity;

import com.teste.marlon.puc_crc.evento.Entity.Evento;
import com.teste.marlon.puc_crc.participante.Entity.Participante;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "inscricao")
@Getter @Setter
public class Inscricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "participante_id", nullable = false)
    private Participante participante;

    @Column(name = "data")
    private LocalDateTime data;

    /*antes de persistir no banco de dados vai executar essa funcao
    e pegar a data atual*/
    @PrePersist
    protected void onCreate() {
        this.data = LocalDateTime.now();
    }
    public Inscricao(){

    }
    public Inscricao(Evento event, Participante part){
        this.evento = event;
        this.participante = part;
    }
}
