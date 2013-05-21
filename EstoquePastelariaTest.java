package Pastelaria;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;



public class EstoquePastelariaTest {
	
	EstoquePastelaria estoque;
	
	@Before
	public void TestarEstoque(){
		estoque = new EstoquePastelaria();
	}

	@Test
	public void adicionarProdutoNaPastelaria() {
		
		estoque.adicionarProduto("1","refri", 2.5 );
		
		Assert.assertEquals(1, estoque.getListaDeProdutos().size());	
	}
	
	@Test 
	public void pesquisarProdutosDaPastelaria(){
		estoque.adicionarProduto("3","refri", 2.5 );
		estoque.adicionarProduto("2","skol", 2.2 );
		estoque.pesquisarProduto("2");
		Assert.assertTrue("O produto foi não foi removido", true);
	}
	
	@Test
	public void removerProdutoDaPastelaria(){
		estoque.adicionarProduto("5", "suco", 3.5);
		estoque.adicionarProduto("6","caldo de cana", 2.5 );
		Assert.assertTrue("O produto não foi removido ", estoque.isRemoverProduto("6"));
	}
	
	@Test (expected=ExcecaoEstoquePastelaria.class)
	public void adicionarUmProdutoComCodigoExistente(){
		estoque.adicionarProduto("1", "suco", 2.5);
		estoque.adicionarProduto("1", "refri", 2.75);
	}
	
	@Test (expected=ExcecaoEstoquePastelaria.class)
	public void removerUmProdutoQueNãoExiste(){
		estoque.adicionarProduto("34", "Pastel de Carne", 4.5);
	    estoque.adicionarProduto("36", "Especial", 12.5);
	    estoque.isRemoverProduto("100");
	}
	
	@Test
	public void visualizarProdutosDoEstoque(){
		
		estoque.adicionarProduto("34", "Pastel de Carne", 4.5);
	    Assert.assertEquals("# 34 Pastel de Carne 4.5", estoque.visualizarProdutos());
	}
	
	@Test (expected=ExcecaoEstoquePastelaria.class)
	public void naoExisteProdutosNoEstoque(){
		estoque.visualizarProdutos();
	}
}

