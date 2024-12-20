package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.Caret;

import br.com.fatec.Lista;
import dao.CursoDAO;
import model.Curso;
import javax.swing.JOptionPane;

public class CursoController implements ActionListener {
//Nesta classe temos os metodos que integram a tela "Curso" com os metodos internos do sistemas "CursoDAO"
//Tambem é aqui que ocorre o tratamento de erro, e retorno responsivo do sistema destas classes
	
	private JTextField tfCursosCod;
	private JTextField tfCursosNome;
	private JTextField tfCursosAreaConhec;
	private JTextArea taCursosLista;

	public CursoController(JTextField tfCursosCod, JTextField tfCursosNome, JTextField tfCursosAreaConhec,
			JTextArea taCursosLista) {
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
			cadastraCurso();
		}
		if (cmd.equals("Remover")) {
			removerCurso();
		}
		if (cmd.equals("Listar Cursos")) {
			try {
				listarCursos();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
//	Função para chamada e tratamento de erro da
//	listagem de cursos na textArea da tela cursos

	private void listarCursos() throws Exception {
		CursoDAO c = new CursoDAO();
		Lista<Curso> lista = c.consultarCursos();
		int tamanho = lista.size();
		StringBuffer buffer = new StringBuffer("Nome\t\t\t"
											 + "Código\t"
											 + "Área do Conhecimento\n");
		for (int i = 0; i < tamanho; i ++) {
			Curso aux = lista.get(i);
			String nomeCurso = aux.getNomeCurso();
			int codigoCurso = aux.getCodigoCurso();
			String areaConhecimentoCurso = aux.getAreaConhecimento();
			
			String separator = "";
			if (nomeCurso.length() > 26) {
				separator = "\t";
			} else if (nomeCurso.length() > 13) {
				separator = "\t\t";
			} else {
				separator = "\t\t\t";
			}
			buffer.append(nomeCurso + separator);
			
			buffer.append(codigoCurso + "\t");
			buffer.append(areaConhecimentoCurso + "\n");
		}
		taCursosLista.setText(buffer.toString());
	}
	
//	Função para chamada e tratamento de erro do Metodo "remoção" de um curso 
//	sendo necessarioa penas a passagem do parametro "codigo"

	private void removerCurso() {
		CursoDAO c = new CursoDAO();
		if (tfCursosCod.getText().equals("")) { 
 			JOptionPane.showMessageDialog(null, "FALHA NA REMOÇÃO \n INSIRA O CODIGO DE ALGUM CURSO PARA REMOVER");
			
		} else {
			int codigo = Integer.parseInt(tfCursosCod.getText());
			try {
				c.removerCurso(codigo);
				taCursosLista.setText(" CURSO DE CODIGO " + tfCursosCod.getText() + " REMOVIDO COM SUCESSO");
			} catch (Exception e) {
				taCursosLista.setCaret((Caret) e);
			}
		}

	}
	
//	Função para a chamada e tratamento de erro do metodo "atualizar" 
//	sendo necessario o preeenchimento de todos os campos 

	private void atualizarCurso() {
		Curso curso = new Curso();
		CursoDAO c = new CursoDAO();
		if (tfCursosNome.getText().equals("") || tfCursosCod.getText().equals("")
				|| tfCursosAreaConhec.getText().equals("")) {
			JOptionPane.showMessageDialog(null," FALHA NA ATUALIZAÇÃO \n TODOS OS CAMPOS DEVEM SER PREENCHIDOS");
		} else {
			try {
				curso.setNomeCurso(tfCursosNome.getText());
				curso.setCodigoCurso(Integer.parseInt(tfCursosCod.getText()));
				curso.setAreaConhecimento(tfCursosAreaConhec.getText());
				c.atualizarCurso(curso);
				taCursosLista.setText(" CURSO ATUALIZADO COM SUCESSO \n INFORMAÇÕES CADASTRADAS : " + "\n CODIGO : "
						+ tfCursosCod.getText() + "\n NOME: " + tfCursosNome.getText() + "\n AREA DO CONHECIMENTO: "
						+ tfCursosAreaConhec.getText());
			} catch (Exception e) {
				taCursosLista.setText(e.getMessage());

			}
		}
	}
	
//	Função para a chamada e tratamento de erro do metodo "cadastrar" 
//	sendo necessario o preeenchimento de todos os campos 

	private void cadastraCurso() {
	    Curso curso = new Curso();
	    CursoDAO c = new CursoDAO();
	    
	    if (tfCursosNome.getText().equals("") || tfCursosCod.getText().equals("")
	            || tfCursosAreaConhec.getText().equals("")) {
	        JOptionPane.showMessageDialog(null, "FALHA NO CADASTRO \n TODOS OS CAMPOS DEVEM SER PREENCHIDOS");
	        return;
	    }
	    
	    try {
	        curso.setCodigoCurso(Integer.parseInt(tfCursosCod.getText()));
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "O CÓDIGO DO CURSO DEVE SER UM NÚMERO VÁLIDO");
	        return;
	    }
	    
	    curso.setNomeCurso(tfCursosNome.getText());
	    curso.setAreaConhecimento(tfCursosAreaConhec.getText());
	    
// Verificação de cadastro duplicado e mensagem de erro para usuário.
	    if (c.cursoJaExiste(curso.getCodigoCurso())) {
	        JOptionPane.showMessageDialog(null, "O CURSO JÁ ESTÁ CADASTRADO COM O CÓDIGO: " + curso.getCodigoCurso());
	        return;
	    }
	    
	    c.inserirCurso(curso);
	    taCursosLista.setText("CADASTRO REALIZADO \n INFORMAÇÕES CADASTRADAS: " +
	            "\n CODIGO: " + tfCursosCod.getText() +
	            "\n NOME: " + tfCursosNome.getText() +
	            "\n AREA DO CONHECIMENTO: " + tfCursosAreaConhec.getText() );
	}

}
