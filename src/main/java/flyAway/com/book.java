package flyAway.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class book extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public book() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String fno=request.getParameter("fno");
		String comp=request.getParameter("comp");
		String sou=request.getParameter("sou");
		String dest=request.getParameter("dest");
		String depDate=request.getParameter("depDate");
		String arrDate=request.getParameter("arrDate");
		String dep=request.getParameter("dep");
		String arr=request.getParameter("arr");
		String price=request.getParameter("price");
		int pr=Integer.parseInt(price);
		String npass=request.getParameter("npass");
		int nPas=Integer.parseInt(npass);
		String seatsLeft1=request.getParameter("seatsLeft");
		int seatsLeft=Integer.parseInt(seatsLeft1);
		
		travelDetail td =new travelDetail();
		td.travelDetail(fno, comp, sou, dest, depDate, arrDate, dep, arr, pr, nPas);
		td.setSeatsLeft(seatsLeft);
						
			out.print("<html>");
			out.print("<body>");
		if(td.getcName()!=null) {	
			out.print("<h1><a href=SearchFlight>FlyAway.Com</a></h1>");
			out.print("<form action='logout' method='post'><div style='text-align: right'>");
			out.print(td.getcName());
			out.println("<input type='submit' value='Logout'>");
			out.print("</div></form>");
			out.print("<form action='loPass' method='post'>");
		}else {
			out.print("<h1><a href=homePage.html>FlyAway.Com</a></h1>");
			out.print("<form action='login.html' method='post'>");
		}
			out.print("<table border='0' bgcolor='yellow' align='center' cellspacing='15'>");
			out.print("</td><td style='text-align: center' width='60 px'>");						
			out.print(fno);
			out.print("</td><td style='text-align: center' width='60 px'>");						
			out.print(comp);
			out.print("</td><td style='text-align: center' width='100 px'>");
			out.print("<b>From</b><br>");
			out.print(sou+"<br>");
			out.print(depDate+"("+dep+")");
			out.print("</td><td style='text-align: center' width='100 px'>");
			out.print("<b>To</b><br>");
			out.print(dest+"<br>");
			out.print(arrDate+"("+arr+")");
			out.print("</td><td style='text-align: center' width='60 px'>");
			out.print(pr+" Rs.");
			out.print("</td><td style='text-align: center' width='100 px'>");
		if(td.getcName()!=null) {
			td.setJ(0);
			out.println("<input type='submit' value='Add Passenger'>");
		}else {
			td.setJ(0);
			out.println("<input type='submit' value='Login/Sign up'>");
		}	
			out.print("</td></tr>");
			out.print("</table>");
			out.print("</form></body></html>");
			
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
