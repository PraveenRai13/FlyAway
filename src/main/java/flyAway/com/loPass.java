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
import javax.servlet.http.HttpSession;

public class loPass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public loPass() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		PrintWriter out=response.getWriter();
		travelDetail td=new travelDetail();
		int npas=td.getNpas();
		int j=td.getJ();
		int i=0;
		int f=0;
		try {
			out.print("<html><body>");
			out.print("<h1><a href=SearchFlight>FlyAway.Com</a></h1>");
			out.print("<form action='logout' method='post'><div style='text-align: right'>");
			out.print(td.getcName());
			out.println("<input type='submit' value='Logout'>");
			out.print("</div></form>");
			out.print("<div style='text-align: center'><form>");
			out.print("<table border='0' bgcolor='yellow' align='center' cellspacing='15'>");
			out.print("</td><td style='text-align: center' width='60 px'>");						
			out.print(td.getFno());
			out.print("</td><td style='text-align: center' width='60 px'>");						
			out.print(td.getComp());
			out.print("</td><td style='text-align: center' width='100 px'>");
			out.print("<b>From</b><br>");
			out.print(td.getSou()+"<br>");
			out.print(td.getDepDate()+"("+td.getDep()+")");
			out.print("</td><td style='text-align: center' width='100 px'>");
			out.print("<b>To</b><br>");
			out.print(td.getDest()+"<br>");
			out.print(td.getArrDate()+"("+td.getArr()+")");
			out.print("</td><td style='text-align: center' width='60 px'>");
			out.print(td.getPrice()+" Rs.");
			out.print("</td>");
			out.print("</table></form></div>");
			out.print("<br>");
		
		if(j==0) {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/flyaway","root","rahul");
			Statement stmt1=con1.createStatement();
			ResultSet ID=(ResultSet) stmt1.executeQuery("select id from Passenger");
			while(ID.next()) {
				f++;
			}
			td.setId(f);
			stmt1.close();
			con1.close();
		}
		
		if(j!=0) {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/flyaway","root","rahul");
			Statement stmt=con.createStatement();
			ResultSet pList=(ResultSet) stmt.executeQuery("select * from Passenger where id >"+td.getId()+" and User_Name='"+td.getuName()+"'");
			
		while(pList.next()) {
			i++;
		if(i==1) {
			out.print("<table border='0' bgcolor='grey' align='center' cellspacing='15'>");
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
			out.print("</body></html>");
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
		}			
			stmt.close();
			con.close();
		}
			out.print("</body></html>");
			
		} catch (Exception e) {
			out.print(e);
		}
				
		if(j<npas) {
			RequestDispatcher rd = request.getRequestDispatcher("/AddPassenger.html");
			rd.include(request, response);
		}
		if(j!=0){
			out.print("<html><body>");
			out.print("<form action='reviewTicket'>");
			double fare=(td.getPrice()*i)+(td.getPrice()*0.18);
			td.setFare(fare);
			out.print("<div style='text-align: center'><input type='submit' value='Pay"+fare+" Rs.'>");
			out.print("<br>Total Ticket Price(Price+18% GST) = "+fare+"Rs.");
			out.print("</div></form>");
			out.print("</body></html>");			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
