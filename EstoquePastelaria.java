package Pastelaria;

import java.util.ArrayList;
import java.util.List;


public class EstoquePastelaria {
	
	private Produto produto;
	private List<Produto> listaDeProdutos = new ArrayList<Produto>();

	public List<Produto> getListaDeProdutos() {
		// TODO Auto-generated method stub
		return this.listaDeProdutos;
	}

	public Produto pesquisarProduto(String codigo) {
		// TODO Auto-generated method stub
		for(int i = 0; i< listaDeProdutos.size(); i++){
			Produto produto = listaDeProdutos.get(i);
			//O equals não funciona com inteiros?
			if (codigo.equals(produto.getCodigo())){
				return produto;
			}
		}
		return null;
	}
	
	public void adicionarProduto(String codigo, String nome, double preco) {
		// TODO Auto-generated method stub
		for (int i = 0; i < listaDeProdutos.size();i++){
			if (produto.getCodigo() == codigo){
				throw new ExcecaoEstoquePastelaria("O código já existe");
			}
		}
		this.produto = new Produto(codigo, nome, preco);
		this.listaDeProdutos.add(produto);
	}

	public boolean isRemoverProduto(String codigo) {
		// TODO Auto-generated method stub
		Produto produto = pesquisarProduto(codigo);
		for (int i = 0; i < listaDeProdutos.size();i++){
			if (produto == null){
				throw new ExcecaoEstoquePastelaria("O produto não existe");
			}
		}
		if(produto != null){
			listaDeProdutos.remove(produto);
			return true;
		}else{
			return false;
		}
	}

	public String visualizarProdutos() {
		// TODO Auto-generated method stub
		
		if(this.listaDeProdutos.size() == 0){
			 throw new ExcecaoEstoquePastelaria("O produto não existe");
		}
		String visualizar = "#";
		for (int i = 0; i < listaDeProdutos.size(); i++ ){
			Produto produto = listaDeProdutos.get(i);
			visualizar = visualizar+" "+produto.getCodigo()+" "+produto.getNome()+" "+produto.getPreco();
		}
		return visualizar;
	}

	

}
