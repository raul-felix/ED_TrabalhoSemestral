package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.Caret;

import br.com.fatec.Lista;
import dao.DisciplinaDAO;
import model.Disciplina;

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
			cadastraDisciplina();
		}	
		if (cmd.equals("Remover")) {
			removerDisciplina();
		}
		if (cmd.equals("Listar Disciplinas")) {
			try {
				listarDisciplina();
			} catch (Exception e1) {
				taDisLista.setText(e1.getMessage());
			}
		}
	}

	private void listarDisciplina() throws Exception {
		DisciplinaDAO d = new DisciplinaDAO();
		Lista<Disciplina> lista = d.consultarDisciplinas();
		int tamanho = lista.size();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < tamanho ; i ++) {
			Disciplina aux = lista.get(i);
			String nomeDis = aux.getNomeDisciplina();
			int codigoDis = aux.getCodigoDisciplina();
			String dataDis = aux.getDiaDaSemana();
			LocalTime horarioDis = aux.getHorarioinicial();
			int cargaHorariaDis = aux.getQtdHorasDiarias();
			int codigoProcessoDis = aux.getCodigoProcesso();
			int codigoCurso = aux.getCodigoCurso();
			buffer.append("Nome da Disciplina: " +  nomeDis+ ",\t" +
					  "Código do Disciplina: " + codigoDis + ",\t" +
					  "Data : " + dataDis+ "Horário: " + horarioDis + ",\t" + 
					  "Carga Horária Diaria: " + cargaHorariaDis + ",\t" + 
					  "Código do Procesos: " + codigoProcessoDis + ",\t" + 
					  "Código do Curso: " + codigoCurso + ",\t" +"\n");
		}
		taDisLista.setText(buffer.toString()); 
	}

	private void removerDisciplina() {
		DisciplinaDAO d = new DisciplinaDAO();
		Disciplina disciplina = new Disciplina();
		disciplina.setCodigoDisciplina(Integer.parseInt(tfDisCodigo.getText()));
		
		if (tfDisCodigo.getText().equals("")) {
			taDisLista.setText(" FALHA NA REMOÇÃO \n INSIRA O CODIGO DE ALGUM CURSO PARA REMOVER");
		} else {
			int codigo = Integer.parseInt(tfDisCodigo.getText()) ;
			try {
				d.removerDisciplina(codigo);
				taDisLista.setText(" CURSO " + disciplina.getCodigoCurso() + " REMOVIDO COM SUCESSO");
			} catch (Exception e) {
				taDisLista.setCaret((Caret) e);
			}
		}
	}
	
	private void atualizarDisciplina() {
		Disciplina disciplina = new Disciplina();
		disciplina.setNomeDisciplina(tfDisDisciplina.getText());
		disciplina.setCodigoDisciplina(Integer.parseInt(tfDisCodigo.getText()));
		disciplina.setDiaDaSemana(tfDisData.getText());
//		disciplina.setHorarioinicial(Integer.parseInt(tfDisCargaHorariaDiaria.getText())); O que fazer com o local time?
		disciplina.setQtdHorasDiarias(Integer.parseInt(tfDisCargaHorariaDiaria.getText())); // precisa mesmo ser int ?
		disciplina.setCodigoProcesso(Integer.parseInt(tfDisCodProcesso.getText()));
		disciplina.setCodigoCurso(Integer.parseInt(tfDisCodCurso.getText()));

		
		DisciplinaDAO d = new DisciplinaDAO();
		if (tfDisDisciplina.getText().equals("") ||tfDisCodigo.getText().equals("") ||tfDisData.getText().equals("") ||
				tfDisHorario.getText().equals("") ||tfDisCargaHorariaDiaria.getText().equals("") 
				||tfDisCodProcesso.getText().equals("") || tfDisCodCurso.getText().equals("")) {
			taDisLista.setText(" FALHA NA ATUALIZAÇÃO \n PREENCHA TODOS OS CAMPOS PARA REALIZAR A ATUALIZAÇÃO");
		} else {
			try {
				d.atualizarDisciplina(disciplina);
				taDisLista.setText(" CURSO ATUALIZADO COM SUCESSO \n INFORMAÇÕES CADASTRADAS : " + "\n NOME : "
						+ disciplina.getNomeDisciplina() + "\n CODIGO DA DISCIPLINA: " + disciplina.getCodigoDisciplina() +
						"\n DATA : " +disciplina.getDiaDaSemana() + "\n HORARIO : " +disciplina.getHorarioinicial()+ 
						"\n CARGA HORARIA DIARIA: " +disciplina.getQtdHorasDiarias()+ "\n CODIGO DO PROCESSO: " +disciplina.getCodigoProcesso()+
						"\n CODIGO DO CURSO: "+disciplina.getCodigoCurso()); 
			} catch (Exception e) {
				taDisLista.setCaret((Caret) e);
			}
		}
	}
	
	private void cadastraDisciplina() {
		Disciplina disciplina = new Disciplina();
		disciplina.setNomeDisciplina(tfDisDisciplina.getText());
		disciplina.setCodigoDisciplina(Integer.parseInt(tfDisCodigo.getText()));
		disciplina.setDiaDaSemana(tfDisData.getText());
//		disciplina.setHorarioinicial(Integer.parseInt(tfDisCargaHorariaDiaria.getText())); O que fazer com o local time?
		disciplina.setQtdHorasDiarias(Integer.parseInt(tfDisCargaHorariaDiaria.getText())); // precisa mesmo ser int ?
		disciplina.setCodigoProcesso(Integer.parseInt(tfDisCodProcesso.getText()));
		disciplina.setCodigoCurso(Integer.parseInt(tfDisCodCurso.getText()));

		
		DisciplinaDAO d = new DisciplinaDAO();
		if (tfDisDisciplina.getText().equals("") ||tfDisCodigo.getText().equals("") ||tfDisData.getText().equals("") ||
				tfDisHorario.getText().equals("") ||tfDisCargaHorariaDiaria.getText().equals("") 
				||tfDisCodProcesso.getText().equals("") || tfDisCodCurso.getText().equals("")) {
			taDisLista.setText(" FALHA NO CADASTRO \n PREENCHA TODOS OS CAMPOS PARA REALIZAR O CADASTRO");
		} else {
			try {
				d.inserirDisciplina(disciplina);
				taDisLista.setText(" CURSO CADASTRADO COM SUCESSO \n INFORMAÇÕES CADASTRADAS : " + "\n NOME : "
						+ disciplina.getNomeDisciplina() + "\n CODIGO DA DISCIPLINA: " + disciplina.getCodigoDisciplina() +
						"\n DATA : " +disciplina.getDiaDaSemana() + "\n HORARIO : " +disciplina.getHorarioinicial()+ 
						"\n CARGA HORARIA DIARIA: " +disciplina.getQtdHorasDiarias()+ "\n CODIGO DO PROCESSO: " +disciplina.getCodigoProcesso()+
						"\n CODIGO DO CURSO: "+disciplina.getCodigoCurso()); 
			} catch (Exception e) {
				taDisLista.setCaret((Caret) e);
			}
		}
		
	}

	

}
