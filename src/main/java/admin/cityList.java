package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class cityList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public cityList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		int i = 0;
		int j=0;
		try {
			out.print("<html><body bgcolor='yellow'>");
			out.print("<h1><a href=adminHome.html>FlyAway.Com</a></h1>");
			out.print("<form action='adlogout' method='post'><div style='text-align: right'>");
			out.print("Admin");
			out.println("<input type='submit' value='Logout'><br>");
			out.println("<a href=adChangePass>Change Password</a>");
			out.print("</div></form>");
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/flyaway","root","rahul");
			Statement stmt=con.createStatement();
					
			ResultSet flList=(ResultSet) stmt.executeQuery("select Distinct Source from flightlist");
									
		while(flList.next()) {
			j++;
			i++;
			if(j==1) {
				out.print("<html><body>");
				out.print("<table border='0' bgcolor='grey' align='center' cellspacing='15'>");
				out.print("<tr><td style='text-align: center' width='50 px'>");						
				out.print("Sr.No.");
				out.print("</td><td style='text-align: center' width='100 px'>");
				out.print("List of Cities");			
				out.print("</td></tr>");
				out.print("</table>");
				out.print("</body></html>");
				out.print("<br>");						
			}
				out.print("<html><body><div style='text-align: center'>");
				out.print("<table border='0' bgcolor='pink' align='center' cellspacing='20'>");
				out.print("<tr><td style='text-align: center' width='20 px'>");						
				out.print(i+".");
				out.print("</td><td style='text-align: center' width='100 px'>");
				out.print(flList.getString("Source")+"<br>");
				out.print("</td></tr>");
				out.print("</table>");
				out.print("</form></div></body></html>");
			
		}
		if(i==0) {
			out.print("<h2>No cities available for ticket booking.</h2>");
		}
			out.print("<html><body>");
			out.print("<div style='text-align: center'><h3><a href=adminHome.html>Back</a></h3></div>");
			out.print("</body></html>");
			stmt.close();
			con.close();
		} catch (Exception e) {
			out.print("<h2>*No places available to book ticket.</h2>");
			out.print("<html><body>");
			out.print("<div style='text-align: center'><h3><a href=flManage.html>Back</a></h3></div>");
			out.print("</body></html>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
