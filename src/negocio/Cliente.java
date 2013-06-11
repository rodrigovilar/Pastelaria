package negocio;

public class Cliente {

	private String nome;
	private String telefone;
	private String endereco;
	private String pontoRef;
	
	public Cliente(String nome, String telefone, String endereço, String pontoRef) {
		
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereço;
		this.pontoRef = pontoRef;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereço() {
		return endereco;
	}

	public void setEndereço(String endereço) {
		this.endereco = endereço;
	}

	public String getPontoRef() {
		return pontoRef;
	}

	public void setPontoRef(String pontoRef) {
		this.pontoRef = pontoRef;
	}
	
}

