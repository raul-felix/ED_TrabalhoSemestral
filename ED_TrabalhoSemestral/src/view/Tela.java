package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CursoController;
import controller.DisciplinaController;
import controller.InscriPorPontosController;
import controller.InscricaoController;
import controller.ProfessorController;
import controller.TabelaController;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Tela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfCursosCod;
	private JTextField tfCursosNome;
	private JTextField tfCursosAreaConhec;
	private JTextField tfDisCodigo;
	private JTextField tfDisData;
	private JTextField tfDisHorario;
	private JTextField tfDisCargaHorariaDiaria;
	private JTextField tfDisCodProcesso;
	private JTextField tfDisCodCurso;
	private JTextField tfProfCPF;
	private JTextField tfProfNome;
	private JTextField tfProfAreaCon;
	private JTextField tfProfPont;
	private JTextField tfDisDisciplina;
	private JTextField tfInscCPF;
	private JTextField tfInscCodDic;
	private JTextField tfInscCodProc;
	private JTextField tfCodDisciPorPontos;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Tela() {
		setResizable(false);
		setTitle("Sistema");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 840, 680);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 255, 255));
		tabbedPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tabbedPane.setBounds(10, 11, 804, 619);
		contentPane.add(tabbedPane);
		
		JPanel tabDisciplina = new JPanel();
		tabDisciplina.setBackground(new Color(238, 238, 238));
		tabbedPane.addTab("Disciplina", null, tabDisciplina, null);
		tabDisciplina.setLayout(null);
		
		JLabel lblDisDisciplina = new JLabel("Disciplina:");
		lblDisDisciplina.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDisDisciplina.setBounds(20, 31, 70, 28);
		tabDisciplina.add(lblDisDisciplina);
		
		JLabel lblDisData = new JLabel("Data:");
		lblDisData.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDisData.setBounds(155, 81, 70, 28);
		tabDisciplina.add(lblDisData);
		
		JLabel lblDisHorario = new JLabel("Horário:");
		lblDisHorario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDisHorario.setBounds(286, 81, 70, 28);
		tabDisciplina.add(lblDisHorario);
		
		JLabel lblDisCod = new JLabel("Código:");
		lblDisCod.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDisCod.setBounds(20, 81, 70, 28);
		tabDisciplina.add(lblDisCod);
		
		tfDisCodigo = new JTextField();
		tfDisCodigo.setBounds(20, 120, 86, 28);
		tabDisciplina.add(tfDisCodigo);
		tfDisCodigo.setColumns(10);
		
		tfDisData = new JTextField();
		tfDisData.setBounds(155, 120, 86, 28);
		tabDisciplina.add(tfDisData);
		tfDisData.setColumns(10);
		
		tfDisHorario = new JTextField();
		tfDisHorario.setBounds(286, 120, 86, 28);
		tabDisciplina.add(tfDisHorario);
		tfDisHorario.setColumns(10);
		
		JLabel lblDisCargaHoraria = new JLabel("Carga Horária Diária:");
		lblDisCargaHoraria.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDisCargaHoraria.setBounds(417, 81, 156, 28);
		tabDisciplina.add(lblDisCargaHoraria);
		
		tfDisCargaHorariaDiaria = new JTextField();
		tfDisCargaHorariaDiaria.setBounds(417, 120, 86, 28);
		tabDisciplina.add(tfDisCargaHorariaDiaria);
		tfDisCargaHorariaDiaria.setColumns(10);
		
		JLabel lblDisCodProcesso = new JLabel("Código do Processo:");
		lblDisCodProcesso.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDisCodProcesso.setBounds(20, 180, 178, 28);
		tabDisciplina.add(lblDisCodProcesso);
		
		tfDisCodProcesso = new JTextField();
		tfDisCodProcesso.setBounds(20, 219, 156, 28);
		tabDisciplina.add(tfDisCodProcesso);
		tfDisCodProcesso.setColumns(10);
		
		JButton btnDisCadastrar = new JButton("Cadastrar");
		btnDisCadastrar.setBounds(174, 275, 115, 28);
		tabDisciplina.add(btnDisCadastrar);
		
		JLabel lblDisCodCurso = new JLabel("Código do Curso:");
		lblDisCodCurso.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDisCodCurso.setBounds(286, 183, 178, 23);
		tabDisciplina.add(lblDisCodCurso);
		
		tfDisCodCurso = new JTextField();
		tfDisCodCurso.setBounds(286, 219, 163, 28);
		tabDisciplina.add(tfDisCodCurso);
		tfDisCodCurso.setColumns(10);
		
		tfDisDisciplina = new JTextField();
		tfDisDisciplina.setBounds(100, 34, 460, 23);
		tabDisciplina.add(tfDisDisciplina);
		tfDisDisciplina.setColumns(10);
		
		JButton btnDisListar = new JButton("Listar Disciplinas");
		btnDisListar.setBounds(329, 275, 115, 28);
		tabDisciplina.add(btnDisListar);
		
		JButton btnDisAtualizar = new JButton("Atualizar");
		btnDisAtualizar.setBounds(20, 275, 115, 28);
		tabDisciplina.add(btnDisAtualizar);
		
		JButton btnDisRemover = new JButton("Remover");
		btnDisRemover.setBounds(493, 275, 115, 28);
		tabDisciplina.add(btnDisRemover);
		
		JScrollPane scrollPaneDisciplina = new JScrollPane();
		scrollPaneDisciplina.setBounds(20, 314, 758, 265);
		tabDisciplina.add(scrollPaneDisciplina);
		
		JTextArea taDisLista = new JTextArea();
		scrollPaneDisciplina.setViewportView(taDisLista);
		
		JPanel tabCursos = new JPanel();
		tabCursos.setBackground(new Color(238, 238, 238));
		tabbedPane.addTab("Cursos", null, tabCursos, "Cadastro de Cursos");
		tabCursos.setLayout(null);
		
		JLabel lblCursosCod = new JLabel("Código do Curso:");
		lblCursosCod.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCursosCod.setHorizontalAlignment(SwingConstants.LEFT);
		lblCursosCod.setBounds(22, 24, 191, 27);
		tabCursos.add(lblCursosCod);
		
		tfCursosCod = new JTextField();
		tfCursosCod.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCursosCod.setLabelFor(tfCursosCod);
		tfCursosCod.setBounds(22, 52, 123, 33);
		tabCursos.add(tfCursosCod);
		tfCursosCod.setColumns(10);
		
		JLabel lblCursosNome = new JLabel("Nome do Curso:");
		lblCursosNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblCursosNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCursosNome.setBounds(23, 97, 191, 27);
		tabCursos.add(lblCursosNome);
		
		tfCursosNome = new JTextField();
		lblCursosNome.setLabelFor(tfCursosNome);
		tfCursosNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfCursosNome.setColumns(10);
		tfCursosNome.setBounds(23, 125, 556, 33);
		tabCursos.add(tfCursosNome);
		
		JLabel lblCursosAreaConhec = new JLabel("Área do Conhecimento:");
		lblCursosAreaConhec.setHorizontalAlignment(SwingConstants.LEFT);
		lblCursosAreaConhec.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCursosAreaConhec.setBounds(22, 179, 191, 27);
		tabCursos.add(lblCursosAreaConhec);
		
		tfCursosAreaConhec = new JTextField();
		lblCursosAreaConhec.setLabelFor(tfCursosAreaConhec);
		tfCursosAreaConhec.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfCursosAreaConhec.setColumns(10);
		tfCursosAreaConhec.setBounds(22, 207, 556, 33);
		tabCursos.add(tfCursosAreaConhec);
		
		JButton btnCursosCadastrar = new JButton("Cadastrar");
		btnCursosCadastrar.setBounds(169, 273, 103, 27);
		tabCursos.add(btnCursosCadastrar);
		
		JButton btnCursosAtualizar = new JButton("Atualizar");
		btnCursosAtualizar.setBounds(22, 273, 103, 27);
		tabCursos.add(btnCursosAtualizar);
		
		JButton btnCursosRemover = new JButton("Remover");
		btnCursosRemover.setBounds(476, 273, 103, 27);
		tabCursos.add(btnCursosRemover);
		
		JButton btnCursosListar = new JButton("Listar Cursos");
		btnCursosListar.setBounds(322, 273, 103, 27);
		tabCursos.add(btnCursosListar);
		
		JScrollPane scrollPaneCursos = new JScrollPane();
		scrollPaneCursos.setBounds(22, 311, 754, 268);
		tabCursos.add(scrollPaneCursos);
		
		JTextArea taCursosLista = new JTextArea();
		scrollPaneCursos.setViewportView(taCursosLista);
		
		JPanel tabProfessor = new JPanel();
		tabProfessor.setBackground(new Color(238, 238, 238));
		tabbedPane.addTab("Professor", null, tabProfessor, null);
		tabProfessor.setLayout(null);
		
		JLabel lblProfCPF = new JLabel("CPF:");
		lblProfCPF.setBounds(37, 61, 50, 23);
		lblProfCPF.setFont(new Font("Tahoma", Font.BOLD, 14));
		tabProfessor.add(lblProfCPF);
		
		tfProfCPF = new JTextField();
		tfProfCPF.setBounds(88, 63, 200, 23);
		tabProfessor.add(tfProfCPF);
		tfProfCPF.setColumns(10);
		
		JLabel lblProfNome = new JLabel("Nome:");
		lblProfNome.setBounds(41, 109, 46, 14);
		lblProfNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		tabProfessor.add(lblProfNome);
		
		tfProfNome = new JTextField();
		tfProfNome.setBounds(33, 130, 525, 23);
		tabProfessor.add(tfProfNome);
		tfProfNome.setColumns(10);
		
		JLabel lblProfAreaCon = new JLabel("Área de conhecimento:");
		lblProfAreaCon.setBounds(33, 185, 172, 23);
		lblProfAreaCon.setFont(new Font("Tahoma", Font.BOLD, 14));
		tabProfessor.add(lblProfAreaCon);
		
		tfProfAreaCon = new JTextField();
		tfProfAreaCon.setBounds(33, 219, 418, 23);
		tabProfessor.add(tfProfAreaCon);
		tfProfAreaCon.setColumns(10);
		
		JLabel lblProfPont = new JLabel("Pontuação:");
		lblProfPont.setBounds(477, 185, 157, 23);
		lblProfPont.setFont(new Font("Tahoma", Font.BOLD, 14));
		tabProfessor.add(lblProfPont);
		
		tfProfPont = new JTextField();
		tfProfPont.setBounds(477, 218, 81, 23);
		tabProfessor.add(tfProfPont);
		tfProfPont.setColumns(10);
		
		JButton btnProfCadastrar = new JButton("Cadastrar");
		btnProfCadastrar.setBounds(242, 265, 111, 30);
		btnProfCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tabProfessor.add(btnProfCadastrar);
		
		JButton btnProfAtualizar = new JButton("Atualizar");
		btnProfAtualizar.setBounds(33, 265, 111, 30);
		tabProfessor.add(btnProfAtualizar);
		
		JButton btnProfRemover = new JButton("Remover");
		btnProfRemover.setBounds(447, 265, 111, 30);
		tabProfessor.add(btnProfRemover);
		
		JButton btnProfListar = new JButton("Listar Professores");
		btnProfListar.setBounds(358, 63, 200, 23);
		tabProfessor.add(btnProfListar);
		
		JScrollPane scrollPaneProfessor = new JScrollPane();
		scrollPaneProfessor.setBounds(31, 306, 741, 273);
		tabProfessor.add(scrollPaneProfessor);
		
		JTextArea taProfLista = new JTextArea();
		scrollPaneProfessor.setViewportView(taProfLista);
		
		JPanel tabInscricao = new JPanel();
		tabInscricao.setBackground(new Color(238, 238, 238));
		tabbedPane.addTab("Inscrição", null, tabInscricao, null);
		tabInscricao.setLayout(null);
		
		JLabel lblInscCPF = new JLabel("CPF:");
		lblInscCPF.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInscCPF.setBounds(31, 39, 43, 23);
		tabInscricao.add(lblInscCPF);
		
		tfInscCPF = new JTextField();
		tfInscCPF.setBounds(84, 39, 191, 23);
		tabInscricao.add(tfInscCPF);
		tfInscCPF.setColumns(10);
		
		JLabel lblInscCodDisc = new JLabel("Código da Disciplina:");
		lblInscCodDisc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInscCodDisc.setBounds(31, 106, 149, 23);
		tabInscricao.add(lblInscCodDisc);
		
		tfInscCodDic = new JTextField();
		tfInscCodDic.setBounds(190, 106, 191, 23);
		tabInscricao.add(tfInscCodDic);
		tfInscCodDic.setColumns(10);
		
		JLabel lblInscCodProc = new JLabel("Código do Processo:");
		lblInscCodProc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInscCodProc.setBounds(31, 182, 149, 23);
		tabInscricao.add(lblInscCodProc);
		
		tfInscCodProc = new JTextField();
		tfInscCodProc.setBounds(189, 182, 192, 23);
		tabInscricao.add(tfInscCodProc);
		tfInscCodProc.setColumns(10);
		
		JButton btnInscAtualizar = new JButton("Atualizar");
		btnInscAtualizar.setBounds(31, 247, 110, 30);
		tabInscricao.add(btnInscAtualizar);
		
		JButton btnInscCadastrar = new JButton("Cadastrar");
		btnInscCadastrar.setBounds(235, 247, 110, 30);
		tabInscricao.add(btnInscCadastrar);
		
		JButton btnInscRemover = new JButton("Remover");
		btnInscRemover.setBounds(454, 247, 110, 30);
		tabInscricao.add(btnInscRemover);
		
		JButton btnInscListarInsc = new JButton("Listar Inscrições");
		btnInscListarInsc.setBounds(402, 41, 140, 23);
		tabInscricao.add(btnInscListarInsc);
		
		JScrollPane scrollPaneInscricao = new JScrollPane();
		scrollPaneInscricao.setBounds(31, 299, 743, 280);
		tabInscricao.add(scrollPaneInscricao);
		
		JTextArea taInscLista = new JTextArea();
		scrollPaneInscricao.setViewportView(taInscLista);
		
		JPanel tabConsInscri = new JPanel();
		tabbedPane.addTab("Consulta Inscritos", null, tabConsInscri, null);
		tabConsInscri.setLayout(null);
		
		JLabel lblCodDisciPorPontos = new JLabel("Código da Disciplina:");
		lblCodDisciPorPontos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCodDisciPorPontos.setBounds(10, 18, 151, 14);
		tabConsInscri.add(lblCodDisciPorPontos);
		
		tfCodDisciPorPontos = new JTextField();
		tfCodDisciPorPontos.setBounds(171, 17, 73, 20);
		tabConsInscri.add(tfCodDisciPorPontos);
		tfCodDisciPorPontos.setColumns(10);
		
		JButton btnListasPorPontos = new JButton("Listar por Pontuação");
		btnListasPorPontos.setBounds(277, 16, 173, 23);
		tabConsInscri.add(btnListasPorPontos);
		
		JScrollPane scrollPaneInscriPorPontos = new JScrollPane();
		scrollPaneInscriPorPontos.setBounds(10, 59, 779, 520);
		tabConsInscri.add(scrollPaneInscriPorPontos);
		
		JTextArea taInscriPorPontos = new JTextArea();
		scrollPaneInscriPorPontos.setViewportView(taInscriPorPontos);
		
		JPanel tabTabelaInscri = new JPanel();
		tabbedPane.addTab("Tabela de Inscrições", null, tabTabelaInscri, null);
		tabTabelaInscri.setLayout(null);
		
		JButton btnCarregarTabela = new JButton("Carregar Tabela");
		btnCarregarTabela.setBounds(10, 11, 135, 23);
		tabTabelaInscri.add(btnCarregarTabela);
		
		JScrollPane scrollPaneTabela = new JScrollPane();
		scrollPaneTabela.setBounds(10, 45, 779, 534);
		tabTabelaInscri.add(scrollPaneTabela);
		
		JTextArea taTabela = new JTextArea();
		scrollPaneTabela.setViewportView(taTabela);
		
		CursoController cCont = new CursoController(tfCursosCod, tfCursosNome, tfCursosAreaConhec, taCursosLista);
		
		btnCursosAtualizar.addActionListener(cCont);
		btnCursosCadastrar.addActionListener(cCont);
		btnCursosRemover.addActionListener(cCont);
		btnCursosListar.addActionListener(cCont);
		
		DisciplinaController cDis = new DisciplinaController(tfDisCodigo, tfDisData, tfDisHorario, tfDisCargaHorariaDiaria, tfDisCodProcesso, tfDisCodCurso, tfDisDisciplina, taDisLista);
		
		btnDisAtualizar.addActionListener(cDis);
		btnDisCadastrar.addActionListener(cDis);
		btnDisRemover.addActionListener(cDis);
		btnDisListar.addActionListener(cDis);
		
		InscricaoController cInscri = new InscricaoController(tfInscCPF, tfInscCodDic, tfInscCodProc, taInscLista);
		
		btnInscAtualizar.addActionListener(cInscri);
		btnInscCadastrar.addActionListener(cInscri);
		btnInscRemover.addActionListener(cInscri);
		btnInscListarInsc.addActionListener(cInscri);
		
		ProfessorController cProf = new ProfessorController(tfProfCPF, tfProfNome, tfProfAreaCon, tfProfPont, taProfLista);
		
		btnProfAtualizar.addActionListener(cProf);
		btnProfCadastrar.addActionListener(cProf);
		btnProfRemover.addActionListener(cProf);
		btnProfListar.addActionListener(cProf);
		
		InscriPorPontosController cInscriPorPontos = new InscriPorPontosController(tfCodDisciPorPontos, taInscriPorPontos);
		
		btnListasPorPontos.addActionListener(cInscriPorPontos);
		
		TabelaController cTab = new TabelaController(taTabela);
		
		btnCarregarTabela.addActionListener(cTab);
	}
}

