package pages;


import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SecondServlet extends HttpServlet {
	
	/*
	 * web container creates a map
	 * key : /test
	 * value : pages.FirstServlet
	 * */
	public void init(ServletConfig config) throws ServletException {
		
		System.out.println("in init()"+Thread.currentThread().getName());
	}

	
	public void destroy() {
		
		System.out.println("in destroy()"+Thread.currentThread().getName());
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("in doGet()"+Thread.currentThread().getName());
		//set response content-type
		response.setContentType("text/html");
		//get the print writer to send the response-buffered character response from servlet to client
		try(PrintWriter p=response.getWriter()){
			p.print("<html><body><br>");
			p.print("<h1>Hello vaishnav !, from Second Servlet class</h1><br>");
			p.write("<h2>Servlet class : "+getClass()+" "+LocalDateTime.now()+"</h2>");
			p.print("</body></html>");
			
		}
		
	}

}
