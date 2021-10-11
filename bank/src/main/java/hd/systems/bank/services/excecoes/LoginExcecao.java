package hd.systems.bank.services.excecoes;

public class LoginExcecao extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public LoginExcecao(String msg) {
		super(msg);
	}

}
