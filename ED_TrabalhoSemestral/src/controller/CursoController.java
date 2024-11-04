package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.CursoDAO;

public class CursoController implements ActionListener{
	
	private JTextField tfCursosCod;
	private JTextField tfCursosNome;
	private JTextField tfCursosAreaConhec;
	private JTextArea taCursosLista;
	
	public CursoController(JTextField tfCursosCod, JTextField tfCursosNome, JTextField tfCursosAreaConhec, JTextArea taCursosLista) {
		this.tfCursosCod = tfCursosCod;
		this.tfCursosNome = tfCursosNome;
		this.tfCursosAreaConhec = tfCursosAreaConhec;
		this.taCursosLista = taCursosLista;
	}

	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if (cmd.equals("Atualizar")) {
			atualizarCurso();
		}	
		if (cmd.equals("Cadastrar")) {
			inserirCurso();
		}	
		if (cmd.equals("Remover")) {
			removerCurso();
		}
		if (cmd.equals("Listar Cursos")) {
			listarCursos();
		}
	}	
}
