package utilities;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

public class DbConnection {
	static Connection con;
	static ResourceBundle rb = ResourceBundle.getBundle("utilities.db");
	public DbConnection() {
	}
	public static Connection connect() {
		try {
			if (con == null || con.isClosed()) {
				try {
					String dbdir = "/db/";
					File f = new File(dbdir);
					if (!f.exists())
						f.mkdir();
					String DbName = "StudentDB.accdb";
					String url = dbdir + "/" + DbName;
					File f2 = new File(url);
					if (!f2.exists()) {
						try {
							InputStream iStream = DbConnection.class
									.getResourceAsStream("dbs/" + DbName);
							Files.copy(iStream, f2.toPath(),
									StandardCopyOption.REPLACE_EXISTING);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					Class.forName(rb.getString("driverName"));
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, e);
				}
				con = DriverManager.getConnection(
						rb.getString("connectionString")+rb.getString("url"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void disconnect(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public static void disconnect(ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public static void disconnect(PreparedStatement conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

}
