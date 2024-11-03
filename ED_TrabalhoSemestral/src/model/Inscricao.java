package model;

public class Inscricao {
	private String cpf;
	private int codigoDisciplina;
	private int codigoProcesso;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public int getCodigoDisciplina() {
		return codigoDisciplina;
	}
	public void setCodigoDisciplina(int codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}
	public int getCodigoProcesso() {
		return codigoProcesso;
	}
	public void setCodigoProcesso(int codigoProcesso) {
		this.codigoProcesso = codigoProcesso;
	}
	
	@Override
	public String toString() {
		return getCpf() + ";" + getCodigoDisciplina() + ";" + getCodigoProcesso();
	}
}
