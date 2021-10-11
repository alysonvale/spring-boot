package hd.systems.bank.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import hd.systems.bank.entidades.Caixa;

public interface CaixaRepositorio extends JpaRepository<Caixa, Integer> {

}
