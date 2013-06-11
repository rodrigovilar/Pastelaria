package pastelaria;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class PastelariaFacadeTest {

	PastelariaFacade fachada;

	@Before
	public void inicializar() {
		fachada = new PastelariaFacade();
	}

	@Test
	public void cadastrarProdutoNoEstoqueTest() {

		Produto p = new Produto("1", "refri", 2.5, 3);
		fachada.cadastrarProduto(p);

		Produto p2 = fachada.pesquisarProdutoNoEstoque(p.getCodigo());
		Assert.assertEquals(p2.getCodigo(), p.getCodigo());
	}

	@Test
	public void adicionarDoisProdutosNoEstoqueTest() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 3);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);

		Produto p4 = fachada.pesquisarProdutoNoEstoque(p2.getCodigo());

		Assert.assertEquals(p4.getNome(), p2.getNome());
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void adicionarProdutoNoEstoqueComCodigoJaExistente() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 3);
		Produto p2 = new Produto("2", "Cerveja", 4.0, 1);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);

	}

	@Test
	public void atualizarQtdeDeUmProdutosNoEstoqueTest() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 3);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);

		fachada.atualizarQtdeDeUmProdutoNoEstoque("2", 5);

		Assert.assertTrue("Esperava obter 8", p1.getQtdeProduto() == 8);
	}

	@Test
	public void removerPermanentementeProdutoDoEstoqueTest() {

		Produto p = new Produto("2", "Cerveja", 4.0, 1);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);

		fachada.cadastrarProduto(p);
		fachada.cadastrarProduto(p2);

		Assert.assertTrue("O produto não foi removido, a assertiva deu false",
				fachada.removerProdutoPermanentemente(p2.getCodigo()) == true);

	}

	@Test
	public void diminuirQtdeDeUmProdutoTest() {

		Produto p = new Produto("2", "Cerveja", 4.0, 1);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);

		fachada.cadastrarProduto(p);
		fachada.cadastrarProduto(p2);

		fachada.diminuirQtdeDeUmProduto("3");

		Assert.assertTrue("Não esta diminuindo o produto do estoque",
				p2.getQtdeProduto() == 2);
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void removerUmProdutoQueNaoExisteTest() {

		Produto p = new Produto("2", "Cerveja", 4.0, 1);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);

		fachada.cadastrarProduto(p);
		fachada.cadastrarProduto(p2);

		Assert.assertFalse("O produto não foi removido, a assertiva deu false",
				fachada.removerProdutoPermanentemente("5") == false);
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void pesquisarUmProdutoInexistenteTest() {

		Produto p = new Produto("2", "Cerveja", 4.0, 1);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);

		fachada.cadastrarProduto(p);
		fachada.cadastrarProduto(p2);

		Produto p3 = fachada.pesquisarProdutoNoEstoque("5");
		// teste furado
		Assert.assertTrue("O objeto não deveria existir", p3 == null);
	}

	/*
	 * @Test public void visualizarTodosOsProdutosDoEstoqueTest() {
	 * 
	 * Produto p = new Produto("2", "Cerveja", 4.0, 1);
	 * 
	 * fachada.cadastrarProduto(p);
	 * 
	 * Assert.assertTrue("",fachada.visualizarProdutosDoEstoque()
	 * =="[2Cerveja4.01]");
	 * 
	 * 
	 * 
	 * }
	 */

	@Test
	public void adicionarUmClienteNoSistemaTest() {

		Cliente cliente = new Cliente("Renata", "32266279",
				"R. Coração de Jesus", "Paróquia Santo Antonio");
		fachada.adicionarClienteNoSistema(cliente);
		Cliente cliente2 = fachada.pesquisarClienteNoSistema(cliente
				.getTelefone());
		Assert.assertTrue("Número de telefone inesperado",
				cliente2.getTelefone() == "32266279");
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void adicionarClienteComTelefonesIguaisTest() {

		Cliente cliente = new Cliente("Renata", "32266279",
				"R. Coração de Jesus", "Paróquia Santo Antonio");
		fachada.adicionarClienteNoSistema(cliente);
		Cliente cliente2 = new Cliente("João", "32266279",
				"R. Coração de Jesus", "Paróquia Santo Antonio");
		fachada.adicionarClienteNoSistema(cliente2);
		Assert.assertTrue("Os telefones deveriam ser iguais",
				cliente.getTelefone() == cliente2.getTelefone());
	}

	@Test
	public void removerUmClienteDoSistemaTest() {

		Cliente cliente = new Cliente("Renata", "32266279",
				"R. Coração de Jesus", "Paróquia Santo Antonio");
		Cliente cliente2 = new Cliente("João", "86010671", "R. José Barbalho",
				"Mercadinho Boa Vista");
		fachada.adicionarClienteNoSistema(cliente);
		fachada.adicionarClienteNoSistema(cliente2);

		Assert.assertTrue("O cliente não foi removido",
				fachada.isRemoverClienteDoSistema(cliente) == true);
	}

	@Test
	public void pesquisarClienteNoSistemaTest() {

		Cliente cliente = new Cliente("Renata", "32266279",
				"R. Coração de Jesus", "Paróquia Santo Antonio");

		Cliente cliente2 = new Cliente("João", "86010671",
				"R. Coração de Jesus", "Paróquia Santo Antonio");

		fachada.adicionarClienteNoSistema(cliente);
		fachada.adicionarClienteNoSistema(cliente2);

		Cliente cliente3 = fachada.pesquisarClienteNoSistema(cliente
				.getTelefone());

		Assert.assertTrue("Esperava o nome Renata",
				cliente3.getNome() == "Renata");
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void pesquisarUmClienteInexistenteTest() {

		Cliente cliente = new Cliente("Renata", "32266279",
				"R. Coração de Jesus", "Paróquia Santo Antonio");

		fachada.adicionarClienteNoSistema(cliente);

		fachada.pesquisarClienteNoSistema("9998877");
	}

	@Test
	public void adicionarProdutosNaComandaTest() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 3);

		fachada.cadastrarProduto(p1);
		
		List<Produto> p = new ArrayList<Produto>();
		
		Comanda c = new Comanda(10, p, 2);

		fachada.adicionarItemNaComanda(c);

		Assert.assertTrue("Deveria ter um produto adicionado", fachada.comanda
				.getComandas().size() == 1);

	}

	@Test
	public void adicionarDoisItensNaComandaTest() {

		Produto p = new Produto("2", "Cerveja", 4.0, 3);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);

		fachada.cadastrarProduto(p);
		fachada.cadastrarProduto(p2);

		List<Produto> listaP1 = new ArrayList<Produto>();
		List<Produto> listaP2 = new ArrayList<Produto>();
		
		Comanda c = new Comanda(7, listaP1, 2);
		Comanda c1 = new Comanda(4,listaP2, 1);

		fachada.adicionarItemNaComanda(c);
		fachada.adicionarItemNaComanda(c1);

		Comanda c3 = fachada.pesquisarItemNaComandaTest(c1.getNumMesa(),
				c1.getProduto().get(index));

		Assert.assertEquals("Suco", c3.getNome());
	}

	@Test
	public void removerItemDaComandaTest() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 3);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);

		Comanda c = new Comanda(2, p1.getCodigo(), p1.getNome(), 1,
				p1.getPreco());
		Comanda c1 = new Comanda(5, p2.getCodigo(), p2.getNome(), 1,
				p2.getPreco());

		fachada.adicionarItemNaComanda(c);
		fachada.adicionarItemNaComanda(c1);

		Assert.assertEquals(true,
				fachada.removerItemDaComadanda(c1.getNumMesa(), c1.getCodigo()));
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void removerItemQueNaoExisteNaComanda() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 3);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);

		Comanda c = new Comanda(3, p1.getCodigo(), p1.getNome(), 1,
				p1.getPreco());
		Comanda c1 = new Comanda(4, p2.getCodigo(), p1.getNome(), 1,
				p2.getPreco());

		fachada.adicionarItemNaComanda(c);
		fachada.adicionarItemNaComanda(c1);

		fachada.removerProdutoPermanentemente("5");
	}

	@Test
	public void diminuirQtdeDeProdutoNaComandaTest() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 3);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);

		Comanda c = new Comanda(4, p1.getCodigo(), p1.getNome(), 1,
				p1.getPreco());
		Comanda c1 = new Comanda(3, p2.getCodigo(), p1.getNome(), 2,
				p2.getPreco());

		fachada.adicionarItemNaComanda(c);
		fachada.adicionarItemNaComanda(c1);

		fachada.diminuiQtdeDeProdutoNaComanda(c1.getCodigo());

		Assert.assertEquals(1, c1.getQtdeItem());
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void pesquisarUmItemDaComandaInexistenteTest() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 3);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);

		Comanda c = new Comanda(2, p1.getCodigo(), p1.getNome(), 1,
				p1.getPreco());
		Comanda c1 = new Comanda(3, p2.getCodigo(), p1.getNome(), 1,
				p2.getPreco());

		fachada.adicionarItemNaComanda(c);
		fachada.adicionarItemNaComanda(c1);

		fachada.pesquisarItemNaComandaTest(10, "20");
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void adicionarComandaComNumMesaIguaisTest() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 3);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);

		Comanda c = new Comanda(2, p1.getCodigo(), p1.getNome(), 1,
				p1.getPreco());
		Comanda c1 = new Comanda(2, p2.getCodigo(), p2.getNome(), 1,
				p2.getPreco());

		fachada.adicionarItemNaComanda(c);
		fachada.adicionarItemNaComanda(c1);

	}

	/*
	 * @Test public void visualizarUmaComandaTest(){
	 * 
	 * Produto p1 = new Produto("2", "Cerveja", 4.0, 3);
	 * 
	 * fachada.cadastrarProduto(p1);
	 * 
	 * Comanda c = new Comanda (2, p1.getCodigo(),p1.getNome(), 1 ,
	 * p1.getPreco());
	 * 
	 * fachada.adicionarItemNaComanda(c);
	 * 
	 * String str = fachada.visulizarUmaComanda (c.getNumMesa());
	 * 
	 * Assert.assertEquals("Nome da Pastelaria [2 Cerveja 4.0 3]", str); }
	 */

	@Test(expected = ExcecaoPastelaria.class)
	public void visualizarComandaInexistente() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 3);

		fachada.cadastrarProduto(p1);

		Comanda c = new Comanda(2, p1.getCodigo(), p1.getNome(), 1,
				p1.getPreco());

		fachada.adicionarItemNaComanda(c);

		fachada.visulizarUmaComanda(20);
	}

	@Test
	public void abrirCaixaTest() {

		Caixa caixa = new Caixa(10, 04, 2013, 51.99, 0);
		fachada.abrirCaixa(caixa);

		Caixa caixa2 = fachada.pesquisarCaixaSalvo(caixa.getDia(),
				caixa.getMes(), caixa.getAno());

		Assert.assertEquals(51.99, caixa2.getDinheiroInicioDia());

	}
	
	@Test(expected = ExcecaoPastelaria.class)
	public void abrirCaixaComDiaMesAnoJaExistente(){
		
		Caixa caixa = new Caixa(10, 04, 2013, 51.99, 0);
		fachada.abrirCaixa(caixa);
		
		Caixa caixa2 = new Caixa(10, 04, 2013, 51.99, 0);
		fachada.abrirCaixa(caixa2);
	}
	
	@Test(expected = ExcecaoPastelaria.class)
	public void pesquisarUmaDataDeCaixaInexistente(){
		
		Caixa caixa = new Caixa(10, 04, 2013, 51.99, 0);
		fachada.abrirCaixa(caixa);

		fachada.pesquisarCaixaSalvo(10, 04, 2014);

	}
	
	@Test
	public void pesquisarCaixaSalvoTest() {

		Caixa caixa = new Caixa(10, 04, 2013, 51.99, 0);
		fachada.abrirCaixa(caixa);

		Caixa caixa2 = fachada.pesquisarCaixaSalvo(caixa.getDia(),
				caixa.getMes(), caixa.getAno());

		Assert.assertTrue(
				"O dia deveria ser 10 e o dinheiro inicial deveria ser 51.99",
				caixa2.getDia() == 10 || caixa2.getDinheiroInicioDia() == 51.99);
	}
	
	@Test
	public void realizarUmaVendaTest(){
		
		Produto p1 = new Produto("2", "Cerveja", 4.0, 3);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);

		Comanda c = new Comanda(2, p1.getCodigo(), p1.getNome(), 1,
				p1.getPreco());
		Comanda c1 = new Comanda(3, p2.getCodigo(), p1.getNome(), 1,
				p2.getPreco());

		fachada.adicionarItemNaComanda(c);
		fachada.adicionarItemNaComanda(c1);
		
		fachada.realizarVenda(c.getNumMesa(), c.getCodigo(), c.getQtdeItem());

		
	}
	
	

}
