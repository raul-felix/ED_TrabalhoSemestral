package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.Caret;

import br.com.fatec.Lista;
import dao.DisciplinaDAO;
import dao.InscricaoDAO;
import dao.ProfessorDAO;
import model.Disciplina;
import model.Inscricao;
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
		Lista<Professor> lista = p.consultarProfessor();
		int tamanho = lista.size();
		for (int i = 0; i < tamanho - 1; i ++) {
			Professor aux = lista.get(i);
			String CPF = aux.getCpf();
			String Nome = aux.getNomeProfessor();
			String AreaConhecimento = aux.getAreaConhecimento();
			int QtdPontos = aux.getQtdPontos();

 		  taProfLista.setText(CPF + Nome+AreaConhecimento+QtdPontos);
		}
		
	}

	private void removerProfessor() {

		Professor professor = new Professor();
		professor.setCpf(tfProfCPF.getText());
		professor.setNomeProfessor(tfProfCPF.getText());
		professor.setAreaConhecimento(tfProfCPF.getText());
		professor.setQtdPontos(Integer.parseInt(tfProfCPF.getText()) );
		professor.setCodigoProfessor(Integer.parseInt(tfProfCPF.getText()));

		
		ProfessorDAO p = new ProfessorDAO();
		if (tfProfAreaCon.getText().equals("") || tfProfCPF.getText().equals("") ||tfProfNome.getText().equals("") ||
				tfProfPont.getText().equals("") ) {
			taProfLista.setText(" FALHA NO CADASTRO \n PREENCHA TODOS OS CAMPOS PARA REALIZAR A ATUALIZAÇÃO");
		} else {
			int codigo = Integer.parseInt(tfProfCPF.getText()) ; // metodo não funcionando, resolver questão do contador "CodigoProfessor"
			try {
				p.removerProfessor(0);
				taProfLista.setText(" CURSO ATUALIZADO COM SUCESSO \n INFORMAÇÕES CADASTRADAS : " + "\n NOME : "
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
		professor.setCpf(tfProfCPF.getText());
		professor.setNomeProfessor(tfProfCPF.getText());
		professor.setAreaConhecimento(tfProfCPF.getText());
		professor.setQtdPontos(Integer.parseInt(tfProfCPF.getText()) );
		professor.setCodigoProfessor(Integer.parseInt(tfProfCPF.getText()));

		
		ProfessorDAO p = new ProfessorDAO();
		if (tfProfAreaCon.getText().equals("") || tfProfCPF.getText().equals("") ||tfProfNome.getText().equals("") ||
				tfProfPont.getText().equals("") ) {
			taProfLista.setText(" FALHA N INSCRIÇÃO \n PREENCHA TODOS OS CAMPOS PARA REALIZAR A ATUALIZAÇÃO");
		} else {
			try {
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
		professor.setCpf(tfProfCPF.getText());
		professor.setNomeProfessor(tfProfNome.getText());
		professor.setAreaConhecimento(tfProfAreaCon.getText());
		professor.setQtdPontos(Integer.parseInt(tfProfPont.getText()) );
//		professor.setCodigoProfessor(Integer.parseInt(tfProfCPF.getText()));

		
		ProfessorDAO p = new ProfessorDAO();
		if (tfProfAreaCon.getText().equals("") || tfProfCPF.getText().equals("") ||tfProfNome.getText().equals("") ||
				tfProfPont.getText().equals("") ) {
			taProfLista.setText(" FALHA NO CADASTRO \n PREENCHA TODOS OS CAMPOS PARA REALIZAR A ATUALIZAÇÃO");
		} else {
			try {
				p.inserirProfessor(professor);
				taProfLista.setText(" CURSO ATUALIZADO COM SUCESSO \n INFORMAÇÕES CADASTRADAS : " + "\n NOME : "
						+ professor.getNomeProfessor() + "\n CPF: " + professor.getCpf() +
						"\n AREA DO CONHECIMENTO : " +professor.getAreaConhecimento() + "\n QUANTIDADE DE PONTOS : " +professor.getQtdPontos() 
						); 
			} catch (Exception e) {
				taProfLista.setCaret((Caret) e);
			}
		}
	}
		
	}

	
	


