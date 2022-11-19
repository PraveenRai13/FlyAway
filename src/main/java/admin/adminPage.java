package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class adminPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public adminPage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String adUsername=request.getParameter("adUname");
		String adPass=request.getParameter("adPass");
		loginDetail admin=new loginDetail();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/flyaway","root","rahul");
			Statement stmt=con.createStatement();
			ResultSet login=(ResultSet) stmt.executeQuery("select * from admin");
			int i=0;
			
			while(login.next()) {
				if(adUsername.equals(login.getString("adUsername")) && adPass.equals(login.getString("adPass"))){
					admin.setAdName(login.getString("adName"));
					admin.setUser(login.getString("adUsername"));
					admin.setAdPass(login.getString("adPass"));
					RequestDispatcher rd=request.getRequestDispatcher("/adminHome.html");
					rd.include(request, response);					
					i++;
				}
			}
			stmt.close();
			con.close();
			if(i==0) {
				RequestDispatcher rd=request.getRequestDispatcher("/adminLogin.html");
				rd.include(request, response);
				out.print("<h2>*Username or Password incorrect.</h2>");
			}
			
		} catch (Exception e) {
			out.print(e);
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
