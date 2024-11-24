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
	public Curso buscarCurso(int codigo) {
		String arquivo = "C:\\TEMP\\cursos.csv";
		
		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			String linha;
			while((linha = ler.readLine()) != null) {
				String[] dados = linha.split(";");
				int codigoCurso = Integer.parseInt(dados[0]);
				
				if (codigo == codigoCurso) {
					Curso curso = new Curso();
					curso.setCodigoCurso(codigoCurso); 
					curso.setNomeCurso(dados[1]);
					curso.setAreaConhecimento(dados[2]);
					return curso;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return null;
	}

	public void inserirCurso(Curso curso) {
		String caminho = "C:\\TEMP";
		File arquivo = new File(caminho, "cursos.csv");
		File diretorio = new File(caminho);
		
		if (cursoJaExiste(curso.getCodigoCurso())) {
	        System.err.println("Curso já cadastrado com o código: " + curso.getCodigoCurso());
	        return; // Não grava
	    }
				
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
	
	public boolean cursoJaExiste(int codigoCurso) { // Método para verficiar se o curso já existe de forma a evitar duplicados
	    String caminho = "C:\\TEMP\\cursos.csv";
	    File arquivo = new File(caminho);
	    
	    if (!arquivo.exists()) { //Se o arquivo não existe, logo o curso não existe
	        return false;
	    }
	    
	    try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
	        String linha;
	        while ((linha = leitor.readLine()) != null) {
	            String[] dados = linha.split(";");
	            if (dados.length > 0 && Integer.parseInt(dados[0]) == codigoCurso) {
	                return true;
	            }
	        }
	    } catch (IOException | NumberFormatException e) {
	        System.err.println(e.getMessage());
	    }
	    
	    return false;
	}
	
	

	public Lista<Curso> consultarCursos() {
		String arquivo = "C:\\TEMP\\cursos.csv";
		Lista<Curso> cursos = new Lista<Curso>();
		
		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			String linha;
			while((linha = ler.readLine()) != null) {
				String[] dados = linha.split(";");
				Curso curso = new Curso();
				curso.setCodigoCurso(Integer.parseInt(dados[0])); 
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
					if(Integer.parseInt(dados[0]) == curso.getCodigoCurso()) {
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
