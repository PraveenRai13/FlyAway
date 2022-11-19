package flyAway.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class addPass extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public addPass() {
        super();
    }
        
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		travelDetail td=new travelDetail();
		String pname=request.getParameter("pname");
		String page=request.getParameter("page");
		String pgender=request.getParameter("pgender");
		try {
			Configuration cfg=new Configuration();
			cfg.configure("flyAway.cfg.xml");
			SessionFactory factory=cfg.buildSessionFactory();
			passenger p=new passenger();
			p.setPname(pname);
			p.setPage(page);
			p.setPgender(pgender);
			p.setPuname(td.getuName());
			
			Session session=factory.openSession();
			Transaction tx=session.beginTransaction();			
			session.save(p);
			tx.commit();						
						
			int j = td.getJ();
			j++;
			td.setJ(j);			
			RequestDispatcher rd=request.getRequestDispatcher("/loPass");
			rd.forward(request, response);
			
		} catch (Exception e) {
			out.print(e);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
