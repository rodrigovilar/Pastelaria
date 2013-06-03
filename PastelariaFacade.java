package pastelaria;

public class PastelariaFacade {
	
	GerenteDeEstoquePastelaria estoque = new GerenteDeEstoquePastelaria();


	public void adicionarProduto(Produto p) {
		// TODO Auto-generated method stub
		estoque.adicionarProduto(p);
	}

	public Produto pesquisarProduto(int cod) {
		// TODO Auto-generated method stub
		return estoque.pesquisarProduto(cod);
	}

	public void removerProduto(Produto p) {
		// TODO Auto-generated method stub
		estoque.removerProduto(p);
	}
	
	
	
	

	
}
