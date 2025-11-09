package br.com.fiap.beans;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "TB_PROFISSIONAL_SAUDE")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor

public class ProfissionalSaude extends PanacheEntity {

    @Column(length = 100, nullable = false)
    public String nome;

    @Column(length = 15, nullable = false, unique = true)
    public String crm; // Conselho Regional de Medicina ou similar

    @Embedded
    public Contato contato;

    @Embedded
    public Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "especialidade_id", nullable = false)
    public Especialidade especialidade;

    public ProfissionalSaude(String nome, String crm, Contato contato, Endereco endereco, Especialidade especialidade) {
        this.nome = nome;
        this.crm = crm;
        this.contato = contato;
        this.endereco = endereco;
        this.especialidade = especialidade;
    }


}
