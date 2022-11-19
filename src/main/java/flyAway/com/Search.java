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

public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;     

    public Search() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		String source=request.getParameter("source");
		String destination=request.getParameter("destination");
		String jdate=request.getParameter("jdate");
		String nPassenger=request.getParameter("nPassenger");
		int nPas=Integer.parseInt(nPassenger);
		travelDetail td=new travelDetail();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/flyaway","root","rahul");
			Statement stmt=con.createStatement();
			ResultSet fList=(ResultSet) stmt.executeQuery("select * from flightlist where source = '"+source+"' and Destination = '"+destination+"' and Departure_Date= '"+jdate+"'");
			int i = 0;
			int j=0;
			
			while(fList.next()) {
					j++;
					if(j==1&&nPas<=6) {
						out.print("<html><body bgcolor='yellow'>");
						if(td.getcName()==null) {
							out.print("<h1><a href=homePage.html>FlyAway.Com</a></h1>");
							out.print("<div style='text-align: right'>");
							out.print("<form action='login.html'>");
							out.print("<input type='submit' value='Login/Sign up'>");
							out.print("</form>");
							out.print("</div>");
						}else {
							out.print("<h1><a href=SearchFlight>FlyAway.Com</a></h1>");
							out.print("<form action='logout' method='post'><div style='text-align: right'>");
							out.print(td.getcName());
							out.println("<input type='submit' value='Logout'>");
							out.print("</div></form>");
						}
						out.print("<table border='0' bgcolor='grey' align='center' cellspacing='15'>");
						out.print("<tr><td style='text-align: center' width='50 px'>");						
						out.print("Sr. No.");
						out.print("</td><td style='text-align: center' width='80 px'>");						
						out.print("Flight. No.");
						out.print("</td><td style='text-align: center' width='60 px'>");
						out.print("Airline");
						out.print("</td><td style='text-align: center' width='100 px'>");
						out.print("Source");
						out.print("</td><td style='text-align: center' width='80 px'>");
						out.print("Destination");
						out.print("</td><td style='text-align: center' width='80 px'>");
						out.print("Seats Left");
						out.print("</td><td style='text-align: center' width='80 px'>");
						out.print("Price");
						out.print("</td><td style='text-align: center' width='40 px'>");
						out.print("Book");
						out.print("</td></tr>");
						out.print("</table>");
						out.print("</body></html>");
						out.print("<br>");						
					}
					
					if(nPas <= fList.getInt("Seats_Left") && nPas<=6 && fList.getInt("Seats_Left")!=0) {
						i++;						
						out.print("<html><body><div style='text-align: center'>");
						out.print("<form action='book' method='post'>");
						out.print("<table border='0' bgcolor='pink' align='center' cellspacing='20'>");
						out.print("<tr><td style='text-align: center' width='40 px'>");						
						out.print(i+".");
						out.print("</td><td style='text-align: center' width='60 px'>");						
						out.print(fList.getString("Flight_No"));
						out.print("</td><td style='text-align: center' width='60 px'>");						
						out.print(fList.getString("Airline"));
						out.print("</td><td style='text-align: center' width='100 px'>");
						out.print(fList.getString("Source")+"<br>");
						out.print(fList.getString("Departure_Date")+"("+fList.getString("Departure_Time")+")");
						out.print("</td><td style='text-align: center' width='100 px'>");
						out.print(fList.getString("Destination")+"<br>");
						out.print(fList.getString("Arrival_Date")+"("+fList.getString("Arrival_Time")+")");
						out.print("</td><td style='text-align: center' width='60 px'>");
						out.print(fList.getString("Seats_Left")+" Seats");
						out.print("</td><td style='text-align: center' width='60 px'>");
						out.print(fList.getString("Price")+" Rs.");
						out.print("</td><td style='text-align: center' width='60 px'>");
						out.print("<input type='submit' value='Book'>");
						out.print("</td></tr>");
						out.print("</table>");
						out.print("<input type='hidden' name='fno' value="+fList.getString("Flight_No")+">");
						out.print("<input type='hidden' name='comp' value="+fList.getString("Airline")+">");
						out.print("<input type='hidden' name='sou' value="+fList.getString("Source")+">");
						out.print("<input type='hidden' name='dest' value="+fList.getString("Destination")+">");
						out.print("<input type='hidden' name='depDate' value="+fList.getString("Departure_Date")+">");
						out.print("<input type='hidden' name='arrDate' value="+fList.getString("Arrival_Date")+">");
						out.print("<input type='hidden' name='dep' value="+fList.getString("Departure_Time")+">");
						out.print("<input type='hidden' name='arr' value="+fList.getString("Arrival_Time")+">");
						out.print("<input type='hidden' name='price' value="+fList.getString("Price")+">");
						out.print("<input type='hidden' name='seatsLeft' value="+fList.getString("Seats_Left")+">");
						out.print("<input type='hidden' name='npass' value="+nPassenger+">");
						out.print("</form>");
						out.print("</div></body></html>");
					}		
			}
			stmt.close();
			con.close();
			
			if(i==0) {
				if(td.getcName()==null) {
					RequestDispatcher rd=request.getRequestDispatcher("/homePage.html");
					rd.include(request, response);
					if(nPas>6) {
						out.print("<h2>*Maximum 6 tickets allowed.</h2>");
					}else {
						out.print("<h2>*No flights available.</h2>");
					}
				}else {
					RequestDispatcher rd=request.getRequestDispatcher("/SearchFlight");
					rd.include(request, response);
					if(nPas>6) {
						out.print("<h2>*Maximum 6 tickets allowed.</h2>");
					}else {
						out.print("<h2>*No flights available.</h2>");
					}
				}
			}
		} catch (Exception e) {
			if(td.getcName()==null) {
				RequestDispatcher rd=request.getRequestDispatcher("/homePage.html");
				rd.include(request, response);
				out.print("<h2>*No flights available.</h2>");
			}else {
				RequestDispatcher rd=request.getRequestDispatcher("/SearchFlight");
				rd.include(request, response);
				out.print("<h2>*No flights available.</h2>");
			}
		}		

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
