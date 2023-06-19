package entities;

public class Despesa {

	private int id;
	private Categoria categoria;
	private String despesa;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getDespesa() {
		return despesa;
	}

	public void setDespesa(String despesa) {
		this.despesa = despesa;
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
		return "Despesa [id=" + id + ", categoria=" + categoria + ", despesa=" + despesa + ", mensal=" + mensal
				+ ", ocasional=" + ocasional + ", totalAno=" + totalAno + "]";
	}
}
