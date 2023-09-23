package com.silicon.crud;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginControl")

/*This Class used to response the user while login. it pass the values to Login validate class and validate the credentials*/
public class LoginControl extends HttpServlet {
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter pw=response.getWriter();
			HttpSession hs=request.getSession();
			
			/*Get the values from HTML and process the Credentials*/
			String username=request.getParameter("uname");
			String password=request.getParameter("pass");
			
			if(username.equals("admin") && password.equals("admin")) {
				RequestDispatcher rd=request.getRequestDispatcher("adminview.jsp");
				rd.forward(request, response);
			
			}else if(LoginValidate.validate(username, password)) {
				
				hs.setAttribute("uname",username);
				
				RequestDispatcher rd=request.getRequestDispatcher("facultyview.jsp");
				rd.forward(request, response);
			}else {
				pw.println("Login Failed");
				RequestDispatcher rd=request.getRequestDispatcher("Login.html");
				
				rd.include(request, response);
			}		
	}
}
