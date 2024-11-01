package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

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
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField tsDisDisciplina;

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
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 255, 255));
		tabbedPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tabbedPane.setBounds(10, 11, 604, 419);
		contentPane.add(tabbedPane);
		
		JPanel tabDisciplina = new JPanel();
		tabDisciplina.setBackground(new Color(255, 255, 255));
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
		btnDisCadastrar.setBounds(168, 341, 115, 23);
		tabDisciplina.add(btnDisCadastrar);
		
		JLabel lblDisCodCurso = new JLabel("Código do Curso:");
		lblDisCodCurso.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDisCodCurso.setBounds(286, 183, 178, 23);
		tabDisciplina.add(lblDisCodCurso);
		
		tfDisCodCurso = new JTextField();
		tfDisCodCurso.setBounds(286, 219, 163, 28);
		tabDisciplina.add(tfDisCodCurso);
		tfDisCodCurso.setColumns(10);
		
		tsDisDisciplina = new JTextField();
		tsDisDisciplina.setBounds(100, 34, 460, 23);
		tabDisciplina.add(tsDisDisciplina);
		tsDisDisciplina.setColumns(10);
		
		JButton btnDisListar = new JButton("Listar Disciplinas");
		btnDisListar.setBounds(20, 285, 156, 23);
		tabDisciplina.add(btnDisListar);
		
		JButton btnDisAtualizar = new JButton("Atualizar");
		btnDisAtualizar.setBounds(20, 341, 115, 23);
		tabDisciplina.add(btnDisAtualizar);
		
		JButton btnDisConsultar = new JButton("Consultar");
		btnDisConsultar.setBounds(311, 341, 115, 23);
		tabDisciplina.add(btnDisConsultar);
		
		JButton btnDisRemover = new JButton("Remover");
		btnDisRemover.setBounds(458, 341, 115, 23);
		tabDisciplina.add(btnDisRemover);
		
		JPanel tabCursos = new JPanel();
		tabCursos.setBackground(new Color(255, 255, 255));
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
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(172, 344, 103, 23);
		tabCursos.add(btnCadastrar);
		
		JButton btnCursosConsultar = new JButton("Consultar");
		btnCursosConsultar.setBounds(324, 344, 103, 23);
		tabCursos.add(btnCursosConsultar);
		
		JButton btnCursosAtualizar = new JButton("Atualizar");
		btnCursosAtualizar.setBounds(22, 344, 103, 23);
		tabCursos.add(btnCursosAtualizar);
		
		JButton btnCursosRemover = new JButton("Remover");
		btnCursosRemover.setBounds(476, 344, 103, 23);
		tabCursos.add(btnCursosRemover);
		
		JButton btnCursosListar = new JButton("Listar todos os cursos");
		btnCursosListar.setBounds(397, 58, 182, 23);
		tabCursos.add(btnCursosListar);
		
		JPanel tabProfessor = new JPanel();
		tabProfessor.setBackground(new Color(255, 255, 255));
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
		tfProfAreaCon.setBounds(33, 219, 338, 23);
		tabProfessor.add(tfProfAreaCon);
		tfProfAreaCon.setColumns(10);
		
		JLabel lblProfPont = new JLabel("Pontuação:");
		lblProfPont.setBounds(401, 185, 157, 23);
		lblProfPont.setFont(new Font("Tahoma", Font.BOLD, 14));
		tabProfessor.add(lblProfPont);
		
		tfProfPont = new JTextField();
		tfProfPont.setBounds(401, 218, 157, 23);
		tabProfessor.add(tfProfPont);
		tfProfPont.setColumns(10);
		
		JButton btnProfCadastrar = new JButton("Cadastrar");
		btnProfCadastrar.setBounds(245, 325, 111, 23);
		btnProfCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tabProfessor.add(btnProfCadastrar);
		
		JButton btnProfPesqProf = new JButton("Pesquisar Professor");
		btnProfPesqProf.setBounds(320, 63, 162, 23);
		btnProfPesqProf.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tabProfessor.add(btnProfPesqProf);
		
		JButton btnProfAtualizar = new JButton("Atualizar");
		btnProfAtualizar.setBounds(33, 325, 111, 23);
		tabProfessor.add(btnProfAtualizar);
		
		JButton btnProfRemover = new JButton("Remover");
		btnProfRemover.setBounds(447, 325, 111, 23);
		tabProfessor.add(btnProfRemover);
		
		JButton btnProfListar = new JButton("Listar todos Professores");
		btnProfListar.setBounds(37, 276, 200, 23);
		tabProfessor.add(btnProfListar);
		
		JPanel tabInscricao = new JPanel();
		tabInscricao.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Inscrição", null, tabInscricao, null);
		tabInscricao.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("CPF:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(31, 39, 43, 23);
		tabInscricao.add(lblNewLabel_2);
		
		textField_5 = new JTextField();
		textField_5.setBounds(84, 39, 191, 23);
		tabInscricao.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Código da Disciplina:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(31, 106, 149, 23);
		tabInscricao.add(lblNewLabel_3);
		
		textField_6 = new JTextField();
		textField_6.setBounds(190, 106, 191, 23);
		tabInscricao.add(textField_6);
		textField_6.setColumns(10);
		
		JPanel tabConsInscri = new JPanel();
		tabbedPane.addTab("Consulta Inscritos", null, tabConsInscri, null);
		
		JPanel tabConsDiscip = new JPanel();
		tabbedPane.addTab("Consulta Disciplinas", null, tabConsDiscip, null);
	}
}

