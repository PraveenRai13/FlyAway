package admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class flightAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public flightAdd() {
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
							
		try {
			Configuration cfg=new Configuration();
			cfg.configure("flyAway.cfg.xml");
			SessionFactory factory=cfg.buildSessionFactory();
			flightDetail flight=new flightDetail();
			flight.setfNo(fNo);
			flight.setAirline(airline);
			flight.setFrom(from);
			flight.setWhere(where);
			flight.setDepDate(depDate);
			flight.setArrDate(arrDate);
			flight.setDepTime(depTime);
			flight.setArrTime(arrTime);
			flight.setCap(cap);
			flight.setSeat(cap);
			flight.setPrice(price);
			
			Session session=factory.openSession();
			Transaction tx=session.beginTransaction();			
			session.save(flight);
			tx.commit();						
			session.close();
			factory.close();
			
			RequestDispatcher rd=request.getRequestDispatcher("/flightAdd.html");
			rd.include(request, response);
			out.print("<h2>Flight Add Successful</h2>");
			
		} catch (Exception e) {
			RequestDispatcher rd=request.getRequestDispatcher("/flightAdd.html");
			rd.include(request, response);
			out.print("<h2>Flight already exist</h2>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
