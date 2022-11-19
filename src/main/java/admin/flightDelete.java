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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class flightDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public flightDelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String fNo=request.getParameter("fNo");
		int i=0;
								
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/flyAway","root","rahul");
			Statement stmt=con.createStatement();
			
			ResultSet rs=(ResultSet) stmt.executeQuery("select Flight_No from flightList");
						
			while(rs.next() && i==0) {
				if(fNo.equals(rs.getString(1))) {
					i++;
				}	
			}
		if(i==1) {
			PreparedStatement stmt1=con.prepareStatement("delete from flightlist where Flight_No=?");
			stmt1.setString(1, fNo);
			stmt1.execute();
			RequestDispatcher rd=request.getRequestDispatcher("/flightDelete.html");
			rd.include(request, response);
			out.print("<h2>Flight Deleted Successful</h2>");
		}else {
			RequestDispatcher rd=request.getRequestDispatcher("/flightDelete.html");
			rd.include(request, response);
			out.print("<h2>Flight not exist</h2>");
		}
			stmt.close();
			con.close();
		} catch (Exception e) {
			RequestDispatcher rd=request.getRequestDispatcher("/flightDelete.html");
			rd.include(request, response);
			out.print("<h2>Flight not exist</h2>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
