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
						"Data invalida, o caixa jï¿½ foi aberto nesta data");
			}

		}

		c.setAberto(true);
		// c.getValores();
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
		throw new ExcecaoPastelaria("Nï¿½o foi aberto o caixa neste dia");
	}

	public void AdicionarValorRecebido(Caixa caixa, ValorRecebido pagamento) {

		for (Caixa c : caixas) {
			if ((caixa.getAberto() == true)) {
				c.getValores().add(pagamento);

			}
		}

	}

	public double fecharCaixaDia(Caixa caixa) {

		double valorTotal = 0;
		for (Caixa c : caixas) {
			if (caixa.getAberto() == true) {
				for (ValorRecebido v : c.getValores()) {

					valorTotal = valorTotal + v.getValorPago();
				}
				
			}
			caixa.setAberto(false);
		}
		
		caixa.setDinheiroFimDia(valorTotal);
		return valorTotal;
	}

	public double fecharCaixaMensal() {
		
		double valorTotal = 0 ;
		for (Caixa c : caixas){
			if (c.getAberto() == false){
				
				valorTotal = c.getDinheiroFimDia();
			}
		}
		return valorTotal;
	}
	
	public double calcularValorPagoEmEspecie(Caixa caixa) {

		double valorTotal = 0;
		for (Caixa c : caixas) {
			if (caixa.getAberto() == true) {
				for (ValorRecebido v : c.getValores()) {
					
					if (v.getFormaPagamento()==(Recebimento.ESPECIE)) {
						valorTotal = valorTotal + v.getValorPago();
						
					}
				}
			}
		}

		return valorTotal;
	}

	public double calcularValorPagoEmDebito(Caixa caixa) {
		double valorTotal = 0;
		for (Caixa c : caixas) {
			if (caixa.getAberto() == true) {
				for (ValorRecebido v : c.getValores()) {
					
					if (v.getFormaPagamento()==(Recebimento.DEBITO)) {
						valorTotal = valorTotal + v.getValorPago();
						
					}
				}
			}
		}

		return valorTotal;
	}

	public double calcularValorPagoEmCrédito(Caixa caixa) {
		double valorTotal = 0;
		for (Caixa c : caixas) {
			if (caixa.getAberto() == true) {
				for (ValorRecebido v : c.getValores()) {
					
					if (v.getFormaPagamento()==(Recebimento.CREDITO)) {
						valorTotal = valorTotal + v.getValorPago();
						
					}
				}
			}
		}

		return valorTotal;
	}

	

	
}
