import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Statement;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class adminface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JComboBox<String> comboBoxName;
	/**
	 * Launch the application.
	 **/
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
					adminface frame = new adminface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
	
	Connection connection=null;
    
	
	private JTextField textFieldId;
	private JTextField textFieldName;
	private JTextField textFieldCost;
	private JTextField textFieldRow;
	private JTextField textFieldSearch;
	private JTextField textFieldTickets;
	
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
	
	
	public adminface() throws SQLException {
		
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
		scrollPane.setBounds(185, 11, 310, 271);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					int thesh =table.getSelectedRow();
					String Id1=(table.getModel().getValueAt(thesh, 0).toString());
					String query="select * from tickets where Id='"+Id1+"'";
					PreparedStatement pst=connection.prepareStatement(query);
					
					ResultSet rs=pst.executeQuery();
					while(rs.next())
					{
						textFieldId.setText(rs.getString("id"));
						textFieldName.setText(rs.getString("name"));
						textFieldCost.setText(rs.getString("cost"));
						textFieldRow.setText(rs.getString("row"));
						textFieldTickets.setText(rs.getString("tickets"));
					}
				
					
					pst.close();
					}catch(Exception ex)
					{
						ex.printStackTrace();
					}
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblid = new JLabel("Id");
		lblid.setFont(new Font("Verdana", Font.BOLD, 16));
		lblid.setBounds(505, 11, 75, 33);
		contentPane.add(lblid);
		
		JLabel lblname = new JLabel("Name");
		lblname.setFont(new Font("Verdana", Font.BOLD, 16));
		lblname.setBounds(505, 42, 75, 33);
		contentPane.add(lblname);
		
		JLabel lblcost = new JLabel("Cost");
		lblcost.setFont(new Font("Verdana", Font.BOLD, 16));
		lblcost.setBounds(505, 73, 75, 33);
		contentPane.add(lblcost);
		
		JLabel lblrow = new JLabel("Row");
		lblrow.setFont(new Font("Verdana", Font.BOLD, 16));
		lblrow.setBounds(505, 104, 75, 33);
		contentPane.add(lblrow);
		
		JLabel lbltickets = new JLabel("Tickets");
		lbltickets.setFont(new Font("Verdana", Font.BOLD, 16));
		lbltickets.setBounds(505, 137, 86, 33);
		contentPane.add(lbltickets);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(616, 20, 86, 20);
		contentPane.add(textFieldId);
		textFieldId.setColumns(10);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(616, 51, 86, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldCost = new JTextField();
		textFieldCost.setBounds(616, 82, 86, 20);
		contentPane.add(textFieldCost);
		textFieldCost.setColumns(10);
		
		textFieldRow = new JTextField();
		textFieldRow.setBounds(616, 113, 86, 20);
		contentPane.add(textFieldRow);
		textFieldRow.setColumns(10);
		
		textFieldTickets = new JTextField();
		textFieldTickets.setColumns(10);
		textFieldTickets.setBounds(616, 144, 86, 20);
		contentPane.add(textFieldTickets);
		
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query="insert into tickets (id,name,cost,row,tickets) values(?,?,?,?,?)";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, textFieldId.getText());
					pst.setString(2, textFieldName.getText());
					pst.setString(3, textFieldCost.getText());
					pst.setString(4, textFieldRow.getText());
					pst.setString(5, textFieldTickets.getText());
					pst.execute();
					JOptionPane.showMessageDialog(null,"Data Saved");
					pst.close();
					}catch (Exception e) 
					{
						e.printStackTrace();
					}
				refreshTable();
			}
		});
		btnSave.setFont(new Font("Verdana", Font.BOLD, 16));
		btnSave.setBounds(522, 228, 160, 33);
		contentPane.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					
					String query="update tickets set id='"+textFieldId.getText()+"',name='"+textFieldName.getText()+"',cost='"+textFieldCost.getText()+"',row='"+textFieldRow.getText()+"',tickets='"+textFieldTickets.getText()+"' where id='"+textFieldId.getText()+"' ";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null,"Data Updated");
					pst.close();
					}catch(Exception e)
					{
						e.printStackTrace();
					}
				refreshTable();
			}
		});
		btnUpdate.setFont(new Font("Verdana", Font.BOLD, 16));
		btnUpdate.setBounds(522, 272, 160, 33);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int action=JOptionPane.showConfirmDialog(null, "Do you really want to delete","Delete",JOptionPane.YES_NO_CANCEL_OPTION);
				if (action==0)
				{	
				try{
					String query="delete from tickets where id='"+textFieldId.getText()+"'";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null,"Data Deleted");
					pst.close();
					}catch(Exception e)
					{
						e.printStackTrace();
					}
				refreshTable();
			   }
			}
		});
		btnDelete.setFont(new Font("Verdana", Font.BOLD, 16));
		btnDelete.setBounds(522, 316, 160, 33);
		contentPane.add(btnDelete);
		
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
						textFieldId.setText(rs.getString("id"));
						textFieldName.setText(rs.getString("name"));
						textFieldCost.setText(rs.getString("cost"));
						textFieldRow.setText(rs.getString("row"));
						textFieldTickets.setText(rs.getString("tickets"));
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
		textFieldSearch.setColumns(10);
		textFieldSearch.setBounds(26, 125, 137, 33);
		contentPane.add(textFieldSearch);
		
		JButton btnStatistic = new JButton("Statistic");
		btnStatistic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Statistic stat = new Statistic();
				stat.setVisible(true);
			}
		});
		btnStatistic.setFont(new Font("Verdana", Font.BOLD, 16));
		btnStatistic.setBounds(26, 236, 137, 106);
		contentPane.add(btnStatistic);
		
		refreshTable();
		fillComboBox();
	}
}
