import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Statistic extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField text1;
	private JTextField text2;
	private JTextField text3;
	private JTextField text4;

	/**
	 * Launch the application.
	 */
			public void run() {
				try {
					Statistic frame = new Statistic();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	/**
	 * Create the frame.
	 */
	public Statistic() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 884, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(10, 11, 848, 464);
		contentPane.add(panel);
		
		JLabel lblStatistic1 = new JLabel("Statistic_1");
		lblStatistic1.setFont(new Font("Verdana", Font.BOLD, 11));
		lblStatistic1.setBounds(21, 506, 80, 14);
		contentPane.add(lblStatistic1);
		
		JLabel lblStatistic2 = new JLabel("Statistic_2");
		lblStatistic2.setFont(new Font("Verdana", Font.BOLD, 11));
		lblStatistic2.setBounds(127, 506, 80, 14);
		contentPane.add(lblStatistic2);
		
		JLabel lblStatistic3 = new JLabel("Statistic_3");
		lblStatistic3.setFont(new Font("Verdana", Font.BOLD, 11));
		lblStatistic3.setBounds(242, 506, 80, 14);
		contentPane.add(lblStatistic3);
		
		JLabel lblStatistic4 = new JLabel("Statistic_4");
		lblStatistic4.setFont(new Font("Verdana", Font.BOLD, 11));
		lblStatistic4.setBounds(355, 506, 80, 14);
		contentPane.add(lblStatistic4);
		
		text1 = new JTextField();
		text1.setFont(new Font("Verdana", Font.BOLD, 11));
		text1.setBounds(10, 531, 86, 20);
		contentPane.add(text1);
		text1.setColumns(10);
		
		text2 = new JTextField();
		text2.setFont(new Font("Verdana", Font.BOLD, 11));
		text2.setColumns(10);
		text2.setBounds(121, 531, 86, 20);
		contentPane.add(text2);
		
		text3 = new JTextField();
		text3.setFont(new Font("Verdana", Font.BOLD, 11));
		text3.setColumns(10);
		text3.setBounds(236, 531, 86, 20);
		contentPane.add(text3);
		
		text4 = new JTextField();
		text4.setFont(new Font("Verdana", Font.BOLD, 11));
		text4.setColumns(10);
		text4.setBounds(349, 531, 86, 20);
		contentPane.add(text4);
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int s1=Integer.parseInt(text1.getText());
				int s2=Integer.parseInt(text2.getText());
				int s3=Integer.parseInt(text3.getText());
				int s4=Integer.parseInt(text4.getText());
				
				DefaultCategoryDataset dataset=new DefaultCategoryDataset();
				dataset.setValue(s1,"","Statistic1");
				dataset.setValue(s2,"","Statistic2");
				dataset.setValue(s3,"","Statistic3");
				dataset.setValue(s4,"","Statistic4");
				
				JFreeChart chart = ChartFactory.createBarChart("Statistics", "", "", dataset,PlotOrientation.HORIZONTAL,false,false,false);
				CategoryPlot catPlot=chart.getCategoryPlot();
				catPlot.setRangeGridlinePaint(Color.BLACK);
				ChartPanel chartPanel= new ChartPanel(chart);
				panel.removeAll();
				panel.add(chartPanel,BorderLayout.CENTER);
				panel.validate();
				
			}
		});
		btnView.setFont(new Font("Verdana", Font.BOLD, 11));
		btnView.setBounds(459, 506, 112, 45);
		contentPane.add(btnView);
	}
}
