/*
 * A classe CursoDAO é responsável por gerenciar o acesso aos dados de cursos armazenados em um arquivo CSV.
 * Ela fornece métodos para buscar, inserir, verificar duplicidade, consultar, atualizar e remover cursos.
 * Cada curso é identificado por um código único.
 */

package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import br.com.fatec.Lista;
import model.Curso;

public class CursoDAO {
    // Busca um curso pelo código no arquivo CSV e retorna o curso encontrado, ou null se não for encontrado.
    public Curso buscarCurso(int codigo) {
        String arquivo = "C:\\TEMP\\cursos.csv";
        
        try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = ler.readLine()) != null) {
                String[] dados = linha.split(";"); // Divide a linha em partes usando ";" como separador.
                int codigoCurso = Integer.parseInt(dados[0]); // Obtém o código do curso.
                
                if (codigo == codigoCurso) { // Compara o código fornecido com o código do curso.
                    Curso curso = new Curso();
                    curso.setCodigoCurso(codigoCurso); 
                    curso.setNomeCurso(dados[1]);
                    curso.setAreaConhecimento(dados[2]);
                    return curso; // Retorna o curso encontrado.
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        return null; // Retorna null se o curso não for encontrado.
    }

    // Insere um novo curso no arquivo CSV, verificando se já existe para evitar duplicatas.
    public void inserirCurso(Curso curso) {
        String caminho = "C:\\TEMP";
        File arquivo = new File(caminho, "cursos.csv");
        File diretorio = new File(caminho);
        
        // Verifica se o curso já existe antes de inserir.
        if (cursoJaExiste(curso.getCodigoCurso())) {
            System.err.println("Curso já cadastrado com o código: " + curso.getCodigoCurso());
            return; // Sai do método sem gravar.
        }
        
        // Cria o diretório se ele não existir.
        if (!diretorio.exists()) {
            diretorio.mkdir();
        }
        
        // Cria o arquivo se ele não existir.
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        
        // Adiciona o curso ao arquivo CSV.
        try (BufferedWriter gravar = new BufferedWriter(new FileWriter(arquivo, true))) {
            gravar.write(curso.toString());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    // Verifica se um curso já existe no arquivo CSV, baseado no código do curso.
    public boolean cursoJaExiste(int codigoCurso) {
        String caminho = "C:\\TEMP\\cursos.csv";
        File arquivo = new File(caminho);
        
        // Se o arquivo não existe, o curso não pode existir.
        if (!arquivo.exists()) {
            return false;
        }
        
        // Lê o arquivo para verificar se o curso está presente.
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length > 0 && Integer.parseInt(dados[0]) == codigoCurso) {
                    return true; // Retorna true se o curso for encontrado.
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println(e.getMessage());
        }
        
        return false; // Retorna false se o curso não for encontrado.
    }

    // Consulta todos os cursos no arquivo CSV e os retorna como uma lista.
    public Lista<Curso> consultarCursos() {
        String arquivo = "C:\\TEMP\\cursos.csv";
        Lista<Curso> cursos = new Lista<Curso>();
        
        try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = ler.readLine()) != null) {
                String[] dados = linha.split(";"); // Divide a linha em partes.
                Curso curso = new Curso();
                curso.setCodigoCurso(Integer.parseInt(dados[0])); 
                curso.setNomeCurso(dados[1]);
                curso.setAreaConhecimento(dados[2]);
                cursos.addLast(curso); // Adiciona o curso à lista.
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        return cursos; // Retorna a lista de cursos.
    }

    // Atualiza os dados de um curso existente no arquivo CSV.
    public void atualizarCurso(Curso curso) {
        String arquivo = "C:\\TEMP\\cursos.csv";
        String atualizado = "C:\\TEMP\\cursos_temp.csv";
        
        try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))) {
            try (BufferedWriter gravar = new BufferedWriter(new FileWriter(atualizado))) {
                String linha;
                while ((linha = ler.readLine()) != null) {
                    String[] dados = linha.split(";");
                    if (Integer.parseInt(dados[0]) == curso.getCodigoCurso()) {
                        gravar.write(curso.toString()); // Atualiza o curso correspondente.
                    } else {
                        gravar.write(linha); // Mantém as linhas que não precisam ser alteradas.
                        gravar.newLine();
                    }
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        // Substitui o arquivo original pelo arquivo atualizado.
        new File(arquivo).delete();
        new File(atualizado).renameTo(new File(arquivo));
    }

    // Remove um curso do arquivo CSV baseado no código fornecido.
    public void removerCurso(int codigo) {
        String arquivo = "C:\\TEMP\\cursos.csv";
        String atualizado = "C:\\TEMP\\cursos_temp.csv";
        
        try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))) {
            try (BufferedWriter gravar = new BufferedWriter(new FileWriter(atualizado))) {
                String linha;
                while ((linha = ler.readLine()) != null) {
                    String[] dados = linha.split(";");
                    if (Integer.parseInt(dados[0]) != codigo) { // Mantém os cursos que não têm o código informado.
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
        
        // Substitui o arquivo original pelo arquivo atualizado.
        new File(arquivo).delete();
        new File(atualizado).renameTo(new File(arquivo));
    }
}
