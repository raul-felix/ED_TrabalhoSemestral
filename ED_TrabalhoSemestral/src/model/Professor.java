package model;

public class Professor {
	private int cpf;
	private String nomeProfessor;
	private String areaConhecimento;
	private int qtdPontos;
	
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
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	
	@Override
	public String toString() {
		return "Professor [cpf=" + cpf + ", nomeProfessor=" + nomeProfessor + ", areaConhecimento=" + areaConhecimento
				+ ", qtdPontos=" + qtdPontos + "]";
	}
}
