package Pastelaria;

public class Produto {
	
	private String codigo;
	private String nome;
	private double preco;

	public Produto(String codigo, String nome, double preco) {
		// TODO Auto-generated constructor stub
		this.codigo = codigo;
		this.nome = nome;
		this.preco = preco;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNome() {
		// TODO Auto-generated method stub
		return nome;
	}

	public double getPreco() {
		// TODO Auto-generated method stub
		return preco;
	}

}
