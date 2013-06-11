package negocio;

import java.util.ArrayList;
import java.util.List;


public class Comanda {
	
	private int numMesa;
	private List<ItemDeComanda> itens = new ArrayList<ItemDeComanda>();
	
	public Comanda(int numMesa){
		this.numMesa=numMesa;
	}
	
	public int getNumMesa() {
		return numMesa;
	}

	public void setNumMesa(int numMesa) {
		this.numMesa = numMesa;
	}

	public List<ItemDeComanda> getItens() {
		return itens;
	}

	public void setItens(List<ItemDeComanda> itens) {
		this.itens = itens;
	}

	
	
}
