package testes;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import negocio.Caixa;
import negocio.Cliente;
import negocio.Comanda;
import negocio.ItemDeComanda;
import negocio.Produto;
import negocio.ValorRecebido;

import org.junit.Before;
import org.junit.Test;

import excecao.ExcecaoPastelaria;
import facades.PastelariaFacade;
import gerenciadores.GerenteDeComanda;
import gerenciadores.Recebimento;

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
		Assert.assertEquals(p2, p);

	}

	@Test
	public void adicionarDoisProdutosNoEstoqueTest() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 3);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);

		Produto p4 = fachada.pesquisarProdutoNoEstoque(p2.getCodigo());

		Assert.assertEquals(p4, p2);
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void adicionarProdutoNoEstoqueComCodigoJaExistente() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 3);
		Produto p2 = new Produto("2", "Cerveja", 4.0, 1);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);

	}

	@Test
	public void adicionarQtdeDeUmProdutoNoEstoqueTest() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 3);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);

		fachada.adicionarQtdeDeUmProdutoNoEstoque("2", 5);

		Assert.assertTrue("Esperava obter 8", p1.getQtdeProduto() == 8);
	}

	@Test
	public void atualizarProdutoTest() {

		Produto p1 = new Produto("23", "Pastel Especial", 11.0, 45);
		fachada.cadastrarProduto(p1);
		Produto p2 = new Produto("23", "Suco", 3.0, 3);

		Produto p3 = fachada.atualizarProduto(p1.getCodigo(), p2);

		Assert.assertEquals(p2, p3);

	}

	@Test
	public void removerPermanentementeProdutoDoEstoqueTest() {

		Produto p = new Produto("2", "Cerveja", 4.0, 1);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);

		fachada.cadastrarProduto(p);
		fachada.cadastrarProduto(p2);

		Assert.assertTrue("O produto não foi removido, a assertiva deu false",
				fachada.removerProdutoPermanentemente(p2.getCodigo()));

	}

	@Test
	public void diminuirQtdeDeUmProdutoTest() {

		Produto p = new Produto("2", "Cerveja", 4.0, 1);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);

		fachada.cadastrarProduto(p);
		fachada.cadastrarProduto(p2);

		fachada.diminuirQtdeDeUmProduto("3", 1);

		Assert.assertTrue("Não esta diminuindo o produto do estoque",
				p2.getQtdeProduto() == 2);
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void removerUmProdutoQueNaoExisteTest() {

		Produto p = new Produto("2", "Cerveja", 4.0, 1);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);

		fachada.cadastrarProduto(p);
		fachada.cadastrarProduto(p2);

		fachada.removerProdutoPermanentemente("5");
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void pesquisarUmProdutoInexistenteTest() {

		Produto p = new Produto("2", "Cerveja", 4.0, 1);
		Produto p2 = new Produto("3", "Suco", 3.0, 3);

		fachada.cadastrarProduto(p);
		fachada.cadastrarProduto(p2);

		Produto p3 = fachada.pesquisarProdutoNoEstoque("5");
		fachada.pesquisarProdutoNoEstoque(p3.getCodigo());

	}

	@Test
	public void adicionarUmClienteNoSistemaTest() {

		Cliente cliente = new Cliente("Renata", "32266279",
				"R. Coração de Jesus", "Paróquia Santo Antonio");
		fachada.adicionarClienteNoSistema(cliente);
		Cliente cliente2 = fachada.pesquisarClienteNoSistema(cliente
				.getTelefone());
		Assert.assertEquals(cliente, cliente2);
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void adicionarClienteComTelefonesIguaisTest() {

		Cliente cliente = new Cliente("Renata", "32266279",
				"R. Coração de Jesus", "Paróquia Santo Antonio");
		fachada.adicionarClienteNoSistema(cliente);
		Cliente cliente2 = new Cliente("João", "32266279",
				"R. Coração de Jesus", "Paróquia Santo Antonio");
		fachada.adicionarClienteNoSistema(cliente2);

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

		fachada.adicionarItemNaComanda(20, item);
		fachada.adicionarItemNaComanda(30, item2);

		Assert.assertEquals(5, fachada.quantidadeDeComandasAbertas());
		// Rodrigo, você tinha reclamado porque tinha este método
		// Modifiquei o método na fachada, onde ele chama o getComanda().size
		// Este método foi criado porque nos testes só podemos inicializar a
		// fachada, certo?
		// A não ser que não precise deste teste, para saber como a lista de
		// comandas está se comportando...
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

		fachada.removerItemDaComadanda(c, item.getProduto().getCodigo());

		Assert.assertEquals(c.getItens().size(), 1);
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

		fachada.removerItemDaComadanda(c, "5");
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

		Comanda c = new Comanda(9);
		Comanda c1 = new Comanda(9);

		fachada.adicionarComanda(c1);
		fachada.adicionarComanda(c);

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

		Caixa caixa = new Caixa(10, 04, 2013, 51.99, 0, false);
		fachada.abrirCaixa(caixa);

		Caixa caixa2 = fachada.pesquisarCaixaSalvo(caixa.getDia(),
				caixa.getMes(), caixa.getAno());

		Assert.assertEquals(51.99, caixa2.getDinheiroInicioDia());

	}

	@Test(expected = ExcecaoPastelaria.class)
	public void abrirCaixaComDiaMesAnoJaExistente() {

		Caixa caixa = new Caixa(10, 04, 2013, 51.99, 0, false);
		fachada.abrirCaixa(caixa);

		Caixa caixa2 = new Caixa(10, 04, 2013, 51.99, 0, false);
		fachada.abrirCaixa(caixa2);
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void pesquisarUmaDataDeCaixaInexistente() {

		Caixa caixa = new Caixa(10, 04, 2013, 51.99, 0, false);
		fachada.abrirCaixa(caixa);

		fachada.pesquisarCaixaSalvo(10, 04, 2014);

	}

	@Test
	public void pesquisarCaixaSalvoTest() {

		Caixa caixa = new Caixa(10, 04, 2013, 51.99, 0, false);
		fachada.abrirCaixa(caixa);

		Caixa caixa2 = fachada.pesquisarCaixaSalvo(caixa.getDia(),
				caixa.getMes(), caixa.getAno());

		Assert.assertTrue(
				"O dia deveria ser 10 e o dinheiro inicial deveria ser 51.99",
				caixa2.getDia() == 10 && caixa2.getDinheiroInicioDia() == 51.99);
	}

	@Test
	public void fecharValorTotalDeUmaComandaComUmItemDeCadaTest() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 30);
		Produto p2 = new Produto("3", "Suco", 3.0, 30);
		Produto p3 = new Produto("36", "Pastel Especial", 11.0, 10);
		Produto p4 = new Produto("100", "Refrigerante lata", 3.5, 80);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);
		fachada.cadastrarProduto(p3);
		fachada.cadastrarProduto(p4);

		Comanda c = new Comanda(9);

		fachada.adicionarComanda(c);

		ItemDeComanda item = new ItemDeComanda(p1, 1);
		ItemDeComanda item2 = new ItemDeComanda(p2, 1);
		ItemDeComanda item3 = new ItemDeComanda(p3, 1);
		ItemDeComanda item4 = new ItemDeComanda(p4, 1);

		fachada.adicionarItemNaComanda(9, item);
		fachada.adicionarItemNaComanda(9, item2);
		fachada.adicionarItemNaComanda(9, item3);
		fachada.adicionarItemNaComanda(9, item4);

		double valor = fachada.fecharValorTotalDaComandaComPorcentagem(c);

		Assert.assertEquals(23.65, valor);

	}

	@Test
	public void fecharValorTotalDeUmaComandaComVariosItemDeCadaTest() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 30);
		Produto p2 = new Produto("3", "Suco", 3.0, 30);
		Produto p3 = new Produto("36", "Pastel Especial", 11.0, 10);
		Produto p4 = new Produto("100", "Refrigerante lata", 3.5, 80);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);
		fachada.cadastrarProduto(p3);
		fachada.cadastrarProduto(p4);

		Comanda c = new Comanda(9);

		fachada.adicionarComanda(c);

		ItemDeComanda item = new ItemDeComanda(p1, 10);
		ItemDeComanda item2 = new ItemDeComanda(p2, 13);
		ItemDeComanda item3 = new ItemDeComanda(p3, 15);
		ItemDeComanda item4 = new ItemDeComanda(p4, 18);

		fachada.adicionarItemNaComanda(9, item);
		fachada.adicionarItemNaComanda(9, item2);
		fachada.adicionarItemNaComanda(9, item3);
		fachada.adicionarItemNaComanda(9, item4);

		double valor = fachada.fecharValorTotalDaComandaComPorcentagem(c);

		Assert.assertEquals(337.7, valor);

	}

	@Test
	public void retirarDezPorCentoDaComandaTest() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 30);
		Produto p2 = new Produto("3", "Suco", 3.0, 30);
		Produto p3 = new Produto("36", "Pastel Especial", 11.0, 10);
		Produto p4 = new Produto("100", "Refrigerante lata", 3.5, 80);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);
		fachada.cadastrarProduto(p3);
		fachada.cadastrarProduto(p4);

		Comanda c = new Comanda(9);

		fachada.adicionarComanda(c);

		ItemDeComanda item = new ItemDeComanda(p1, 1);
		ItemDeComanda item2 = new ItemDeComanda(p2, 1);
		ItemDeComanda item3 = new ItemDeComanda(p3, 1);
		ItemDeComanda item4 = new ItemDeComanda(p4, 1);

		fachada.adicionarItemNaComanda(9, item);
		fachada.adicionarItemNaComanda(9, item2);
		fachada.adicionarItemNaComanda(9, item3);
		fachada.adicionarItemNaComanda(9, item4);

		double valor = fachada.fecharValorTotalDaComandaSemPorcentagem(c);

		Assert.assertEquals(21.50, valor);

	}

	@Test
	public void adicionarUmValorRecebidoAoCaixaTest() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 30);
		Produto p2 = new Produto("3", "Suco", 3.0, 30);
		Produto p3 = new Produto("36", "Pastel Especial", 11.0, 10);
		Produto p4 = new Produto("100", "Refrigerante lata", 3.5, 80);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);
		fachada.cadastrarProduto(p3);
		fachada.cadastrarProduto(p4);

		Comanda c = new Comanda(9);

		fachada.adicionarComanda(c);

		ItemDeComanda item = new ItemDeComanda(p1, 1);
		ItemDeComanda item2 = new ItemDeComanda(p2, 1);
		ItemDeComanda item3 = new ItemDeComanda(p3, 1);
		ItemDeComanda item4 = new ItemDeComanda(p4, 1);

		fachada.adicionarItemNaComanda(9, item);
		fachada.adicionarItemNaComanda(9, item2);
		fachada.adicionarItemNaComanda(9, item3);
		fachada.adicionarItemNaComanda(9, item4);

		double valor = fachada.fecharValorTotalDaComandaComPorcentagem(c);

		Caixa caixa = new Caixa(10, 04, 2013, 0, 0, true);
		fachada.abrirCaixa(caixa);

		ValorRecebido pagamento = new ValorRecebido(valor, Recebimento.ESPÉCIE);

		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento);

		Assert.assertEquals(valor, caixa.getValores().get(0).getValorPago());

	}

	@Test
	public void adicionarValoresTresRecebidosTest() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 30);
		Produto p2 = new Produto("3", "Suco", 3.0, 30);
		Produto p3 = new Produto("36", "Pastel Especial", 11.0, 10);
		Produto p4 = new Produto("100", "Refrigerante lata", 3.5, 80);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);
		fachada.cadastrarProduto(p3);
		fachada.cadastrarProduto(p4);

		Comanda c = new Comanda(9);
		Comanda c2 = new Comanda(10);
		Comanda c3 = new Comanda(11);

		fachada.adicionarComanda(c);
		fachada.adicionarComanda(c2);
		fachada.adicionarComanda(c3);

		ItemDeComanda item = new ItemDeComanda(p1, 7);
		ItemDeComanda item2 = new ItemDeComanda(p2, 6);
		ItemDeComanda item3 = new ItemDeComanda(p3, 4);
		ItemDeComanda item4 = new ItemDeComanda(p4, 5);

		fachada.adicionarItemNaComanda(9, item);
		fachada.adicionarItemNaComanda(9, item2);
		fachada.adicionarItemNaComanda(10, item3);
		fachada.adicionarItemNaComanda(11, item4);

		double valor = fachada.fecharValorTotalDaComandaComPorcentagem(c);
		double valor2 = fachada.fecharValorTotalDaComandaComPorcentagem(c2);
		double valor3 = fachada.fecharValorTotalDaComandaComPorcentagem(c3);

		Caixa caixa = new Caixa(10, 04, 2013, 0, 0, true);
		fachada.abrirCaixa(caixa);

		ValorRecebido pagamento = new ValorRecebido(valor, Recebimento.ESPÉCIE);
		ValorRecebido pagamento2 = new ValorRecebido(valor2,
				Recebimento.CREDITO);
		ValorRecebido pagamento3 = new ValorRecebido(valor3, Recebimento.DÉDITO);

		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento);
		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento2);
		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento3);

		Assert.assertEquals(3, caixa.getValores().size());

	}

	@Test
	public void fecharDoCaixaDoDiaTest() {

		Produto p1 = new Produto("2", "Cerveja", 4.0, 30);
		Produto p2 = new Produto("3", "Suco", 3.0, 30);
		Produto p3 = new Produto("36", "Pastel Especial", 11.0, 10);
		Produto p4 = new Produto("100", "Refrigerante lata", 3.5, 80);

		fachada.cadastrarProduto(p1);
		fachada.cadastrarProduto(p2);
		fachada.cadastrarProduto(p3);
		fachada.cadastrarProduto(p4);

		Comanda c = new Comanda(9);
		Comanda c2 = new Comanda(10);
		Comanda c3 = new Comanda(11);

		fachada.adicionarComanda(c);
		fachada.adicionarComanda(c2);
		fachada.adicionarComanda(c3);

		ItemDeComanda item = new ItemDeComanda(p1, 1); // 4
		ItemDeComanda item2 = new ItemDeComanda(p2, 1);// 3
		ItemDeComanda item3 = new ItemDeComanda(p3, 1);// 11
		ItemDeComanda item4 = new ItemDeComanda(p4, 1);// 3.5

		fachada.adicionarItemNaComanda(9, item);
		fachada.adicionarItemNaComanda(9, item2);
		fachada.adicionarItemNaComanda(10, item3);
		fachada.adicionarItemNaComanda(11, item4);

		double valor = fachada.fecharValorTotalDaComandaComPorcentagem(c); // 7.70
		double valor2 = fachada.fecharValorTotalDaComandaComPorcentagem(c2);// 12.1
		double valor3 = fachada.fecharValorTotalDaComandaComPorcentagem(c3);// 3.85

		Caixa caixa = new Caixa(10, 04, 2013, 0, 0, true);
		fachada.abrirCaixa(caixa);

		ValorRecebido pagamento = new ValorRecebido(valor, Recebimento.ESPÉCIE);
		ValorRecebido pagamento2 = new ValorRecebido(valor2,
				Recebimento.CREDITO);
		ValorRecebido pagamento3 = new ValorRecebido(valor3, Recebimento.DÉDITO);

		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento);
		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento2);
		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento3);

		double caixaFechado = fachada.fecharCaixaDoDia(caixa);
		// pesquisar como arredondar, ou só aparecer duas casas decimais
		Assert.assertEquals(23.650000000000002, caixaFechado);

	}

	@Test
	public void fechamentoDoCaixaMensalTest() {

	}

}
