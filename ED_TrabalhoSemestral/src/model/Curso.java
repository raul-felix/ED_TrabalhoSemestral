package model;

public class Curso {
	private String codigoCurso;
	private String nomeCurso;
	private String areaConhecimento;
	
	public String getCodigoCurso() {
		return codigoCurso;
	}
	public void setCodigoCurso(String codigoCurso) {
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
