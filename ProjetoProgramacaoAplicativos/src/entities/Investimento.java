package entities;

public class Investimento {
	private int id;
	private String investimento;
	private double mensal;
	private double ocasional;
	private double totalAno;
	private String data;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInvestimento() {
		return investimento;
	}
	public void setInvestimento(String investimento) {
		this.investimento = investimento;
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
	public void setOcasional(double ocasional) {
		this.ocasional = ocasional;
	}
	public double getTotalAno() {
		return totalAno;
	}
	public void setTotalAno(double totalAno) {
		this.totalAno = totalAno;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "Investimento [id=" + id + ", investimento=" + investimento + ", mensal=" + mensal + ", ocasional="
				+ ocasional + ", totalAno=" + totalAno + "]";
	}
}
