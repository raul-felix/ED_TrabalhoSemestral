package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import dao.CursoDAO;
import model.Curso;

public class CursoController implements ActionListener{
	
	private JTextField tfCursosCod;
	private JTextField tfCursosNome;
	private JTextField tfCursosAreaConhec;
	
	public CursoController(JTextField tfCursosCod, JTextField tfCursosNome, JTextField tfCursosAreaConhec) {
		this.tfCursosCod = tfCursosCod;
		this.tfCursosNome = tfCursosNome;
		this.tfCursosAreaConhec = tfCursosAreaConhec;
	}

	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if (cmd.equals("Atualizar")) {
			atualizarCurso();
		}	
		if (cmd.equals("Cadastrar")) {
			inserirCurso();
		}
		if (cmd.equals("Consultar")) {
			buscarCurso();
		}		
		if (cmd.equals("Remover")) {
			removerCurso();
		}
		if (cmd.equals("Listar")) {
			listarCursos();
		}
	}

}
