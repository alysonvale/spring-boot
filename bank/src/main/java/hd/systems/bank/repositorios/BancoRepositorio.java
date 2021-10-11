package hd.systems.bank.repositorios;

import hd.systems.bank.entidades.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BancoRepositorio extends JpaRepository<Banco, Integer> {
	
}
