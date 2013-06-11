package pastelaria;

public class PastelariaFacade {
	
	GerenteDeEstoquePastelaria estoque = new GerenteDeEstoquePastelaria();
	GerenteDeEntregaDomicilio entrega = new GerenteDeEntregaDomicilio();
	GerenteDeComanda comanda = new GerenteDeComanda();
	GerenteDeCaixa caixa = new GerenteDeCaixa();
	
	public void cadastrarProduto(Produto p) {
		
		estoque.cadastrarNovoProduto(p);
	}

	public Produto pesquisarProdutoNoEstoque(String codigo) {
		return estoque.pesquisarProduto(codigo);
	}

	public void atualizarQtdeDeUmProdutoNoEstoque(String codigo, int qtde) {
		
		estoque.atualizarQtdeProduto(codigo, qtde);
	}

	public boolean removerProdutoPermanentemente(String codigo) {
	
		return estoque.isRemoverProduto(codigo);
	}

	public void diminuirQtdeDeUmProduto(String codigo) {
		
		estoque.diminuirQtdeProduto(codigo);
		
	}

	public String visualizarProdutosDoEstoque() {
		
		return estoque.visualizarProduto();
	}

	public void adicionarClienteNoSistema(Cliente cliente) {
		
		entrega.adicionarCliente(cliente);
		
	}

	public Cliente pesquisarClienteNoSistema(String telefone) {
		
		return entrega.pesquisarCliente(telefone);
	}

	public boolean isRemoverClienteDoSistema(Cliente cliente) {
		
		return entrega.isRemoverCliente(cliente.getTelefone());
	}

	public void adicionarItemNaComanda(Comanda c) {
		
		comanda.adicionarComanda(c);
	}

	public boolean removerItemDaComadanda(int numMesa, String codigo) {
		
		return comanda.isRemoverProduto(numMesa, codigo);
	}

	public Comanda pesquisarItemNaComandaTest(int numMesa, String codigo) {

		return comanda.pesquisarItem(numMesa, codigo);
	}

	public void diminuiQtdeDeProdutoNaComanda(String codigo) {
		
		comanda.diminuirItem(codigo);
	}

	public String visulizarUmaComanda(int numMesa) {
		
		return comanda.visualizarComanda(numMesa);
	}

	public void abrirCaixa(Caixa c) {
	
		caixa.abrirCaixa(c);
	}

	public Caixa pesquisarCaixaSalvo(int dia, int mes, int ano) {
		
		return caixa.pesquisarCaixasSalvos(dia, mes, ano);
	}

	public void realizarVenda(int numMesa, String codigo, int qtdeItem) {
		// TODO Auto-generated method stub
		caixa.realizarVenda(numMesa, codigo, qtdeItem);
	}

	

}
