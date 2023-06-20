package entities;

public class CategoriaOrganizada {

	private String categoria;
	private double totalMensal;
	private double totalOcasional;
	private double totalAnual;

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getTotalMensal() {
		return totalMensal;
	}

	public void setTotalMensal(double totalMensal) {
		this.totalMensal = totalMensal;
	}

	public double getTotalOcasional() {
		return totalOcasional;
	}

	public void setTotalOcasional(double totalOcasional) {
		this.totalOcasional = totalOcasional;
	}

	public double getTotalAnual() {
		return totalAnual;
	}

	public void setTotalAnual(double totalAnual) {
		this.totalAnual = totalAnual;
	}

	@Override
	public String toString() {
		return "CategoriaOrganizada [categoria=" + categoria + ", totalMensal=" + totalMensal + ", totalOcasional="
				+ totalOcasional + ", totalAnual=" + totalAnual + "]";
	}

}
