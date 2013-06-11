package negocio;

public class Produto {
	
	private String codigo;
	private String nome;
	private double preco;
	private int qtdeProduto;
	
	public Produto(String codigo, String nome, double preco, int qtdeProduto) {
		
		this.codigo = codigo;
		this.nome = nome;
		this.preco = preco;
		this.qtdeProduto = qtdeProduto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQtdeProduto() {
		return qtdeProduto;
	}

	public void setQtdeProduto(int qtdeProduto) {
		this.qtdeProduto = qtdeProduto;
	}

	public String toString() {
		return "Codigo= " + codigo + " nome= " + nome + ", preco= "
				+ preco + ", qtdeProduto= " + qtdeProduto + "] \n";
	}
}
