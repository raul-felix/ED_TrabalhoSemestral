package model;

public class Inscricao {
	private int cpf;
	private int codigoDisciplina;
	private int codigoProcesso;
	
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
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
		return "Inscricao [cpf=" + cpf + ", codigoDisciplina=" + codigoDisciplina + ", codigoProcesso=" + codigoProcesso
				+ "]";
	}
}
