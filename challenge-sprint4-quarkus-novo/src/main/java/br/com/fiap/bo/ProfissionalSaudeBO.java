package br.com.fiap.bo;

import br.com.fiap.beans.ProfissionalSaude;
import br.com.fiap.dao.ProfissionalSaudeDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class ProfissionalSaudeBO {

    @Inject
    ProfissionalSaudeDAO profissionalSaudeDAO;

    public List<ProfissionalSaude> listarTodos() {
        return profissionalSaudeDAO.listarTodos();
    }

    public ProfissionalSaude buscarPorId(Long id) {
        return profissionalSaudeDAO.buscarPorId(id)
                .orElseThrow(() -> new NotFoundException("Profissional de Saúde não encontrado."));
    }

    @Transactional
    public ProfissionalSaude criar(ProfissionalSaude profissionalSaude) {
        // Regra de Negócio: Validação de CRM (simples, apenas verifica se não é nulo)
        if (profissionalSaude.crm == null || profissionalSaude.crm.isEmpty()) {
            throw new IllegalArgumentException("CRM não pode ser nulo ou vazio.");
        }
        profissionalSaudeDAO.inserir(profissionalSaude);
        return profissionalSaude;
    }

    @Transactional
    public ProfissionalSaude atualizar(Long id, ProfissionalSaude profissionalSaudeAtualizado) {
        ProfissionalSaude profissionalExistente = buscarPorId(id);

        // Atualiza os campos
        profissionalExistente.nome = profissionalSaudeAtualizado.nome;
        profissionalExistente.crm = profissionalSaudeAtualizado.crm;
        profissionalExistente.contato = profissionalSaudeAtualizado.contato;
        profissionalExistente.endereco = profissionalSaudeAtualizado.endereco;
        profissionalExistente.especialidade = profissionalSaudeAtualizado.especialidade;

        if (profissionalExistente.crm == null || profissionalExistente.crm.isEmpty()) {
            throw new IllegalArgumentException("CRM não pode ser nulo ou vazio.");
        }

        profissionalSaudeDAO.atualizar(profissionalExistente);
        return profissionalExistente;
    }

    @Transactional
    public void deletar(Long id) {
        profissionalSaudeDAO.deletar(id);
    }
}
