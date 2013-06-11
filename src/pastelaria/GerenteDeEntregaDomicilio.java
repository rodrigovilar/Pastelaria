package pastelaria;

import java.util.ArrayList;
import java.util.List;



public class GerenteDeEntregaDomicilio {

	private List<Cliente> listaDeClientes = new ArrayList<Cliente>();

	public void adicionarCliente(Cliente cliente) {

		for (int i = 0; i < listaDeClientes.size(); i++) {
			if (listaDeClientes.get(i).getTelefone()
					.equals(cliente.getTelefone())) {
				throw new ExcecaoPastelaria("O cliente já existe");
			}
		}
		listaDeClientes.add(cliente);

	}

	public Cliente pesquisarCliente(String telefone) {

		for (int i = 0; i < this.listaDeClientes.size(); i++) {
			Cliente cliente = listaDeClientes.get(i);
			if (cliente.getTelefone().equals(telefone)) {
				return cliente;
			}
		}
		
		throw new ExcecaoPastelaria("O cliente não existe");
	}

	public boolean isRemoverCliente(String telefone) {
		
		Cliente cliente = this.pesquisarCliente(telefone);
		if (cliente != null) {
			this.listaDeClientes.remove(cliente);
			return true;
		} else {
			return false;
		}

	}

}
