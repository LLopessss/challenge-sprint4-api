package br.com.fiap.dao;

import br.com.fiap.beans.ProfissionalSaude;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProfissionalSaudeDAO {

    public List<ProfissionalSaude> listarTodos() {
        return ProfissionalSaude.listAll();
    }

    public Optional<ProfissionalSaude> buscarPorId(Long id) {
        return ProfissionalSaude.findByIdOptional(id);
    }

    @Transactional
    public void inserir(ProfissionalSaude profissionalSaude) {
        profissionalSaude.persist();
    }

    @Transactional
    public void atualizar(ProfissionalSaude profissionalSaude) {
        // O Panache gerencia a atualização automaticamente em um contexto transacional
    }

    @Transactional
    public void deletar(Long id) {
        ProfissionalSaude.deleteById(id);
    }
}
