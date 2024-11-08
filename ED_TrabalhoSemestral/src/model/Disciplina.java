package model;

import java.time.LocalTime;

public class Disciplina {
	private int codigoDisciplina;
	private String nomeDisciplina;
	private String diaDaSemana;
	private LocalTime horarioinicial;
	private int qtdHorasDiarias;
	private int codigoProcesso;
	private String codigoCurso;
	
	public int getCodigoDisciplina() {
		return codigoDisciplina;
	}
	public void setCodigoDisciplina(int codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}
	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}
	public String getDiaDaSemana() {
		return diaDaSemana;
	}
	public void setDiaDaSemana(String dados) {
		this.diaDaSemana = dados;
	}
	public LocalTime getHorarioinicial() {
		return horarioinicial;
	}
	public void setHorarioinicial(LocalTime horarioinicial) {
		this.horarioinicial = horarioinicial;
	}
	public int getQtdHorasDiarias() {
		return qtdHorasDiarias;
	}
	public void setQtdHorasDiarias(int qtdHorasDiarias) {
		this.qtdHorasDiarias = qtdHorasDiarias;
	}
	public int getCodigoProcesso() {
		return codigoProcesso;
	}
	public void setCodigoProcesso(int codigoProcesso) {
		this.codigoProcesso = codigoProcesso;
	}
	public String getCodigoCurso() {
		return codigoCurso;
	}
	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
	
	@Override
	public String toString() {
		return getCodigoDisciplina() + ";" + getNomeDisciplina() + ";" + getDiaDaSemana() + ";" + getHorarioinicial() + ";" + getQtdHorasDiarias() + ";" + getCodigoProcesso() + ";" + getCodigoCurso() +"\n";
	}
}