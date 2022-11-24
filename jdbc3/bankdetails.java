package jdbc3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class bankdetails {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/bank";
		String un = "root";
		String pwd = "root";
		Connection con = null;
		PreparedStatement pstmt1 =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(url, un, pwd);
				Scanner scan = new Scanner(System.in);
				
				
			System.out.println("<.......Welcome to Bank.......>");
			System.out.println("Enter the Account number");
			int Acc_num = scan.nextInt();
			System.out.println("Enter the Pin");
			int Pin = scan.nextInt();
			
			pstmt1 = con.prepareStatement("Select *from details where Acc_num = ? Pin= ?");
			pstmt1.setInt(1,Acc_num);
			pstmt1.setInt(2, Pin);
			
			ResultSet res1 = pstmt1.executeQuery();
			
			res1.next();
			
			String name = res1.getString(2);
			int bal = res1.getInt(4);
			System.out.println("welcome"+ name );
			System.out.println("Available balance is :" + bal);
			
			
			
			
		}catch (Exception e) {
				e.printStackTrace();

	}

	}
}
