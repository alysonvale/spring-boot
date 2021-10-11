package hd.systems.bank.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hd.systems.bank.entidades.Acesso;
import hd.systems.bank.repositorios.AcessoRepositorio;

@Service
public class AcessoServico {

    @Autowired
    private AcessoRepositorio repository;

    public List<Acesso> findAll(){
        return repository.findAll();
    }

    public Acesso findById(Long id) {
        Optional<Acesso> obj = repository.findById(id);
        return obj.get();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Acesso insert(Acesso novoAcesso) {
        return repository.save(novoAcesso);
    }

}
