package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalTime;

import br.com.fatec.Lista;
import model.Disciplina;

public class DisciplinaDAO {
	public void buscar(String codigo) {
		boolean encontrado = false;
		Lista<Disciplina> disciplinas = listar();
		int tamanho = disciplinas.size(), i = 0;
		
		while(i < tamanho) {
			Disciplina disciplina;
			try {
				disciplina = disciplinas.get(i);
				
				String codigoDisciplina = disciplina.getCodigoDisciplina();
				
				if(codigoDisciplina.equals(codigo)) {
					System.out.println("Curso [Código da Disciplina: " + disciplina.getCodigoDisciplina() + ", \n Nome da Disciplina: " + disciplina.getNomeDisciplina() + ", \n Dia da semana que a disciplina será ministrada: " + disciplina.getDiaDaSemana() + ", \n Horário inicial que a disciplina será ministrada: " + disciplina.getHorarioinicial() + ", \n Quantidade de horas diárias: " + disciplina.getQtdHorasDiarias() + ", \n Código do Processo: " + disciplina.getCodigoProcesso() + ", \n Código do Curso: " + disciplina.getCodigoCurso() + "]");
					encontrado = true;
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        if (!encontrado) {
            System.out.println("A disciplina de código '" + codigo + "' não foi encontrada! Por favor, verifique o código e digite novamente!");
        }
	}

	public void salvar(Disciplina disciplina) {
		String arquivo = "C:\\TEMP\\disciplinas.csv";
		try (BufferedWriter gravar = new BufferedWriter(new FileWriter(arquivo))){
			gravar.write(disciplina.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Lista<Disciplina> listar() {
		String arquivo = "C:\\TEMP\\disciplinas.csv";
		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			String linha;
			while((linha = ler.readLine()) != null) {
				String[] dados = linha.split(",");
				Disciplina disciplina = new Disciplina();
				disciplina.setCodigoDisciplina(dados[0]); 
				disciplina.setNomeDisciplina(dados[1]);
				disciplina.setDiaDaSemana(Integer.parseInt(dados[2]));
				disciplina.setHorarioinicial(LocalTime.parse(dados[3]));
				disciplina.setQtdHorasDiarias(Integer.parseInt(dados[4]));
				disciplina.setCodigoProcesso(Integer.parseInt(dados[5]));
				disciplina.setCodigoCurso(dados[6]);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void atualizar(Disciplina disciplina) {
		String arquivo = "C:\\TEMP\\disciplinas.csv";
		String atualizado = "C:\\TEMP\\disciplinas_temp.csv";
		
		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			try (BufferedWriter gravar = new BufferedWriter(new FileWriter(atualizado))){
				String linha;
				while((linha = ler.readLine()) != null) {
					String[] dados = linha.split(",");
					if(dados[0].equals(disciplina.getCodigoDisciplina())) {
						gravar.write(disciplina.toString());
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
		String arquivo = "C:\\TEMP\\disciplinas.csv";
		String atualizado = "C:\\TEMP\\disciplinas_temp.csv";
		
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
