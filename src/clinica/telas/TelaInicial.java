package clinica.telas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clinica.dao.DAO;

public class TelaInicial extends JFrame {
	
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuItem itmUser;
	private JMenuItem itmPaciente;
	private JMenuItem itmOS;
	private JMenu itmRel;
	private JMenu mnNewMenu_2;
	private JMenu mnNewMenu;
	private JTextField Lbluser;
	private JTextField lbldata;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaInicial() {
		con = dao.conectar();
		setTitle("Tela Inicial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1124, 659);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDesktopPane desktop = new JDesktopPane();
		desktop.setBounds(0, 35, 973, 577);
		contentPane.add(desktop);
		desktop.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1110, 32);
		contentPane.add(menuBar);
		
		mnNewMenu = new JMenu("Cadastro");
		menuBar.add(mnNewMenu);
		
		itmUser = new JMenuItem("Usuario");
		itmUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//abrir na outra pagina
				TelaUser user = new TelaUser();
				user.setVisible(true);
				//onde vai abrir
				desktop.add(user);
			}
		});
		mnNewMenu.add(itmUser);
		
		itmPaciente = new JMenuItem("Pacientes");
		itmPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPaciente paciente = new TelaPaciente();
			    paciente.setVisible(true);
			    desktop.add(paciente);
			}
		});
		mnNewMenu.add(itmPaciente);
		
		itmOS = new JMenuItem("OS");
		itmOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//abrir na outra pagina
				TelaOS os = new TelaOS();
				os.setVisible(true);
				//onde vai abrir
				desktop.add(os);
			}
		});
		mnNewMenu.add(itmOS);
		
		itmRel = new JMenu("New menu");
		menuBar.add(itmRel);
		
		mnNewMenu_2 = new JMenu("New menu");
		menuBar.add(mnNewMenu_2);
		
		Lbluser = new JTextField();
		Lbluser.setBounds(983, 84, 96, 19);
		contentPane.add(Lbluser);
		Lbluser.setColumns(10);
		
		lbldata = new JTextField();
		lbldata.setBounds(983, 135, 96, 19);
		contentPane.add(lbldata);
		lbldata.setColumns(10);
	}
	
	//deixar publico para outras classes
		//botao direito e exposed no public no designing
	public JMenuItem getItmUser() {
		return itmUser;
	}
	public JMenuItem getItmPaciente() {
		return itmPaciente;
	}
	public JMenuItem getItmOS() {
		return itmOS;
	}
	public JMenu getMnNewMenu() {
		return mnNewMenu;
	}
	public JTextField getLbluser() {
		return Lbluser;
	}
	public JTextField getLbldata() {
		return lbldata;
	}
	public JMenu getItmRel() {
		return itmRel;
	}
}
