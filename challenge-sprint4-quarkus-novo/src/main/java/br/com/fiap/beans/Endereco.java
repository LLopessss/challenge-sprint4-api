package br.com.fiap.beans;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    public String rua;
    public String numero;
    public String cidade;
    public String estado;
    public String cep;
}
