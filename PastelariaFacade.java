package pastelaria;

public class PastelariaFacade {
	
	GerenteDeEstoquePastelaria estoque = new GerenteDeEstoquePastelaria();
	GerenteDeEntregaDomicilio entrega = new GerenteDeEntregaDomicilio();

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

	public void adicionarClienteNoSistema(Cliente cliente) {
		// TODO Auto-generated method stub
		entrega.adicionarCliente(cliente);
		
	}

	public Cliente pesquisarClienteNoSistema(String telefone) {
		// TODO Auto-generated method stub
		return entrega.pesquisarCliente(telefone);
	}

	public boolean isRemoverClienteDoSistema(Cliente cliente) {
		// TODO Auto-generated method stub
		return entrega.isRemoverCliente(cliente.getTelefone());
	}
	
	
	
	

	
}
