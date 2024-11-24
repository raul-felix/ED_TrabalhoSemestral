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
	public int hashCode() {
		long codigo = Long.parseLong(getCpf());
		
		codigo = (int) (((codigo / 1000000) / 7.89435798437) / 3.146789);
		
		return (int) codigo;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professor other = (Professor) obj;
		return qtdPontos == other.qtdPontos;
	}
	@Override
	public String toString() {
		return getCodigoProfessor() + ";" + getCpf() + ";" + getNomeProfessor() + ";" + getAreaConhecimento() + ";" + getQtdPontos();
	}
}
