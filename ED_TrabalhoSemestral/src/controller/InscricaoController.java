package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.Caret;

import br.com.fatec.Lista;
import dao.InscricaoDAO;
import model.Inscricao;

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
			cadastraInscricao();
		}	
		if (cmd.equals("Remover")) {
			try {
				removerInscricao();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Listar Inscrições")) {
			try {
				listarInscricao();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void listarInscricao() throws Exception {
		InscricaoDAO ins = new InscricaoDAO();
		Lista<Inscricao> lista = ins.consultarInscricoes();
		int tamanho = lista.size();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < tamanho; i ++) {
			Inscricao aux = lista.get(i);
			String CPF = aux.getCpf();
			int codigoDis = aux.getCodigoDisciplina();
			int codigoProceso = aux.getCodigoProcesso();
			buffer.append("CPF: " + CPF + ",\t" +
						  "Código Disciplina: " + codigoDis + ",\t" +
						  "Código Processo: " + codigoProceso + "\n");
		}
		taInscLista.setText(buffer.toString());
		
	}

	private void removerInscricao() throws Exception {
		InscricaoDAO ins = new InscricaoDAO();
		Lista<Inscricao> lista = ins.consultarInscricoes();
		int tamanho = lista.size();
		for (int i = 0; i < tamanho - 1; i ++) {
			Inscricao aux = lista.get(i);
			String CPF = aux.getCpf();
			int codigoDisciplina = aux.getCodigoDisciplina();
			int codigoProcesso = aux.getCodigoProcesso();
 		  taInscLista.setText(CPF + codigoDisciplina + codigoProcesso);
		}
		
	}

	private void atualizarInscricao() {
		Inscricao inscricao = new Inscricao();
		inscricao.setCpf(tfInscCPF.getText());
		inscricao.setCodigoDisciplina(Integer.parseInt(tfInscCodDic.getText()));
		inscricao.setCodigoProcesso(Integer.parseInt(tfInscCodProc.getText()));
		InscricaoDAO i = new InscricaoDAO();
		if ( tfInscCodDic.getText().equals("")|| tfInscCodProc.getText().equals("") || tfInscCPF.getText().equals("")) {
			taInscLista.setText(" FALHA NO CADASTRO \n PREENCHA TODOS OS CAMPOS PARA REALIZAR O CADASTRO");
		}else {
			try {
				i.atualizarInscricao(inscricao);
				taInscLista.setText(" INSCRIÇÃO ATUALIZADA COM SUCESSO \n INFORMAÇÕES ATUALIZADAS : " + "\n CPF : "
						+ inscricao.getCpf() + "\n CODIGO DA DISCIPLINA: " + inscricao.getCodigoDisciplina() +
						"\n CODIGO DO PROCESSO : " +inscricao.getCodigoProcesso() ); 
			} catch (Exception e) {
				taInscLista.setCaret((Caret) e);
			}
		}

	}
	
	private void cadastraInscricao() {
		Inscricao inscricao = new Inscricao();
		inscricao.setCpf(tfInscCPF.getText());
		inscricao.setCodigoDisciplina(Integer.parseInt(tfInscCodDic.getText()));
		inscricao.setCodigoProcesso(Integer.parseInt(tfInscCodProc.getText()));
		InscricaoDAO i = new InscricaoDAO();
		if ( tfInscCodDic.getText().equals("")|| tfInscCodProc.getText().equals("") || tfInscCPF.getText().equals("")) {
			taInscLista.setText(" FALHA NO CADASTRO \n PREENCHA TODOS OS CAMPOS PARA REALIZAR O CADASTRO");
		}else {
			try {
				i.inserirInscricao(inscricao);
				taInscLista.setText(" INSCRIÇÃO CADASTRADO COM SUCESSO \n INFORMAÇÕES CADASTRADAS : " + "\n CPF : "
						+ inscricao.getCpf() + "\n CODIGO DA DISCIPLINA: " + inscricao.getCodigoDisciplina() +
						"\n CODIGO DO PROCESSO : " +inscricao.getCodigoProcesso() ); 
			} catch (Exception e) {
				taInscLista.setCaret((Caret) e);
			}
		}
		

		
	}

	
	

}
