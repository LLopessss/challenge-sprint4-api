package br.com.fiap.beans;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "TB_ESPECIALIDADE")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor

public class Especialidade extends PanacheEntity {

    @Column(length = 50, nullable = false, unique = true)
    public String nome;

    public Especialidade(String nome) {
        this.nome = nome;
    }


}
