package hd.systems.bank.repositorios;

import hd.systems.bank.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio extends JpaRepository<Cliente, String>{

}
