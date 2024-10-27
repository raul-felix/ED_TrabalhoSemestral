package model;

public class Professor {
	private String codigoProfessor;
	private long cpf;
	private String nomeProfessor;
	private String areaConhecimento;
	private int qtdPontos;

	public String getCodigoProfessor() {
		return codigoProfessor;
	}
	public void setCodigoProfessor(String codigoProfessor) {
		this.codigoProfessor = codigoProfessor;
	}
	public long getCpf() {
		return cpf;
	}
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}
	public String getNomeProfessor() {
		return nomeProfessor;
	}
	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}
	public String getAreaConhecimento() {
		return areaConhecimento;
	}
	public void setAreaConhecimento(String areaConhecimento) {
		this.areaConhecimento = areaConhecimento;
	}
	public int getQtdPontos() {
		return qtdPontos;
	}
	public void setQtdPontos(int qtdPontos) {
		this.qtdPontos = qtdPontos;
	}
	
	@Override
	public String toString() {
		return getCodigoProfessor() + "," + getCpf() + "," + getNomeProfessor() + "," + getAreaConhecimento() + "," + getQtdPontos() + "\n";
	}
}
