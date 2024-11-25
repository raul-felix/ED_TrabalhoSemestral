// A classe ProfessorDAO gerencia a leitura e escrita de informações sobre professores em um arquivo CSV.
// Ela permite buscar, inserir, consultar, atualizar e remover professores no arquivo, realizando operações de manipulação de arquivos.

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

    // Método para buscar um professor no arquivo CSV pelo CPF
    public Professor buscarProfessor(String cpf) {
        String arquivo = "C:\\TEMP\\professor.csv";
        
        try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
            String linha;
            // Lê linha por linha do arquivo
            while((linha = ler.readLine()) != null) {
                String[] dados = linha.split(";"); // Divide cada linha pelo delimitador ";"
                String cpfProfessor = dados[1]; // Obtém o CPF do professor da linha
                
                // Se o CPF do professor encontrado for igual ao informado, retorna o professor
                if (cpf.equals(cpfProfessor)) {
                    Professor professor = new Professor();
                    professor.setCodigoProfessor(Integer.parseInt(dados[0])); 
                    professor.setCpf(dados[1]);
                    professor.setNomeProfessor(dados[2]);
                    professor.setAreaConhecimento(dados[3]);
                    professor.setQtdPontos(Integer.parseInt(dados[4]));
                    return professor;
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage()); // Caso haja erro, exibe a mensagem
        }
        
        return null; // Se não encontrar o professor, retorna null
    }

    // Método para inserir um novo professor no arquivo CSV
    public void inserirProfessor(Professor professor) {
        String caminho = "C:\\TEMP";
        File arquivo = new File(caminho, "professor.csv");
        File diretorio = new File(caminho);
        
        // Verifica se o professor já existe pelo CPF
        if (professorJaExiste(professor.getCpf())) {
            System.err.println("Professor já cadastrado com o CPF: " + professor.getCpf());
            return; // Não grava o professor
        }
        
        // Cria o diretório e o arquivo caso não existam
        if(!diretorio.exists()) {
            diretorio.mkdir();
        }
        
        if(!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                System.err.println(e.getMessage()); // Caso haja erro na criação do arquivo
            }
        }

        // Grava o professor no arquivo CSV
        try (BufferedWriter gravar = new BufferedWriter(new FileWriter(arquivo, true))){
            gravar.write(professor.toString()); // Escreve os dados do professor
            gravar.newLine(); // Pula para a próxima linha no arquivo
        } catch (Exception e) {
            System.err.println(e.getMessage()); // Caso haja erro ao gravar
        }

    }    

    // Método para verificar se um professor já existe no arquivo pelo CPF
    public boolean professorJaExiste(String cpf) {
        String caminho = "C:\\TEMP";
        File arquivo = new File(caminho, "professor.csv");

        // Se o arquivo não existir, retorna falso
        if (!arquivo.exists()) {
            return false;
        }

        // Lê o arquivo e verifica se o CPF já existe
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;

            leitor.readLine(); // Ignora a primeira linha (cabeçalho)

            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(";");
             // Pega a segunda coluna do arquivo e compara se já existe um cadastro com o mesmo CPF.
                if (dados.length > 1 && dados[1].equals(cpf)) {
                    return true; // Se o CPF for encontrado, retorna verdadeiro
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage()); // Caso haja erro ao ler o arquivo
        }

        return false; // Se o CPF não for encontrado, retorna falso
    }

    // Método para consultar todos os professores no arquivo CSV
    public Lista<Professor> consultarProfessores() {
        String arquivo = "C:\\TEMP\\professor.csv";
        Lista<Professor> professores = new Lista<Professor>();
        
        try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
            String linha;
            // Lê linha por linha do arquivo e adiciona os professores na lista
            while((linha = ler.readLine()) != null) {
                String[] dados = linha.split(";");
                Professor professor = new Professor();
                professor.setCodigoProfessor(Integer.parseInt(dados[0])); 
                professor.setCpf(dados[1]);
                professor.setNomeProfessor(dados[2]);
                professor.setAreaConhecimento(dados[3]);
                professor.setQtdPontos(Integer.parseInt(dados[4]));
                professores.addLast(professor); // Adiciona o professor à lista
            }
        } catch (Exception e) {
            System.err.println(e.getMessage()); // Caso haja erro ao ler o arquivo
        }
        
        return professores; // Retorna a lista de professores
    }

    // Método para atualizar os dados de um professor no arquivo CSV
    public void atualizarProfessor(Professor professor) {
        String arquivo = "C:\\TEMP\\professor.csv";
        String atualizado = "C:\\TEMP\\professor_temp.csv";
        
        try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
            try (BufferedWriter gravar = new BufferedWriter(new FileWriter(atualizado))){
                String linha;
                // Lê linha por linha e escreve os dados atualizados no arquivo temporário
                while((linha = ler.readLine()) != null) {
                    String[] dados = linha.split(";");
                    if(Integer.parseInt(dados[0]) == professor.getCodigoProfessor()) {
                        gravar.write(professor.toString()); // Se encontrar o professor, escreve os dados atualizados
                        gravar.newLine();
                    } else {
                        gravar.write(linha); // Caso contrário, copia a linha original
                        gravar.newLine();
                    }
                }
            } catch (Exception e) {
                System.err.println(e.getMessage()); // Caso haja erro ao gravar no arquivo temporário
            }
        } catch (Exception e) {
            System.err.println(e.getMessage()); // Caso haja erro ao ler o arquivo
        }
        
        // Substitui o arquivo original pelo arquivo temporário atualizado
        new File(arquivo).delete();
        new File(atualizado).renameTo(new File(arquivo));
    }

    // Método para remover um professor do arquivo CSV
    public void removerProfessor(long codigo) {
        String arquivo = "C:\\TEMP\\professor.csv";
        String atualizado = "C:\\TEMP\\professor_temp.csv";
        
        try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))){
            try (BufferedWriter gravar = new BufferedWriter(new FileWriter(atualizado))){
                String linha;
                // Lê linha por linha e copia todas as linhas, exceto a do professor a ser removido
                while((linha = ler.readLine()) != null) {
                    String[] dados = linha.split(";");
                    if(Integer.parseInt(dados[1]) != codigo) {
                        gravar.write(linha); // Se o código for diferente, copia a linha
                        gravar.newLine();
                    }
                }
            } catch (Exception e) {
                System.err.println(e.getMessage()); // Caso haja erro ao gravar no arquivo temporário
            }
        } catch (Exception e) {
            System.err.println(e.getMessage()); // Caso haja erro ao ler o arquivo
        }
        
        // Substitui o arquivo original pelo arquivo temporário sem o professor removido
        new File(arquivo).delete();
        new File(atualizado).renameTo(new File(arquivo));
    }
}
