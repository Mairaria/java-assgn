package student;

import java.awt.EventQueue;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import utilities.DbConnection;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;

public class StudentDetails extends JFrame {

	private JPanel contentPane;
	private JTable tblStudentDetails;
	static StudentDetails frame = new StudentDetails();
	Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
	public StudentDetails() {
		initialize();
		connect();
		Student.displayDetails(tblStudentDetails);
		
	}
	void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 842, 409);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(105, 105, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tblStudentDetails = new JTable();
		tblStudentDetails.setBackground(Color.LIGHT_GRAY);
		JScrollPane tbl = new JScrollPane(tblStudentDetails);
		tbl.setBackground(new Color(135, 206, 235));
		tbl.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		tbl.setBounds(10, 40, 806, 319);
		contentPane.add(tbl);
	
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(727, 11, 89, 23);
		contentPane.add(btnRefresh);
	}
	Connection connect() {
		return con = DbConnection.connect();
	}
}
