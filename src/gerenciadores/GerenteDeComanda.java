package gerenciadores;

import java.util.ArrayList;
import java.util.List;

import excecao.ExcecaoPastelaria;


import negocio.Comanda;
import negocio.ItemDeComanda;
import negocio.Produto;

public class GerenteDeComanda {

	private List<Comanda> comandas = new ArrayList<Comanda>();

	public List<Comanda> getComandas() {
		return comandas;
	}

	public void adicionarComanda(Comanda c) {

		for (int i = 0; i < comandas.size(); i++) {
			if (this.comandas.get(i).getNumMesa() == c.getNumMesa()) {
				throw new ExcecaoPastelaria("A mesa já está aberta");
			}
		}
		this.comandas.add(c);
	}

	public void adicionarItensNaComanda(int numMesa, ItemDeComanda item) {

		for (Comanda comanda : comandas) {
			if (comanda.getNumMesa() == numMesa) {
				boolean jaTinha = false;
				for (ItemDeComanda i : comanda.getItens()) {
					if (i.getProduto().getCodigo() == item.getProduto().getCodigo()) {

						int aux = i.getQtdeItem();
						item.setQtdeItem(aux + item.getQtdeItem());
						jaTinha = true;
					} 
				}
				
				if (!jaTinha){
					comanda.getItens().add(item);
				}
			}
		}

	}

	public ItemDeComanda pesquisarItem(int numMesa, String codigo) {

		for (Comanda comanda : comandas) {
			if (comanda.getNumMesa() == numMesa) {
				
				for (ItemDeComanda item : comanda.getItens()) {

					if (item.getProduto().getCodigo()
							.equals(codigo)) {
						return item;
					}
				}

			}
		}
		throw new ExcecaoPastelaria("O item não existe");
	}

	public boolean isRemoverItem(Comanda c, String codigo) {

		ItemDeComanda item = this.pesquisarItem(c.getNumMesa(), codigo);
		
		if (item != null) {
			c.getItens().remove(item);
			return true;
		} else {

			throw new ExcecaoPastelaria("O item não existe");
		}
	}

	public void diminuirItem(int numMesa, ItemDeComanda item, int qtdeDiminuida) {

		for (Comanda comanda : comandas) {
			if (comanda.getNumMesa() == numMesa) {
				for (ItemDeComanda i : comanda.getItens()) {

					if (i.getProduto().getCodigo() == item
							.getProduto().getCodigo()) {

						int aux = i.getQtdeItem();
						i.setQtdeItem(aux - qtdeDiminuida);

					}
				}
			}
		}
	}

	public String visualizarComanda(int numMesa) {

		for (int i = 0; i < comandas.size(); i++) {

			if (this.comandas.get(i).getNumMesa() == numMesa) {
				String str = "Nome da Pastelaria";
				return str + comandas.toString();
			}
		}
		throw new ExcecaoPastelaria("A comanda não existe");

	}

	public Comanda pesquisarComanda(int numMesa) {
	
		for(int i = 0; i< comandas.size();i++){
			if(comandas.get(i).getNumMesa()==numMesa){
				return comandas.get(i);
			}
		}
		throw new ExcecaoPastelaria("A comanda não existe");
	}

	

	public boolean isRemoverComandaPermanentemente(int numMesa) {
		// TODO Auto-generated method stub
		for (Comanda comanda : comandas){
			if(comanda.getNumMesa() == numMesa){
				comandas.remove(comanda);
				return true;
			}
		}
		
		throw new ExcecaoPastelaria("A comanda não existe");
	}

}
