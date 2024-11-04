package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InscricaoController implements ActionListener {
	
	private JTextField tfInscCPF;
	private JTextField tfInscCodDic;
	private JTextField tfInscCodProc;
	private JTextArea taInscLista;
	
	public InscricaoController(JTextField tfInscCPF, JTextField tfInscCodDic, JTextField tfInscCodProc, JTextArea taInscLista) {
		this.tfInscCPF = tfInscCPF;
		this.tfInscCodDic = tfInscCodDic;
		this.tfInscCodProc = tfInscCodProc;
		this.taInscLista = taInscLista;
	}
	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if (cmd.equals("Atualizar")) {
			atualizarInscricao();
		}	
		if (cmd.equals("Cadastrar")) {
			inserirInscricao();
		}	
		if (cmd.equals("Remover")) {
			removerInscricao();
		}
		if (cmd.equals("Listar Inscrições")) {
			listarInscricao();
		}
	}
	

}
