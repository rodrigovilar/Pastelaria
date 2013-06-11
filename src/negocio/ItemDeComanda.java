package negocio;


public class ItemDeComanda {
	
	private Produto produto;
	private int qtdeItem;
	
	public ItemDeComanda(Produto produto, int qtdeItem) {
		
		this.produto = produto;
		this.qtdeItem = qtdeItem;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQtdeItem() {
		return qtdeItem;
	}

	public void setQtdeItem(int qtdeItem) {
		this.qtdeItem = qtdeItem;
	}
	
}
