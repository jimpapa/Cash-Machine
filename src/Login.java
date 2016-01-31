
import java.awt.EventQueue;

import java.awt.Image;

import javax.swing.ImageIcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	
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

public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
			try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
				e.printStackTrace();
				}
			}
		});
       }
	

	Connection connection=null;
	private JTextField textFieldUN;
	private JPasswordField passwordField;
	private JLabel label;
	
	public Login() throws SQLException  {
		
		connection=connect();
		
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 337);
	    contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UserName");
		lblNewLabel.setBounds(193, 62, 84, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel1 = new JLabel("Password");
		lblNewLabel1.setBounds(193, 112, 84, 34);
		contentPane.add(lblNewLabel1);
		
		textField = new JTextField();
		textField.setBounds(183, 62, 69, 0);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textFieldUN = new JTextField();
		textFieldUN.setBounds(302, 66, 151, 27);
		contentPane.add(textFieldUN);
		textFieldUN.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		Image img = new ImageIcon(this.getClass().getResource("/ok.png")).getImage();
		btnLogin.setIcon(new ImageIcon(img));
		btnLogin.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query="select * from user where name=? and password=?";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1,textFieldUN.getText());
				    pst.setString(2,passwordField.getText());
					ResultSet rs=pst.executeQuery();
					int count=0;
					while(rs.next()){
						count=count+1;
						}
					if(count==1)
					{
						JOptionPane.showMessageDialog(null,"Welcome admin");
						contentPane.setVisible(false);
						adminface admface = new adminface();
						admface.setVisible(true);
					}
					else if(count>1){
						JOptionPane.showMessageDialog(null,"Welcome tamia");
						contentPane.setVisible(false);
						face face = new face();
						face.setVisible(true);
					}
					
					else{
						JOptionPane.showMessageDialog(null,"Username and Password is not correct Try Again...");
					}
					rs.close();
					pst.close();
				     
				}catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		btnLogin.setBounds(264, 176, 169, 56);
		contentPane.add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setBounds(302, 112, 151, 27);
		contentPane.add(passwordField);
		
		label = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/login.png")).getImage();
		label.setIcon(new ImageIcon(img1));
		label.setBounds(21, 11, 162, 211);
		contentPane.add(label);

	}
}
