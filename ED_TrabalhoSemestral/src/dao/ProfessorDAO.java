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
	public Professor buscarProfessor(int codigo) {
		String arquivo = "C:\\TEMP\\professor.csv";
		
		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			String linha;
			while((linha = ler.readLine()) != null) {
				String[] dados = linha.split(";");
				int codigoProfessor = Integer.parseInt(dados[0]);
				
				if (codigo == codigoProfessor) {
					Professor professor = new Professor();
					professor.setCodigoProfessor(codigoProfessor); 
					professor.setCpf(dados[1]);
					professor.setNomeProfessor(dados[2]);
					professor.setAreaConhecimento(dados[3]);
					professor.setQtdPontos(Integer.parseInt(dados[4]));
					return professor;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return null;
	}

	public void inserirProfessor(Professor professor) {
		String caminho = "C:\\TEMP";
		File arquivo = new File(caminho, "professor.csv");
		File diretorio = new File(caminho);
		
		if (professorJaExiste(professor.getCpf())) {
	        System.err.println("Professor já cadastrado com o CPF: " + professor.getCpf());
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
			gravar.write(professor.toString());
			gravar.newLine();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}	
	public boolean professorJaExiste(String cpf) {
	    String caminho = "C:\\TEMP";
	    File arquivo = new File(caminho, "professor.csv");

	    if (!arquivo.exists()) {
	        return false;
	    }

	    try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
	        String linha;

	        leitor.readLine();

	        while ((linha = leitor.readLine()) != null) {
	            String[] dados = linha.split(";");
	            if (dados.length > 1 && dados[1].equals(cpf)) {
	                return true;
	            }
	        }
	    } catch (IOException e) {
	        System.err.println(e.getMessage());
	    }

	    return false;
	}

	public Lista<Professor> consultarProfessores() {
		String arquivo = "C:\\TEMP\\professor.csv";
		Lista<Professor> professores = new Lista<Professor>();
		
		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			String linha;
			while((linha = ler.readLine()) != null) {
				String[] dados = linha.split(";");
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
					String[] dados = linha.split(";");
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

	public void removerProfessor(long codigo) {
		String arquivo = "C:\\TEMP\\professor.csv";
		String atualizado = "C:\\TEMP\\professor_temp.csv";
		
		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			try (BufferedWriter gravar = new BufferedWriter(new FileWriter(atualizado))){
				String linha;
				while((linha = ler.readLine()) != null) {
					String[] dados = linha.split(";");
					if(Integer.parseInt(dados[1]) != codigo) {
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
