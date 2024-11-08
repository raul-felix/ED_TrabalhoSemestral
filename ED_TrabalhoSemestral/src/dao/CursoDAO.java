package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import br.com.fatec.Lista;
import model.Curso;

public class CursoDAO{
	public void buscarCurso(String codigo) {
		boolean encontrado = false;
		Lista<Curso> cursos = consultarCursos();
		int tamanho = cursos.size(), i = 0;
		
		while(i < tamanho) {
			Curso curso;
			try {
				curso = cursos.get(i);
				
				String codigoCurso = curso.getCodigoCurso();
				
				if(codigoCurso.equals(codigo)) {
					System.out.println("Curso [Código do Curso: " + curso.getCodigoCurso() + ", \n Nome do Curso: " + curso.getNomeCurso() + ", \n Área de conhecimento necessária:" +curso.getAreaConhecimento() + "]");
					encontrado = true;
					break;
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			} finally {
				i++;
			}
		}
        if (!encontrado) {
            System.out.println("O curso de código '" + codigo + "' não foi encontrado! Por favor, verifique o código e digite novamente!");
        }
	}

	public void inserirCurso(Curso curso) {
		String caminho = "C:\\TEMP";
		File arquivo = new File(caminho, "cursos.csv");
		File diretorio = new File(caminho);
		
		if(!diretorio.exists()) {
			diretorio.mkdir();
		}
		
		if(!arquivo.exists()) {
			try {
				arquivo.createNewFile();
			}catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
		try (BufferedWriter gravar = new BufferedWriter(new FileWriter(arquivo, true))){
			gravar.write(curso.toString());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public Lista<Curso> consultarCursos() {
		String arquivo = "C:\\TEMP\\cursos.csv";
		Lista<Curso> cursos = new Lista<Curso>();
		
		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			String linha;
			while((linha = ler.readLine()) != null) {
				String[] dados = linha.split(";");
				Curso curso = new Curso();
				curso.setCodigoCurso(dados[0]); 
				curso.setNomeCurso(dados[1]);
				curso.setAreaConhecimento(dados[2]);
				cursos.addLast(curso);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return cursos;
	}

	public void atualizarCurso(Curso curso) {
		String arquivo = "C:\\TEMP\\cursos.csv";
		String atualizado = "C:\\TEMP\\cursos_temp.csv";
		
		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			try (BufferedWriter gravar = new BufferedWriter(new FileWriter(atualizado))){
				String linha;
				while((linha = ler.readLine()) != null) {
					String[] dados = linha.split(";");
					if(dados[0].equals(curso.getCodigoCurso())) {
						gravar.write(curso.toString());
					}else {
						gravar.write(linha);
						gravar.newLine();
					}
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		new File(arquivo).delete();
        new File(atualizado).renameTo(new File(arquivo));
	}

	public void removerCurso(int codigo) {
		String arquivo = "C:\\TEMP\\cursos.csv";
		String atualizado = "C:\\TEMP\\cursos_temp.csv";
		
		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			try (BufferedWriter gravar = new BufferedWriter(new FileWriter(atualizado))){
				String linha;
				while((linha = ler.readLine()) != null) {
					String[] dados = linha.split(";");
					if(Integer.parseInt(dados[0]) != codigo) {
						gravar.write(linha);
						gravar.newLine();
					}
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		new File(arquivo).delete();
        new File(atualizado).renameTo(new File(arquivo));
	}
}
