package br.com.fiap.beans;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "TB_PACIENTE")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor

public class Paciente extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(length = 100, nullable = false)
    public String nome;

    @Column(length = 14, nullable = false, unique = true)
    public String cpf;

    @Embedded
    public Contato contato;

    @Embedded
    public Endereco endereco;

    public Paciente(String nome, String cpf, Contato contato, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.contato = contato;
        this.endereco = endereco;
    }


}
