package br.com.fiap.dao;

import br.com.fiap.beans.Consulta;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ConsultaDAO {

    public List<Consulta> listarTodos() {
        return Consulta.listAll();
    }

    public Optional<Consulta> buscarPorId(Long id) {
        return Consulta.findByIdOptional(id);
    }

    @Transactional
    public void inserir(Consulta consulta) {
        consulta.persist();
    }

    @Transactional
    public void atualizar(Consulta consulta) {
        // O Panache gerencia a atualização automaticamente em um contexto transacional
    }

    @Transactional
    public void deletar(Long id) {
        Consulta.deleteById(id);
    }
}
