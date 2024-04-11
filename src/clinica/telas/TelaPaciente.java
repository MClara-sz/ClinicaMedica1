package clinica.telas;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import clinica.dao.DAO;
import net.proteanit.sql.DbUtils;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaPaciente extends JInternalFrame {
	
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	private static final long serialVersionUID = 1L;

	private JTable tabela;
	private JTextField txtCliId;
	private JTextField txtCliNome;
	private JTextField txtCliFone;
	private JTextField txtPaciEnd;
	private JTextField txtCliMail;
	private JButton btnSalvar;
	private JTextField txtPacipesquisar;
	private JTextField txtClipesquisar;
	private JTextField txtCliEnd;
	private JButton btnPesquisar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPaciente frame = new TelaPaciente();
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
	public TelaPaciente() {
		setTitle("paciente");
		con = dao.conectar();

		setMaximizable(true);
		setClosable(true);

		setBounds(100, 100, 572, 398);
		getContentPane().setLayout(null);
		
		txtPacipesquisar = new JTextField();
		txtPacipesquisar.addKeyListener(new KeyAdapter() {
			@Override
			//key released serve para:
			public void keyReleased(KeyEvent e) {
				buscar_clientes();

			}
		});
				
		tabela = new JTable();
		tabela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				inserir_campos();

			}
		});
		tabela.setBounds(41, 29, 463, 112);
		getContentPane().add(tabela);
		
		txtCliId = new JTextField();
		txtCliId.setEnabled(false);
		txtCliId.setColumns(10);
		txtCliId.setBounds(98, 152, 96, 20);
		getContentPane().add(txtCliId);
		
		txtCliNome = new JTextField();
		txtCliNome.setColumns(10);
		txtCliNome.setBounds(98, 182, 348, 20);
		getContentPane().add(txtCliNome);
		
		txtCliEnd = new JTextField();
		txtCliEnd.setColumns(10);
		txtCliEnd.setBounds(98, 212, 348, 20);
		getContentPane().add(txtCliEnd);
		
		txtCliFone = new JTextField();
		txtCliFone.setColumns(10);
		txtCliFone.setBounds(98, 242, 348, 20);
		getContentPane().add(txtCliFone);
		
		txtCliMail = new JTextField();
		txtCliMail.setColumns(10);
		txtCliMail.setBounds(98, 272, 348, 20);
		getContentPane().add(txtCliMail);
		
		btnSalvar = new JButton("");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();

			}
		});
		btnSalvar.setToolTipText("Salvar");
		btnSalvar.setBounds(125, 310, 69, 56);
		getContentPane().add(btnSalvar);
		
		JButton btnAtualizar = new JButton("");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterar();

			}
		});
		btnAtualizar.setToolTipText("Atualizar");
		btnAtualizar.setBounds(234, 310, 69, 56);
		getContentPane().add(btnAtualizar);
		
		JButton btnDeletar = new JButton("");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();

			}
		});
		btnDeletar.setToolTipText("Deletar");
		btnDeletar.setBounds(343, 310, 69, 56);
		getContentPane().add(btnDeletar);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblId.setBounds(47, 152, 62, 20);
		getContentPane().add(lblId);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNome.setBounds(31, 185, 49, 14);
		getContentPane().add(lblNome);
		
		JLabel lblEnd = new JLabel("Endereço");
		lblEnd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEnd.setBounds(14, 215, 68, 14);
		getContentPane().add(lblEnd);
		
		JLabel lblTel = new JLabel("Telefone");
		lblTel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTel.setBounds(24, 245, 68, 14);
		getContentPane().add(lblTel);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(31, 275, 49, 14);
		getContentPane().add(lblEmail);
		
		txtClipesquisar = new JTextField();
		txtClipesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				buscar_clientes();
			}
		});
		txtClipesquisar.setBounds(76, 0, 376, 19);
		getContentPane().add(txtClipesquisar);
		txtClipesquisar.setColumns(10);
		
		btnPesquisar = new JButton("New button");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar_clientes();

			}
		});
		btnPesquisar.setBounds(0, -1, 85, 21);
		getContentPane().add(btnPesquisar);

	}
	
	
	
	private void salvar() {
		String lerbd ="insert into tblpacientes (nomepaci, endpaci, fonepaci, emailpaci) values (?,?,?,?)";
		try {
			//con = dao.conectar();
			pst = con.prepareStatement(lerbd);
			//rs = pst.executeQuery();
			pst.setString(1,txtCliNome.getText());
			pst.setString(2,txtCliEnd.getText());
			pst.setString(3,txtCliFone.getText());
			pst.setString(4,txtCliMail.getText());
			if ((txtCliNome.getText().isEmpty()) || txtCliFone.getText().isEmpty()) {
				
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorio!");
			}else {
				int inserido = pst.executeUpdate();
				//a  linha abaixo ajuda na analise di codigo e entreterimento
				//sistem.out.print(inserido);
				if (inserido > 0){
					JOptionPane.showMessageDialog(null,"Cliente cadastrado cm sucesso!!!");
					
				limpar_campos();	
				}
			}
					}catch (Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}
	}
			private void buscar_clientes() {
				String lerbd = "select * from tblpacientes where nomepaci like ?";
				try {
					//con = dao.conectar();
					pst = con.prepareStatement(lerbd);
					pst.setString(1,txtClipesquisar.getText() + "%");
					rs = pst.executeQuery();
					//a linha abaixo usa a a biblioteca pra preencher a tabela
					tabela.setModel(DbUtils.resultSetToTableModel(rs));
					
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
			
			//metodo p preenche os cmapos do form cm o conteudo da tabela
			private void inserir_campos() {
				int inserir = tabela.getSelectedRow();
				txtCliId.setText(tabela.getModel().getValueAt(inserir, 0).toString());
				txtCliNome.setText(tabela.getModel().getValueAt(inserir, 1).toString());
				txtCliEnd.setText(tabela.getModel().getValueAt(inserir, 2).toString());
				txtCliFone.setText(tabela.getModel().getValueAt(inserir, 3).toString());
				txtCliMail.setText(tabela.getModel().getValueAt(inserir, 4).toString());
				
				
				btnSalvar.setEnabled(false);
			}
			
			
			private void alterar() {
				String lerbd = "update tblpacientes set nomepaci=?, endpaci=?,fonepaci=?, emailpaci=? where idpaci=?";
				try {
					pst = con.prepareStatement(lerbd);
					pst.setString(1,txtCliNome.getText());
					pst.setString(2,txtCliEnd.getText());
					pst.setString(3,txtCliFone.getText());
					pst.setString(4,txtCliMail.getText());
					pst.setString(5,txtCliId.getText());
					
					if(((txtCliNome.getText().isEmpty()) ||  txtCliFone.getText().isEmpty()) ) {
						JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorios!!!");
					}else {
						int inserido = pst.executeUpdate();
						//a linha abaixo ajuda na analise do codigo e entreterimento
						//sistem.out.println(inserido);
						if(inserido > 0) {
							JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!!!");
							
							//limpar_campos();
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
						String lerbd ="delete from tblpacientes where idpaci=?";
						try {
							
							pst = con.prepareStatement(lerbd);
							pst.setString(1, txtCliId.getText());
							int excluido = pst.executeUpdate();
							if (excluido>0) {
								JOptionPane.showMessageDialog(null, "Cliente apagado com sucesso");
								
								
							}else {
								JOptionPane.showMessageDialog(null,"usuario ou senha incorreto");
							}
								limpar_campos();
							}catch (Exception e) {
								JOptionPane.showMessageDialog(null, e);
		
							}
						}
				
				}
				
				
				private void limpar_campos() {
					txtClipesquisar.setText(null);
					txtCliId.setText(null);
					txtCliNome.setText(null);
					txtCliFone.setText(null);
					txtCliEnd.setText(null);
					txtCliMail.setText(null);
					//((DefaultTableModel) tabela.getModel())setRowCount(0);
				}
}
