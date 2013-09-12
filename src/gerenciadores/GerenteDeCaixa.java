package gerenciadores;

import java.util.ArrayList;
import java.util.List;

import excecao.ExcecaoPastelaria;

import negocio.Caixa;
import negocio.Comanda;
import negocio.ItemDeComanda;
import negocio.ValorRecebido;

public class GerenteDeCaixa {

	private List<Caixa> caixas = new ArrayList<Caixa>();

	public void abrirCaixa(Caixa c) {

		for (int i = 0; i < this.caixas.size(); i++) {
			if (this.caixas.get(i).getDia() == c.getDia()
					&& this.caixas.get(i).getMes() == c.getMes()
					&& this.caixas.get(i).getAno() == c.getAno()) {
				throw new ExcecaoPastelaria(
						"Data invalida, o caixa já foi aberto nesta data");
			}

		}

		c.setStatus(true);
		c.getValores();
		this.caixas.add(c);
	}

	public Caixa pesquisarCaixasSalvos(int dia, int mes, int ano) {

		for (int i = 0; i < this.caixas.size(); i++) {
			if (this.caixas.get(i).getDia() == dia
					&& this.caixas.get(i).getMes() == mes
					&& this.caixas.get(i).getAno() == ano) {

				return this.caixas.get(i);
			}
		}
		throw new ExcecaoPastelaria("Não foi aberto o caixa neste dia");
	}

	public void AdicionarValorRecebido(Caixa caixa, double valor,
			Recebimento forma) {

		// double auxEspecie = 0;
		// double auxDebito = 0;
		// double auxCredito = 0;

		ValorRecebido pago = new ValorRecebido(valor, forma);

		if (caixa.getStatus() == true) {
			for (Caixa c : caixas) {
				if ((pago.getFormaPagamento() == forma)
						&& (pago.getValorPago() == valor)) {
					for (ValorRecebido v : c.getValores()) {
						v = pago;

						c.getValores().add(v);
					}

				}
			}
		}
		/**
		 * if (pago.getFormaPagamento() == formaPagamento.DEBITO) { auxEspecie
		 * =+ auxEspecie; } if (pago.getFormaPagamento() ==
		 * formaPagamento.CREDITO) { auxEspecie =+ auxDebito; } if
		 * (pago.getFormaPagamento() == formaPagamento.ESPECIE) { auxEspecie =+
		 * auxCredito; }
	**/	 

	}
	
	

}
