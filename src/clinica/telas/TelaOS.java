package clinica.telas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import clinica.dao.DAO;
import net.proteanit.sql.DbUtils;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;

public class TelaOS extends JInternalFrame {

	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	private static final long serialVersionUID = 1L;
	private String tipo;
	private JTextField txtOSExame;
	private JTextField txtOSServ;
	private JTextField txtOSMed;
	private JTextField txtOSTrat;
	private JTextField txtOSVal;
	private JRadioButton rbtOs;
	private JRadioButton rbtOrca;

	private JTable tblPaciente;
	private JTextField txtPacipesquisar;
	private JTextField txtPaciId;
	private JTextField txtOs;
	private JTextField txtData;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JButton btnOsSalvar;
	private JButton btnOsAtualizar;
	private JButton btnOsPesquisar;
	private JButton btnOsDeletar;
	private JButton btnOsPrint;
	private JComboBox cboOsSit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaOS frame = new TelaOS();
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
	public TelaOS() {
		con = dao.conectar();
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				//rbtOrca.setSelected(true);
			}
		});
		setTitle("os");
		con = dao.conectar();
		setMaximizable(true);
		setClosable(true);
		con = dao.conectar();
		setBounds(100, 100, 723, 506);
		getContentPane().setLayout(null);

		cboOsSit = new JComboBox();
		cboOsSit.setModel(new DefaultComboBoxModel(new String[] { "em andamento..." }));
		cboOsSit.setBounds(123, 127, 127, 18);
		getContentPane().add(cboOsSit);

		txtOSExame = new JTextField();
		txtOSExame.setBounds(123, 188, 475, 19);
		getContentPane().add(txtOSExame);
		txtOSExame.setColumns(10);

		txtOSServ = new JTextField();
		txtOSServ.setBounds(123, 280, 475, 19);
		getContentPane().add(txtOSServ);
		txtOSServ.setColumns(10);

		txtOSMed = new JTextField();
		txtOSMed.setBounds(123, 331, 189, 19);
		getContentPane().add(txtOSMed);
		txtOSMed.setColumns(10);

		txtOSTrat = new JTextField();
		txtOSTrat.setBounds(123, 238, 475, 19);
		getContentPane().add(txtOSTrat);
		txtOSTrat.setColumns(10);

		txtOSVal = new JTextField();
		txtOSVal.setBounds(421, 331, 177, 19);
		getContentPane().add(txtOSVal);
		txtOSVal.setColumns(10);

		tblPaciente = new JTable();
		tblPaciente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				preencher_campos();
			}
		});
		tblPaciente.setBounds(288, 53, 403, 65);
		getContentPane().add(tblPaciente);

		txtPacipesquisar = new JTextField();
		txtPacipesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisar_clientes();
			}
		});
		txtPacipesquisar.setColumns(10);
		txtPacipesquisar.setBounds(288, 23, 206, 20);
		getContentPane().add(txtPacipesquisar);

		txtPaciId = new JTextField();
		txtPaciId.setEditable(false);
		txtPaciId.setColumns(10);
		txtPaciId.setBounds(619, 21, 70, 22);
		getContentPane().add(txtPaciId);

		JLabel lblNewLabel_6 = new JLabel("ID");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(565, 23, 44, 20);
		getContentPane().add(lblNewLabel_6);

		btnOsSalvar = new JButton("");
		btnOsSalvar.setToolTipText("salvar");
		btnOsSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gerar_os();
			}
		});
		btnOsSalvar.setBounds(137, 394, 65, 53);
		getContentPane().add(btnOsSalvar);

		 btnOsAtualizar = new JButton("");
		btnOsAtualizar.setToolTipText("atualizar");
		btnOsAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizar_os();
			}
		});
		btnOsAtualizar.setBounds(247, 394, 65, 53);
		getContentPane().add(btnOsAtualizar);

		 btnOsDeletar = new JButton("");
		btnOsDeletar.setToolTipText("deletar");
		btnOsDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletar_os();
			}
		});
		btnOsDeletar.setBounds(346, 394, 65, 53);
		getContentPane().add(btnOsDeletar);

		 btnOsPesquisar = new JButton("");
		btnOsPesquisar.setToolTipText("pesquisar");
		btnOsPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar_os();
			}
		});
		btnOsPesquisar.setBounds(462, 394, 65, 53);
		getContentPane().add(btnOsPesquisar);

		 btnOsPrint = new JButton("");
		btnOsPrint.setToolTipText("print");
		btnOsPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnOsPrint.setBounds(558, 394, 65, 53);
		getContentPane().add(btnOsPrint);

		txtOs = new JTextField();
		txtOs.setEditable(false);
		txtOs.setColumns(10);
		txtOs.setBounds(10, 23, 86, 19);
		getContentPane().add(txtOs);

		txtData = new JTextField();
		txtData.setEditable(false);
		txtData.setColumns(10);
		txtData.setBounds(106, 21, 96, 19);
		getContentPane().add(txtData);

		 rbtOrca = new JRadioButton("Orçamento");
		rbtOrca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// atribuindo um texto a variavel
				tipo = "Orçamento";
			}
		});
		rbtOrca.setBounds(10, 68, 86, 25);
		getContentPane().add(rbtOrca);

		 rbtOs = new JRadioButton("Ordem de Serviço");
		rbtOs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "OS";
			}
		});
		rbtOs.setBounds(106, 70, 114, 23);
		getContentPane().add(rbtOs);

		lblNewLabel = new JLabel("exame");
		lblNewLabel.setBounds(51, 191, 45, 13);
		getContentPane().add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Tratamento");
		lblNewLabel_1.setBounds(51, 241, 45, 13);
		getContentPane().add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Serviços");
		lblNewLabel_2.setBounds(51, 283, 45, 13);
		getContentPane().add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Medico");
		lblNewLabel_3.setBounds(51, 334, 45, 13);
		getContentPane().add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("Valor");
		lblNewLabel_4.setBounds(366, 334, 45, 13);
		getContentPane().add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("Situação");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(37, 128, 58, 14);
		getContentPane().add(lblNewLabel_5);

	}

	private void pesquisar_clientes() {

		String lerbd = "select idpaci as Id, nomepaci as Nome, fonepaci as Fone from tblpacientes where nomepaci like ?";

		try {

			pst = con.prepareStatement(lerbd);

			pst.setString(1, txtPacipesquisar.getText() + "%");

			rs = pst.executeQuery();

			tblPaciente.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);

		}

	}

	private void preencher_campos() {

		int preencher = tblPaciente.getSelectedRow();

		txtPaciId.setText(tblPaciente.getModel().getValueAt(preencher, 0).toString());

	}

	private void gerar_os() {

		String lerbd = "insert into tblos( tipo, situacao, tratamento, servico, medico, valor, idpaci) values ( ?, ?, ?, ?, ?, ?, ?)";

		try {

			pst = con.prepareStatement(lerbd);

			pst.setString(1, tipo);

			pst.setString(2, cboOsSit.getSelectedItem().toString());

			pst.setString(3, txtOSTrat.getText());

			pst.setString(4, txtOSServ.getText());

			pst.setString(5, txtOSMed.getText());

			pst.setString(6, txtOSVal.getText());

			pst.setString(7, txtPaciId.getText().replace(",", "."));

			if (((txtPaciId.getText().isEmpty()) || (txtOSExame.getText().isEmpty()) || txtOSTrat.getText().isEmpty())
					|| cboOsSit.getSelectedItem().equals(" ")) {

				JOptionPane.showMessageDialog(null, "Preencha todos os campos!!!");

			} else {

				int inserido = pst.executeUpdate();

				if (inserido > 0) {

					JOptionPane.showMessageDialog(null, "Ordem de serviço aberta cm sucesso!!!");

					btnOsSalvar.setEnabled(true);

					btnOsAtualizar.setEnabled(false);

					btnOsDeletar.setEnabled(false);

					btnOsPesquisar.setEnabled(false);

					btnOsPrint.setEnabled(true);

				}

			}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);

		}

	}

	private void pesquisar_os() {

		String num_os = JOptionPane.showInputDialog("Numero da OS");

		String lerbd = "select * from tblos where os= " + num_os;

		try {

			pst = con.prepareStatement(lerbd);

			rs = pst.executeQuery();

			if (rs.next()) {

				txtOs.setText(rs.getString(1));

				txtData.setText(rs.getString(2));

				// setando os radio button

				String tipo = rs.getString(3);

				if (tipo.equals("OS")) {

					rbtOs.setSelected(true);

					tipo = "OS";

				} else {

					rbtOrca.setSelected(true);

					tipo = "Orçamento";

				}

				cboOsSit.setSelectedItem(rs.getString(4));

				txtOSTrat.setText(rs.getString(5));

				txtOSServ.setText(rs.getString(6));

				txtOSMed.setText(rs.getString(7));

				txtOSVal.setText(rs.getString(8));

				txtPaciId.setText(rs.getString(9));

				// desativando botoes nn ultilizados

				btnOsSalvar.setEnabled(false);

				btnOsPesquisar.setEnabled(false);

				txtPacipesquisar.setEnabled(false);

				tblPaciente.setVisible(false);

				// ativando outros botoes

				btnOsSalvar.setEnabled(true);

				btnOsDeletar.setEnabled(true);

				btnOsPrint.setEnabled(true);

			} else {

				JOptionPane.showMessageDialog( null, "Os nn localizados!!!");

			}

		} catch (java.sql.SQLSyntaxErrorException e) {

			JOptionPane.showMessageDialog(null, "OS Incorreta!!!");

			// System.out.println(e);

		} catch (Exception e2) {

			JOptionPane.showMessageDialog(null, e2);

		}

	}

	private void atualizar_os() {

		String lerbd = "update tblos set  tipo=?, situacao=?, tratamento=?, servico=?, medico=?, valor=?  where os=?";

		try {

			pst = con.prepareStatement(lerbd);

			pst.setString(1, tipo);

			pst.setString(2, cboOsSit.getSelectedItem().toString());

			pst.setString(3, txtOSTrat.getText());

			pst.setString(4, txtOSServ.getText());

			pst.setString(5, txtOSMed.getText());

			pst.setString(6, txtOSVal.getText());

			pst.setString(7, txtPaciId.getText().replace(",", "."));

			if ((((txtPaciId.getText().isEmpty()) || txtOSExame.getText().isEmpty()) || txtOSTrat.getText().isEmpty())
					|| cboOsSit.getSelectedItem().equals(" ")) {

				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorios!!!");

			} else {

				int inserido = pst.executeUpdate();

				// a linha abaixo ajuda na analise do codigo e entreterimento

				// sistem.out.println(inserido);

				if (inserido > 0) {

					JOptionPane.showMessageDialog(null, "Ordem de Serviço alterada cm sucesso");

					limpar_campos();

				}

			}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);

		}

	}

	private void deletar_os() {

		int confirma = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir a ordem de serviço?!", "Atencao",
				JOptionPane.YES_NO_OPTION);

		if (confirma == JOptionPane.YES_OPTION) {

			String lerbd = "delete from tblos where os=?";

			try {

				pst = con.prepareStatement(lerbd);

				pst.setString(1, txtOs.getText());

				int deleta = pst.executeUpdate();

				if (deleta > 0) {

					JOptionPane.showMessageDialog(null, "OS apagado com sucesso");

					limpar_campos();

				}

			} catch (Exception e) {

				JOptionPane.showMessageDialog(null, e);

			}

		}

	}

	private void limpar_campos() {

		((DefaultTableModel) tblPaciente.getModel()).setRowCount(0);

		cboOsSit.setSelectedItem(" ");

		btnOsPesquisar.setText(null);

		txtOs.setText(null);

		txtData.setText(null);

		txtPaciId.setText(null);

		txtOSExame.setText(null);

		txtOSTrat.setText(null);

		txtOSServ.setText(null);

		txtOSMed.setText(null);

		txtOSVal.setText(null);

		btnOsSalvar.setEnabled(true);

		txtPacipesquisar.setEnabled(true);

		tblPaciente.setVisible(true);

		btnOsAtualizar.setEnabled(false);

		btnOsDeletar.setEnabled(false);

		btnOsPrint.setEnabled(false);

	}
}