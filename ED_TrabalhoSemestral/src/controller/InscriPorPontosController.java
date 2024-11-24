package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.directory.InvalidAttributeIdentifierException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.fatec.Lista;
import br.com.jonas.Sorts;
import dao.CursoDAO;
import dao.DisciplinaDAO;
import dao.InscricaoDAO;
import dao.ProfessorDAO;
import model.Curso;
import model.Disciplina;
import model.Inscricao;
import model.Professor;
import util.QuickSortProfessor;

public class InscriPorPontosController implements ActionListener {
	
	private JTextArea taInscriPorPontos;
	private JTextField tfCodDisciPorPontos;
	
	public InscriPorPontosController(JTextField tfCodDisciPorPontos, JTextArea taInscriPorPontos) {
		this.tfCodDisciPorPontos = tfCodDisciPorPontos;
		this.taInscriPorPontos = taInscriPorPontos;
	}
	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if (cmd.equals("Listar por Pontuação")) {
			try {
				int codDisciplina = Integer.parseInt(tfCodDisciPorPontos.getText());
				listarPorPontuacao(codDisciplina);
			} catch (NumberFormatException e0) {
				JOptionPane.showMessageDialog(null, "Digite um código de disciplina válido.");
				e0.printStackTrace();
			} catch (InvalidAttributeIdentifierException e1) {
				JOptionPane.showMessageDialog(null, "Código de disciplina inválido. Tente novamente.");
				e1.printStackTrace();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//Lista os inscritos de uma disciplina pela pontuação, da maior para a menor
	private void listarPorPontuacao(int codDisciplina) throws Exception {
		InscricaoDAO inscricaoDAO = new InscricaoDAO();
		ProfessorDAO professorDAO = new ProfessorDAO();
		
		//Lista as inscrições da disciplina do código usado como parâmetro
		//Se a lista estiver vazia, é porque não existem inscritos daquela disciplina
		Lista<Inscricao> listaInscricoes = inscricaoDAO.consultarInscricoesPorDisciplina(codDisciplina);
		int qtdInscricoes = listaInscricoes.size();
		if (qtdInscricoes == 0) {
			throw new InvalidAttributeIdentifierException();
		}
		
		//Cria uma lista dos professores a partir da lista de inscrições
		Lista<Professor> listaProfessores = new Lista<Professor>();
		for (int i = 0; i < qtdInscricoes; i++) {
			String cpf = listaInscricoes.get(i).getCpf();
			Professor professor = professorDAO.buscarProfessor(cpf);
			if (professor != null) {
				listaProfessores.addLast(professor);
			}
		}
		
		//Cria um array de professores e faz o quick sort desse vetor de acordo com a pontuação
		int qtdProfessores = listaProfessores.size();
		Professor[] arrProfessores = new Professor[qtdProfessores];
		for (int i = 0; i < qtdProfessores; i++) {
			arrProfessores[i] = listaProfessores.get(i);
		}
		Professor[] arrProfessoresOrd = QuickSortProfessor.quickSort(arrProfessores, 0, qtdInscricoes - 1);
		
		//Cria um string buffer para adicionar todos os professores e mostrar no text area da tela 
		StringBuffer buffer = new StringBuffer("Nome\t\t"
											 + "CPF\t\t"
											 + "Área do Conhecimento\t"
											 + "Pontuação\n");
		for (int i = 0; i < qtdProfessores; i++) {
			Professor professor = arrProfessoresOrd[i];
			String nomeProfessor = professor.getNomeProfessor();
			String cpfProfessor = professor.getCpf();
			String areaConhecimento = professor.getAreaConhecimento();
			String pontuacao = Integer.toString(professor.getQtdPontos());
			String separator = (nomeProfessor.length() > 13) ? "\t" : "\t\t";
			buffer.append(nomeProfessor + separator);
			
			separator = (cpfProfessor.length() > 13) ? "\t" : "\t\t";
			buffer.append(cpfProfessor + separator);
			
			separator = (areaConhecimento.length() > 13) ? "\t" : "\t\t";
			buffer.append(areaConhecimento + separator);
			buffer.append(pontuacao + "\n");
		}
		taInscriPorPontos.setText(buffer.toString());
	}
}