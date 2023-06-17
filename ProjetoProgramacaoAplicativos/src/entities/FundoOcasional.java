package entities;

public class FundoOcasional {
	private int id;
	private String fundoOcasional;
	private double mensal;
	private double ocasional;
	private double totalAno;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFundoOcasional() {
		return fundoOcasional;
	}
	public void setFundoOcasional(String fundoOcasional) {
		this.fundoOcasional = fundoOcasional;
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
	@Override
	public String toString() {
		return "FundoOcasional [id=" + id + ", fundoOcasional=" + fundoOcasional + ", mensal=" + mensal + ", ocasional="
				+ ocasional + ", totalAno=" + totalAno + "]";
	}
	
	
}
