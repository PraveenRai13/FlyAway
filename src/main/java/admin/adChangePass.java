package admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class adChangePass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public adChangePass() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		out.print("<html><body bgcolor='yellow'>");
		out.print("<h1><a href=adminHome.html>FlyAway.Com</a></h1>");
		out.print("<form action='adlogout' method='post'><div style='text-align: right'>");
		out.print("Admin");
		out.println("<input type='submit' value='Logout'><br>");
		out.print("</div></form>");
		out.print("<div style='text-align: center'><form action='adSetPass' method='post'>");		
		out.print("<table border='0' bgcolor='grey' align='center' cellspacing='15'>");
		out.print("<h2>Change Password</h2>");
		out.print("<tr><td style='text-align: center' width='50 px'>");						
		out.print("Admin Username:");
		out.print("</td><td style='text-align: center' width='100 px'>");						
		out.print("<input type='text' name='adUname' required>");
		out.print("</td></tr><tr><td>");
		out.print("Admin Name:");
		out.print("</td><td style='text-align: center' width='100 px'>");
		out.print("<input type='text' name='adName' required>");
		out.print("</td></tr><tr><td>");
		out.print("Admin New Password:");
		out.print("</td><td style='text-align: center' width='100 px'>");
		out.print("<input type='text' name='adNewPass' required>");
		out.print("</td></tr>");
		out.print("</table>");
		out.print("<input type='hidden' name='x' value='0'>");
		out.print("<input type='submit' value='Change Password'>");
		out.print("</form></div></body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
