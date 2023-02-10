package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.Utilizer;



/**
 * Servlet implementation class SignIn
 */
@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilizer user = new Utilizer();
		
		request.setAttribute("user", user);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MainPage.jsp");;
				
//		try {
//			Class.forName ("oracle.jdbc.driver.OracleDriver");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("Password"));

		if(request.getRequestURI().contains("SignIn"))//registration
		{
			user.setFirstName(request.getParameter("firstName"));
			user.setLastName(request.getParameter("lastName"));
			user.setBirthday(request.getParameter("birthday"));
			user.setPhoneNumber(request.getParameter("phoneNumber"));
			response.getWriter().append("registration");
			//user.registration();
		}
		else
		{
			String rightPassword = user.authentication();
			if(!user.getPassword().equals(rightPassword))
			{
				response.getWriter().append("authentication failed");

				//dispatcher = getServletContext().getRequestDispatcher("/error.jsp");				
			}
			response.getWriter().append("authentication successed");

		}
			//dispatcher.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
