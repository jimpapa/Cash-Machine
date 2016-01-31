import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.Statement;

import net.proteanit.sql.DbUtils;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class face extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JComboBox<String> comboBoxName;
	/**
	 * Launch the application.
	 */
	
	public Connection connect()
	 {
		 Connection connection = null;
		    try {
		        // Load the JDBC driver
		        String driverName = "com.mysql.jdbc.Driver"; // MySQL MM JDBC driver
		        Class.forName(driverName);
		    
		        // Create a connection to the database

		        String url = "jdbc:mysql://localhost/user";
		        String username = "root";
		        String password = "1234";
		        connection = DriverManager.getConnection(url, username, password);
		    } catch (ClassNotFoundException e) {
		        // Could not find the database driver
		    } catch (SQLException e) {
		        // Could not connect to the database
		    }
		    return connection;
	 }
	 
	public ResultSet executeQuery(String query, Connection connection)
	 {
		 if (connect()!=null)
		 {
			 try {
				Statement statement = (Statement) connection.createStatement();
				statement.execute(query);
				return statement.getResultSet();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 
		 return null;
	 }
	
	 public void disconnect(Connection connection)
	 {
		 if (connection !=null)
		 {
			 try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	 }
	
			public ResultSet run() {
				try {
					face frame = new face();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}

	Connection connection=null;
	private JTextField textFieldSearch;
	private JLabel lbltickets;

	public ResultSet refreshTable()
	{
		try{
			String query="select * from tickets";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs =pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		return null;
	}
	
	public ResultSet fillComboBox()
	{
		try{
			String query="select * from tickets";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{
				comboBoxName.addItem(rs.getString("name"));
			}
		
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		return null;
	}
	
	
	public face() throws SQLException {
		
		connection=connect();
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 788, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btntickets = new JButton("Show tickets");
		btntickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				String query="select * from tickets";
				PreparedStatement pst=connection.prepareStatement(query);
				ResultSet rs =pst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				pst.close();
				rs.close();
				}catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		btntickets.setFont(new Font("Verdana", Font.BOLD, 16));
		btntickets.setBounds(260, 293, 160, 49);
		contentPane.add(btntickets);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(193, 11, 302, 271);
		contentPane.add(scrollPane);
		
		final JLabel Id = new JLabel("");
		Id.setFont(new Font("Verdana", Font.BOLD, 16));
		Id.setBounds(611, 11, 75, 33);
		contentPane.add(Id);
		
		final JLabel Name = new JLabel("");
		Name.setFont(new Font("Verdana", Font.BOLD, 16));
		Name.setBounds(611, 48, 75, 33);
		contentPane.add(Name);
		
		final JLabel Cost = new JLabel("");
		Cost.setFont(new Font("Verdana", Font.BOLD, 16));
		Cost.setBounds(611, 83, 75, 33);
		contentPane.add(Cost);
		
		final JLabel Row = new JLabel("");
		Row.setFont(new Font("Verdana", Font.BOLD, 16));
		Row.setBounds(611, 116, 75, 33);
		contentPane.add(Row);
		
		final JLabel Tickets = new JLabel("");
		Tickets.setFont(new Font("Verdana", Font.BOLD, 16));
		Tickets.setBounds(611, 149, 75, 33);
		contentPane.add(Tickets);
		
		final JButton Print = new JButton("Print");
		Print.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Document document=new Document();
				try {
					PdfWriter.getInstance(document, new FileOutputStream("Eishthrio.pdf"));
				} catch (FileNotFoundException | DocumentException e1) {
					e1.printStackTrace();
				}
				document.open();
				try {
					document.add(new Paragraph("Concert Ticket:"));
					document.add(new Paragraph("Id="));
					document.add(new Paragraph(Id.getText().toString()));
					document.add(new Paragraph("Name="));
					document.add(new Paragraph(Name.getText().toString()));
					document.add(new Paragraph("Cost="));
					document.add(new Paragraph(Cost.getText().toString()));
					document.add(new Paragraph("Row="));
					document.add(new Paragraph(Row.getText().toString()));
				} catch (DocumentException e1) {
					e1.printStackTrace();
				}
				document.close();
				try{
					String query="update tickets set id='"+Id.getText()+"',name='"+Name.getText()+"',cost='"+Cost.getText()+"',row='"+Row.getText()+"',tickets='"+Tickets.getText()+"'-1 where id='"+Id.getText()+"' ";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null,"Printing");
					pst.close();
					}catch(Exception e2)
					{
						e2.printStackTrace();
					}
				try{
					String query="delete from tickets where tickets=0" ;
					PreparedStatement pst=connection.prepareStatement(query);
					pst.execute();
					pst.close();
					}catch(Exception e3)
					{
						e3.printStackTrace();
					}
				refreshTable();
				
				
				Print.setVisible(false);
			}
		});
		Print.setFont(new Font("Verdana", Font.BOLD, 16));
		Print.setBounds(26, 189, 137, 93);
		contentPane.add(Print);
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				try{
					Print.setVisible(true);
					int thesh =table.getSelectedRow();
					String Id1=(table.getModel().getValueAt(thesh, 0).toString());
					String query="select * from tickets where Id='"+Id1+"'";
					PreparedStatement pst=connection.prepareStatement(query);
					
					ResultSet rs=pst.executeQuery();
					while(rs.next())
					{
						Id.setText(rs.getString("id"));
						Name.setText(rs.getString("name"));
						Cost.setText(rs.getString("cost"));
						Row.setText(rs.getString("row"));
						Tickets.setText(rs.getString("tickets"));
					}
					
					pst.close();
					}catch(Exception ex)
					{
						ex.printStackTrace();
					}
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblid = new JLabel("Id:");
		lblid.setFont(new Font("Verdana", Font.BOLD, 16));
		lblid.setBounds(505, 11, 75, 33);
		contentPane.add(lblid);
		
		JLabel lblname = new JLabel("Name:");
		lblname.setFont(new Font("Verdana", Font.BOLD, 16));
		lblname.setBounds(505, 48, 75, 33);
		contentPane.add(lblname);
		
		JLabel lblcost = new JLabel("Cost:");
		lblcost.setFont(new Font("Verdana", Font.BOLD, 16));
		lblcost.setBounds(505, 83, 75, 33);
		contentPane.add(lblcost);
		
		JLabel lblrow = new JLabel("Row:");
		lblrow.setFont(new Font("Verdana", Font.BOLD, 16));
		lblrow.setBounds(505, 116, 75, 33);
		contentPane.add(lblrow);
		
		lbltickets = new JLabel("Tickets:");
		lbltickets.setFont(new Font("Verdana", Font.BOLD, 16));
		lbltickets.setBounds(505, 149, 96, 33);
		contentPane.add(lbltickets);
		
	    comboBoxName = new JComboBox<String>();
	    comboBoxName.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		try{		
					String query="select * from tickets where name=?";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1,(String)comboBoxName.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					while(rs.next())
					{
						Id.setText(rs.getString("id"));
						Name.setText(rs.getString("name"));
						Cost.setText(rs.getString("cost"));
						Row.setText(rs.getString("row"));
						Tickets.setText(rs.getString("tickets"));
					}
					pst.close();
					}catch(Exception ex)
					{
						ex.printStackTrace();
					}
	    	}
	    });
		comboBoxName.setFont(new Font("Verdana", Font.BOLD, 16));
		comboBoxName.setBounds(26, 37, 137, 33);
		contentPane.add(comboBoxName);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try{
					String query="select * from tickets where name=?";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1,textFieldSearch.getText());
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));					
					pst.close();
					}catch(Exception ex)
					{
						ex.printStackTrace();
					}
			}
		});
		textFieldSearch.setFont(new Font("Verdana", Font.BOLD, 16));
		textFieldSearch.setBounds(26, 103, 137, 33);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		refreshTable();
		fillComboBox();
	}
}
