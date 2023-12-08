package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDaoImpl;
import dao.UserDaoImpl;
import pojos.User;

/**
 * Servlet implementation class UserHome
 */
@WebServlet("/userHome")
public class UserHome extends HttpServlet {
	UserDaoImpl userDaoImpl;
	CandidateDaoImpl candidateDaoImpl;
	public void init(ServletConfig config) throws ServletException {
		try {
			userDaoImpl=new UserDaoImpl();
			candidateDaoImpl=new CandidateDaoImpl();
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
	}

	@Override
	public void destroy() {
		try {
			userDaoImpl.cleanUp();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		try(PrintWriter pw=response.getWriter()){
			HttpSession session=request.getSession();
			User u=(User) session.getAttribute("user_info");
			
			pw.write("Hello "+u.getFirstName()+" "+u.getLastName()+" your voting status : "+u.isVotingStatus());
			pw.println();
			pw.print("<a href=\"castVote.html\">Click here to vote</a>");
		}
	}

}
