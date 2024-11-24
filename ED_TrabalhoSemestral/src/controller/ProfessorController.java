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
//Nesta classe temos os metodos que integram a tela "Professor" com os metodos internos do sistemas "ProfessorDAO"
//Tambem é aqui que ocorre o tratamento de erro, e retorno responsivo do sistema Destas classes
	
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
			cadastrarProfessor();
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

//	Função para chamada e tratamento de erro da
//	listagem de Professores na textArea da tela Professor	
	
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

//	Função para a chamada e tratamento de erro do Metodo "remoção" de um Professor 
//	sendo necessarioa penas a passagem do parametro "CPF"
	private void removerProfessor() {

		Professor professor = new Professor();
		professor.setCpf(tfProfCPF.getText());
		ProfessorDAO p = new ProfessorDAO();
		if (tfProfCPF.getText().equals("") ) {
			JOptionPane.showMessageDialog(null," FALHA NO CADASTRO \n INSIRA O CPF PARA REMOVER UM PROFESSOR");
		} else {
			long codigo = Long.parseLong(tfProfCPF.getText()) ; 
			try {
				p.removerProfessor(codigo);
				taProfLista.setText(" PROFESSOR SOBRE O CPF "+professor.getCpf()+ "  REMOVIDO COM SUCESSO "); 
			} catch (Exception e) {
				taProfLista.setCaret((Caret) e);
			}
		}
	
		
	}
//	Função para a chamada e tratamento de erro do metodo "atualizar" 
//	sendo necessario o preeenchimento de todos os campos

	private void atualizarProfessor() {

		Professor professor = new Professor();
		ProfessorDAO p = new ProfessorDAO();
		if (tfProfAreaCon.getText().equals("") || tfProfCPF.getText().equals("") ||tfProfNome.getText().equals("") ||
				tfProfPont.getText().equals("") ) {
			JOptionPane.showMessageDialog(null," FALHA NA ATUALIZAÇÃO \n TODOS OS CAMPOS DEVEM SER PREENCHIDOS");
		} else {
			try {
				professor.setCpf(tfProfCPF.getText());
				professor.setNomeProfessor(tfProfNome.getText());
				professor.setAreaConhecimento(tfProfAreaCon.getText());
				professor.setQtdPontos(Integer.parseInt(tfProfPont.getText()) );
				professor.setCodigoProfessor(professor.hashCode());
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
	
//	Função para a chamada e tratamento de erro do metodo "cadastrar" 
//	sendo necessario o preeenchimento de todos os campos
	
	private void cadastrarProfessor() {
		Professor professor = new Professor();
		ProfessorDAO p = new ProfessorDAO();
		if (tfProfAreaCon.getText().equals("") || tfProfCPF.getText().equals("") ||tfProfNome.getText().equals("") ||
				tfProfPont.getText().equals("") ) {
			JOptionPane.showMessageDialog(null," FALHA NO CADASTRO \n TODOS OS CAMPOS DEVEM SER PREENCHIDOS");
		} else {
			try {
				professor.setCpf(tfProfCPF.getText());
				professor.setNomeProfessor(tfProfNome.getText());
				professor.setAreaConhecimento(tfProfAreaCon.getText());
				professor.setQtdPontos(Integer.parseInt(tfProfPont.getText()) );
				professor.setCodigoProfessor(professor.hashCode());
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

	
	


