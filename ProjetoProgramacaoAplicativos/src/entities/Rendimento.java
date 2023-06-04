package entities;

public class Rendimento {
	private Categoria categoria;
	private String rendimento;
	private double mensal;
	private double ocasional;
	private double totalAno;
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getRendimento() {
		return rendimento;
	}
	public void setRendimento(String rendimento) {
		this.rendimento = rendimento;
	}
	public double getMensal() {
		return mensal;
	}
	public void setMensal(double mensal) {
		this.mensal = mensal;
	}
	public double getOcasional() {
		return ocasional;
	}
	public void setOcasional(double ano) {
		this.ocasional = ano;
	}
	public double getTotalAno() {
		return totalAno;
	}
	public void setTotalAno(double totalAno) {
		this.totalAno = totalAno;
	}
}
