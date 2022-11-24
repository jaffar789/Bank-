package jdbc3;

public class demo {

	public static void main(String[] args) {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver successfully loaded");
		}
	catch (ClassNotFoundException e) 
	{
		e.printStackTrace();
	}

	}

}
