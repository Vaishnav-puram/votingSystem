package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDaoImpl;
import pojos.User;


@WebServlet(urlPatterns = "/validate",loadOnStartup = 1)
public class Login extends HttpServlet {
	UserDaoImpl userDaoImpl;
	public void init(ServletConfig config) throws ServletException {
		System.out.println("IN init()"+Thread.currentThread().getName());
		try {
			userDaoImpl=new UserDaoImpl();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServletException("exception in init()", e);
		}
	}

	
	public void destroy() {
		try {
			userDaoImpl.cleanUp();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		try(PrintWriter pw=response.getWriter()) {
			System.out.println("inside doPost of Login servlet");
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			System.out.println(email+" "+password);
			User u=userDaoImpl.authenticateUser(email, password);
			System.out.println("user --> "+u);
			HttpSession session=request.getSession();
			session.setAttribute("user_info", u);
			System.out.println(session.getAttribute("user_info"));
			if(u!=null) {
				System.out.println("Role -->"+u.getRole());
				if(u.getRole().equals("admin")) {
					response.sendRedirect("admin_page");
				}else {
					if(!u.isVotingStatus()) {
						response.sendRedirect("userHome");
					}else {
						response.sendRedirect("userHome2");
					}
				}
			}else {
				pw.print("Invalid credentials ! <a href=\"login.html\">Signin again ?</a> ");
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}

}
