package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import br.com.fatec.Lista;
import model.Disciplina;

public class DisciplinaDAO {
	public Disciplina buscarDisciplina(int codigo) {
		String arquivo = "C:\\TEMP\\disciplinas.csv";
		
		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			String linha;
			while((linha = ler.readLine()) != null) {
				String[] dados = linha.split(";");
				int codigoDisciplina = Integer.parseInt(dados[0]);
				
				if (codigo == codigoDisciplina) {
					Disciplina disciplina = new Disciplina();
					disciplina.setCodigoDisciplina(codigoDisciplina); 
					disciplina.setNomeDisciplina(dados[1]);
					disciplina.setDiaDaSemana(dados[2]);
					disciplina.setHorarioinicial(LocalTime.parse(dados[3], DateTimeFormatter.ofPattern("HH:mm")));
					disciplina.setQtdHorasDiarias(Integer.parseInt(dados[4]));
					disciplina.setCodigoProcesso(Integer.parseInt(dados[5]));
					disciplina.setCodigoCurso(Integer.parseInt(dados[6]));
					return disciplina;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public void inserirDisciplina(Disciplina disciplina) {
		String caminho = "C:\\TEMP";
		File arquivo = new File(caminho, "disciplinas.csv");
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
			gravar.write(disciplina.toString());
			gravar.newLine();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public Lista<Disciplina> consultarDisciplinas() {
		String arquivo = "C:\\TEMP\\disciplinas.csv";
		Lista<Disciplina> disciplinas = new Lista<Disciplina>();
		
		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			String linha;
			while((linha = ler.readLine()) != null) {
				String[] dados = linha.split(";");
				Disciplina disciplina = new Disciplina();
				disciplina.setCodigoDisciplina(Integer.parseInt(dados[0])); 
				disciplina.setNomeDisciplina(dados[1]);
				disciplina.setDiaDaSemana(dados[2]);
				disciplina.setHorarioinicial(LocalTime.parse(dados[3], DateTimeFormatter.ofPattern("HH:mm")));
				disciplina.setQtdHorasDiarias(Integer.parseInt(dados[4]));
				disciplina.setCodigoProcesso(Integer.parseInt(dados[5]));
				disciplina.setCodigoCurso(Integer.parseInt(dados[6]));
				disciplinas.addLast(disciplina);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return disciplinas;
	}

	public void atualizarDisciplina(Disciplina disciplina) {
		String arquivo = "C:\\TEMP\\disciplinas.csv";
		String atualizado = "C:\\TEMP\\disciplinas_temp.csv";
		
		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			try (BufferedWriter gravar = new BufferedWriter(new FileWriter(atualizado))){
				String linha;
				while((linha = ler.readLine()) != null) {
					String[] dados = linha.split(";");
					if(Integer.parseInt(dados[0]) == disciplina.getCodigoDisciplina()) {
						gravar.write(disciplina.toString());
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

	public void removerDisciplina(int codigo) {
		String arquivo = "C:\\TEMP\\disciplinas.csv";
		String atualizado = "C:\\TEMP\\disciplinas_temp.csv";
		
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
