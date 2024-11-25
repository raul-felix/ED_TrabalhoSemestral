// A classe DisciplinaDAO gerencia as operações de CRUD (criar, ler, atualizar, excluir) de objetos do tipo Disciplina.
// Os dados das disciplinas são armazenados em um arquivo CSV localizado em "C:\\TEMP\\disciplinas.csv".

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

    // Busca uma disciplina pelo código no arquivo CSV.
    public Disciplina buscarDisciplina(int codigo) {
        String arquivo = "C:\\TEMP\\disciplinas.csv";

        try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            // Lê o arquivo linha por linha.
            while ((linha = ler.readLine()) != null) {
                String[] dados = linha.split(";"); // Divide os dados separados por ";".

                int codigoDisciplina = Integer.parseInt(dados[0]);
                // Verifica se o código da disciplina corresponde ao informado.
                if (codigo == codigoDisciplina) {
                    Disciplina disciplina = new Disciplina();
                    disciplina.setCodigoDisciplina(codigoDisciplina);
                    disciplina.setNomeDisciplina(dados[1]);
                    disciplina.setDiaDaSemana(dados[2]);
                    disciplina.setHorarioinicial(LocalTime.parse(dados[3], DateTimeFormatter.ofPattern("HH:mm")));
                    disciplina.setQtdHorasDiarias(Integer.parseInt(dados[4]));
                    disciplina.setCodigoProcesso(Integer.parseInt(dados[5]));
                    disciplina.setCodigoCurso(Integer.parseInt(dados[6]));
                    return disciplina; // Retorna a disciplina encontrada.
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage()); // Exibe erros de leitura do arquivo.
        }
        return null; // Retorna null caso a disciplina não seja encontrada.
    }

    // Insere uma nova disciplina no arquivo CSV.
    public void inserirDisciplina(Disciplina disciplina) {
        String caminho = "C:\\TEMP";
        File arquivo = new File(caminho, "disciplinas.csv");
        File diretorio = new File(caminho);

        // Cria o diretório se ele não existir.
        if (!diretorio.exists()) {
            diretorio.mkdir();
        }

        // Cria o arquivo se ele não existir.
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo: " + e.getMessage());
                return;
            }
        }

        // Verifica se a disciplina já está cadastrada.
        if (disciplinaJaExiste(disciplina.getCodigoDisciplina())) {
            System.err.println("Disciplina com código " + disciplina.getCodigoDisciplina() + " já cadastrada!");
            return;
        }

        try (BufferedWriter gravar = new BufferedWriter(new FileWriter(arquivo, true))) {
            gravar.write(disciplina.toString()); // Escreve os dados da disciplina no arquivo.
            gravar.newLine(); // Adiciona uma nova linha.
        } catch (Exception e) {
            System.err.println(e.getMessage()); // Exibe erros de escrita no arquivo.
        }
    }

    // Verifica se uma disciplina já existe no arquivo CSV.
    public boolean disciplinaJaExiste(int codigoDisciplina) {
        String caminho = "C:\\TEMP";
        File arquivo = new File(caminho, "disciplinas.csv");

        // Retorna falso se o arquivo não existir.
        if (!arquivo.exists()) {
            return false;
        }

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            // Lê o arquivo linha por linha.
            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(";");
             // Pega a primeira coluna do arquivo e compara se já existe um cadastro com o código da disciplina.
                if (dados.length > 0 && Integer.parseInt(dados[0]) == codigoDisciplina) {
                    return true; // Retorna true se o código já estiver cadastrado.
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println(e.getMessage()); // Exibe erros de leitura do arquivo.
        }

        return false; // Retorna false caso a disciplina não seja encontrada.
    }

    // Consulta todas as disciplinas no arquivo CSV e retorna uma lista delas.
    public Lista<Disciplina> consultarDisciplinas() {
        String arquivo = "C:\\TEMP\\disciplinas.csv";
        Lista<Disciplina> disciplinas = new Lista<Disciplina>();

        try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            // Lê o arquivo linha por linha.
            while ((linha = ler.readLine()) != null) {
                String[] dados = linha.split(";");
                Disciplina disciplina = new Disciplina();
                disciplina.setCodigoDisciplina(Integer.parseInt(dados[0]));
                disciplina.setNomeDisciplina(dados[1]);
                disciplina.setDiaDaSemana(dados[2]);
                disciplina.setHorarioinicial(LocalTime.parse(dados[3], DateTimeFormatter.ofPattern("HH:mm")));
                disciplina.setQtdHorasDiarias(Integer.parseInt(dados[4]));
                disciplina.setCodigoProcesso(Integer.parseInt(dados[5]));
                disciplina.setCodigoCurso(Integer.parseInt(dados[6]));
                disciplinas.addLast(disciplina); // Adiciona a disciplina na lista.
            }
        } catch (Exception e) {
            System.err.println(e.getMessage()); // Exibe erros de leitura do arquivo.
        }
        return disciplinas; // Retorna a lista de disciplinas.
    }

    // Atualiza os dados de uma disciplina no arquivo CSV.
    public void atualizarDisciplina(Disciplina disciplina) {
        String arquivo = "C:\\TEMP\\disciplinas.csv";
        String atualizado = "C:\\TEMP\\disciplinas_temp.csv";

        try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))) {
            try (BufferedWriter gravar = new BufferedWriter(new FileWriter(atualizado))) {
                String linha;
                // Lê o arquivo original e escreve as atualizações em um arquivo temporário.
                while ((linha = ler.readLine()) != null) {
                    String[] dados = linha.split(";");
                    if (Integer.parseInt(dados[0]) == disciplina.getCodigoDisciplina()) {
                        gravar.write(disciplina.toString()); // Atualiza os dados da disciplina.
                    } else {
                        gravar.write(linha); // Mantém os dados inalterados.
                    }
                    gravar.newLine();
                }
            } catch (Exception e) {
                System.err.println(e.getMessage()); // Exibe erros de escrita.
            }
        } catch (Exception e) {
            System.err.println(e.getMessage()); // Exibe erros de leitura.
        }

        // Substitui o arquivo original pelo arquivo atualizado.
        new File(arquivo).delete();
        new File(atualizado).renameTo(new File(arquivo));
    }

    // Remove uma disciplina do arquivo CSV pelo código.
    public void removerDisciplina(int codigo) {
        String arquivo = "C:\\TEMP\\disciplinas.csv";
        String atualizado = "C:\\TEMP\\disciplinas_temp.csv";

        try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))) {
            try (BufferedWriter gravar = new BufferedWriter(new FileWriter(atualizado))) {
                String linha;
                // Lê o arquivo original e escreve no arquivo temporário apenas as disciplinas que não correspondem ao código informado.
                while ((linha = ler.readLine()) != null) {
                    String[] dados = linha.split(";");
                    if (Integer.parseInt(dados[0]) != codigo) {
                        gravar.write(linha);
                        gravar.newLine();
                    }
                }
            } catch (Exception e) {
                System.err.println(e.getMessage()); // Exibe erros de escrita.
            }
        } catch (Exception e) {
            System.err.println(e.getMessage()); // Exibe erros de leitura.
        }

        // Substitui o arquivo original pelo arquivo atualizado.
        new File(arquivo).delete();
        new File(atualizado).renameTo(new File(arquivo));
    }
}
