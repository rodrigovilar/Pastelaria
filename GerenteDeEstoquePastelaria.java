package pastelaria;

import java.util.ArrayList;
import java.util.List;

public class GerenteDeEstoquePastelaria {

	private List<Produto> listaDeProdutos = new ArrayList<Produto>();
	Produto produto = new Produto(0, "sem nome", 0);

	public void adicionarProduto(Produto p) {

		for (int i = 0; i < listaDeProdutos.size(); i++) {
			if (this.listaDeProdutos.get(i).getCodigo() == p.getCodigo()) {
				throw new ExcecaoEstoquePastelaria(
						"O código do produto já existe");
			}
		}

		this.listaDeProdutos.add(p);
	}

	public Produto pesquisarProduto(int codigo) {

		for (int i = 0; i < listaDeProdutos.size(); i++) {
			if (this.listaDeProdutos.get(i).getCodigo() == codigo) {
				return this.listaDeProdutos.get(i);
			}
		}
		return produto;
	}

	public void removerProduto(Produto p) {

		for (int i = 0; i < listaDeProdutos.size(); i++) {
			
			if (produto == null){
				throw new ExcecaoEstoquePastelaria("O produto não existe");
			} 
			if (this.listaDeProdutos.get(i).getCodigo()== p.getCodigo()) {
				this.listaDeProdutos.remove(i);
			}
		}

	}
	

}
