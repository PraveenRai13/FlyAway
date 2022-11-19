package flyAway.com;

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

public class forgotP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public forgotP() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		String fpname=request.getParameter("fpname");
		String fpmonum1=request.getParameter("fpmonum");
		long fpmonum=Long.parseLong(fpmonum1);
		String newPass=request.getParameter("newPass");
						
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/flyaway","root","rahul");
			Statement stmt=con.createStatement();
			ResultSet cusList=(ResultSet) stmt.executeQuery("select User_Name, Mo_Num, Password from Customer");
			int i = 0;
			travelDetail td=new travelDetail();
			
			while(cusList.next()) {
				if(fpname.equals(cusList.getString(1)) && fpmonum==cusList.getLong(2)) {
					i++;
					PreparedStatement stmt1=con.prepareStatement("update Customer set Password=? where User_Name=?");
					stmt1.setString(1, newPass);
					stmt1.setString(2, fpname);
					stmt1.execute();
					RequestDispatcher rd=request.getRequestDispatcher("/login.html");
					rd.include(request, response);
					out.print("<h2>Password Updated</h2>");
				}
			}
			if(i==0) {
				RequestDispatcher rd=request.getRequestDispatcher("/forgotPass.html");
				rd.include(request, response);
				out.print("<h2>Username or Mobile Number incorrect.</h2>");
			}
			stmt.close();
			con.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
