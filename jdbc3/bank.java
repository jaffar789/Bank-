package jdbc3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.util.Scanner;

public class bank {

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
			
			pstmt1 = con.prepareStatement("Select * From details where Acc_num = ? and Pin= ?");
			pstmt1.setInt(1,Acc_num);
			pstmt1.setInt(2, Pin);
			
			ResultSet res1 = pstmt1.executeQuery();
			
			res1.next();
			
			String Name = res1.getString(2);
			int Balance = res1.getInt(4);
			System.out.println("welcome:"+ "<..." + Name + "...>" );
			System.out.println("Available balance is :" + Balance);
			// Transfering details
			
			
			System.out.println("<..... Transfer.....> ");
			System.out.println("Enter the account number ");
			int bacc_num = scan.nextInt();
			System.out.println("Enter the transfer amount");
			int t_amount = scan.nextInt();
			
			con.setAutoCommit(false);
			Savepoint s = con.setSavepoint();
			
			PreparedStatement pstmt2 = con.prepareStatement("update details set balance = balance - ? " + "  where Acc_num = ? ");
			pstmt2.setInt(1, t_amount);
			pstmt2.setInt(2,Acc_num);
			pstmt2.executeUpdate();
			
			
			System.out.println("<....Incoming credit request....>");
			System.out.println(Name + "account number" + Acc_num + " wants to transfer" + t_amount );
			System.out.println(" press yes to receive");
			System.out.println(" press no to reject");
			String choice = scan.next();
			if (choice.equals("yes")) {
				PreparedStatement pstmt3 = con.prepareStatement("update details set balance = balance + ? " + " where Acc_num = ? ");
				pstmt3.setInt(1, t_amount);
				pstmt3.setInt(2, bacc_num);
				pstmt3.executeUpdate();
				
				PreparedStatement pstmt4= con.prepareStatement("Select * From details " + "where Acc_num = ?");
				pstmt4.setInt(1, bacc_num);
				ResultSet res2 = pstmt4.executeQuery();
				res2.next();
				
				System.out.println("update balance is : " + res2.getInt(4));
					
			} else {

		con.rollback(s);
			
			PreparedStatement pstmt4= con.prepareStatement("Select * From details where Acc_num = ?");
			pstmt4.setInt(1, bacc_num);
			ResultSet res2 = pstmt4.executeQuery();
			res2.next();

			System.out.println("Existing balance is : " + res2.getInt(4));
				}
			
			con.commit();

		}
		catch (Exception e) {
				e.printStackTrace();
			}
		}
		
			


}

