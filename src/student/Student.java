package student;

import java.awt.EventQueue;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.DateFormatter;

import net.proteanit.sql.DbUtils;
import utilities.DbConnection;
import utilities.Resources;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;

public class Student extends JFrame {

	private JPanel contentPane;
	private static JTextField txtStdID;
	private static JTextField txtStdName;
	private static JTextField txtStdForm;
	private static JTextField txtStream;
	private static JTextField txtDOB;
	private static Connection con ;
	static DateFormat df = new SimpleDateFormat("MM/dd/YYYY");
	static DateFormatter formatter = new DateFormatter(df);
	static JFormattedTextField txtDate =new JFormattedTextField(formatter);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student frame = new Student();
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
	public Student() {
		initialize();
		Connect();
	}
	void initialize(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 230);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student ID :");
		lblNewLabel.setBounds(10, 11, 97, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Student Name :");
		lblNewLabel_1.setBounds(10, 52, 97, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblForm = new JLabel("Form:");
		lblForm.setBounds(10, 93, 97, 14);
		contentPane.add(lblForm);
		
		JLabel lblNewLabel_2 = new JLabel("Stream:");
		lblNewLabel_2.setBounds(290, 11, 82, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		lblDateOfBirth.setBounds(290, 52, 97, 14);
		contentPane.add(lblDateOfBirth);
		
		txtStdID = new JTextField();
		txtStdID.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new EtchedBorder(EtchedBorder.LOWERED, null, null)));
		txtStdID.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtStdID.setBounds(97, 8, 173, 20);
		contentPane.add(txtStdID);
		txtStdID.setColumns(10);
		
		txtStdName = new JTextField();
		txtStdName.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new EtchedBorder(EtchedBorder.LOWERED, null, null)));
		txtStdName.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtStdName.setColumns(10);
		txtStdName.setBounds(97, 49, 173, 20);
		contentPane.add(txtStdName);
		
		txtStdForm = new JTextField();
		txtStdForm.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new EtchedBorder(EtchedBorder.LOWERED, null, null)));
		txtStdForm.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtStdForm.setColumns(10);
		txtStdForm.setBounds(97, 90, 86, 20);
		contentPane.add(txtStdForm);
		
		txtStream = new JTextField();
		txtStream.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new EtchedBorder(EtchedBorder.LOWERED, null, null)));
		txtStream.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtStream.setColumns(10);
		txtStream.setBounds(390, 11, 152, 20);
		contentPane.add(txtStream);
		
		txtDOB = new JTextField();
		txtDOB.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new EtchedBorder(EtchedBorder.LOWERED, null, null)));
		txtDOB.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtDOB.setColumns(10);
		txtDOB.setBounds(390, 49, 152, 20);
		
		JButton save = new JButton("Add to System");
		save.setBackground(Color.GREEN);
		save.setForeground(Color.RED);
		save.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		save.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean a = checkEmpty(txtStdID,txtStdName,txtStdForm,txtStream);
				if(a == false) {
					try {
						SaveDetails();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(null, "All field are required !!!");
				}
			}
		});
		save.setBounds(420, 89, 122, 23);
		contentPane.add(save);
		
		JButton btnDisplay = new JButton("Display Details");
		btnDisplay.setBackground(Color.GREEN);
		btnDisplay.setForeground(Color.RED);
		btnDisplay.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnDisplay.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Resources.openApp(StudentDetails.frame);
			}
		});
		btnDisplay.setBounds(420, 123, 122, 23);
		contentPane.add(btnDisplay);
		
		
		df.setLenient(false);
		formatter.setAllowsInvalid(false);
		formatter.setOverwriteMode(true);
		txtDate.setBounds(390, 49, 152, 20);
		txtDate.setColumns(10);
		txtDate.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new EtchedBorder(EtchedBorder.LOWERED, null, null)));
		txtDate.setValue(new Date());
		contentPane.add(txtDate);
	}
	static Connection Connect() {
		return con = DbConnection.connect();
	}
	static void SaveDetails() throws SQLException {
		Connect();
		String sql = "insert into tblStudentDetails values (?,?,?,?,?)";
		if(checkID(txtStdID) == false ) {
			DbConnection.disconnect(studentDetails());
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,txtStdID.getText());
			pst.setString(2,txtStdName.getText());
			pst.setInt(3,Integer.parseInt(txtStdForm.getText()));
			pst.setString(4,txtStream.getText());
			pst.setString(5,txtDate.getText());
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null,"ID "+txtStdID.getText()+" Successfully Added");
 			resetTextField(txtStdID,txtStdName,txtStdForm,txtStream
			);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		}else {
			JOptionPane.showMessageDialog(null, "Id exists in system");
		}
	}
	static void displayDetails(JTable tbl) {
		Connect();
		String sql = "select * from tblStudentDetails";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			tbl.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,e+"Error in retrieving data from database");
		}
	}
	static boolean checkEmpty(JTextField... s) {
		for(JTextField a : s) {
			if(a.getText().isEmpty()) {
				return true;
			}
		}
		return false;
		
	}
	static void resetTextField(JTextField... s) {
		for(JTextField a:s) {
			a.setText("");
		}
	}
	static ResultSet studentDetails() {
		Connect();
		String sql = "select * from tblStudentDetails";
		ResultSet rs = null;
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e + "\nError in getting student details");
		}
		return rs;
	}
	static boolean checkID(JTextField txt) throws SQLException {
		ResultSet rs = studentDetails();
		while(rs.next()) {
			String ID = rs.getString("StudentID");
			if(ID.equals(txt.getText())) {
				return true;
			}
		}
		return false;
	}
	
	class stdphotoFilter extends FileFilter{
		final static String JPEG ="jpeg";
		final static String JPG = "jpg";
		final static String GIF = "gif";

		@Override
		public boolean accept(File f) {
			if (f.isDirectory()) {
				return true;
			}
			String filextension = getExtension(f);
			if(filextension != null) {
				if(filextension.equals(JPEG)||
						filextension.equals(JPG)||
						filextension.equals(GIF)) {
					return true;
				}else {
					return false;
				}
			}
			
			return false;
		}

		@Override
		public String getDescription() {
			return "images only";
		}
		String getExtension(File f) {
			String ext = null;
			String s = f.getName();
			int i = s.lastIndexOf('.');
			
			if(i > 0 && i < s.length()-1) {
				ext = s.substring(i+1).toLowerCase();
			}
			return ext;
		}
		
	}
}
