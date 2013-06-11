package pastelaria;

import java.util.ArrayList;
import java.util.List;

public class Comanda {

	private int numMesa;
	private List<Produto> produto = new ArrayList<Produto>();
	private int qtdeItemProd;

	public Comanda(int numMesa, List<Produto> produto, int qtdeItemProd) {

		this.numMesa = numMesa;
		this.produto = produto;
		this.qtdeItemProd = qtdeItemProd;

	}

	public int getNumMesa() {
		return numMesa;
	}

	public void setNumMesa(int numMesa) {
		this.numMesa = numMesa;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

	public int getQtdeItemProd() {
		return qtdeItemProd;
	}

	public void setQtdeItemProd(int qtdeItemProd) {
		this.qtdeItemProd = qtdeItemProd;
	}

	
}