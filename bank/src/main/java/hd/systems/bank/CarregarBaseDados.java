package hd.systems.bank;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hd.systems.bank.entidades.Banco;
import hd.systems.bank.entidades.Cliente;
import hd.systems.bank.repositorios.BancoRepositorio;
import hd.systems.bank.repositorios.ClienteRepositorio;


@Configuration
public class CarregarBaseDados {

    private static final Logger log = LoggerFactory.getLogger(CarregarBaseDados.class);

    @Bean
    CommandLineRunner initDatabase(BancoRepositorio banco, ClienteRepositorio cliente) {
        return args -> {

            log.info("Carregando " + banco.save(new Banco(00000000,"BCO DO BRASIL S.A.",001,"Sim","RSFN","Banco do Brasil S.A.")));
            log.info("Carregando " + banco.save(new Banco(00360305,"CAIXA ECONOMICA FEDERAL",104,"Sim","RSFN","CAIXA ECONOMICA FEDERAL")));
            log.info("Carregando " + banco.save(new Banco(4902979,"BCO PAN S.A.",623,"Sim","RSFN","Banco Pan S.A.")));
            log.info("Carregando " + banco.save(new Banco(18236120,"NU PAGAMENTOS S.A.",260,"Não","RSFN","Nu Pagamentos S.A.")));
            log.info("Carregando " + banco.save(new Banco(60701190,"ITAÚ UNIBANCO S.A.",341,"Sim","RSFN","ITAÚ UNIBANCO S.A.")));
            log.info("Carregando " + banco.save(new Banco(60746948,"BCO BRADESCO S.A.",237,"Sim","RSFN","Banco Bradesco S.A.")));
            log.info("Carregando " + banco.save(new Banco(33657248,"BNDES", 007 ,"Não"	,"RSFN"	,"BANCO NACIONAL DE DESENVOLVIMENTO ECONOMICO E SOCIAL")));


            // INSERINDO CLIENTES
            String[] lista = ("Francisco Time 2, Cristina Time 3, Baiano Time 3, Diego Time 1, "
                    + "Rafaela Time 1, Henrique Time 1, Daniel Time 2, Henrique Time 2, Cristiane Time 2, "
                    + "HMaria Time 1, Cristiano Time 1, Jonathan Time 3,Hugley Time 3, "
                    + "Marcelo, Fabiano Time 4, Diogo Henrique").split(",");
            List<Banco> lBancos = banco.findAll();

            int cont = 1;
            int bancos = 0;
            for (String nome : lista) {
                nome = nome.trim();
                String[] nomes = nome.split(" ");
                String primeiroNome = nomes[0];
                log.info("Carregando " + cliente.save(new Cliente(nome, "(85)9999-9999", primeiroNome + ".test", primeiroNome ,
                        lBancos.get(bancos).getCodigo() , lBancos.get(bancos).getNome_reduzido() ,"BCTT-00001-2021" + (10000 + cont++), (double) 1000)));
                if (bancos < lBancos.size() - 1) {
                    bancos++;
                }else {
                    bancos = 0;
                }
            }

        };
    }


}
