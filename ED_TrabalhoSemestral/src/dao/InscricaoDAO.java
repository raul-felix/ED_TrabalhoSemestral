package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import br.com.fatec.Lista;
import model.Inscricao;

public class InscricaoDAO {
	public Inscricao buscarInscricao(String cpf, int codigoDisciplina) {
		String arquivo = "C:\\TEMP\\inscricoes.csv";
		
		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			String linha;
			while((linha = ler.readLine()) != null) {
				String[] dados = linha.split(";");
				String cpfProfessorInscr = dados[0];
				int codigoDisciplinaInscr = Integer.parseInt(dados[1]);
				
				if (cpf.equals(cpfProfessorInscr) && codigoDisciplina == codigoDisciplinaInscr) {
					Inscricao inscricao = new Inscricao();
					inscricao.setCpf(cpfProfessorInscr); 
					inscricao.setCodigoDisciplina(codigoDisciplinaInscr);
					inscricao.setCodigoProcesso(Integer.parseInt(dados[2]));
					return inscricao;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return null;
	}

	public void inserirInscricao(Inscricao inscricao) {
		String caminho = "C:\\TEMP";
		File arquivo = new File(caminho, "inscricoes.csv");
		File diretorio = new File(caminho);
		
		if (inscricaoJaExiste(inscricao.getCpf())) {
	        System.err.println("Inscrição já realizada com o CPF: " + inscricao.getCpf());
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
			gravar.write(inscricao.toString());
			gravar.newLine();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public boolean inscricaoJaExiste(String cpf) {
	    String caminho = "C:\\TEMP";
	    File arquivo = new File(caminho, "inscricoes.csv");

	    if (!arquivo.exists()) {
	        return false;
	    }

	    try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
	        String linha;

	        leitor.readLine();

	        while ((linha = leitor.readLine()) != null) {
	            String[] dados = linha.split(";");
	            if (dados.length > 0 && dados[0].equals(cpf)) {
	                return true;
	            }
	        }
	    } catch (IOException e) {
	        System.err.println(e.getMessage());
	    }

	    return false;
	}

	public Lista<Inscricao> consultarInscricoes() {
		String arquivo = "C:\\TEMP\\inscricoes.csv";
		Lista<Inscricao> inscricoes = new Lista<Inscricao>();
		
		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			String linha;
			while((linha = ler.readLine()) != null) {
				String[] dados = linha.split(";");
				Inscricao inscricao = new Inscricao();
				inscricao.setCpf(dados[0]); 
				inscricao.setCodigoDisciplina(Integer.parseInt(dados[1]));
				inscricao.setCodigoProcesso(Integer.parseInt(dados[2]));
				inscricoes.addLast(inscricao);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return inscricoes;
	}

	public void atualizarInscricao(Inscricao inscricao) {
		String arquivo = "C:\\TEMP\\inscricoes.csv";
		String atualizado = "C:\\TEMP\\inscricoes_temp.csv";
		
		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			try (BufferedWriter gravar = new BufferedWriter(new FileWriter(atualizado))){
				String linha;
				while((linha = ler.readLine()) != null) {
					String[] dados = linha.split(";");
					if(Integer.parseInt(dados[2]) == inscricao.getCodigoProcesso()) {
						gravar.write(inscricao.toString());
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

	public void removerInscricao(int codigo) {
		String arquivo = "C:\\TEMP\\inscricoes.csv";
		String atualizado = "C:\\TEMP\\inscricoes_temp.csv";
		
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
