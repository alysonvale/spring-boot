package hd.systems.bank.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import hd.systems.bank.entidades.excecao.EntitiesException;

@Entity
@Table(name = "caixa")
public class Caixa{
	
	private static final int MAX_CEDULAS = 200;

	//GERANDO O BANK 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo", nullable = false)
	private Integer codigo_caixa; 

	private int qtd_cedulas_2;

	private int qtd_cedulas_5;

	private int qtd_cedulas_10;

	private int qtd_cedulas_50;

	public Caixa() {
		setQtd_cedulas_2(MAX_CEDULAS);
		setQtd_cedulas_5(MAX_CEDULAS);
		setQtd_cedulas_10(MAX_CEDULAS);
		setQtd_cedulas_50(MAX_CEDULAS);
	}

	public Caixa(int qtd_cedulas_2, int qtd_cedulas_5, int qtd_cedulas_10, int qtd_cedulas_50) {
		setQtd_cedulas_2(qtd_cedulas_2);
		setQtd_cedulas_5(qtd_cedulas_5);
		setQtd_cedulas_10(qtd_cedulas_10);
		setQtd_cedulas_50(qtd_cedulas_50);

	}

	public int getQtd_cedulas_2() {
		return qtd_cedulas_2;
	}

	public void setQtd_cedulas_2(int qtd_cedulas_2) {

		if (validarQuantidade(qtd_cedulas_2)) {
			this.qtd_cedulas_2 = qtd_cedulas_2;
		} else {
			throw new EntitiesException("Quantidades de cedulas invalidas");
		}
	}

	public int getQtd_cedulas_5() {
		return qtd_cedulas_5;
	}

	public void setQtd_cedulas_5(int qtd_cedulas_5) {
		if (validarQuantidade(qtd_cedulas_5)) {
			this.qtd_cedulas_5 = qtd_cedulas_5;
		} else {
			throw new EntitiesException("Quantidades de cedulas invalidas");
		}
	}

	public int getQtd_cedulas_10() {
		return qtd_cedulas_10;
	}

	public void setQtd_cedulas_10(int qtd_cedulas_10) {
		if (validarQuantidade(qtd_cedulas_10)) {
			this.qtd_cedulas_10 = qtd_cedulas_10;
		} else {
			throw new EntitiesException("Quantidades de cedulas invalidas");
		}
	}

	public int getQtd_cedulas_50() {
		return qtd_cedulas_50;
	}

	public void setQtd_cedulas_50(int qtd_cedulas_50) {
		if (validarQuantidade(qtd_cedulas_10)) {
			this.qtd_cedulas_50 = qtd_cedulas_50;
		} else {
			throw new EntitiesException("Quantidades de cedulas invalidas");
		}
	}

	public Integer getCodigo_caixa() {
		return codigo_caixa;
	}

	private boolean validarQuantidade(int qtd_cedulas) {
		if (qtd_cedulas <= MAX_CEDULAS & qtd_cedulas >= 0) {
			return true;
		}
		return false;
	}

	// VERIFICADOR DE SALDO
	public boolean verificarSaldoSuficiente(Double valor, Cliente cliente) {
		if (cliente.getSaldo() >= valor) {
			return true;
		}
		return false;
	}

	public int[] calcularCedulas(Double valor) {
		int[] quantCedulas = { 0, 0, 0, 0 };
		while (valor != 0) {
			if (quantCedulas[0] < getQtd_cedulas_50() && (valor >= 50 && valor != 51 && valor != 53)) {
				if (valor >= 50 && valor != 51) {
					quantCedulas[0] += 1;
					valor -= 50;
				}
			} else if (quantCedulas[1] < getQtd_cedulas_10() && (valor >= 10 && valor != 11 && valor != 13)) {
				if (valor >= 10 && valor != 11) {
					quantCedulas[1] += 1;
					valor -= 10;
				}
			} else if (quantCedulas[2] < getQtd_cedulas_5() && (valor >= 5 && valor != 6 && valor != 8)) {
				if (valor >= 5 && valor != 6) {
					quantCedulas[2] += 1;
					valor -= 5;
				}
			} else if (quantCedulas[3] < getQtd_cedulas_2() && (valor >= 2) && ((valor % 2 ) == 0)) {
				if (valor >= 2) {
					quantCedulas[3] += 1;
					valor -= 2;
				}
			} else if (valor != 0) {
				return null;
			}
		}
		System.out.println(quantCedulas);
		return quantCedulas;
	}

	private void subtrairCedulas(int[] cedulasUsadas) {
		this.setQtd_cedulas_50(getQtd_cedulas_50() - cedulasUsadas[0]);
		this.setQtd_cedulas_10(getQtd_cedulas_10() - cedulasUsadas[1]);
		this.setQtd_cedulas_5(getQtd_cedulas_5() - cedulasUsadas[2]);
		this.setQtd_cedulas_2(getQtd_cedulas_2() - cedulasUsadas[3]);
	}

	private boolean valorSuficienteCaixa(Double valor) {
		double valorTotal = ((this.qtd_cedulas_50 * 50.0) + (this.qtd_cedulas_10 * 10.0) + (this.qtd_cedulas_5 * 5.0)
				+ (this.qtd_cedulas_2 * 2.0));
		if (valorTotal < valor) {
			return false;
		}
		return true;
	}

	public boolean verificarCasaDecimal(Double valor) {
		double num = valor;
		double decimal = num % 1;
		if (decimal != 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String toString() {
		return "Caixa [codigo_caixa=" + codigo_caixa + ", qtd_cedulas_2=" + qtd_cedulas_2 + ", qtd_cedulas_5="
				+ qtd_cedulas_5 + ", qtd_cedulas_10=" + qtd_cedulas_10 + ", qtd_cedulas_50=" + qtd_cedulas_50 + "]";
	}
}
