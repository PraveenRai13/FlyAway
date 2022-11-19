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

public class flightUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public flightUpdate() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String fNo=request.getParameter("fNo");
		String airline=request.getParameter("airline");
		String from=request.getParameter("from");
		String where=request.getParameter("where");
		String depDate=request.getParameter("depDate");
		String arrDate=request.getParameter("arrDate");
		String depHr=request.getParameter("depHr");
		String depMin=request.getParameter("depMin");
		String depZone=request.getParameter("depZone");
		String depTime=depHr+":"+depMin+" "+depZone;
		String arrHr=request.getParameter("arrHr");
		String arrMin=request.getParameter("arrMin");
		String arrZone=request.getParameter("arrZone");
		String arrTime=arrHr+":"+arrMin+" "+arrZone;
		String cap1=request.getParameter("cap");
		int cap=Integer.parseInt(cap1);
		String price1=request.getParameter("price");
		int price=Integer.parseInt(price1);
		int i=0;
				
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/flyAway","root","root");
			Statement stmt=con.createStatement();
			
			ResultSet rs=(ResultSet) stmt.executeQuery("select Flight_No from flightList");
						
			while(rs.next() && i==0) {
				if(fNo.equals(rs.getString(1))) {
					i++;
				}	
			}
		if(i==1) {
			PreparedStatement stmt1=con.prepareStatement("update flightlist set Airline=?,Source=?,Destination=?,Departure_Date=?,Arrival_Date=?,Departure_Time=?,Arrival_Time=?,Capacity=?,Price=?,Seats_Left=? where Flight_No=?");
			stmt1.setString(11, fNo);
			stmt1.setString(1, airline);
			stmt1.setString(2, from);
			stmt1.setString(3, where);
			stmt1.setString(4, depDate);
			stmt1.setString(5, arrDate);
			stmt1.setString(6, depTime);
			stmt1.setString(7, arrTime);
			stmt1.setInt(8, cap);
			stmt1.setInt(9, price);
			stmt1.setInt(10, cap);
			stmt1.execute();
			RequestDispatcher rd=request.getRequestDispatcher("/flightUpdate.html");
			rd.include(request, response);
			out.print("<h2>Flight Updated Successful</h2>");
		}else {
			RequestDispatcher rd=request.getRequestDispatcher("/flightUpdate.html");
			rd.include(request, response);
			out.print("<h2>Flight not exist</h2>");
		}
			stmt.close();
			con.close();
		} catch (Exception e) {
			RequestDispatcher rd=request.getRequestDispatcher("/flightUpdate.html");
			rd.include(request, response);
			out.print("<h2>Flight not exist</h2>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
