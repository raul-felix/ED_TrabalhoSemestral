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
	public void buscarInscricao(int codigo) {
		boolean encontrado = false;
		Lista<Inscricao> inscricoes = consultarInscricao();
		int tamanho = inscricoes.size(), i = 0;
		
		while(i < tamanho) {
			Inscricao inscricao;
			try {
				inscricao = inscricoes.get(i);
				
				int codigoInscricao = inscricao.getCodigoProcesso();
				
				if(codigoInscricao == codigo) {
					System.out.println("Inscricao [CPF do professor: " + inscricao.getCpf() + ", \n Código da Disciplina: " + inscricao.getCodigoDisciplina() + ", \n Código do Processo: " + inscricao.getCodigoProcesso() + "]");
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
        	System.out.println("A inscrição de código '" + codigo + "' não foi encontrada! Por favor, verifique o código e digite novamente!");
        }
	}

	public void inserirInscricao(Inscricao inscricao) {
		String caminho = "C:\\TEMP";
		File arquivo = new File(caminho, "inscricoes.csv");
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
			gravar.write(inscricao.toString());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public Lista<Inscricao> consultarInscricao() {
		String arquivo = "C:\\TEMP\\inscricoes.csv";
		Lista<Inscricao> inscricoes = new Lista<Inscricao>();
		
		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			String linha;
			while((linha = ler.readLine()) != null) {
				String[] dados = linha.split(",");
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
					String[] dados = linha.split(",");
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
