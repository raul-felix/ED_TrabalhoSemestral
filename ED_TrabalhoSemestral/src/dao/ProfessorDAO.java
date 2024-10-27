package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import br.com.fatec.Lista;
import model.Professor;

public class ProfessorDAO {
	public void buscar(String codigo) {
		boolean encontrado = false;
		Lista<Professor> professores = listar();
		int tamanho = professores.size(), i = 0;
		
		while(i < tamanho) {
			Professor professor;
			try {
				professor = professores.get(i);
				
				String codigoProfessor = professor.getCodigoProfessor();
				
				if(codigoProfessor.equals(codigo)) {
					System.out.println("Curso [Código do Professor: " + professor.getCodigoProfessor() + ", \n CPF: " + professor.getCpf() + ", \n Nome do Professor: " + professor.getNomeProfessor() + ", \n Área de Conhecimento: " + professor.getAreaConhecimento() + ", \n Quantidade de Pontos: " + professor.getNomeProfessor() + "]");
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

	public void salvar(Professor professor) {
		String arquivo = "C:\\TEMP\\professor.csv";
		try (BufferedWriter gravar = new BufferedWriter(new FileWriter(arquivo))){
			gravar.write(professor.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Lista<Professor> listar() {
		String arquivo = "C:\\TEMP\\professor.csv";

		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			String linha;
			while((linha = ler.readLine()) != null) {
				String[] dados = linha.split(",");
				Professor professor = new Professor();
				professor.setCodigoProfessor(dados[0]); 
				professor.setCpf(Integer.parseInt(dados[1]));
				professor.setNomeProfessor(dados[2]);
				professor.setAreaConhecimento(dados[3]);
				professor.setQtdPontos(Integer.parseInt(dados[4]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public void atualizar(Professor professor) {
		String arquivo = "C:\\TEMP\\professor.csv";
		String atualizado = "C:\\TEMP\\professor_temp.csv";
		
		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			try (BufferedWriter gravar = new BufferedWriter(new FileWriter(atualizado))){
				String linha;
				while((linha = ler.readLine()) != null) {
					String[] dados = linha.split(",");
					if(dados[0].equals(professor.getCodigoProfessor())) {
						gravar.write(professor.toString());
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
		String arquivo = "C:\\TEMP\\professor.csv";
		String atualizado = "C:\\TEMP\\professor_temp.csv";
		
		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			try (BufferedWriter gravar = new BufferedWriter(new FileWriter(atualizado))){
				String linha;
				while((linha = ler.readLine()) != null) {
					String[] dados = linha.split(",");
					if(dados[0].equals(codigo)) {
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
