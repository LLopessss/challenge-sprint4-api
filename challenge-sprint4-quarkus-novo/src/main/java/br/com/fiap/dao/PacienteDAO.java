package br.com.fiap.dao;

import br.com.fiap.beans.Paciente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PacienteDAO {

    public List<Paciente> listarTodos() {
        return Paciente.listAll();
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return Paciente.findByIdOptional(id);
    }

    @Transactional
    public void inserir(Paciente paciente) {
        paciente.persist();
    }

    @Transactional
    public void atualizar(Paciente paciente) {
        // O Panache gerencia a atualização automaticamente em um contexto transacional
    }

    @Transactional
    public void deletar(Long id) {
        Paciente.deleteById(id);
    }
}
