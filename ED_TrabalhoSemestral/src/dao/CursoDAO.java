package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import br.com.fatec.Lista;
import model.Curso;

public class CursoDAO{
	public void buscar(String codigo) {
		boolean encontrado = false;
		Lista<Curso> cursos = listar();
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
				e.printStackTrace();
			}
		}
        if (!encontrado) {
            System.out.println("O curso de código '" + codigo + "' não foi encontrado! Por favor, verifique o código e digite novamente!");
        }
	}

	public void salvar(Curso curso) {
		String arquivo = "C:\\TEMP\\cursos.csv";
		try (BufferedWriter gravar = new BufferedWriter(new FileWriter(arquivo))){
			gravar.write(curso.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Lista<Curso> listar() {
		String arquivo = "C:\\TEMP\\cursos.csv";
		
		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			String linha;
			while((linha = ler.readLine()) != null) {
				String[] dados = linha.split(",");
				Curso curso = new Curso();
				curso.setCodigoCurso(dados[0]); 
				curso.setNomeCurso(dados[1]);
				curso.setAreaConhecimento(dados[2]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public void atualizar(Curso curso) {
		String arquivo = "C:\\TEMP\\cursos.csv";
		String atualizado = "C:\\TEMP\\cursos_temp.csv";
		
		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			try (BufferedWriter gravar = new BufferedWriter(new FileWriter(atualizado))){
				String linha;
				while((linha = ler.readLine()) != null) {
					String[] dados = linha.split(",");
					if(dados[0].equals(curso.getCodigoCurso())) {
						gravar.write(curso.toString());
					}else {
						gravar.write(linha);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		new File(arquivo).delete();
        new File(atualizado).renameTo(new File(arquivo));
	}

	public void remover(String codigo) {
		String arquivo = "C:\\TEMP\\cursos.csv";
		String atualizado = "C:\\TEMP\\cursos_temp.csv";
		
		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			try (BufferedWriter gravar = new BufferedWriter(new FileWriter(atualizado))){
				String linha;
				while((linha = ler.readLine()) != null) {
					String[] dados = linha.split(",");
					if(!dados[0].equals(codigo)) {
						gravar.write(linha);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		new File(arquivo).delete();
        new File(atualizado).renameTo(new File(arquivo));
	}
}
