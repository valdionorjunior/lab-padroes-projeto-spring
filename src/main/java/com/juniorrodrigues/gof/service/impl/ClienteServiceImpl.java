package com.juniorrodrigues.gof.service.impl;

import com.juniorrodrigues.gof.model.Cliente;
import com.juniorrodrigues.gof.model.Endereco;
import com.juniorrodrigues.gof.repository.ClienteRepository;
import com.juniorrodrigues.gof.repository.EnderecoRepository;
import com.juniorrodrigues.gof.service.ClienteService;
import com.juniorrodrigues.gof.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {
// singleton: Injeta os componentes do Spring com @Autowired
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {

        return clienteRepository.findAll();
    }

    // Strategy: Implementa os metodos definidos na interface
    // Facade: Abstrair integracoes com subsistemas, provendo uma interface simples
    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);

    }

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep); // integraco com api externa para vusca de cep
            enderecoRepository.save(novoEndereco);
            return novoEndereco;});
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

    @Override
    public void atualziar(Long id, Cliente cliente) {
        Cliente clientDB = clienteRepository.findById(id).get();
        if(clientDB != null){
            salvarClienteComCep(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}
