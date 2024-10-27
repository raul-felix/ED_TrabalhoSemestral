package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import br.com.fatec.Lista;
import model.Inscricao;

public class InscricaoDAO {
	public void buscar(String codigo) {
		boolean encontrado = false;
		Lista<Inscricao> inscricoes = listar();
		int tamanho = inscricoes.size(), i = 0;
		
		while(i < tamanho) {
			Inscricao inscricao;
			try {
				inscricao = inscricoes.get(i);
				
				String codigoInscricao = inscricao.getCodigoProcesso();
				
				if(codigoInscricao.equals(codigo)) {
					System.out.println("Curso [CPF do professor: " + inscricao.getCpf() + ", \n Código da Disciplina: " + inscricao.getCodigoDisciplina() + ", \n Código do Processo: " + inscricao.getCodigoProcesso() + "]");
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

	public void salvar(Inscricao inscricao) {
		String arquivo = "C:\\TEMP\\inscricoes.csv";
		try (BufferedWriter gravar = new BufferedWriter(new FileWriter(arquivo))){
			gravar.write(inscricao.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Lista<Inscricao> listar() {
		String arquivo = "C:\\TEMP\\inscricoes.csv";

		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			String linha;
			while((linha = ler.readLine()) != null) {
				String[] dados = linha.split(",");
				Inscricao inscricao = new Inscricao();
				inscricao.setCpf(Long.parseLong(dados[0])); 
				inscricao.setCodigoDisciplina(dados[1]);
				inscricao.setCodigoProcesso((dados[2]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public void atualizar(Inscricao inscricao) {
		String arquivo = "C:\\TEMP\\inscricoes.csv";
		String atualizado = "C:\\TEMP\\inscricoes_temp.csv";
		
		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
			try (BufferedWriter gravar = new BufferedWriter(new FileWriter(atualizado))){
				String linha;
				while((linha = ler.readLine()) != null) {
					String[] dados = linha.split(",");
					if(dados[0].equals(inscricao.getCodigoProcesso())) {
						gravar.write(inscricao.toString());
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
		String arquivo = "C:\\TEMP\\inscricoes.csv";
		String atualizado = "C:\\TEMP\\inscricoes_temp.csv";
		
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
