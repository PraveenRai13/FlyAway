package flyAway.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchFlight() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		travelDetail cName=new travelDetail();
				
		PrintWriter out=response.getWriter();
		out.print("<h1><a href=SearchFlight>FlyAway.Com</a></h1>");
		out.print("<html>");
		out.print("<body bgcolor='yellow'>");
		out.print("<form action='logout' method='post'><div style='text-align: right'>");
		out.print(cName.getcName());
		out.println("<input type='submit' value='Logout'>");
		out.print("</div></form>");
		out.print("<div style='text-align: center'>");
		out.print("<form action='Search' method='POST'>");
		out.print("<table border='0' bgcolor='cream' align='center' cellspacing='15'>");
		out.print("<h2>Search Flights</h2>");
		out.print("<tr><td>Where from:</td>");
		out.print("<td><input type='text' name='source' required></td></tr>");
		out.print("<tr><td>Where to:</td>");
		out.print("<td><input type='text' name='destination' required></td></tr>");
		out.print("<tr><td>Journey Date(DD/MM/YYYY):<input type='text' name='jdate' size='20' required></td>");
		out.print("<td>No. of Passengers:<input type='text' name='nPassenger' size='2' required></td></tr>");
		out.print("</table>");
		out.print("<input type='submit' value='Search Flights'>");
		out.print("<input type='reset' value='Reset'>");
		out.print("</form>");
		out.print("</div>");
		out.print("</body>");
		out.print("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
