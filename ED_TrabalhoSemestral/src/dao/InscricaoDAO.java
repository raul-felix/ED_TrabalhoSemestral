// Classe InscricaoDAO:
// Esta classe é responsável por gerenciar operações relacionadas às inscrições em disciplinas,
// incluindo buscar, inserir, verificar existência, consultar, atualizar e remover inscrições.
// Os dados são armazenados em um arquivo CSV localizado no diretório "C:\\TEMP".

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

    // Busca uma inscrição pelo CPF e código da disciplina.
    public Inscricao buscarInscricao(String cpf, int codigoDisciplina) {
        String arquivo = "C:\\TEMP\\inscricoes.csv";
        
        try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = ler.readLine()) != null) {
                String[] dados = linha.split(";");
                String cpfProfessorInscr = dados[0];
                int codigoDisciplinaInscr = Integer.parseInt(dados[1]);
                
                // Verifica se o CPF e o código da disciplina correspondem.
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

    // Insere uma nova inscrição no arquivo.
    public void inserirInscricao(Inscricao inscricao) {
        String caminho = "C:\\TEMP";
        File arquivo = new File(caminho, "inscricoes.csv");
        File diretorio = new File(caminho);
        
        // Verifica se a inscrição já existe pelo CPF.
        if (inscricaoJaExiste(inscricao.getCpf())) {
            System.err.println("Inscrição já realizada com o CPF: " + inscricao.getCpf());
            return; // Não grava se já existir.
        }
        
        // Cria o diretório, se necessário.
        if (!diretorio.exists()) {
            diretorio.mkdir();
        }
        
        // Cria o arquivo, se necessário.
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }        
        
        try (BufferedWriter gravar = new BufferedWriter(new FileWriter(arquivo, true))) {
            // Grava a inscrição no arquivo.
            gravar.write(inscricao.toString());
            gravar.newLine();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    // Verifica se uma inscrição com o CPF fornecido já existe.
    public boolean inscricaoJaExiste(String cpf) {
        String caminho = "C:\\TEMP";
        File arquivo = new File(caminho, "inscricoes.csv");

        if (!arquivo.exists()) {
            return false;
        }

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;

            leitor.readLine(); // Ignora a primeira linha, se for cabeçalho.

            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length > 0 && dados[0].equals(cpf)) {
                    return true; // CPF encontrado.
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    // Retorna uma lista com todas as inscrições registradas.
    public Lista<Inscricao> consultarInscricoes() {
        String arquivo = "C:\\TEMP\\inscricoes.csv";
        Lista<Inscricao> inscricoes = new Lista<Inscricao>();
        
        try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = ler.readLine()) != null) {
                String[] dados = linha.split(";");
                Inscricao inscricao = new Inscricao();
                inscricao.setCpf(dados[0]); 
                inscricao.setCodigoDisciplina(Integer.parseInt(dados[1]));
                inscricao.setCodigoProcesso(Integer.parseInt(dados[2]));
                inscricoes.addLast(inscricao); // Adiciona a inscrição à lista.
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        return inscricoes;
    }
    
    // Consulta inscrições de uma disciplina específica pelo código.
    public Lista<Inscricao> consultarInscricoesPorDisciplina(int codDisciplina) throws Exception {
        String arquivo = "C:\\TEMP\\inscricoes.csv";
        Lista<Inscricao> inscricoes = new Lista<Inscricao>();
        
        try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = ler.readLine()) != null) {
                String[] dados = linha.split(";");
                if (Integer.parseInt(dados[1]) == codDisciplina) {
                    Inscricao inscricao = new Inscricao();
                    inscricao.setCpf(dados[0]); 
                    inscricao.setCodigoDisciplina(Integer.parseInt(dados[1]));
                    inscricao.setCodigoProcesso(Integer.parseInt(dados[2]));
                    inscricoes.addLast(inscricao); // Adiciona à lista apenas inscrições da disciplina especificada.
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        return inscricoes;
    }

    // Atualiza os dados de uma inscrição existente.
    public void atualizarInscricao(Inscricao inscricao) {
        String arquivo = "C:\\TEMP\\inscricoes.csv";
        String atualizado = "C:\\TEMP\\inscricoes_temp.csv";
        
        try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))) {
            try (BufferedWriter gravar = new BufferedWriter(new FileWriter(atualizado))) {
                String linha;
                while ((linha = ler.readLine()) != null) {
                    String[] dados = linha.split(";");
                    // Substitui a inscrição que possui o código do processo fornecido.
                    if (Integer.parseInt(dados[2]) == inscricao.getCodigoProcesso()) {
                        gravar.write(inscricao.toString());
                    } else {
                        gravar.write(linha);
                    }
                    gravar.newLine();
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        // Substitui o arquivo antigo pelo atualizado.
        new File(arquivo).delete();
        new File(atualizado).renameTo(new File(arquivo));
    }

    // Remove uma inscrição com base no código do processo.
    public void removerInscricao(int codigo) {
        String arquivo = "C:\\TEMP\\inscricoes.csv";
        String atualizado = "C:\\TEMP\\inscricoes_temp.csv";
        
        try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))) {
            try (BufferedWriter gravar = new BufferedWriter(new FileWriter(atualizado))) {
                String linha;
                while ((linha = ler.readLine()) != null) {
                    String[] dados = linha.split(";");
                    // Grava apenas as inscrições cujo código do processo não corresponda ao fornecido.
                    if (Integer.parseInt(dados[0]) != codigo) {
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
        
        // Substitui o arquivo antigo pelo atualizado.
        new File(arquivo).delete();
        new File(atualizado).renameTo(new File(arquivo));
    }
}
