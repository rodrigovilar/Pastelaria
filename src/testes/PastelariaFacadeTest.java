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

	private Produto cadastrarProduto(String id, String nome, double preco,
			int quantidade) {
		
		Produto p = new Produto(id, nome, preco, quantidade);
		fachada.cadastrarProduto(p);
		return p;
	}

	private Comanda cadastrarComanda(int numMesa) {

		Comanda c = new Comanda(numMesa);
		fachada.adicionarComanda(c);
		return c;
	}

	private ItemDeComanda cadastrarProdutoEQtde(Produto p, int qtde) {
		
		ItemDeComanda item = new ItemDeComanda(p, qtde);
		return item;
	}

	private ValorRecebido cadastrarValorRecebido(double valorPagamento,
			Recebimento forma) {
		
		ValorRecebido pagamento = new ValorRecebido(valorPagamento, forma);
		return pagamento;
	}

	private Cliente cadastrarCliente(String nome, String tel, String end,
			String pontoRef) {
		
		Cliente cliente = new Cliente(nome, tel, end, pontoRef);
		fachada.adicionarClienteNoSistema(cliente);
		return cliente;

	}

	@Test
	public void cadastrarProdutoNoEstoqueTest() {

		Produto p = cadastrarProduto("1", "refri", 2.5, 3);
		Produto p2 = fachada.pesquisarProdutoNoEstoque(p.getCodigo());
		Assert.assertEquals(p2, p);

	}

	@Test
	public void adicionarDoisProdutosNoEstoqueTest() {

		Produto p2 = cadastrarProduto("3", "Suco", 3.0, 3);

		Produto p4 = fachada.pesquisarProdutoNoEstoque(p2.getCodigo());

		Assert.assertEquals(p4, p2);
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void adicionarProdutoNoEstoqueComCodigoJaExistente() {

		Produto p1 = cadastrarProduto("2", "Cerveja", 4.0, 3);
		Produto p2 = cadastrarProduto("2", "Cerveja", 4.0, 1);

	}

	@Test
	public void adicionarQtdeDeUmProdutoNoEstoqueTest() {

		Produto p1 = cadastrarProduto("2", "Cerveja", 4.0, 3);
		Produto p2 = cadastrarProduto("3", "Suco", 3.0, 3);

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

		Produto p = cadastrarProduto("2", "Cerveja", 4.0, 1);
		Produto p2 = cadastrarProduto("3", "Suco", 3.0, 3);

		Assert.assertTrue(
				"O produto nï¿½o foi removido, a assertiva deu false",
				fachada.removerProdutoPermanentemente(p2.getCodigo()));

	}

	@Test
	public void diminuirQtdeDeUmProdutoTest() {

		Produto p = cadastrarProduto("2", "Cerveja", 4.0, 1);
		Produto p2 = cadastrarProduto("3", "Suco", 3.0, 3);

		fachada.diminuirQtdeDeUmProduto("3", 1);

		Assert.assertTrue("Nï¿½o esta diminuindo o produto do estoque",
				p2.getQtdeProduto() == 2);
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void removerUmProdutoQueNaoExisteTest() {

		Produto p = cadastrarProduto("2", "Cerveja", 4.0, 1);
		Produto p2 = cadastrarProduto("3", "Suco", 3.0, 3);

		fachada.removerProdutoPermanentemente("5");
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void pesquisarUmProdutoInexistenteTest() {

		Produto p = cadastrarProduto("2", "Cerveja", 4.0, 1);
		Produto p2 = cadastrarProduto("3", "Suco", 3.0, 3);

		Produto p3 = fachada.pesquisarProdutoNoEstoque("5");
		fachada.pesquisarProdutoNoEstoque(p3.getCodigo());

	}

	@Test
	public void adicionarUmClienteNoSistemaTest() {

		Cliente cliente = cadastrarCliente("Renata", "32266279",
				"R. Coraï¿½ï¿½o de Jesus", "Parï¿½quia Santo Antonio");
		Cliente cliente2 = fachada.pesquisarClienteNoSistema(cliente
				.getTelefone());
		Assert.assertEquals(cliente, cliente2);
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void adicionarClienteComTelefonesIguaisTest() {

		Cliente cliente = cadastrarCliente("Renata", "32266279",
				"R. Coraï¿½ï¿½o de Jesus", "Parï¿½quia Santo Antonio");

		Cliente cliente2 = cadastrarCliente("Joï¿½o", "32266279",
				"R. Coraï¿½ï¿½o de Jesus", "Parï¿½quia Santo Antonio");

	}

	@Test
	public void removerUmClienteDoSistemaTest() {

		Cliente cliente = cadastrarCliente("Renata", "32266279",
				"R. Coraï¿½ï¿½o de Jesus", "Parï¿½quia Santo Antonio");
		Cliente cliente2 = cadastrarCliente("Joï¿½o", "86010671",
				"R. Josï¿½ Barbalho", "Mercadinho Boa Vista");

		Assert.assertTrue("O cliente nï¿½o foi removido",
				fachada.isRemoverClienteDoSistema(cliente) == true);
	}

	@Test
	public void pesquisarClienteNoSistemaTest() {

		Cliente cliente = cadastrarCliente("Renata", "32266279",
				"R. Coraï¿½ï¿½o de Jesus", "Parï¿½quia Santo Antonio");

		Cliente cliente2 = cadastrarCliente("Joï¿½o", "86010671",
				"R. Coraï¿½ï¿½o de Jesus", "Parï¿½quia Santo Antonio");

		Cliente cliente3 = fachada.pesquisarClienteNoSistema(cliente
				.getTelefone());

		Assert.assertTrue("Esperava o nome Renata",
				cliente3.getNome() == "Renata");
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void pesquisarUmClienteInexistenteTest() {

		Cliente cliente = cadastrarCliente("Renata", "32266279",
				"R. Coraï¿½ï¿½o de Jesus", "Parï¿½quia Santo Antonio");

		fachada.pesquisarClienteNoSistema("9998877");
	}

	@Test
	public void adicionarComandaVazia() {

		Comanda comanda = cadastrarComanda(10);
		Comanda comanda2 = fachada.pesquisarComanda(10);

		Assert.assertEquals(0, comanda2.getItens().size());
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void pesquisarComandaInexistenteTest() {

		Comanda comanda = cadastrarComanda(10);
		fachada.pesquisarComanda(30);
	}

	@Test
	public void quantidadeDeComandasAbertasTest() {

		Produto p1 = cadastrarProduto("2", "Cerveja", 4.0, 3);
		Produto p2 = cadastrarProduto("3", "Suco", 3.0, 3);

		ItemDeComanda item = cadastrarProdutoEQtde(p1, 20);
		ItemDeComanda item2 = cadastrarProdutoEQtde(p2, 40);

		Comanda c = cadastrarComanda(20);
		Comanda c1 = cadastrarComanda(30);
		Comanda c2 = cadastrarComanda(7);
		Comanda c3 = cadastrarComanda(3);
		Comanda c4 = cadastrarComanda(9);

		fachada.adicionarItemNaComanda(c.getNumMesa(), item);
		fachada.adicionarItemNaComanda(c1.getNumMesa(), item2);

		Assert.assertEquals(5, fachada.quantidadeDeComandasAbertas());
		// Rodrigo, vocï¿½ tinha reclamado porque tinha este mï¿½todo
		// Modifiquei o mï¿½todo na fachada, onde ele chama o getComanda().size
		// Este mï¿½todo foi criado porque nos testes sï¿½ podemos inicializar a
		// fachada, certo?
		// A nï¿½o ser que nï¿½o precise deste teste, para saber como a lista de
		// comandas estï¿½ se comportando...
		// Mesmo sem item a comanda está aberta
	}

	@Test
	public void removerComandaTest() {

		Produto p1 = cadastrarProduto("2", "Cerveja", 4.0, 3);
		Produto p2 = cadastrarProduto("3", "Suco", 3.0, 3);

		Comanda c = cadastrarComanda(20);
		Comanda c1 = cadastrarComanda(30);
		Comanda c2 = cadastrarComanda(7);
		Comanda c3 = cadastrarComanda(3);
		Comanda c4 = cadastrarComanda(9);

		fachada.removerComandaPermanentemente(c3.getNumMesa());
		int qtde = fachada.quantidadeDeComandasAbertas();

		Assert.assertTrue("Nï¿½o foi removida comanda", qtde == 4);

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

		Produto p = cadastrarProduto("2", "Cerveja", 4.0, 3);

		Comanda c = this.cadastrarComanda(10);

		ItemDeComanda item = cadastrarProdutoEQtde(p, 2);
		fachada.adicionarItemNaComanda(c.getNumMesa(), item);

		ItemDeComanda i = fachada.pesquisarItemNaComandaTest(10, item
				.getProduto().getCodigo());

		Assert.assertEquals("Cerveja", i.getProduto().getNome());
	}

	@Test
	public void adicionarMesmoItemEmVariasComandas() {

		Produto p1 = cadastrarProduto("2", "Cerveja", 4.0, 3);

		ItemDeComanda item = this.cadastrarProdutoEQtde(p1, 20);

		Comanda c = cadastrarComanda(20);
		Comanda c1 = cadastrarComanda(21);
		Comanda c2 = cadastrarComanda(22);
		Comanda c3 = cadastrarComanda(23);
		Comanda c4 = cadastrarComanda(24);

		fachada.adicionarItemNaComanda(c.getNumMesa(), item);
		fachada.adicionarItemNaComanda(c1.getNumMesa(), item);
		fachada.adicionarItemNaComanda(c2.getNumMesa(), item);
		fachada.adicionarItemNaComanda(c3.getNumMesa(), item);
		fachada.adicionarItemNaComanda(c4.getNumMesa(), item);

		ItemDeComanda item1 = fachada.pesquisarItemNaComandaTest(
				c.getNumMesa(), item.getProduto().getCodigo());
		ItemDeComanda item2 = fachada.pesquisarItemNaComandaTest(
				c1.getNumMesa(), item.getProduto().getCodigo());
		ItemDeComanda item3 = fachada.pesquisarItemNaComandaTest(
				c2.getNumMesa(), item.getProduto().getCodigo());
		ItemDeComanda item4 = fachada.pesquisarItemNaComandaTest(
				c3.getNumMesa(), item.getProduto().getCodigo());
		ItemDeComanda item5 = fachada.pesquisarItemNaComandaTest(
				c4.getNumMesa(), item.getProduto().getCodigo());

		Assert.assertTrue("O item nï¿½o estï¿½ sendo adicionado as comanda  ",
				(item1 == item2) && (item3 == item4) && (item5 == item));

	}

	@Test
	public void removerItemDaComandaTest() {

		Produto p1 = cadastrarProduto("2", "Cerveja", 4.0, 3);
		Produto p2 = cadastrarProduto("3", "Suco", 3.0, 3);

		ItemDeComanda item = cadastrarProdutoEQtde(p1, 20);
		ItemDeComanda item2 = cadastrarProdutoEQtde(p2, 40);

		Comanda c = this.cadastrarComanda(20);
		fachada.adicionarItemNaComanda(c.getNumMesa(), item);
		fachada.adicionarItemNaComanda(c.getNumMesa(), item2);

		fachada.removerItemDaComadanda(c, item.getProduto().getCodigo());

		Assert.assertEquals(c.getItens().size(), 1);
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void removerItemQueNaoExisteNaComanda() {

		Produto p1 = cadastrarProduto("2", "Cerveja", 4.0, 3);
		Produto p2 = cadastrarProduto("3", "Suco", 3.0, 3);

		ItemDeComanda item = cadastrarProdutoEQtde(p1, 2);
		ItemDeComanda item2 = cadastrarProdutoEQtde(p2, 4);

		Comanda c = this.cadastrarComanda(8);

		fachada.adicionarItemNaComanda(c.getNumMesa(), item);
		fachada.adicionarItemNaComanda(c.getNumMesa(), item2);

		fachada.removerItemDaComadanda(c, "5");
	}

	@Test
	public void diminuirQtdeDeProdutoNaComandaTest() {

		Produto p1 = cadastrarProduto("2", "Cerveja", 4.0, 3);
		Produto p2 = cadastrarProduto("3", "Suco", 3.0, 3);

		ItemDeComanda item = cadastrarProdutoEQtde(p1, 2);
		ItemDeComanda item2 = cadastrarProdutoEQtde(p2, 4);

		Comanda c = this.cadastrarComanda(9);

		fachada.adicionarItemNaComanda(c.getNumMesa(), item);
		fachada.adicionarItemNaComanda(c.getNumMesa(), item2);

		fachada.diminuiQtdeDeProdutoNaComanda(9, item, 1);

		Assert.assertEquals(1, item.getQtdeItem());
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void pesquisarUmItemDaComandaInexistenteTest() {

		Produto p1 = cadastrarProduto("2", "Cerveja", 4.0, 3);
		Produto p2 = cadastrarProduto("3", "Suco", 3.0, 3);
		Produto p3 = cadastrarProduto("5", "Pastel de Carne", 3.0, 3);

		ItemDeComanda item = new ItemDeComanda(p1, 2);
		ItemDeComanda item2 = new ItemDeComanda(p2, 4);

		Comanda c = this.cadastrarComanda(9);

		fachada.adicionarItemNaComanda(c.getNumMesa(), item);
		fachada.adicionarItemNaComanda(c.getNumMesa(), item2);

		fachada.diminuiQtdeDeProdutoNaComanda(c.getNumMesa(), item, 1);

		fachada.pesquisarItemNaComandaTest(c.getNumMesa(), p3.getCodigo());
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void adicionarComandaComNumMesaIguaisTest() {

		Comanda c = this.cadastrarComanda(9);
		Comanda c1 = this.cadastrarComanda(9);

	}

	@Test(expected = ExcecaoPastelaria.class)
	public void visualizarComandaInexistente() {

		Produto p1 = cadastrarProduto("2", "Cerveja", 4.0, 3);

		ItemDeComanda item = this.cadastrarProdutoEQtde(p1, 2);
		Comanda c = new Comanda(30);

		fachada.adicionarItemNaComanda(c.getNumMesa(), item);

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
	public void fecharValorTotalDeUmaComandaComUmaQtdeDeItemDeCadaTest() {

		Produto p1 = cadastrarProduto("2", "Cerveja", 4.0, 30);
		Produto p2 = cadastrarProduto("3", "Suco", 3.0, 30);
		Produto p3 = cadastrarProduto("36", "Pastel Especial", 11.0, 10);
		Produto p4 = cadastrarProduto("100", "Refrigerante lata", 3.5, 80);

		Comanda c = cadastrarComanda(9);

		ItemDeComanda item = cadastrarProdutoEQtde(p1, 1);
		fachada.adicionarItemNaComanda(c.getNumMesa(), item);

		ItemDeComanda item2 = cadastrarProdutoEQtde(p2, 1);
		fachada.adicionarItemNaComanda(c.getNumMesa(), item2);

		ItemDeComanda item3 = cadastrarProdutoEQtde(p3, 1);
		fachada.adicionarItemNaComanda(c.getNumMesa(), item3);

		ItemDeComanda item4 = cadastrarProdutoEQtde(p4, 1);
		fachada.adicionarItemNaComanda(c.getNumMesa(), item4);

		double valor = fachada.fecharValorTotalDaComandaComPorcentagem(c);

		Assert.assertEquals(23.65, valor);

	}

	@Test
	public void fecharValorTotalDeUmaComandaComVariasQtdesDeItemDeCadaTest() {

		Produto p1 = cadastrarProduto("2", "Cerveja", 4.0, 30);
		Produto p2 = cadastrarProduto("3", "Suco", 3.0, 30);
		Produto p3 = cadastrarProduto("36", "Pastel Especial", 11.0, 10);
		Produto p4 = cadastrarProduto("100", "Refrigerante lata", 3.5, 80);

		Comanda c = new Comanda(9);

		fachada.adicionarComanda(c);

		ItemDeComanda item = cadastrarProdutoEQtde(p1, 10);
		fachada.adicionarItemNaComanda(c.getNumMesa(), item);

		ItemDeComanda item2 = cadastrarProdutoEQtde(p2, 13);
		fachada.adicionarItemNaComanda(c.getNumMesa(), item2);

		ItemDeComanda item3 = cadastrarProdutoEQtde(p3, 15);
		fachada.adicionarItemNaComanda(c.getNumMesa(), item3);

		ItemDeComanda item4 = cadastrarProdutoEQtde(p4, 18);
		fachada.adicionarItemNaComanda(c.getNumMesa(), item4);

		double valor = fachada.fecharValorTotalDaComandaComPorcentagem(c);

		Assert.assertEquals(337.7, valor);

	}

	@Test
	public void retirarDezPorCentoDaComandaTest() {

		Produto p1 = cadastrarProduto("2", "Cerveja", 4.0, 30);
		Produto p2 = cadastrarProduto("3", "Suco", 3.0, 30);
		Produto p3 = cadastrarProduto("36", "Pastel Especial", 11.0, 10);
		Produto p4 = cadastrarProduto("100", "Refrigerante lata", 3.5, 80);

		Comanda c = new Comanda(9);

		fachada.adicionarComanda(c);

		ItemDeComanda item = cadastrarProdutoEQtde(p1, 1);
		fachada.adicionarItemNaComanda(c.getNumMesa(), item);

		ItemDeComanda item2 = cadastrarProdutoEQtde(p2, 1);
		fachada.adicionarItemNaComanda(c.getNumMesa(), item2);

		ItemDeComanda item3 = cadastrarProdutoEQtde(p3, 1);
		fachada.adicionarItemNaComanda(c.getNumMesa(), item3);

		ItemDeComanda item4 = cadastrarProdutoEQtde(p4, 1);
		fachada.adicionarItemNaComanda(c.getNumMesa(), item4);

		double valor = fachada.fecharValorTotalDaComandaSemPorcentagem(c);

		Assert.assertEquals(21.50, valor);

	}

	@Test
	public void adicionarUmValorRecebidoAoCaixaTest() {

		Produto p1 = cadastrarProduto("2", "Cerveja", 4.0, 30);
		Produto p2 = cadastrarProduto("3", "Suco", 3.0, 30);
		Produto p3 = cadastrarProduto("36", "Pastel Especial", 11.0, 10);
		Produto p4 = cadastrarProduto("100", "Refrigerante lata", 3.5, 80);

		Comanda c = new Comanda(9);
		fachada.adicionarComanda(c);

		ItemDeComanda item = cadastrarProdutoEQtde(p1, 1);
		fachada.adicionarItemNaComanda(c.getNumMesa(), item);

		ItemDeComanda item2 = cadastrarProdutoEQtde(p2, 1);
		fachada.adicionarItemNaComanda(c.getNumMesa(), item2);

		ItemDeComanda item3 = cadastrarProdutoEQtde(p3, 1);
		fachada.adicionarItemNaComanda(c.getNumMesa(), item3);

		ItemDeComanda item4 = cadastrarProdutoEQtde(p4, 1);
		fachada.adicionarItemNaComanda(c.getNumMesa(), item4);

		double valor = fachada.fecharValorTotalDaComandaComPorcentagem(c);

		Caixa caixa = new Caixa(10, 04, 2013, 0, 0, true);
		fachada.abrirCaixa(caixa);

		ValorRecebido pagamento = cadastrarValorRecebido(valor,
				Recebimento.ESPECIE);

		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento);

		Assert.assertEquals(valor, caixa.getValores().get(0).getValorPago());

	}

	@Test
	public void adicionarValoresTresRecebidosTest() { // Três comandas
														// diferentes

		Produto p1 = cadastrarProduto("2", "Cerveja", 4.0, 30);
		Produto p2 = cadastrarProduto("3", "Suco", 3.0, 30);
		Produto p3 = cadastrarProduto("36", "Pastel Especial", 11.0, 10);
		Produto p4 = cadastrarProduto("100", "Refrigerante lata", 3.5, 80);

		Comanda c = new Comanda(9);
		fachada.adicionarComanda(c);
		ItemDeComanda item = cadastrarProdutoEQtde(p1, 7);
		fachada.adicionarItemNaComanda(c.getNumMesa(), item);
		double valor = fachada.fecharValorTotalDaComandaComPorcentagem(c);

		Comanda c2 = new Comanda(10);
		fachada.adicionarComanda(c2);
		ItemDeComanda item2 = cadastrarProdutoEQtde(p2, 6);
		fachada.adicionarItemNaComanda(c2.getNumMesa(), item2);
		double valor2 = fachada.fecharValorTotalDaComandaComPorcentagem(c2);

		Comanda c3 = new Comanda(11);
		fachada.adicionarComanda(c3);
		ItemDeComanda item3 = cadastrarProdutoEQtde(p3, 4);
		fachada.adicionarItemNaComanda(c3.getNumMesa(), item3);
		ItemDeComanda item4 = cadastrarProdutoEQtde(p4, 5);
		fachada.adicionarItemNaComanda(c3.getNumMesa(), item4);
		double valor3 = fachada.fecharValorTotalDaComandaComPorcentagem(c3);

		Caixa caixa = new Caixa(10, 04, 2013, 0, 0, true);
		fachada.abrirCaixa(caixa);

		ValorRecebido pagamento = new ValorRecebido(valor, Recebimento.ESPECIE);
		ValorRecebido pagamento2 = new ValorRecebido(valor2,
				Recebimento.CREDITO);
		ValorRecebido pagamento3 = new ValorRecebido(valor3, Recebimento.DEBITO);

		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento);
		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento2);
		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento3);

		Assert.assertEquals(3, caixa.getValores().size());

	}

	@Test
	public void CalcularValorPagoEmEspecieTest() {

		Produto p1 = cadastrarProduto("2", "Cerveja", 4.0, 30);

		Comanda c1 = cadastrarComanda(9);
		ItemDeComanda item1 = this.cadastrarProdutoEQtde(p1, 1);
		fachada.adicionarItemNaComanda(c1.getNumMesa(), item1);
		double valor1 = fachada.fecharValorTotalDaComandaComPorcentagem(c1);// 4.4

		Caixa caixa = new Caixa(10, 04, 2013, 0, 0, true);
		fachada.abrirCaixa(caixa);

		ValorRecebido pagamento1 = cadastrarValorRecebido(valor1,
				Recebimento.ESPECIE);//
		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento1);

		double valorEspecie = fachada.calcularValorEspecieDiaTest(caixa);

		Assert.assertEquals(4.40, valorEspecie);
	}

	@Test
	public void calcularVariosValoresPagosEmEspecieDoDiaTest() {

		Produto p1 = cadastrarProduto("2", "Cerveja", 4.0, 30);
		Produto p2 = cadastrarProduto("3", "Suco", 3.0, 30);
		Produto p3 = cadastrarProduto("36", "Pastel Especial", 11.0, 10);
		Produto p4 = cadastrarProduto("100", "Refrigerante lata", 3.5, 80);

		Comanda c1 = cadastrarComanda(9);
		ItemDeComanda item1 = this.cadastrarProdutoEQtde(p1, 4);
		fachada.adicionarItemNaComanda(c1.getNumMesa(), item1);
		double valor1 = fachada.fecharValorTotalDaComandaComPorcentagem(c1);

		Comanda c2 = cadastrarComanda(10);
		ItemDeComanda item2 = this.cadastrarProdutoEQtde(p2, 4);
		fachada.adicionarItemNaComanda(c2.getNumMesa(), item2);
		double valor2 = fachada.fecharValorTotalDaComandaComPorcentagem(c2);

		Comanda c3 = cadastrarComanda(11);
		ItemDeComanda item3 = this.cadastrarProdutoEQtde(p3, 4);
		fachada.adicionarItemNaComanda(c3.getNumMesa(), item3);
		double valor3 = fachada.fecharValorTotalDaComandaComPorcentagem(c3);

		Comanda c4 = cadastrarComanda(12);
		ItemDeComanda item4 = this.cadastrarProdutoEQtde(p4, 4);
		fachada.adicionarItemNaComanda(c4.getNumMesa(), item4);
		double valor4 = fachada.fecharValorTotalDaComandaComPorcentagem(c4);

		Caixa caixa = new Caixa(10, 04, 2013, 0, 0, true);
		fachada.abrirCaixa(caixa);

		ValorRecebido pagamento1 = cadastrarValorRecebido(valor1,
				Recebimento.CREDITO);
		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento1);

		ValorRecebido pagamento2 = cadastrarValorRecebido(valor2,
				Recebimento.ESPECIE);
		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento2);

		ValorRecebido pagamento3 = cadastrarValorRecebido(valor3,
				Recebimento.ESPECIE);
		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento3);

		ValorRecebido pagamento4 = cadastrarValorRecebido(valor4,
				Recebimento.DEBITO);
		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento4);

		double valorEspecie = fachada.calcularValorEspecieDiaTest(caixa);

		Assert.assertEquals(61.599999999999994, valorEspecie);

	}

	@Test
	public void calcularValorPagoEmDebitoTest() {

		Produto p1 = cadastrarProduto("2", "Cerveja", 4.0, 30);

		Comanda c1 = cadastrarComanda(9);
		ItemDeComanda item1 = this.cadastrarProdutoEQtde(p1, 1);
		fachada.adicionarItemNaComanda(c1.getNumMesa(), item1);
		double valor1 = fachada.fecharValorTotalDaComandaComPorcentagem(c1);// 4.4

		Caixa caixa = new Caixa(10, 04, 2013, 0, 0, true);
		fachada.abrirCaixa(caixa);

		ValorRecebido pagamento1 = cadastrarValorRecebido(valor1,
				Recebimento.DEBITO);//
		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento1);

		double valorDebito = fachada.calcularValorDebitoDia(caixa);

		Assert.assertEquals(4.40, valorDebito);

	}

	@Test
	public void calcularVariosValoresPagosEmDebitoTest() {

		Produto p1 = cadastrarProduto("2", "Cerveja", 4.0, 30);
		Produto p2 = cadastrarProduto("3", "Suco", 3.0, 30);
		Produto p3 = cadastrarProduto("36", "Pastel Especial", 11.0, 10);
		Produto p4 = cadastrarProduto("100", "Refrigerante lata", 3.5, 80);

		Comanda c1 = cadastrarComanda(9);
		ItemDeComanda item1 = this.cadastrarProdutoEQtde(p1, 4);
		fachada.adicionarItemNaComanda(c1.getNumMesa(), item1);
		double valor1 = fachada.fecharValorTotalDaComandaComPorcentagem(c1);

		Comanda c2 = cadastrarComanda(10);
		ItemDeComanda item2 = this.cadastrarProdutoEQtde(p2, 4);
		fachada.adicionarItemNaComanda(c2.getNumMesa(), item2);
		double valor2 = fachada.fecharValorTotalDaComandaComPorcentagem(c2);

		Comanda c3 = cadastrarComanda(11);
		ItemDeComanda item3 = this.cadastrarProdutoEQtde(p3, 4);
		fachada.adicionarItemNaComanda(c3.getNumMesa(), item3);
		double valor3 = fachada.fecharValorTotalDaComandaComPorcentagem(c3);

		Comanda c4 = cadastrarComanda(12);
		ItemDeComanda item4 = this.cadastrarProdutoEQtde(p4, 4);
		fachada.adicionarItemNaComanda(c4.getNumMesa(), item4);
		double valor4 = fachada.fecharValorTotalDaComandaComPorcentagem(c4);

		Caixa caixa = new Caixa(10, 04, 2013, 0, 0, true);
		fachada.abrirCaixa(caixa);

		ValorRecebido pagamento1 = cadastrarValorRecebido(valor1,
				Recebimento.DEBITO);
		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento1);

		ValorRecebido pagamento2 = cadastrarValorRecebido(valor2,
				Recebimento.ESPECIE);
		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento2);

		ValorRecebido pagamento3 = cadastrarValorRecebido(valor3,
				Recebimento.DEBITO);
		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento3);

		ValorRecebido pagamento4 = cadastrarValorRecebido(valor4,
				Recebimento.DEBITO);
		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento4);

		double valorEspecie = fachada.calcularValorDebitoDia(caixa);

		Assert.assertEquals(81.4, valorEspecie);

	}

	@Test
	public void calcularValorPagoEmCreditoTest() {

		Produto p1 = cadastrarProduto("2", "Cerveja", 4.0, 30);

		Comanda c1 = cadastrarComanda(9);
		ItemDeComanda item1 = this.cadastrarProdutoEQtde(p1, 1);
		fachada.adicionarItemNaComanda(c1.getNumMesa(), item1);
		double valor1 = fachada.fecharValorTotalDaComandaComPorcentagem(c1);

		Caixa caixa = new Caixa(10, 04, 2013, 0, 0, true);
		fachada.abrirCaixa(caixa);

		ValorRecebido pagamento1 = cadastrarValorRecebido(valor1,
				Recebimento.CREDITO);
		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento1);

		double valorCredito = fachada.calcularValorCreditoDia(caixa);

		Assert.assertEquals(4.40, valorCredito);

	}

	@Test
	public void calcularVariosValoresPagosEmCreditoTest() {

		Produto p1 = cadastrarProduto("2", "Cerveja", 4.0, 30);
		Produto p2 = cadastrarProduto("3", "Suco", 3.0, 30);
		Produto p3 = cadastrarProduto("36", "Pastel Especial", 11.0, 10);
		Produto p4 = cadastrarProduto("100", "Refrigerante lata", 3.5, 80);

		Comanda c1 = cadastrarComanda(9);
		ItemDeComanda item1 = this.cadastrarProdutoEQtde(p1, 4);
		fachada.adicionarItemNaComanda(c1.getNumMesa(), item1);
		double valor1 = fachada.fecharValorTotalDaComandaComPorcentagem(c1);

		Comanda c2 = cadastrarComanda(10);
		ItemDeComanda item2 = this.cadastrarProdutoEQtde(p2, 4);
		fachada.adicionarItemNaComanda(c2.getNumMesa(), item2);
		double valor2 = fachada.fecharValorTotalDaComandaComPorcentagem(c2);

		Comanda c3 = cadastrarComanda(11);
		ItemDeComanda item3 = this.cadastrarProdutoEQtde(p3, 4);
		fachada.adicionarItemNaComanda(c3.getNumMesa(), item3);
		double valor3 = fachada.fecharValorTotalDaComandaComPorcentagem(c3);

		Comanda c4 = cadastrarComanda(12);
		ItemDeComanda item4 = this.cadastrarProdutoEQtde(p4, 4);
		fachada.adicionarItemNaComanda(c4.getNumMesa(), item4);
		double valor4 = fachada.fecharValorTotalDaComandaComPorcentagem(c4);

		Caixa caixa = new Caixa(10, 04, 2013, 0, 0, true);
		fachada.abrirCaixa(caixa);

		ValorRecebido pagamento1 = cadastrarValorRecebido(valor1,
				Recebimento.DEBITO);
		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento1);

		ValorRecebido pagamento2 = cadastrarValorRecebido(valor2,
				Recebimento.CREDITO);
		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento2);

		ValorRecebido pagamento3 = cadastrarValorRecebido(valor3,
				Recebimento.CREDITO);
		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento3);

		ValorRecebido pagamento4 = cadastrarValorRecebido(valor4,
				Recebimento.CREDITO);
		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento4);

		double valorCredito = fachada.calcularValorCreditoDia(caixa);

		Assert.assertEquals(77.0, valorCredito);

	}

	@Test
	public void fecharDoCaixaDoDiaTest() {

		Produto p1 = cadastrarProduto("2", "Cerveja", 4.0, 30);
		Produto p2 = cadastrarProduto("3", "Suco", 3.0, 30);
		Produto p3 = cadastrarProduto("36", "Pastel Especial", 11.0, 10);
		Produto p4 = cadastrarProduto("100", "Refrigerante lata", 3.5, 80);

		Comanda c1 = cadastrarComanda(9);
		ItemDeComanda item1 = this.cadastrarProdutoEQtde(p1, 4);
		fachada.adicionarItemNaComanda(c1.getNumMesa(), item1);
		double valor1 = fachada.fecharValorTotalDaComandaComPorcentagem(c1);

		Comanda c2 = cadastrarComanda(10);
		ItemDeComanda item2 = this.cadastrarProdutoEQtde(p2, 4);
		fachada.adicionarItemNaComanda(c2.getNumMesa(), item2);
		double valor2 = fachada.fecharValorTotalDaComandaComPorcentagem(c2);

		Comanda c3 = cadastrarComanda(11);
		ItemDeComanda item3 = this.cadastrarProdutoEQtde(p3, 4);
		fachada.adicionarItemNaComanda(c3.getNumMesa(), item3);
		double valor3 = fachada.fecharValorTotalDaComandaComPorcentagem(c3);

		Comanda c4 = cadastrarComanda(12);
		ItemDeComanda item4 = this.cadastrarProdutoEQtde(p4, 4);
		fachada.adicionarItemNaComanda(c4.getNumMesa(), item4);
		double valor4 = fachada.fecharValorTotalDaComandaComPorcentagem(c4);

		Caixa caixa = new Caixa(10, 04, 2013, 0, 0, true);
		fachada.abrirCaixa(caixa);

		ValorRecebido pagamento1 = cadastrarValorRecebido(valor1,
				Recebimento.CREDITO);
		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento1);

		ValorRecebido pagamento2 = cadastrarValorRecebido(valor2,
				Recebimento.ESPECIE);
		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento2);

		ValorRecebido pagamento3 = cadastrarValorRecebido(valor3,
				Recebimento.ESPECIE);
		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento3);

		ValorRecebido pagamento4 = cadastrarValorRecebido(valor4,
				Recebimento.DEBITO);
		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento4);

		double caixaFechado = fachada.fecharCaixaDoDia(caixa);
		// pesquisar como arredondar, ou sï¿½ aparecer duas casas decimais
		Assert.assertEquals(94.60000000000001, caixaFechado);

	}

	@Test
	public void fecharCaixaMensalTest() {

		Produto p1 = cadastrarProduto("2", "Cerveja", 4.0, 30);
		Produto p2 = cadastrarProduto("3", "Suco", 3.0, 30);

		Comanda c1 = cadastrarComanda(9);
		ItemDeComanda item1 = this.cadastrarProdutoEQtde(p1, 4);
		fachada.adicionarItemNaComanda(c1.getNumMesa(), item1);
		double valor1 = fachada.fecharValorTotalDaComandaComPorcentagem(c1);

		Caixa caixa = new Caixa(10, 04, 2013, 0, 0, true);
		fachada.abrirCaixa(caixa);

		ValorRecebido pagamento1 = cadastrarValorRecebido(valor1,
				Recebimento.CREDITO);
		fachada.adicionarValorRecebidoAoCaixa(caixa, pagamento1);

		fachada.fecharCaixaDoDia(caixa);

		Comanda c2 = cadastrarComanda(10);
		ItemDeComanda item2 = this.cadastrarProdutoEQtde(p2, 4);
		fachada.adicionarItemNaComanda(c2.getNumMesa(), item2);
		double valor2 = fachada.fecharValorTotalDaComandaComPorcentagem(c2);// 17.60

		Caixa caixa2 = new Caixa(12, 04, 2013, 0, 0, true);
		fachada.abrirCaixa(caixa2);

		ValorRecebido pagamento2 = cadastrarValorRecebido(valor2,
				Recebimento.ESPECIE);
		fachada.adicionarValorRecebidoAoCaixa(caixa2, pagamento2);// 13.2

		fachada.fecharCaixaDoDia(caixa2);

		double valorTotalMes = fachada.fecharCaixaMensal();

		Assert.assertEquals(30.80, valorTotalMes);
	}

}
