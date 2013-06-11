package pastelaria;

import java.util.ArrayList;
import java.util.List;

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

	public Comanda pesquisarItem(int numMesa, String codigo) {

		for (int i = 0; i < comandas.size(); i++) {
			if (this.comandas.get(i).getProduto().get(i).getCodigo().equals(codigo)
					|| this.comandas.get(i).getNumMesa() == numMesa) {
				return this.comandas.get(i);
			}
		}
		throw new ExcecaoPastelaria("O item não existe");
	}

	public boolean isRemoverProduto(int numMesa, String codigo) {

		Comanda c = this.pesquisarItem(numMesa, codigo);

		if (c != null) {
			this.comandas.remove(c);
			return true;
		} else {

			throw new ExcecaoPastelaria("O item não existe");
		}
	}

	public void diminuirItem(String codigo) {

		for (int i = 0; i < comandas.size(); i++) {
			if (this.comandas.get(i).getProduto().get(i).getCodigo() == codigo) {
				int aux = comandas.get(i).getQtdeItemProd();
			
				comandas.get(i).setQtdeItemProd(aux - 1);
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

	
		
		
		
	

}
