package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import br.com.fatec.Lista;
import model.Professor;

public class ProfessorDAO {
	public void buscarProfessor(int codigo) {
		boolean encontrado = false;
		Lista<Professor> professores = consultarProfessor();
		int tamanho = professores.size(), i = 0;
		
		while(i < tamanho) {
			Professor professor;
			try {
				professor = professores.get(i);
				
				int codigoProfessor = professor.getCodigoProfessor();
				
				if(codigoProfessor == codigo) {
					System.out.println("Professor [Código do Professor: " + professor.getCodigoProfessor() + ", \n CPF: " + professor.getCpf() + ", \n Nome do Professor: " + professor.getNomeProfessor() + ", \n Área de Conhecimento: " + professor.getAreaConhecimento() + ", \n Quantidade de Pontos: " + professor.getNomeProfessor() + "]");
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
            System.out.println("O professor de código '" + codigo + "' não foi encontrado! Por favor, verifique o código e digite novamente!");
        }
	}

	public void inserirProfessor(Professor professor) {
		String caminho = "C:\\TEMP";
		File arquivo = new File(caminho, "professor.csv");
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
			gravar.write(professor.toString());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public Lista<Professor> consultarProfessor() {
		String arquivo = "C:\\TEMP\\professor.csv";
		Lista<Professor> professores = new Lista<Professor>();
		
		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			String linha;
			while((linha = ler.readLine()) != null) {
				String[] dados = linha.split(",");
				Professor professor = new Professor();
				professor.setCodigoProfessor(Integer.parseInt(dados[0])); 
				professor.setCpf(dados[1]);
				professor.setNomeProfessor(dados[2]);
				professor.setAreaConhecimento(dados[3]);
				professor.setQtdPontos(Integer.parseInt(dados[4]));
				professores.addLast(professor);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return professores;
	}

	public void atualizarProfessor(Professor professor) {
		String arquivo = "C:\\TEMP\\professor.csv";
		String atualizado = "C:\\TEMP\\professor_temp.csv";
		
		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			try (BufferedWriter gravar = new BufferedWriter(new FileWriter(atualizado))){
				String linha;
				while((linha = ler.readLine()) != null) {
					String[] dados = linha.split(",");
					if(Integer.parseInt(dados[0]) == professor.getCodigoProfessor()) {
						gravar.write(professor.toString());
						gravar.newLine();
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

	public void removerProfessor(int codigo) {
		String arquivo = "C:\\TEMP\\professor.csv";
		String atualizado = "C:\\TEMP\\professor_temp.csv";
		
		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			try (BufferedWriter gravar = new BufferedWriter(new FileWriter(atualizado))){
				String linha;
				while((linha = ler.readLine()) != null) {
					String[] dados = linha.split(",");
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
