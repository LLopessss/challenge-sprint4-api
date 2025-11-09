package br.com.fiap.dao;

import br.com.fiap.beans.Especialidade;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class EspecialidadeDAO {

    public List<Especialidade> listarTodos() {
        return Especialidade.listAll();
    }

    public Optional<Especialidade> buscarPorId(Long id) {
        return Especialidade.findByIdOptional(id);
    }

    @Transactional
    public void inserir(Especialidade especialidade) {
        especialidade.persist();
    }

    @Transactional
    public void atualizar(Especialidade especialidade) {
        // O Panache gerencia a atualização automaticamente em um contexto transacional
    }

    @Transactional
    public void deletar(Long id) {
        Especialidade.deleteById(id);
    }
}
