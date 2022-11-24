package jdbc3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbcinsert {

	public static void main(String[] args) throws SQLException {
		String url = "jdbc:mysql://localhost:3306/tapacademy";
		String un = "root";
		String pwd = "root";
		Connection con = null;
		Statement stmt = null;
		ResultSet res= null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded successfully");
			
			con = DriverManager.getConnection(url,un,pwd);
			System.out.println("Connection Established");
			

			stmt= con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,0);
			String query = "insert into employee(`id`,`name`,`Email`,`Salary`)values(06,'jazz','jazz@gmail.com',1200)";
			stmt.execute(query);
			System.out.println("query executed successfully");
			}	
			catch(ClassNotFoundException e) {
			System.out.println("diive not loaded");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		try {
			stmt.close();
			con.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
			
