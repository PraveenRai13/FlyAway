package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class adSetPass extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public adSetPass() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String adUname=request.getParameter("adUname");
		String adName=request.getParameter("adName");
		String adNewPass=request.getParameter("adNewPass");
		loginDetail admin=new loginDetail();
		String x=request.getParameter("x");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/flyaway","root","rahul");
			Statement stmt=con.createStatement();
			ResultSet login=(ResultSet) stmt.executeQuery("select * from admin");
			int i=0;
			
			while(login.next()) {
				if(adUname.equals(login.getString("adUsername")) && adName.equals(login.getString("adName"))){
					PreparedStatement stmt1=con.prepareStatement("update admin set adPass=? where adUsername=?");
					stmt1.setString(1, adNewPass);
					stmt1.setString(2, adUname);
					stmt1.execute();
					RequestDispatcher rd=request.getRequestDispatcher("/adminLogin.html");
					rd.include(request, response);
					out.print("<h2>*Password change successful.</h2>");
					i++;
				}
			}
			stmt.close();
			con.close();
			if(i==0) {
				if(x=="1") {
				RequestDispatcher rd=request.getRequestDispatcher("/adminForgotPass.html");
				rd.include(request, response);
				out.print("<h2>*Username or Admin Name incorrect.</h2>");
				}else {
					RequestDispatcher rd=request.getRequestDispatcher("/adChangePass");
					rd.include(request, response);
					out.print("<h2>*Username or Admin Name incorrect.</h2>");
				}
			}
			
		} catch (Exception e) {
			out.print(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
