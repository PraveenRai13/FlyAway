package flyAway.com;

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
import javax.servlet.http.HttpSession;
import flyAway.com.*;

public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		travelDetail td1=new travelDetail();
		String uname=request.getParameter("uname");
		String upass=request.getParameter("upass");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/flyaway","root","rahul");
			Statement stmt=con.createStatement();
			ResultSet login=(ResultSet) stmt.executeQuery("select User_Name, Password, First_Name from Customer");
			int i=0;
			
			while(login.next()) {
				if(uname.equals(login.getString(1)) && upass.equals(login.getString(2))){
					td1.setcName(login.getString(3));
					td1.setuName(login.getString(1));
					td1.setuName(login.getString(1));
					if(td1.getFno()==null) {
						RequestDispatcher rd=request.getRequestDispatcher("/SearchFlight");
						rd.forward(request, response);
					}else {
						RequestDispatcher rd=request.getRequestDispatcher("/loPass");
						rd.forward(request, response);
					}					
					i++;
				}
			}
			stmt.close();
			con.close();
			if(i==0) {
				RequestDispatcher rd=request.getRequestDispatcher("/login.html");
				rd.include(request, response);
				out.print("<h2>*Username or Password incorrect.</h2>");
			}
			
		} catch (Exception e) {
			RequestDispatcher rd=request.getRequestDispatcher("/login.html");
			rd.include(request, response);
			out.print("<h2>*User not registered.</h2>");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
