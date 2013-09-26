package negocio;

import java.util.ArrayList;
import java.util.List;

public class Caixa {
	
	private int dia;
	private int mes;
	private int ano;
	private double dinheiroInicioDia;
	private double dinheiroFimDia;
	private boolean aberto;
	private List <ValorRecebido> valores = new ArrayList <ValorRecebido>();
	
	public Caixa(int dia, int mes, int ano, double dinheiroInicioDia, double dinheiroFimDia, boolean aberto) {
		
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		this.dinheiroInicioDia = dinheiroInicioDia;
		this.dinheiroFimDia = dinheiroFimDia = 0;
		this.aberto = aberto;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public double getDinheiroInicioDia() {
		return dinheiroInicioDia;
	}

	public void setDinheiroInicioDia(double dinheiroInicioDia) {
		this.dinheiroInicioDia = dinheiroInicioDia;
	}

	public double getDinheiroFimDia() {
		return dinheiroFimDia;
	}

	public void setDinheiroFimDia(double dinheiroFimDia) {
		this.dinheiroFimDia = dinheiroFimDia;
	}
	
	public List<ValorRecebido> getValores() {
		return valores;
	}

	public void setValores(List<ValorRecebido> valores) {
		this.valores = valores;
	}

	public boolean getAberto() {
		return aberto;
	}

	public void setAberto(boolean aberto) {
		this.aberto = aberto;
	}
	
	
}
