package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

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
			listarProfessor();
		}
	}
	

}
