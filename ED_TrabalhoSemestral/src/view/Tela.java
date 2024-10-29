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

public class Tela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfCursosCodigo;
	private JTextField tfCursosNome;
	private JTextField tfCursosAreaConhec;

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
		
		JPanel tabCursos = new JPanel();
		tabCursos.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Cursos", null, tabCursos, "Cadastro de Cursos");
		tabCursos.setLayout(null);
		
		JLabel lblCursosCodigo = new JLabel("Código do Curso:");
		lblCursosCodigo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCursosCodigo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCursosCodigo.setBounds(22, 24, 191, 27);
		tabCursos.add(lblCursosCodigo);
		
		tfCursosCodigo = new JTextField();
		tfCursosCodigo.setEditable(false);
		tfCursosCodigo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCursosCodigo.setLabelFor(tfCursosCodigo);
		tfCursosCodigo.setBounds(22, 52, 123, 33);
		tabCursos.add(tfCursosCodigo);
		tfCursosCodigo.setColumns(10);
		
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
		btnCadastrar.setBounds(456, 344, 123, 23);
		tabCursos.add(btnCadastrar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(307, 344, 123, 23);
		tabCursos.add(btnVoltar);
	}
}
