package hd.systems.bank.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hd.systems.bank.entidades.Banco;
import hd.systems.bank.repositorios.BancoRepositorio;

@Service
public class BancoServico {

    @Autowired
    private BancoRepositorio repository;

    public List<Banco> findAll(){
        return repository.findAll();
    }

    public Banco findById(int id) {
        Optional<Banco> obj = repository.findById(id);

        return obj.get();
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public Banco insert(Banco novoBanco) {
        return repository.save(novoBanco);
    }

}
