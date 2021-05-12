package Org.Jsp.App;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
@WebServlet("/Logincode")
public class Logincode extends GenericServlet{

	private PreparedStatement preparedStatement;
	private String umail;
	private String epass;

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		
		String uEmail = req.getParameter("em");
		String uPassword = req.getParameter("epass");
		System.out.println(uEmail+ " "+uPassword);
		
		String url ="jdbc:mysql://localhost:3306?user=root&password=12345";
		String query ="select * from adjavaonline.userinfo where EmailID=? and password=?";
		try
		{
		Connection connection=DriverManager.getConnection(url);
		PreparedStatement praparedStatement= connection.prepareStatement(query);
		preparedStatement.setString(1,umail);
		preparedStatement.setString(2,epass);
		
		ResultSet resultSet =preparedStatement.executeQuery();
		if(resultSet.next())
		{
			Random random =new Random();
			int otp=random.nextInt(10000);
			
			if(otp<0)
			{
				otp =otp * -1;
			}
			if(otp<1000) 
			{
				otp = otp+1000;
			}
			System.out.println("otp is : "+otp);
		}
		else
		{
			System.out.println("invalid email or password  !!");
		}
		connection.close();
	}
	catch(Exception e)

	{
		e.printStackTrace();
	}
	}
}

	