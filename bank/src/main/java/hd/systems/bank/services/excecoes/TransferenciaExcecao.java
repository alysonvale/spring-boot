package hd.systems.bank.services.excecoes;

public class TransferenciaExcecao extends RuntimeException {
	static final long serialVersionUID = 1L;

	public TransferenciaExcecao(String msg) {
		super(msg);
		
	}
}
