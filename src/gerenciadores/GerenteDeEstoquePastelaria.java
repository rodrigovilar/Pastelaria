package gerenciadores;

import java.util.ArrayList;
import java.util.List;

import excecao.ExcecaoPastelaria;


import negocio.Produto;

public class GerenteDeEstoquePastelaria {

	private List<Produto> listaDeProdutos = new ArrayList<Produto>();

	public void cadastrarNovoProduto(Produto p) {

		for (int i = 0; i < listaDeProdutos.size(); i++) {
			if (this.listaDeProdutos.get(i).getCodigo() == p.getCodigo()) {
				throw new ExcecaoPastelaria("O código do produto já existe");
			}
		}
		this.listaDeProdutos.add(p);
	}

	public Produto pesquisarProduto(String codigo) {

		for (int i = 0; i < listaDeProdutos.size(); i++) {
			if (this.listaDeProdutos.get(i).getCodigo().equals(codigo)) {
				return this.listaDeProdutos.get(i);
			}
		}
		throw new ExcecaoPastelaria("O produto não existe");
	}

	public void adicionarQtdeProduto(String codigo, int qtde) {

		for (int i = 0; i < listaDeProdutos.size(); i++) {
			if (this.listaDeProdutos.get(i).getCodigo() == codigo) {
				int aux= listaDeProdutos.get(i).getQtdeProduto();
				listaDeProdutos.get(i).setQtdeProduto(aux+qtde);
			}
		}

	}
		
	public boolean isRemoverProduto(String codigo) {
		
		Produto p = this.pesquisarProduto(codigo);
		
		if(p!=null){
			this.listaDeProdutos.remove(p);
			return true;
		}else{
			
			throw new ExcecaoPastelaria("O produto não existe");
		}
	}

	public void diminuirQtdeProduto(String codigo, int qtde) {
		
		for (int i = 0; i < listaDeProdutos.size(); i++) {
			if (this.listaDeProdutos.get(i).getCodigo() == codigo) {
				int aux= listaDeProdutos.get(i).getQtdeProduto();
				listaDeProdutos.get(i).setQtdeProduto(aux-qtde);
			}
		}
		
	}
	
	public String visualizarProduto() {
		
		return listaDeProdutos.toString() ;
	}

	

	public Produto atualizarProdutoNoEstoque(String codigo, Produto produtoAtual) {
		
		for (Produto produto : listaDeProdutos){
			if (produto.getCodigo().equals(codigo)){
				produto.setNome(produtoAtual.getNome());
				produto.setPreco(produtoAtual.getPreco());
				produto.setQtdeProduto(produtoAtual.getQtdeProduto());
			}
		}
		return produtoAtual;
	
	}

}
