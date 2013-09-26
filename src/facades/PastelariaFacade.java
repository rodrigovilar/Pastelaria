package facades;

import java.util.List;

import gerenciadores.GerenteDeCaixa;
import gerenciadores.GerenteDeComanda;
import gerenciadores.GerenteDeEntregaDomicilio;
import gerenciadores.GerenteDeEstoquePastelaria;
import gerenciadores.Recebimento;
import negocio.Caixa;
import negocio.Cliente;
import negocio.Comanda;
import negocio.ItemDeComanda;
import negocio.Produto;
import negocio.ValorRecebido;

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

	public void adicionarQtdeDeUmProdutoNoEstoque(String codigo, int qtde) {
		
		estoque.adicionarQtdeProduto(codigo, qtde);
	}

	public boolean removerProdutoPermanentemente(String codigo) {
	
		return estoque.isRemoverProduto(codigo);
	}

	public void diminuirQtdeDeUmProduto(String codigo, int qtde) {
		
		estoque.diminuirQtdeProduto(codigo, qtde);
		
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

	public void adicionarItemNaComanda(int numMesa, ItemDeComanda item) {
		
		comanda.adicionarItensNaComanda(numMesa, item);
	}

	public boolean removerItemDaComadanda(Comanda c, String codigo) {
		
		return comanda.isRemoverItem(c, codigo);
	}

	public ItemDeComanda pesquisarItemNaComandaTest(int numMesa, String codigo) {

		return comanda.pesquisarItem(numMesa, codigo);
	}

	public void diminuiQtdeDeProdutoNaComanda(int numMesa, ItemDeComanda item, int qtdeDiminuida) {
		
		comanda.diminuirItem(numMesa, item, qtdeDiminuida);
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

	//public void realizarVenda(int numMesa, String codigo, int qtdeItem) {
		// TODO Auto-generated method stub
		//caixa.realizarVenda(numMesa, codigo, qtdeItem);
	//}

	public void adicionarComanda(Comanda c) {
		// TODO Auto-generated method stub
		comanda.adicionarComanda(c);
	}

	public Comanda pesquisarComanda(int i) {
		// TODO Auto-generated method stub
		return comanda.pesquisarComanda(i);
	}

	public int quantidadeDeComandasAbertas() {
		// TODO Auto-generated method stub
		return comanda.getComandas().size();
	}

	public boolean removerComandaPermanentemente(int numMesa) {
		// TODO Auto-generated method stub
		return comanda.isRemoverComandaPermanentemente(numMesa);
	}

	
	public Produto atualizarProduto(String codigo, Produto produtoAtual) {
		// TODO Auto-generated method stub
		return estoque.atualizarProdutoNoEstoque(codigo, produtoAtual);
	}

	public List <Comanda> visualizarComanda() {
		return comanda.getComandas();
		// TODO Auto-generated method stub
		
	}

	public double fecharValorTotalDaComandaComPorcentagem(Comanda c) {
		// TODO Auto-generated method stub
		return comanda.fecharTotalComandaComPorcentagemGarcon(c);
	}
	
	public double fecharValorTotalDaComandaSemPorcentagem(Comanda c){
		return comanda.fecharTotalComandaSemPorcentagemGarcon(c);
	}
	
	public void adicionarValorRecebidoAoCaixa(Caixa c, ValorRecebido pagamento){
		caixa.AdicionarValorRecebido(c, pagamento );
	}

	public double fecharCaixaDoDia(Caixa c) {
		// TODO Auto-generated method stub
		return caixa.fecharCaixaDia(c);
	}

	


}
