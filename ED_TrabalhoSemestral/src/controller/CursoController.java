package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.Caret;

import br.com.fatec.Lista;
import dao.CursoDAO;
import model.Curso;

public class CursoController implements ActionListener {

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

	private void listarCursos() throws Exception {
		CursoDAO c = new CursoDAO();
		Lista<Curso> lista = c.consultarCursos();
		int tamanho = lista.size();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < tamanho; i ++) {
			Curso aux = lista.get(i);
			String nomeCurso = aux.getNomeCurso();
			int codigoCurso = aux.getCodigoCurso();
			String areaConhecimentoCurso = aux.getAreaConhecimento();
			buffer.append("Nome do curso: " + nomeCurso + ",\t" +
						  "Código do curso: " + codigoCurso + ",\t" +
						  "Área do conhecimento: " + areaConhecimentoCurso + "\n");
		}
		taCursosLista.setText(buffer.toString());
	}

	private void removerCurso() {
		Curso curso = new Curso();
		curso.setCodigoCurso(Integer.parseInt(tfCursosCod.getText()));
		CursoDAO c = new CursoDAO();
		if (tfCursosCod.getText().equals("")) {
			taCursosLista.setText(" FALHA NA REMOÇÃO \n INSIRA O CODIGO DE ALGUM CURSO PARA REMOVER");
		} else {
			int codigo = Integer.parseInt(tfCursosCod.getText());
			try {
				c.removerCurso(codigo);
				taCursosLista.setText(" CURSO " + curso.getCodigoCurso() + " REMOVIDO COM SUCESSO");
			} catch (Exception e) {
				taCursosLista.setCaret((Caret) e);
			}
		}

	}

	private void atualizarCurso() {
		Curso curso = new Curso();
		curso.setNomeCurso(tfCursosNome.getText());
		curso.setCodigoCurso(Integer.parseInt(tfCursosCod.getText()));
		curso.setAreaConhecimento(tfCursosAreaConhec.getText());
		CursoDAO c = new CursoDAO();
		if (tfCursosNome.getText().equals("") || tfCursosCod.getText().equals("")
				|| tfCursosAreaConhec.getText().equals("")) {
			taCursosLista.setText(" FALHA NA ATUALIZAÇÃO \n PREENCHA TODOS OS CAMPOS PARA REALIZAR A ATUALIZAÇÃO");
		} else {
			try {
				c.atualizarCurso(curso);
				taCursosLista.setText(" CURSO ATUALIZADO COM SUCESSO \n INFORMAÇÕES CADASTRADAS : " + "\n CODIGO : "
						+ curso.getCodigoCurso() + "\n NOME: " + curso.getNomeCurso() + "\n AREA DO CONHECIMENTO: "
						+ curso.getAreaConhecimento());
			} catch (Exception e) {
				taCursosLista.setText(e.getMessage());

			}
		}
	}

	private void cadastraCurso() {
		Curso curso = new Curso();
		curso.setNomeCurso(tfCursosNome.getText());
		curso.setCodigoCurso(Integer.parseInt(tfCursosCod.getText()));
		curso.setAreaConhecimento(tfCursosAreaConhec.getText());
		CursoDAO c = new CursoDAO();
		if (tfCursosNome.getText().equals("") || tfCursosCod.getText().equals("")
				|| tfCursosAreaConhec.getText().equals("")) {
			taCursosLista.setText(" FALHA NO CADASTRO \n PREENCHA TODOS OS CAMPOS PARA REALIZAR O CADASTRO");
		} else {
			c.inserirCurso(curso);
			taCursosLista.setText(" CADASTRO REALIZADO \n INFORMAÇÕES CADASTRADAS : " + "\n CODIGO : "
					+ curso.getCodigoCurso() + "\n NOME: " + curso.getNomeCurso() + "\n AREA DO CONHECIMENTO: "
					+ curso.getAreaConhecimento());
		}
	}

}
