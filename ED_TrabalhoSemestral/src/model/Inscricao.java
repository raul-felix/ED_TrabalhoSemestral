package model;

public class Inscricao {
	private long cpf;
	private String codigoDisciplina;
	private String codigoProcesso;
	
	public long getCpf() {
		return cpf;
	}
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}
	public String getCodigoDisciplina() {
		return codigoDisciplina;
	}
	public void setCodigoDisciplina(String codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}
	public String getCodigoProcesso() {
		return codigoProcesso;
	}
	public void setCodigoProcesso(String codigoProcesso) {
		this.codigoProcesso = codigoProcesso;
	}
	
	@Override
	public String toString() {
		return getCpf() + "," + getCodigoDisciplina() + "," + getCodigoProcesso() + "\n";
	}
}
