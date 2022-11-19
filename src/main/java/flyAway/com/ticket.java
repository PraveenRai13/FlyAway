package flyAway.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ticket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ticket() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		travelDetail td=new travelDetail();
		int i=0;				
		try {
			out.print("<html><body>");
			out.print("<h1><a href=SearchFlight>FlyAway.Com</a></h1>");
			out.print("<form action='logout' method='post'><div style='text-align: right'>");
			out.print(td.getcName());
			out.println("<input type='submit' value='Logout'>");
			out.print("</div></form>");
			out.print("<div style='text-align: center'><form>");
			out.print("<h2>-----------------------*Flight Ticket*-----------------------</h3>");
			out.print("<table border='0' bgcolor='yellow' align='center' cellspacing='15'>");
			out.print("<tr><td>Flight No.: "+td.getFno());
			out.print("</td><td style='text-align: center' width='150 px'>");						
			out.print("");
			out.print("</td><td style='text-align: center' width='150 px'>");
			out.print("Airline: "+td.getComp());
			out.print("</td><td style='text-align: center' width='150 px'>");
			out.print("");
			out.print("</td></tr><tr><td style='text-align: center' width='150 px'>");
			out.print("From: "+td.getSou());
			out.print("</td><td style='text-align: center' width='150 px'>");
			out.print("");
			out.print("</td><td style='text-align: center' width='150 px'>");
			out.print("Where: "+td.getDest());
			out.print("</td><td style='text-align: center' width='150 px'>");
			out.print("");
			out.print("</td></tr><tr><td style='text-align: center' width='150 px'>");
			out.print("Departure: "+td.getDepDate()+"("+td.getDep()+")");
			out.print("</td><td style='text-align: center' width='150 px'>");
			out.print("");
			out.print("</td><td style='text-align: center' width='150 px'>");
			out.print("Arrival: "+td.getArrDate()+"("+td.getArr()+")");
			out.print("</td><td style='text-align: center' width='150 px'>");
			out.print("");
			out.print("</td></tr><tr><td style='text-align: center' width='150 px'>");
			out.print("Total Price:");
			out.print("</td><td style='text-align: center' width='150 px'>");
			out.print(td.getFare()+" Rs.");
			out.print("</td>");
			out.print("</table></form></div>");
			out.print("<br>");
						
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/flyaway","root","rahul");
			Statement stmt=con.createStatement();
			ResultSet pList=(ResultSet) stmt.executeQuery("select * from Passenger where id >"+td.getId()+" and User_Name='"+td.getuName()+"'");
			
		if(td.getJ()!=0) {	
			int seatsLeft=td.getSeatsLeft();
			seatsLeft=seatsLeft-td.getJ();
			PreparedStatement stmt1=con.prepareStatement("update flightlist set Seats_Left=? where Flight_No=?");
			stmt1.setString(2, td.getFno());			
			stmt1.setInt(1, seatsLeft);
			stmt1.execute();
		}			
		while(pList.next()) {
			i++;
			if(i==1) {
				out.print("<table border='0' bgcolor='grey' align='center' cellspacing='15'>");
				out.print("<h3>Passengers Detail");
				out.print("<tr><td style='text-align: center' width='50 px'>");						
				out.print("Sr. No.");
				out.print("</td><td style='text-align: center' width='100 px'>");						
				out.print("Passenger Name");
				out.print("</td><td style='text-align: center' width='100 px'>");
				out.print("Passenger Age");
				out.print("</td><td style='text-align: center' width='100 px'>");
				out.print("Passenger Gender");					
				out.print("</td></tr>");
				out.print("</table>");
			}
				out.print("<div style='text-align: center'><form>");
				out.print("<table border='0' bgcolor='pink' align='center' cellspacing='15'>");
				out.print("<tr><td style='text-align: center' width='50 px'>");						
				out.print(i+".");
				out.print("</td><td style='text-align: center' width='100 px'>");						
				out.print(pList.getString("Passenger_Name"));
				out.print("</td><td style='text-align: center' width='100 px'>");
				out.print(pList.getString("Passenger_Age"));
				out.print("</td><td style='text-align: center' width='100 px'>");
				out.print(pList.getString("Passenger_Gender"));					
				out.print("</td></tr>");			
				out.print("</table></form></div>");
				out.print("<html><body>");
		}	
			out.print("<html><body>");
			out.print("<div style='text-align: center'><h3><a href=SearchFlight>Book New Ticket</a></h3></div>");
			out.print("</body></html>");
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			out.print(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
