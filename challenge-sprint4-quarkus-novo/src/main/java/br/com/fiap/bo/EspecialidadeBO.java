package br.com.fiap.bo;

import br.com.fiap.beans.Especialidade;
import br.com.fiap.dao.EspecialidadeDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class EspecialidadeBO {

    @Inject
    EspecialidadeDAO especialidadeDAO;

    public List<Especialidade> listarTodos() {
        return especialidadeDAO.listarTodos();
    }

    public Especialidade buscarPorId(Long id) {
        return especialidadeDAO.buscarPorId(id)
                .orElseThrow(() -> new NotFoundException("Especialidade não encontrada."));
    }

    @Transactional
    public Especialidade criar(Especialidade especialidade) {
        especialidadeDAO.inserir(especialidade);
        return especialidade;
    }

    @Transactional
    public Especialidade atualizar(Long id, Especialidade especialidadeAtualizada) {
        Especialidade especialidadeExistente = buscarPorId(id);

        // Atualiza os campos
        especialidadeExistente.nome = especialidadeAtualizada.nome;

        especialidadeDAO.atualizar(especialidadeExistente);
        return especialidadeExistente;
    }

    @Transactional
    public void deletar(Long id) {
        especialidadeDAO.deletar(id);
    }
}
