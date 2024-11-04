package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DisciplinaController implements ActionListener {
	
	private JTextField tfDisCodigo;
	private JTextField tfDisData;
	private JTextField tfDisHorario;
	private JTextField tfDisCargaHorariaDiaria;
	private JTextField tfDisCodProcesso;
	private JTextField tfDisCodCurso;
	private JTextField tfDisDisciplina;
	private JTextArea taDisLista;
	
	public DisciplinaController(JTextField tfDisCodigo, JTextField tfDisData, JTextField tfDisHorario,
			JTextField tfDisCargaHorariaDiaria, JTextField tfDisCodProcesso, JTextField tfDisCodCurso,
			JTextField tfDisDisciplina, JTextArea taDisLista) {
		this.tfDisCodigo = tfDisCodigo;
		this.tfDisData = tfDisData;
		this.tfDisHorario = tfDisHorario;
		this.tfDisCargaHorariaDiaria = tfDisCargaHorariaDiaria;
		this.tfDisCodProcesso = tfDisCodProcesso;
		this.tfDisCodCurso = tfDisCodCurso;
		this.tfDisDisciplina = tfDisDisciplina;
		this.taDisLista = taDisLista;
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if (cmd.equals("Atualizar")) {
			atualizarDisciplina();
		}	
		if (cmd.equals("Cadastrar")) {
			inserirDisciplina();
		}	
		if (cmd.equals("Remover")) {
			removerDisciplina();
		}
		if (cmd.equals("Listar Disciplinas")) {
			listarDisciplina();
		}
	}

}
