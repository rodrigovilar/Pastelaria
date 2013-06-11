package pastelaria;

import java.util.ArrayList;
import java.util.List;

public class GerenteDeCaixa {

	private List<Caixa> caixasSalvos = new ArrayList<Caixa>();
	private GerenteDeComanda comanda = new GerenteDeComanda();

	public void abrirCaixa(Caixa c) {

		for (int i = 0; i < this.caixasSalvos.size(); i++) {
			if (this.caixasSalvos.get(i).getDia() == c.getDia()
					&& this.caixasSalvos.get(i).getMes() == c.getMes()
					&& this.caixasSalvos.get(i).getAno() == c.getAno()) {
				throw new ExcecaoPastelaria(
						"Data invalida, o caixa já foi aberto nesta data");
			}

		}
		this.caixasSalvos.add(c);
	}

	public Caixa pesquisarCaixasSalvos(int dia, int mes, int ano) {

		for (int i = 0; i < this.caixasSalvos.size(); i++) {
			if (this.caixasSalvos.get(i).getDia() == dia
					&& this.caixasSalvos.get(i).getMes() == mes
					&& this.caixasSalvos.get(i).getAno() == ano) {
				
				return this.caixasSalvos.get(i);
			}
		}
		throw new ExcecaoPastelaria("Não foi aberto o caixa neste dia");
	}

	public void realizarVenda(int numMesa, String codigo, int qtdeItem) {
		
		for(int i = 0; i < comanda.getComandas().size(); i++){
			if(this.comanda.getComandas().get(i).getNumMesa()==numMesa){
				
			}
		}
		
	}



}
