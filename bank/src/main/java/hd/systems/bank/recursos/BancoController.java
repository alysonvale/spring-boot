package hd.systems.bank.recursos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hd.systems.bank.entidades.Banco;
import hd.systems.bank.services.BancoServico;

@RestController
@RequestMapping(value = "/banco")
public class BancoController {

    @Autowired
    private BancoServico service;

    @GetMapping
    public ResponseEntity<List<Banco>> findAll() {
        List<Banco> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Banco> findById(@PathVariable int id) {
        Banco obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    Banco novoBanco(@RequestBody Banco novoBanco) {
        return service.insert(novoBanco);
    }

    @DeleteMapping("/{id}")
    void excluirBanco(@PathVariable int id) {
        service.delete(id);
    }
}
