package testes;

import junit.framework.Assert;

import negocio.Caixa;
import negocio.Cliente;
import negocio.Comanda;
import negocio.ItemDeComanda;
import negocio.Produto;

import org.junit.Before;
import org.junit.Test;

import excecao.ExcecaoPastelaria;
import facades.PastelariaFacade;

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
	public void atualizarNomeProduto() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 3);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);

		fachada.atualizarNomeDoProduto(p1.getCodigo(), "Long Neck");

		Assert.assertTrue("O codigo não foi modificado",
				p1.getNome() == "Long Neck");
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
	public void atualizarCodigoProdutoTest() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 3);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);

		fachada.atualizarCodigoProduto(p1.getCodigo(), "4");

		Assert.assertTrue("O codigo não foi modificado", p1.getCodigo() == "4");
	}

	@Test
	public void atualizarPrecoProdutoTest() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 3);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);

		fachada.atualizarPrecoProduto(p1.getCodigo(), 4.5);

		Assert.assertTrue("O codigo não foi modificado", p1.getPreco() == 4.5);
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
	public void adicionarComandaVazia() {
		Comanda comanda = new Comanda(10);
		fachada.adicionarComanda(comanda);
		Comanda comanda2 = fachada.pesquisarComanda(10);

		Assert.assertEquals(0, comanda2.getItens().size());
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void pesquisarComandaInexistenteTest() {

		Comanda comanda = new Comanda(10);
		fachada.adicionarComanda(comanda);
		fachada.pesquisarComanda(30);
	}

	@Test
	public void quantidadeDeComandasAbertasTest() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 3);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);

		ItemDeComanda item = new ItemDeComanda(p1, 20);
		ItemDeComanda item2 = new ItemDeComanda(p2, 40);

		Comanda c = new Comanda(20);
		Comanda c1 = new Comanda(30);
		Comanda c2 = new Comanda(7);
		Comanda c3 = new Comanda(3);
		Comanda c4 = new Comanda(9);

		fachada.adicionarComanda(c);
		fachada.adicionarComanda(c1);
		fachada.adicionarComanda(c2);
		fachada.adicionarComanda(c3);
		fachada.adicionarComanda(c4);

		int qtde = fachada.quantidadeDeComandasAbertas();

		fachada.adicionarItemNaComanda(20, item);
		fachada.adicionarItemNaComanda(30, item2);

		Assert.assertEquals(5, qtde);

	}

	@Test
	public void removerComandaTest() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 3);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);

		Comanda c = new Comanda(20);
		Comanda c1 = new Comanda(30);
		Comanda c2 = new Comanda(7);
		Comanda c3 = new Comanda(3);
		Comanda c4 = new Comanda(9);

		fachada.adicionarComanda(c);
		fachada.adicionarComanda(c1);
		fachada.adicionarComanda(c2);
		fachada.adicionarComanda(c3);
		fachada.adicionarComanda(c4);

		fachada.removerComandaPermanentemente(c3.getNumMesa());
		int qtde = fachada.quantidadeDeComandasAbertas();

		Assert.assertTrue("Não foi removida comanda", qtde == 4);

	}

	@Test(expected = ExcecaoPastelaria.class)
	public void removerComandaInexistenteTest() {

		Produto p = new Produto("2", "Cerveja", 4.0, 3);
		fachada.cadastrarProduto(p);

		Comanda c = new Comanda(10);
		Comanda c1 = new Comanda(30);
		fachada.adicionarComanda(c);

		ItemDeComanda item = new ItemDeComanda(p, 2);
		fachada.adicionarItemNaComanda(10, item);

		fachada.removerComandaPermanentemente(c1.getNumMesa());
	}

	@Test
	public void adicionarItemNaComandaTest() {

		Produto p = new Produto("2", "Cerveja", 4.0, 3);
		fachada.cadastrarProduto(p);

		Comanda c = new Comanda(10);
		fachada.adicionarComanda(c);

		ItemDeComanda item = new ItemDeComanda(p, 2);
		fachada.adicionarItemNaComanda(10, item);

		ItemDeComanda i = fachada.pesquisarItemNaComandaTest(10, item
				.getProduto().getCodigo());

		Assert.assertEquals("Cerveja", i.getProduto().getNome());
	}

	@Test
	public void adicionarMesmoItemEmVariasComandas() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 3);

		fachada.cadastrarProduto(p1);

		ItemDeComanda item = new ItemDeComanda(p1, 20);

		Comanda c = new Comanda(20);
		Comanda c1 = new Comanda(21);
		Comanda c2 = new Comanda(22);
		Comanda c3 = new Comanda(23);
		Comanda c4 = new Comanda(24);

		fachada.adicionarComanda(c);
		fachada.adicionarComanda(c1);
		fachada.adicionarComanda(c2);
		fachada.adicionarComanda(c3);
		fachada.adicionarComanda(c4);

		fachada.adicionarItemNaComanda(20, item);
		fachada.adicionarItemNaComanda(21, item);
		fachada.adicionarItemNaComanda(22, item);
		fachada.adicionarItemNaComanda(23, item);
		fachada.adicionarItemNaComanda(24, item);

		ItemDeComanda item1 = fachada.pesquisarItemNaComandaTest(20, item
				.getProduto().getCodigo());
		ItemDeComanda item2 = fachada.pesquisarItemNaComandaTest(21, item
				.getProduto().getCodigo());
		ItemDeComanda item3 = fachada.pesquisarItemNaComandaTest(22, item
				.getProduto().getCodigo());
		ItemDeComanda item4 = fachada.pesquisarItemNaComandaTest(23, item
				.getProduto().getCodigo());
		ItemDeComanda item5 = fachada.pesquisarItemNaComandaTest(24, item
				.getProduto().getCodigo());

		Assert.assertTrue("O item não está sendo adicionado as comanda  ",
				(item1 == item2) && (item3 == item4) && (item5 == item));

	}

	@Test
	public void removerItemDaComandaTest() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 3);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);

		ItemDeComanda item = new ItemDeComanda(p1, 20);
		ItemDeComanda item2 = new ItemDeComanda(p2, 40);

		Comanda c = new Comanda(20);
		fachada.adicionarComanda(c);

		fachada.adicionarItemNaComanda(20, item);
		fachada.adicionarItemNaComanda(20, item2);

		Assert.assertEquals(true, fachada.removerItemDaComadanda(20, item
				.getProduto().getCodigo()));
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void removerItemQueNaoExisteNaComanda() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 3);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);

		ItemDeComanda item = new ItemDeComanda(p1, 2);
		ItemDeComanda item2 = new ItemDeComanda(p2, 4);

		Comanda c = new Comanda(8);
		
		fachada.adicionarComanda(c);

		fachada.adicionarItemNaComanda(8, item);
		fachada.adicionarItemNaComanda(8, item2);

		fachada.removerProdutoPermanentemente("5");
	}

	@Test
	public void diminuirQtdeDeProdutoNaComandaTest() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 3);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);

		ItemDeComanda item = new ItemDeComanda(p1, 2);
		ItemDeComanda item2 = new ItemDeComanda(p2, 4);

		Comanda c = new Comanda(9);

		fachada.adicionarComanda(c);

		fachada.adicionarItemNaComanda(9, item);
		fachada.adicionarItemNaComanda(9, item2);

		fachada.diminuiQtdeDeProdutoNaComanda(9, item, 1);

		Assert.assertEquals(1, item.getQtdeItem());
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void pesquisarUmItemDaComandaInexistenteTest() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 3);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);
		Produto p3 = new Produto("5", "Pastel de Carne", 3.0, 3);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);
		fachada.cadastrarProduto(p3);

		ItemDeComanda item = new ItemDeComanda(p1, 2);
		ItemDeComanda item2 = new ItemDeComanda(p2, 4);

		Comanda c = new Comanda(9);

		fachada.adicionarComanda(c);

		fachada.adicionarItemNaComanda(9, item);
		fachada.adicionarItemNaComanda(9, item2);

		fachada.diminuiQtdeDeProdutoNaComanda(9, item, 1);

		fachada.pesquisarItemNaComandaTest(9, p3.getCodigo());
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void adicionarComandaComNumMesaIguaisTest() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 3);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);
		Produto p3 = new Produto("5", "Pastel de Carne", 3.0, 3);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);
		fachada.cadastrarProduto(p3);

		ItemDeComanda item = new ItemDeComanda(p1, 2);
		ItemDeComanda item2 = new ItemDeComanda(p2, 4);
		ItemDeComanda item3 = new ItemDeComanda(p3, 90);

		Comanda c = new Comanda(9);
		Comanda c1 = new Comanda(9);

		fachada.adicionarComanda(c1);
		fachada.adicionarComanda(c);

		fachada.adicionarItemNaComanda(9, item);
		fachada.adicionarItemNaComanda(9, item2);
		fachada.adicionarItemNaComanda(9, item3);

	}

	@Test(expected = ExcecaoPastelaria.class)
	public void visualizarComandaInexistente() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 3);
		fachada.cadastrarProduto(p1);

		ItemDeComanda item = new ItemDeComanda(p1, 2);
		Comanda c = new Comanda(30);

		fachada.adicionarComanda(c);
		fachada.adicionarItemNaComanda(30, item);

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
	public void abrirCaixaComDiaMesAnoJaExistente() {

		Caixa caixa = new Caixa(10, 04, 2013, 51.99, 0);
		fachada.abrirCaixa(caixa);

		Caixa caixa2 = new Caixa(10, 04, 2013, 51.99, 0);
		fachada.abrirCaixa(caixa2);
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void pesquisarUmaDataDeCaixaInexistente() {

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
				caixa2.getDia() == 10 && caixa2.getDinheiroInicioDia() == 51.99);
	}

}
