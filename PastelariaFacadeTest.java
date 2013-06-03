package pastelaria;

import static org.junit.Assert.*;
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
	public void adicionarProdutoNoEstoque() {

		Produto p = new Produto(1, "Refri", 2.5);
		fachada.adicionarProduto(p);
		Produto p2 = fachada.pesquisarProduto(p.getCodigo());
		Assert.assertEquals(p2.getCodigo(), p.getCodigo());
	}

	@Test
	public void adicionarDoisProdutosNoEstoque() {

		Produto p1 = new Produto(2, "Cerveja", 4.0);
		Produto p2 = new Produto(3, "Suco", 3.0);

		fachada.adicionarProduto(p1);
		fachada.adicionarProduto(p2);

		Produto p4 = fachada.pesquisarProduto(p2.getCodigo());

		Assert.assertEquals(p4.getNome(), p2.getNome());
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void adicionarProdutoNoEstoqueComCodigoInexistente() {

		Produto p1 = new Produto(2, "Cerveja", 4.0);
		Produto p2 = new Produto(2, "Cerveja", 4.0);

		fachada.adicionarProduto(p1);
		fachada.adicionarProduto(p2);

	}

	// @Test(expected=ExcecaoEstoquePastelaria.class)
	// public void adicionarLetrasNosCodigosDeEstoque(){

	// Produto p = null;
	// fachada.adicionarProduto(p);
	// }

	@Test
	public void removerProdutoDoEstoque() {

		Produto p = new Produto(2, " Pastel de Carne", 4.75);
		Produto p1 = new Produto(3, " Pastel de Frango", 4.75);
		Produto p2 = new Produto(4, " Pastel de Calabresa ", 4.75);
		Produto p3 = new Produto(5, "Cerveja", 4.0);
		Produto p4 = new Produto(6, "Suco", 3.0);

		fachada.adicionarProduto(p);
		fachada.adicionarProduto(p1);
		fachada.adicionarProduto(p2);
		fachada.adicionarProduto(p3);
		fachada.adicionarProduto(p4);

		fachada.removerProduto(p);
		Produto p5 = fachada.pesquisarProduto(p.getCodigo());
		Assert.assertTrue("O produto não foi removido", p5.getCodigo() == 0);
	}

	// @Test(expected = ExcecaoEstoquePastelaria.class)
	// public void removerUmProdutoQueNaoExiste(){
	// Produto p = null;
	// fachada.removerProduto(p);
	// }

	// @Test
	// public void visualizarProdutosNoEstoque(){

	// }

	@Test
	public void adicionarUmClienteNoSistema() {

		Cliente cliente = new Cliente("Renata", "32266279",
				"R. Coração de Jesus", "Paróquia Santo Antonio");
		fachada.adicionarClienteNoSistema(cliente);
		Cliente cliente2 = fachada.pesquisarClienteNoSistema(cliente
				.getTelefone());
		Assert.assertTrue("Número de telefone inesperado",
				cliente2.getTelefone() == "32266279");
	}

	@Test(expected = ExcecaoPastelaria.class)
	public void adicionarClienteComTelefonesIguais() {

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
	public void removerUmClienteDoSistema() {

		Cliente cliente = new Cliente("Renata", "32266279",
				"R. Coração de Jesus", "Paróquia Santo Antonio");
		Cliente cliente2 = new Cliente("João", "86010671", "R. José Barbalho",
				"Mercadinho Boa Vista");
		fachada.adicionarClienteNoSistema(cliente);
		fachada.adicionarClienteNoSistema(cliente2);

		Assert.assertTrue("O cliente não foi removido",
				fachada.isRemoverClienteDoSistema(cliente) == true);
	}

	@Test //rever!!!Acho que não estou testando nada
	public void pesquisarClienteNoSistema() {
		Cliente cliente = new Cliente("Renata", "32266279",
				"R. Coração de Jesus", "Paróquia Santo Antonio");

		Cliente cliente2 = new Cliente("João", "86010671",
				"R. Coração de Jesus", "Paróquia Santo Antonio");

		fachada.adicionarClienteNoSistema(cliente);
		fachada.adicionarClienteNoSistema(cliente2);

		Cliente cliente3 = fachada.pesquisarClienteNoSistema(cliente
				.getTelefone());

		Assert.assertTrue("O nome esperado era Renata",
				cliente3.getNome() == "Renata");
	}
	
	@Test (expected = ExcecaoPastelaria.class)//rever!!!Acho que não estou testando nada
	public void pesquisarUmClienteInexistente(){
		
		Cliente cliente3 = fachada.pesquisarClienteNoSistema("9998877");
		
		Assert.assertEquals(null, cliente3.getNome() == "9998877");
		
		
	}

}
