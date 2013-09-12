package negocio;

import gerenciadores.Recebimento;

public class ValorRecebido {

	double valorPago;
	Recebimento formaPagamento;

	public ValorRecebido(double valorPago, Recebimento formaPagamento) {
		this.valorPago = valorPago;
		this.formaPagamento = formaPagamento;
	}

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorRecebido) {
		this.valorPago = valorRecebido;
	}

	public Recebimento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(Recebimento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

}
