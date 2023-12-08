package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UserDaoImpl;
import pojos.User;


@WebServlet("/registration")
public class Registration extends HttpServlet {
	UserDaoImpl userDao;
	public void init(ServletConfig config) throws ServletException {
		System.out.println("IN init()"+Thread.currentThread().getName());
		try {
			userDao=new UserDaoImpl();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServletException("exception in init()", e);
		}
	}


	public void destroy() {
		// TODO Auto-generated method stub
		try {
			userDao.cleanUp();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname=request.getParameter("firstname");
		String lname=request.getParameter("lastname");
		String email=request.getParameter("email");
		String pass=request.getParameter("password");
		String dob=request.getParameter("date");
		System.out.println(fname);
		System.out.println(lname);
		System.out.println(email);
		System.out.println(pass);
		System.out.println(dob);
		User user=new User(fname,lname,email,pass,Date.valueOf(dob));
		try(PrintWriter p=response.getWriter()){
			userDao.registerNewVoter(user);
			p.write("Voter registered successfully...");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
