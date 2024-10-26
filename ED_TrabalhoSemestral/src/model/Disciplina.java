package model;

import java.time.LocalTime;

public class Disciplina {
	private int codigoDisciplina;
	private String nomeDisciplina;
	private int diaDaSemana;
	private LocalTime horarioinicial;
	private int qtdHorasDiarias;
	private int codigoProcesso;
	private int codigoCurso;
	
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
	public int getDiaDaSemana() {
		return diaDaSemana;
	}
	public void setDiaDaSemana(int diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
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
	public int getCodigoCurso() {
		return codigoCurso;
	}
	public void setCodigoCurso(int codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
	
	@Override
	public String toString() {
		return "Disciplina [codigoDisciplina=" + codigoDisciplina + ", nomeDisciplina=" + nomeDisciplina
				+ ", diaDaSemana=" + diaDaSemana + ", horarioinicial=" + horarioinicial + ", qtdHorasDiarias="
				+ qtdHorasDiarias + ", codigoProcesso=" + codigoProcesso + ", codigoCurso=" + codigoCurso + "]";
	}
}
