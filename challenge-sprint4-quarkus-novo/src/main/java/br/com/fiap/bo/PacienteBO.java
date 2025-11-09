package br.com.fiap.bo;

import br.com.fiap.beans.Paciente;
import br.com.fiap.dao.PacienteDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
public class PacienteBO {

    @Inject
    PacienteDAO pacienteDAO;

    public List<Paciente> listarTodos() {
        return pacienteDAO.listarTodos();
    }

    public Paciente buscarPorId(Long id) {
        return pacienteDAO.buscarPorId(id)
                .orElseThrow(() -> new NotFoundException("Paciente não encontrado."));
    }

    @Transactional
    public Paciente criar(Paciente paciente) {
        // Regra de Negócio: Validação de CPF (simples, apenas verifica o tamanho)
        if (!validarCpf(paciente.cpf)) {
            throw new IllegalArgumentException("CPF inválido. O CPF deve ter 11 ou 14 caracteres.");
        }
        pacienteDAO.inserir(paciente);
        return paciente;
    }

    @Transactional
    public Paciente atualizar(Long id, Paciente pacienteAtualizado) {
        Paciente pacienteExistente = buscarPorId(id);

        // Atualiza os campos
        pacienteExistente.nome = pacienteAtualizado.nome;
        pacienteExistente.cpf = pacienteAtualizado.cpf;
        pacienteExistente.contato = pacienteAtualizado.contato;
        pacienteExistente.endereco = pacienteAtualizado.endereco;

        if (!validarCpf(pacienteExistente.cpf)) {
            throw new IllegalArgumentException("CPF inválido. O CPF deve ter 11 ou 14 caracteres.");
        }

        pacienteDAO.atualizar(pacienteExistente);
        return pacienteExistente;
    }

    @Transactional
    public void deletar(Long id) {
        pacienteDAO.deletar(id);
    }

    // Regra de Negócio: Validação de CPF (simples, apenas verifica o tamanho)
    private boolean validarCpf(String cpf) {
        return cpf != null && (cpf.length() == 11 || cpf.length() == 14);
    }
}
