package br.com.fiap.bo;

import br.com.fiap.beans.Consulta;
import br.com.fiap.dao.ConsultaDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class ConsultaBO {

    @Inject
    ConsultaDAO consultaDAO;

    @Inject
    PacienteBO pacienteBO;

    @Inject
    ProfissionalSaudeBO profissionalSaudeBO;

    public List<Consulta> listarTodos() {
        return consultaDAO.listarTodos();
    }

    public Consulta buscarPorId(Long id) {
        return consultaDAO.buscarPorId(id)
                .orElseThrow(() -> new NotFoundException("Consulta não encontrada."));
    }

    @Transactional
    public Consulta criar(Consulta consulta) {
        // Regra de Negócio: Validação de Agendamento (Data/Hora não pode ser no passado)
        if (consulta.dataHora.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Não é possível agendar consultas no passado.");
        }

        // Validação de existência de Paciente e Profissional
        pacienteBO.buscarPorId(consulta.paciente.id);
        profissionalSaudeBO.buscarPorId(consulta.profissional.id);

        // Regra de Negócio: Status inicial da consulta
        consulta.status = "AGENDADA";

        consultaDAO.inserir(consulta);
        return consulta;
    }

    @Transactional
    public Consulta atualizar(Long id, Consulta consultaAtualizada) {
        Consulta consultaExistente = buscarPorId(id);

        // Atualiza os campos
        consultaExistente.paciente = consultaAtualizada.paciente;
        consultaExistente.profissional = consultaAtualizada.profissional;
        consultaExistente.dataHora = consultaAtualizada.dataHora;
        consultaExistente.tipo = consultaAtualizada.tipo;
        consultaExistente.status = consultaAtualizada.status;

        // Revalidação de Agendamento
        if (consultaExistente.dataHora.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Não é possível agendar consultas no passado.");
        }

        // Validação de existência de Paciente e Profissional
        pacienteBO.buscarPorId(consultaExistente.paciente.id);
        profissionalSaudeBO.buscarPorId(consultaExistente.profissional.id);

        consultaDAO.atualizar(consultaExistente);
        return consultaExistente;
    }

    @Transactional
    public void deletar(Long id) {
        consultaDAO.deletar(id);
    }
}
