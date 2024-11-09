package model;

public class Curso {
	private int codigoCurso;
	private String nomeCurso;
	private String areaConhecimento;
	
	public int getCodigoCurso() {
		return codigoCurso;
	}
	public void setCodigoCurso(int codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	public String getAreaConhecimento() {
		return areaConhecimento;
	}
	public void setAreaConhecimento(String areaConhecimento) {
		this.areaConhecimento = areaConhecimento;
	}
	
	@Override
	public String toString() {
		return getCodigoCurso() + ";" + getNomeCurso() + ";" + getAreaConhecimento() + "\n" ;
	}
}
