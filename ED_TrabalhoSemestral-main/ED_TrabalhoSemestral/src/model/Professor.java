package model;

public class Professor {
	private int codigoProfessor;
	private String cpf;
	private String nomeProfessor;
	private String areaConhecimento;
	private int qtdPontos;

	public int getCodigoProfessor() {
		return codigoProfessor;
	}
	public void setCodigoProfessor(int codigoProfessor) {
		this.codigoProfessor = codigoProfessor;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
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
		return getCodigoProfessor() + ";" + getCpf() + ";" + getNomeProfessor() + ";" + getAreaConhecimento() + ";" + getQtdPontos();
	}
}
