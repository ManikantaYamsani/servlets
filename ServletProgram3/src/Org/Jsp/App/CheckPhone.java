package Org.Jsp.App
'['

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
@WebServlet("/CheckPhone")
public class CheckPhone  extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		
		//JDBC code
		String url ="jdbc:mysql://localhost:3306?user=root&password=12345";
		String query ="select phonenumber from adjavaonline.userinfo where phonenumber=?";
		
		String uphone=req.getParameter("ph");
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedstatement = connection.prepareStatement(query);
			
			String phone;
			preparedstatement.setString(1,uphone);
			ResultSet resultSet= preparedstatement.executeQuery();
			
			if(resultSet.next())
			{
				Random random =new Random();
				int otp=random.nextInt(10000);
				
				if(otp<0) {
					otp =otp * -1;
			}
				if(otp<1000) {
					otp = otp+1000;
				}
				System.out.println("otp is : "+otp);
			}
			else
			{
			System.err.println("invalid phonenumber");
			
			}
			
			connection.close();
			
			
		} 
		catch (Exception e) 
		{
		
			e.printStackTrace();
		} 
		}
	}
