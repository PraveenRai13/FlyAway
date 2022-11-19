package flyAway.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.*;


public class signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public signup() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		String uname=request.getParameter("uname");
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String gender=request.getParameter("ugender");
		String uage=request.getParameter("uage");
		int age=Integer.parseInt(uage);
		String umonum1=request.getParameter("umonum");
		long umonum=Long.parseLong(umonum1);
		String uemail=request.getParameter("uemail");
		String upass=request.getParameter("upass");
				
		if(age >= 18) {
			try {
				Configuration cfg=new Configuration();
				cfg.configure("flyAway.cfg.xml");
				SessionFactory factory=cfg.buildSessionFactory();	
				cxList cxl=new cxList();
				cxl.setUname(uname);
				cxl.setFname(fname);
				cxl.setLname(lname);
				cxl.setGender(gender);
				cxl.setAge(age);
				cxl.setUmonum(umonum);
				cxl.setUemail(uemail);
				cxl.setUpass(upass);
				Session session=factory.openSession();
				Transaction tx=session.beginTransaction();					
				session.save(cxl);
				tx.commit();								
				session.close();
				factory.close();
				RequestDispatcher rd=request.getRequestDispatcher("/login.html");
				rd.include(request, response);
				out.print("<h2>Signup Successful</h2>");
				
			} catch (Exception e) {
				RequestDispatcher rd = request.getRequestDispatcher("/signup.html");
				rd.include(request, response);
				out.print("<h2>*Username or Mobile Number already exist.</h2>");
			}
			}else {
				RequestDispatcher rd=request.getRequestDispatcher("/signup.html");
				rd.include(request, response);
				out.print("Note*: Age should be minimum 18 years.");
			}
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
