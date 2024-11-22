package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.Caret;

import br.com.fatec.Lista;
import dao.ProfessorDAO;
import model.Professor;

public class ProfessorController implements ActionListener {
	
	private JTextField tfProfCPF;
	private JTextField tfProfNome;
	private JTextField tfProfAreaCon;
	private JTextField tfProfPont;
	private JTextArea taProfLista;
	
	public ProfessorController(JTextField tfProfCPF, JTextField tfProfNome, JTextField tfProfAreaCon,
			JTextField tfProfPont, JTextArea taProfLista) {
		this.tfProfCPF = tfProfCPF;
		this.tfProfNome = tfProfNome;
		this.tfProfAreaCon = tfProfAreaCon;
		this.tfProfPont = tfProfPont;
		this.taProfLista = taProfLista;
	}
	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if (cmd.equals("Atualizar")) {
			atualizarProfessor();
		}	
		if (cmd.equals("Cadastrar")) {
			inserirProfessor();
		}	
		if (cmd.equals("Remover")) {
			removerProfessor();
		}
		if (cmd.equals("Listar Professores")) {
			try {
				listarProfessor();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private void listarProfessor() throws Exception {
		ProfessorDAO p = new ProfessorDAO();
		Lista<Professor> lista = p.consultarProfessores();
		int tamanho = lista.size();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < tamanho ; i ++) {
			Professor aux = lista.get(i);
			String CPF = aux.getCpf();
			String Nome = aux.getNomeProfessor();
			String AreaConhecimento = aux.getAreaConhecimento();
			int QtdPontos = aux.getQtdPontos();
			buffer.append("CPF: " + CPF + ",\t" +
					  "Nome : " + Nome + ",\t" +
					  "Area do Conhecimento : " + AreaConhecimento + 
					  "Quantidade de Pontos : " + QtdPontos + "\n");

		}
		taProfLista.setText(buffer.toString());
	}

	private void removerProfessor() {

		Professor professor = new Professor();
		ProfessorDAO p = new ProfessorDAO();
		if (tfProfCPF.getText().equals("") ) {
			JOptionPane.showMessageDialog(null," FALHA NO CADASTRO \n INSIRA O CPF PARA REMOVER UM PROFESSOR");
		} else {
			int codigo = Integer.parseInt(tfProfCPF.getText()) ; // metodo não funcionando, resolver questão do contador "CodigoProfessor"
			try {
				p.removerProfessor(codigo);
				taProfLista.setText(" PROFESSOR REMOVIDO COM SUCESSO \n INFORMAÇÕES CADASTRADAS : " + "\n NOME : "
						+ professor.getNomeProfessor() + "\n CPF: " + professor.getCpf() +
						"\n AREA DO CONHECIMENTO : " +professor.getAreaConhecimento() + "\n QUANTIDADE DE PONTOS : " +professor.getQtdPontos() 
						); 
			} catch (Exception e) {
				taProfLista.setCaret((Caret) e);
			}
		}
	
		
	}

	private void atualizarProfessor() {

		Professor professor = new Professor();
		ProfessorDAO p = new ProfessorDAO();
		if (tfProfAreaCon.getText().equals("") || tfProfCPF.getText().equals("") ||tfProfNome.getText().equals("") ||
				tfProfPont.getText().equals("") ) {
			JOptionPane.showMessageDialog(null," FALHA NA ATUALIZAÇÃO \n PREENCHA TODOS OS CAMPOS PARA REALIZAR A ATUALIZAÇÃO");
		} else {
			try {
				professor.setCpf(tfProfCPF.getText());
				professor.setNomeProfessor(tfProfCPF.getText());
				professor.setAreaConhecimento(tfProfCPF.getText());
				professor.setQtdPontos(Integer.parseInt(tfProfCPF.getText()) );
				professor.setCodigoProfessor(Integer.parseInt(tfProfCPF.getText()));
				p.atualizarProfessor(professor);
				taProfLista.setText(" CURSO ATUALIZADO COM SUCESSO \n INFORMAÇÕES CADASTRADAS : " + "\n NOME : "
						+ professor.getNomeProfessor() + "\n CPF: " + professor.getCpf() +
						"\n AREA DO CONHECIMENTO : " +professor.getAreaConhecimento() + "\n QUANTIDADE DE PONTOS : " +professor.getQtdPontos() 
						); 
			} catch (Exception e) {
				taProfLista.setCaret((Caret) e);
			}
		}
	

	}
	
	private void inserirProfessor() {
		Professor professor = new Professor();
		ProfessorDAO p = new ProfessorDAO();
		if (tfProfAreaCon.getText().equals("") || tfProfCPF.getText().equals("") ||tfProfNome.getText().equals("") ||
				tfProfPont.getText().equals("") ) {
			JOptionPane.showMessageDialog(null," FALHA NO CADASTRO \n PREENCHA TODOS OS CAMPOS PARA REALIZAR A ATUALIZAÇÃO");
		} else {
			try {
				professor.setCpf(tfProfCPF.getText());
				professor.setNomeProfessor(tfProfNome.getText());
				professor.setAreaConhecimento(tfProfAreaCon.getText());
				professor.setQtdPontos(Integer.parseInt(tfProfPont.getText()) );
//				professor.setCodigoProfessor(Integer.parseInt(tfProfCPF.getText()));
				if (p.professorJaExiste(tfProfCPF.getText())) {
				    JOptionPane.showMessageDialog(null, "Inscrição já realizada com o CPF: " + professor.getCpf());
				    return;
				}
				p.inserirProfessor(professor);
				taProfLista.setText("PROFESSOR ATUALIZADO COM SUCESSO \n INFORMAÇÕES CADASTRADAS : " + "\n NOME : "
						+ professor.getNomeProfessor() + "\n CPF: " + professor.getCpf() +
						"\n AREA DO CONHECIMENTO : " +professor.getAreaConhecimento() + "\n QUANTIDADE DE PONTOS : " +professor.getQtdPontos() 
						); 
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao cadastrar professor: " + e.getMessage());
			}
		}
	}
		
	}

	
	


