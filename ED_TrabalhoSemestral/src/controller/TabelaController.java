package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import br.com.fatec.Lista;
import dao.CursoDAO;
import dao.DisciplinaDAO;
import dao.InscricaoDAO;
import dao.ProfessorDAO;
import model.Curso;
import model.Disciplina;
import model.Inscricao;
import model.Professor;

public class TabelaController implements ActionListener {
	
	private JTextArea taTabela;
	
	//Contrutor passando a text area onde será carregada a tabela
	public TabelaController(JTextArea taTabela) {
		this.taTabela = taTabela;
	}
	
	//Cria a tabela assim que é feito o click no botão
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if (cmd.equals("Carregar Tabela")) {
			try {
				criarTabela();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	//Lista todas as inscrições, adiciona cada inscrição na tabela e desenha a tabela na tela  
	private void criarTabela() throws Exception {
		InscricaoDAO ins = new InscricaoDAO();
		Lista<Inscricao> lista = ins.consultarInscricoes();
		int tamanho = lista.size();
		TabelaDisciCursos tabelaDisciCursos = new TabelaDisciCursos();
		for (int i = 0; i < tamanho; i++) {
			Inscricao inscricao = lista.get(i);
			tabelaDisciCursos.adicionarElemento(inscricao);
		}
		
		desenharTabela(tabelaDisciCursos);
	}
	
	//Desenha a tabela na tela
	private void desenharTabela(TabelaDisciCursos tabelaDisciCursos) {
		int tamanhoCursos = tabelaDisciCursos.tabelaHashInscrDisc.length;
		String nomesColunas = "Professor\t\t"
							+ "CPF\t\t"
							+ "Área do Conhecimento\t"
							+ "Pontuação\n";
		StringBuffer buffer = new StringBuffer();
		String nomeCurso = "";
		String nomeDisciplina = "";
		
		//Percorre todos os cursos na tabela e pega a subtabela de disciplinas em cada posição
		for (int i = 0; i < tamanhoCursos; i++) {
			TabelaInscrDisci tabelaInscrDisci = tabelaDisciCursos.tabelaHashInscrDisc[i];
			int tamanhoDisciplinas = tabelaInscrDisci.tabelaHashInscricoes.length;
			
			//Percorre todas as disciplinas na subtabela e pega a lista de inscrições de cada disciplina
			for (int j = 0; j < tamanhoDisciplinas; j++) {
				Lista<Inscricao> listaInscricoes = tabelaInscrDisci.tabelaHashInscricoes[j];
				int tamanhoInscricoes = listaInscricoes.size();
				
				//Percorre a lista de inscrições e adiciona cada inscrição no buffer que será inserido na text area da tela
				for (int k = 0; k < tamanhoInscricoes; k++) {
					try {
						Inscricao inscricao = listaInscricoes.get(k);
						int codDisciplina = inscricao.getCodigoDisciplina();
						DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
						Disciplina disciplina = disciplinaDAO.buscarDisciplina(codDisciplina);
						int codCurso = disciplina.getCodigoCurso();
						CursoDAO cursoDAO = new CursoDAO();
						Curso curso = cursoDAO.buscarCurso(codCurso);
						String cpfProfessor = inscricao.getCpf();
						ProfessorDAO professorDAO = new ProfessorDAO();
						Professor professor = professorDAO.buscarProfessor(cpfProfessor);
						
						//Adiciona o nome do curso no buffer se for diferente do curso anterior
						if (!nomeCurso.equals(curso.getNomeCurso())) {
							nomeCurso = curso.getNomeCurso();
							buffer.append("\nCurso: " + nomeCurso + "\n");
						}
						
						//Adiciona o nome da disciplina no buffer se for diferente da disciplina anterior
						if (!nomeDisciplina.equals(disciplina.getNomeDisciplina())) {
							nomeDisciplina = disciplina.getNomeDisciplina();
							buffer.append("\nDisciplina: " + nomeDisciplina + "\n\n");
							buffer.append(nomesColunas);
						}
						
						//Adiciona os dados do inscrito contando os caracteres para ver quantos tabs são necessários como separador
						String nomeProfessor = professor.getNomeProfessor();
						String areaConhecimento = professor.getAreaConhecimento();
						String pontuacao = Integer.toString(professor.getQtdPontos());
						String separator = (nomeProfessor.length() > 13) ? "\t" : "\t\t";
						buffer.append(nomeProfessor + separator);
						
						separator = (cpfProfessor.length() > 13) ? "\t" : "\t\t";
						buffer.append(cpfProfessor + separator);
						
						separator = (areaConhecimento.length() > 13) ? "\t" : "\t\t";
						buffer.append(areaConhecimento + separator);
						buffer.append(pontuacao + "\n");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		taTabela.setText(buffer.toString());
	}
}