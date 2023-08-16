package com.juniorrodrigues.gof.service;

import com.juniorrodrigues.gof.model.Cliente;

public interface ClienteService {

    Iterable<Cliente> buscarTodos();
    Cliente buscarPorId(Long id);
    void inserir(Cliente cliente);
    void atualziar(Long id, Cliente client);
    void deletar(Long id);
}
