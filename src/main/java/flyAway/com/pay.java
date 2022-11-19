package flyAway.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class pay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public pay() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		travelDetail td=new travelDetail();
		PrintWriter out=response.getWriter();
	if(td.getcName()!=null) {
		out.print("<h1><a href=SearchFlight>FlyAway.Com</a></h1>");
		out.print("<html>");
		out.print("<body bgcolor='yellow'>");
		out.print("<form action='logout' method='post'><div style='text-align: right'>");
		out.print(td.getcName());
		out.println("<input type='submit' value='Logout'>");
		out.print("</div></form>");
		out.print("<div style='text-align: center'>");
		out.print("<form action='ticket' method='POST'>");
		out.print("<table border='0' bgcolor='cream' align='center' cellspacing='15'>");
		out.print("<h2>Card Details</h2>");
		out.print("<tr><td>Card No.:</td>");
		out.print("<td><input type='text' name='CardNo' required></td></tr>");
		out.print("<tr><td>Name on Card:</td>");
		out.print("<td><input type='text' name='nameCard' required></td></tr>");
		out.print("<tr><td>Mobile No.:</td>");
		out.print("<td><input type='text' name='CdMoNo' required></td></tr>");
		out.print("<tr><td>CVV:</td>");
		out.print("<td><input type='text' name='cvv' required></td></tr>");
		out.print("</table>");
		out.print("<input type='submit' value='Pay'>");
		out.print("<input type='reset' value='Reset'>");
		out.print("</form>");
		out.print("</div>");
		out.print("</body>");
		out.print("</html>");
	}else {
		RequestDispatcher rd=request.getRequestDispatcher("/homePage.html");
		rd.forward(request, response);
	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
