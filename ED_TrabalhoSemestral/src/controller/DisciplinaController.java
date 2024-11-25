package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.Caret;

import br.com.fatec.Lista;
import dao.DisciplinaDAO;
import model.Disciplina;

public class DisciplinaController implements ActionListener {
//Nesta classe temos os metodos que integram a tela "Disciplina" com os metodos internos do sistemas "DisciplinaDAO"
//Tambem é aqui que ocorre o tratamento de erro, e retorno responsivo do sistema Destas classes
	
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

//	Função para chamada e tratamento de erro da
//	listagem de Disciplina na textArea da tela Disciplina
	
	private void listarDisciplina() throws Exception {
		DisciplinaDAO d = new DisciplinaDAO();
		Lista<Disciplina> lista = d.consultarDisciplinas();
		int tamanho = lista.size();
		StringBuffer buffer = new StringBuffer("Nome\t\t"
											 + "Código\t"
											 + "Data\t\t"
											 + "Horário\t"
											 + "Carga Horária Diária\t"
											 + "Código do Processo\t"
											 + "Código do Curso\n");
		for (int i = 0; i < tamanho ; i ++) {
			Disciplina aux = lista.get(i);
			String nomeDis = aux.getNomeDisciplina();
			int codigoDis = aux.getCodigoDisciplina();
			String dataDis = aux.getDiaDaSemana();
			LocalTime horarioDis = aux.getHorarioinicial();
			int cargaHorariaDis = aux.getQtdHorasDiarias();
			int codigoProcessoDis = aux.getCodigoProcesso();
			int codigoCurso = aux.getCodigoCurso();
			
			String separator = (nomeDis.length() > 13) ? "\t" : "\t\t";
			buffer.append(nomeDis + separator);
			buffer.append(codigoDis + "\t");
			
			separator = (dataDis.length() > 13) ? "\t" : "\t\t";
			buffer.append(dataDis + separator);
			buffer.append(horarioDis + "\t");
			buffer.append(cargaHorariaDis + "\t\t");
			buffer.append(codigoProcessoDis + "\t\t");
			buffer.append(codigoCurso + "\n");
		}
		taDisLista.setText(buffer.toString()); 
	}
	
	
//	Função para chamada e tratamento de erro do Metodo "remoção" de uma Disciplina 
//	sendo necessarioa penas a passagem do parametro "codigo" de disciplina
	
	private void removerDisciplina() {
		DisciplinaDAO d = new DisciplinaDAO();
		
		if (tfDisCodigo.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "FALHA NA REMOÇÃO \n INSIRA O CODIGO DE ALGUM CURSO PARA REMOVER");
		} else {
			int codigo = Integer.parseInt(tfDisCodigo.getText()) ;
			try {
				d.removerDisciplina(codigo);
				taDisLista.setText(" CURSO " + tfDisCodCurso.getText() + " REMOVIDO COM SUCESSO");
			} catch (Exception e) {
				taDisLista.setCaret((Caret) e);
			}
		}
	}

	
//	Função para chamada e tratamento de erro do metodo "atualizar" 
//	sendo necessario o preeenchimento de todos os campos	
	
	private void atualizarDisciplina() {
		Disciplina disciplina = new Disciplina();
		DisciplinaDAO d = new DisciplinaDAO();
		if (tfDisDisciplina.getText().equals("") ||tfDisCodigo.getText().equals("") ||tfDisData.getText().equals("") ||
				tfDisHorario.getText().equals("") ||tfDisCargaHorariaDiaria.getText().equals("") 
				||tfDisCodProcesso.getText().equals("") || tfDisCodCurso.getText().equals("")) {
			JOptionPane.showMessageDialog(null," FALHA NA ATUALIZAÇÃO \n TODOS OS CAMPOS DEVEM SER PREENCHIDOS");
		} else {
			try {
				disciplina.setNomeDisciplina(tfDisDisciplina.getText());
				disciplina.setCodigoDisciplina(Integer.parseInt(tfDisCodigo.getText()));
				disciplina.setDiaDaSemana(tfDisData.getText());
				disciplina.setHorarioinicial(LocalTime.parse(tfDisHorario.getText()));
				disciplina.setQtdHorasDiarias(Integer.parseInt(tfDisCargaHorariaDiaria.getText())); 
				disciplina.setCodigoProcesso(Integer.parseInt(tfDisCodProcesso.getText()));
				disciplina.setCodigoCurso(Integer.parseInt(tfDisCodCurso.getText()));
				d.atualizarDisciplina(disciplina);
				taDisLista.setText(" DISCIPLINA ATUALIZADA COM SUCESSO \n INFORMAÇÕES CADASTRADAS : " + "\n NOME : "
						+ tfDisDisciplina.getText() + "\n CODIGO DA DISCIPLINA: " + tfDisCodigo.getText() +
						"\n DATA : " +tfDisData.getText() + "\n HORARIO : " +tfDisHorario.getText() + 
						"\n CARGA HORARIA DIARIA: " +tfDisCargaHorariaDiaria.getText()+ "\n CODIGO DO PROCESSO: " +tfDisCodProcesso.getText()+
						"\n CODIGO DO CURSO: "+tfDisCodCurso.getText()); 
			} catch (Exception e) {
				taDisLista.setCaret((Caret) e);
			}
		}
	}
//	Função para a chamada e tratamento de erro do metodo "cadastrar" 
//	sendo necessario o preeenchimento de todos os campos	
	private void cadastraDisciplina() {
		Disciplina disciplina = new Disciplina();
		DisciplinaDAO d = new DisciplinaDAO();
		if (tfDisDisciplina.getText().isEmpty() || tfDisCodigo.getText().isEmpty() || 
		        tfDisData.getText().isEmpty() || tfDisHorario.getText().isEmpty() || 
		        tfDisCargaHorariaDiaria.getText().isEmpty() || tfDisCodProcesso.getText().isEmpty() || 
		        tfDisCodCurso.getText().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "FALHA NO CADASTRO \n TODOS OS CAMPOS DEVEM SER PREENCHIDOS.");
		        return;
		    }
		try {
	        // Preenche os dados da disciplina
	        disciplina.setNomeDisciplina(tfDisDisciplina.getText());
	        disciplina.setCodigoDisciplina(Integer.parseInt(tfDisCodigo.getText()));
	        disciplina.setDiaDaSemana(tfDisData.getText());
	        disciplina.setQtdHorasDiarias(Integer.parseInt(tfDisCargaHorariaDiaria.getText()));
	        disciplina.setCodigoProcesso(Integer.parseInt(tfDisCodProcesso.getText()));
	        disciplina.setCodigoCurso(Integer.parseInt(tfDisCodCurso.getText()));
	        
	        try {
	            disciplina.setHorarioinicial(LocalTime.parse(tfDisHorario.getText()));
	        } catch (DateTimeParseException e) {
	            JOptionPane.showMessageDialog(null, "HORÁRIO INVÁLIDO. USE O FORMATO HH:mm.");
	            return;
	        }
	        
	        // Verificação de cadastro duplicado e mensagem de erro para usuário.
	        if (d.disciplinaJaExiste(disciplina.getCodigoDisciplina())) {
	            JOptionPane.showMessageDialog(null, "FALHA NO CADASTRO: JÁ EXISTE UMA DISCIPLINA COM ESTE CÓDIGO.");
	            return;
	        }
	        
	        d.inserirDisciplina(disciplina);	       
	        taDisLista.setText(" DISCIPLINA CADASTRADA COM SUCESSO \n INFORMAÇÕES CADASTRADAS : " + "\n NOME : "
					+ tfDisDisciplina.getText() + "\n CODIGO DA DISCIPLINA: " + tfDisCodigo.getText() +
					"\n DATA : " +tfDisData.getText() + "\n HORARIO : " +tfDisHorario.getText() + 
					"\n CARGA HORARIA DIARIA: " +tfDisCargaHorariaDiaria.getText()+ "\n CODIGO DO PROCESSO: " +tfDisCodProcesso.getText()+
					"\n CODIGO DO CURSO: "+tfDisCodCurso.getText()); 
	        } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "ALGUNS CAMPOS DEVEM CONTER NÚMEROS VÁLIDOS.");
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "ERRO AO CADASTRAR DISCIPLINA: " + e.getMessage());
	    }
		
	}	

}
