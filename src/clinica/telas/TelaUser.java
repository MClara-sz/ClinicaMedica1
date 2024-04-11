package clinica.telas;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JInternalFrame;

import clinica.dao.DAO;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;



public class TelaUser extends JInternalFrame {
	
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	private static final long serialVersionUID = 1L;
	private JTextField txtFone;
	private JTextField txtSenha;
	private JTextField txtId;
	private JTextField txtLogin;
	private JTextField txtNome;
	private JComboBox cmbPerfil;
	private JButton btnSalvar;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaUser frame = new TelaUser();
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
	public TelaUser() {
		setTitle("user");
		setMaximizable(true);
		setClosable(true);
		con = dao.conectar();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		txtFone = new JTextField();
		txtFone.setBounds(99, 124, 96, 19);
		getContentPane().add(txtFone);
		txtFone.setColumns(10);
		
		txtSenha = new JTextField();
		txtSenha.setBounds(99, 169, 96, 19);
		getContentPane().add(txtSenha);
		txtSenha.setColumns(10);
		
		txtId = new JTextField();
		txtId.setBounds(282, 78, 96, 19);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(282, 169, 96, 19);
		getContentPane().add(txtLogin);
		txtLogin.setColumns(10);
		
		cmbPerfil = new JComboBox();
		cmbPerfil.setModel(new DefaultComboBoxModel(new String[] {"paciente", "medico/adm"}));
		cmbPerfil.setBounds(293, 123, 87, 21);
		getContentPane().add(cmbPerfil);
		
		btnSalvar = new JButton("");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();

			}
		});
		btnSalvar.setToolTipText("Salvar");
		btnSalvar.setBounds(66, 215, 54, 46);
		getContentPane().add(btnSalvar);
		
		JButton btnAtualizar = new JButton("");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterar();
			}
		});
		btnAtualizar.setToolTipText("atualizar");
		btnAtualizar.setBounds(141, 205, 69, 56);
		getContentPane().add(btnAtualizar);
		
		JButton btnDeletar = new JButton("");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();

			}
		});
		btnDeletar.setBounds(222, 205, 69, 56);
		getContentPane().add(btnDeletar);
		
		JButton btnPesquisar = new JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultar();

			}
		});
		btnPesquisar.setBounds(315, 205, 69, 56);
		getContentPane().add(btnPesquisar);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNome.setColumns(10);
		txtNome.setBounds(101, 78, 146, 20);
		getContentPane().add(txtNome);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSenha.setBounds(25, 172, 49, 14);
		getContentPane().add(lblSenha);
		
		JLabel lblFone = new JLabel("Fone");
		lblFone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFone.setBounds(25, 127, 49, 14);
		getContentPane().add(lblFone);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNome.setBounds(25, 81, 49, 14);
		getContentPane().add(lblNome);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblId.setBounds(256, 79, 49, 14);
		getContentPane().add(lblId);
		
		JLabel lblPerfil = new JLabel("Perfil");
		lblPerfil.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPerfil.setBounds(234, 127, 49, 14);
		getContentPane().add(lblPerfil);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLogin.setBounds(222, 172, 49, 14);
		getContentPane().add(lblLogin);

	}
	
	private void salvar() {
		String lerbd = "insert into tblusers (usuario, fone, login, senha, perfil) values (?,?,?,?,?)";
		try {
			//con = dao.conectar();
			pst = con.prepareStatement(lerbd);
			//rs = pst.executeQuery();
		
			
			pst.setString(1,txtNome.getText());
			pst.setString(2,txtFone.getText());
			pst.setString(3,txtLogin.getText());
			pst.setString(4,txtSenha.getText());
			pst.setString(5,cmbPerfil.getSelectedItem().toString());
			
			if ((( txtNome.getText().isEmpty()) || txtLogin.getText().isEmpty())
					|| txtSenha.getText().isEmpty()) {
				
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorio!");
			}else {
				int inserido = pst.executeUpdate();
				//a  linha abaixo ajuda na analise di codigo e entreterimento
				//sistem.out.print(inserido);
				if (inserido > 0){
					JOptionPane.showMessageDialog(null,"Cliente cadastrado cm sucesso!!!");
					
					txtNome.setText(null);
					txtFone.setText(null);
					txtLogin.setText(null);
					txtSenha.setText(null);
					//cmbPerfil.setSelectdItem(null);
					
				}
			}
					}catch (Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}
	}
	
	private void alterar() {
		String lerbd = "update tblusers set usuario=?, fone=?, login=?, senha=?, perfil=? where iduser=?";
		try {
			pst = con.prepareStatement(lerbd);
			pst.setString(1,txtNome.getText());
			pst.setString(2,txtFone.getText());
			pst.setString(3,txtLogin.getText());
			pst.setString(4,txtSenha.getText());
			pst.setString(5,cmbPerfil.getSelectedItem().toString());
			pst.setString(6,txtId.getText());

			if((((txtId.getText().isEmpty()) ||  txtNome.getText().isEmpty()) ||  txtLogin.getText().isEmpty()) ||  txtSenha.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorios!!!");
			}else {
				int inserido = pst.executeUpdate();
				//a linha abaixo ajuda na analise do codigo e entreterimento
				//sistem.out.println(inserido);
				if(inserido > 0) {
					JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!!!");
					txtNome.setText(null);
					txtFone.setText(null);
					txtLogin.setText(null);
					txtSenha.setText(null);
					//cmbPerfil.setSelectItem(null);
					
					//limpar_campos();]
					btnSalvar.setEnabled(true);
				}		
			}
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			
		}
	}

	
	private void excluir() {
		int confirma =JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o usuario","Atencao",JOptionPane.YES_NO_OPTION);
		if (confirma==JOptionPane.YES_OPTION) {
			String lerbd ="delete from tblusers where iduser=?";
			try {
				
				pst = con.prepareStatement(lerbd);
				pst.setString(1, txtId.getText());
				int excluido = pst.executeUpdate();
				if (excluido>0) {
					JOptionPane.showMessageDialog(null, "Cliente apagado com sucesso");
					txtNome.setText(null);
					txtFone.setText(null);
					txtLogin.setText(null);
					txtSenha.setText(null);					
					
				}
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);

				}
			}
		
	}
	private void consultar() {
		String lerbd = "select * from tblusers where iduser=?";
				try {
					con = dao.conectar();
					pst = con.prepareStatement(lerbd);
					pst.setString(1, txtId.getText());
					rs = pst.executeQuery();
					if(rs.next()) {
						txtNome.setText(rs.getString(2));
						txtFone.setText(rs.getString(3));
						txtLogin.setText(rs.getString(4));
						txtSenha.setText(rs.getString(5));
						cmbPerfil.setSelectedItem(rs.getString(6));
					}else {
						JOptionPane.showMessageDialog(null, "usuario inexistente");
						//as linhas abaixo limpa os campos
						txtNome.setText(null);
						txtFone.setText(null);
						txtLogin.setText(null);
						txtSenha.setText(null);
						//cmbPerfil.setSelectedItem(null);
					}
				}catch(Exception e) {
						JOptionPane.showMessageDialog(null, e);
				}
	}
	
}


