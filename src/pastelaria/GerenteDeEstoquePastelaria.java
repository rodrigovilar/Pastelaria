package pastelaria;

import java.util.ArrayList;
import java.util.List;

public class GerenteDeEstoquePastelaria {

	private List<Produto> listaDeProdutos = new ArrayList<Produto>();

	public void cadastrarNovoProduto(Produto p) {

		for (int i = 0; i < listaDeProdutos.size(); i++) {
			if (this.listaDeProdutos.get(i).getCodigo() == p.getCodigo()) {
				throw new ExcecaoPastelaria("O código do produto já existe");
			}
		}
		this.listaDeProdutos.add(p);
	}

	public Produto pesquisarProduto(String codigo) {

		for (int i = 0; i < listaDeProdutos.size(); i++) {
			if (this.listaDeProdutos.get(i).getCodigo().equals(codigo)) {
				return this.listaDeProdutos.get(i);
			}
		}
		throw new ExcecaoPastelaria("O produto não existe");
	}

	public void atualizarQtdeProduto(String codigo, int qtde) {

		for (int i = 0; i < listaDeProdutos.size(); i++) {
			if (this.listaDeProdutos.get(i).getCodigo() == codigo) {
				int aux= listaDeProdutos.get(i).getQtdeProduto();
				listaDeProdutos.get(i).setQtdeProduto(aux+qtde);
			}
		}

	}
	
	public boolean isRemoverProduto(String codigo) {
		
		Produto p = this.pesquisarProduto(codigo);
		
		if(p!=null){
			this.listaDeProdutos.remove(p);
			return true;
		}else{
			
			throw new ExcecaoPastelaria("O produto não existe");
		}
	}

	public void diminuirQtdeProduto(String codigo) {
		
		for (int i = 0; i < listaDeProdutos.size(); i++) {
			if (this.listaDeProdutos.get(i).getCodigo() == codigo) {
				int aux= listaDeProdutos.get(i).getQtdeProduto();
				int qtde=1;
				listaDeProdutos.get(i).setQtdeProduto(aux-qtde);
			}
		}
		
	}
	
	public String visualizarProduto() {
		
		return listaDeProdutos.toString() ;
	}

}
