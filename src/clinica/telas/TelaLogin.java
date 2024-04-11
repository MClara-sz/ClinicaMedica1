package clinica.telas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clinica.dao.DAO;

public class TelaLogin extends JFrame {
	
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtuser;
	private JPasswordField txtpwd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
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
	public TelaLogin() {
		setTitle("login");
		con = dao.conectar();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbluser = new JLabel("Usuario");
		lbluser.setBounds(61, 70, 76, 19);
		contentPane.add(lbluser);
		
		JLabel lblpwd = new JLabel("Senha");
		lblpwd.setBounds(61, 160, 76, 19);
		contentPane.add(lblpwd);
		
		txtuser = new JTextField();
		txtuser.setBounds(166, 70, 230, 19);
		contentPane.add(txtuser);
		txtuser.setColumns(10);
		
		JButton btnLogin = new JButton("New button");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnLogin.setBounds(341, 220, 85, 33);
		contentPane.add(btnLogin);
		
		JLabel lblstatus = new JLabel("New label");
		lblstatus.setBounds(10, 230, 45, 13);
		contentPane.add(lblstatus);
		
		txtpwd = new JPasswordField();
		txtpwd.setBounds(166, 160, 161, 19);
		contentPane.add(txtpwd);
	}
	
	private void status( ) {
		try {
			Connection con  = dao.conectar();
			
			if(con == null) {
				System.out.println("Erro de conexao");
				
				//lblStatus.setIcon(new ImageIcon(TelaLogin.class.getResource("/br/com/prjClara/icones/dboff (2).png")));
			}else {
				System.out.println("Banco de dados conectado");
				//lblStatus.setIcon(new ImageIcon(TelaLogin.class.getResource("/br/com/prjClara/icones/dbon (2).png")));
			}
			con.close();
		}catch (Exception e) {
		
		}
	}
	
	
	public void logar () {
		String lerbd ="select * from tblusers where login=? and senha=?";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(lerbd);
			pst.setString(1,txtuser.getText());
			String pegar = new String(txtpwd.getPassword());
			//pst.setString(2, txtPwd.getText());
			pst.setString(2, pegar);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				//a linha abaixo verifica o perfil
				String perfil=rs.getString(6);
				//codigo abaixo verifica se esta funcionando a leitura do perfil
				// Sys.out.print(perfil};
				//a estrutura abaixo verifica o perfil
				if (perfil.equals("medico/adm")) {
					TelaInicial inicial = new TelaInicial();
					inicial.setVisible(true);
					inicial.getItmUser().setEnabled(true);
					inicial.getItmRel().setEnabled(true);
					inicial.getLbluser().setText(rs.getString(2));
					this.dispose();
					con.close();
				}else {
					TelaInicial inicial = new TelaInicial();
					inicial.setVisible(true);
					inicial.getLbluser().setText(rs.getString(2));
					this.dispose();
				}
			}else {
				JOptionPane.showMessageDialog(null,"usuario ou senha incorreto");
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
}

}
