package br.com.fiap.config;

import br.com.fiap.beans.*;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

@ApplicationScoped
public class DataInitializer {

    @Transactional
    void onStart(@Observes StartupEvent ev) {
        // 1. Especialidades
        Especialidade cardiologia = new Especialidade("Cardiologia");
        Especialidade dermatologia = new Especialidade("Dermatologia");
        cardiologia.persist();
        dermatologia.persist();

        // 2. Pacientes
        Contato contatoPaciente1 = new Contato("11987654321", "joao.silva@email.com");
        Endereco enderecoPaciente1 = new Endereco("Rua A", "100", "São Paulo", "SP", "01000-000");
        Paciente paciente1 = new Paciente("João da Silva", "12345678901", contatoPaciente1, enderecoPaciente1);
        paciente1.persist();

        Contato contatoPaciente2 = new Contato("11998765432", "maria.souza@email.com");
        Endereco enderecoPaciente2 = new Endereco("Av. B", "200", "Rio de Janeiro", "RJ", "20000-000");
        Paciente paciente2 = new Paciente("Maria de Souza", "10987654321", contatoPaciente2, enderecoPaciente2);
        paciente2.persist();

        // 3. Profissionais de Saúde
        Contato contatoProfissional1 = new Contato("1133334444", "dr.carlos@email.com");
        Endereco enderecoProfissional1 = new Endereco("Rua C", "300", "São Paulo", "SP", "01000-000");
        ProfissionalSaude profissional1 = new ProfissionalSaude("Dr. Carlos", "CRM12345", contatoProfissional1, enderecoProfissional1, cardiologia);
        profissional1.persist();

        Contato contatoProfissional2 = new Contato("1155556666", "dra.ana@email.com");
        Endereco enderecoProfissional2 = new Endereco("Av. D", "400", "São Paulo", "SP", "01000-000");
        ProfissionalSaude profissional2 = new ProfissionalSaude("Dra. Ana", "CRM67890", contatoProfissional2, enderecoProfissional2, dermatologia);
        profissional2.persist();

        // 4. Consultas
        Consulta consulta1 = new Consulta(paciente1, profissional1, LocalDateTime.now().plusDays(1), "Rotina", "AGENDADA");
        consulta1.persist();

        Consulta consulta2 = new Consulta(paciente2, profissional2, LocalDateTime.now().plusDays(2), "Emergência", "AGENDADA");
        consulta2.persist();
    }
}
