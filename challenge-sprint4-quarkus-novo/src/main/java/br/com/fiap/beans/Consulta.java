package br.com.fiap.beans;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@Table(name = "TB_CONSULTA")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor

public class Consulta extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    public Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "profissional_id", nullable = false)
    public ProfissionalSaude profissional;

    @Column(nullable = false)
    public LocalDateTime dataHora;

    @Column(length = 50, nullable = false)
    public String tipo;

    @Column(length = 20, nullable = false)
    public String status;

    public Consulta(Paciente paciente, ProfissionalSaude profissional, LocalDateTime dataHora, String tipo, String status) {
        this.paciente = paciente;
        this.profissional = profissional;
        this.dataHora = dataHora;
        this.tipo = tipo;
        this.status = status;
    }


}
