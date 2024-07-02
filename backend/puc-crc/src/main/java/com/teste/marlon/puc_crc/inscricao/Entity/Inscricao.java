package com.teste.marlon.puc_crc.inscricao.Entity;

import com.teste.marlon.puc_crc.evento.Entity.Evento;
import com.teste.marlon.puc_crc.participante.Entity.Participante;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
@Entity
@Table(name = "Inscricao")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
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
}
