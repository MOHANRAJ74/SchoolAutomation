package com.silicon.crud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.silicon.core.JDBCConnection;
import com.silicon.model.FacultyDetails;

@WebServlet("/UpdateFaculty")

/*This class uesed for Updating the Faculty details.*/
public class UpdateFaculty extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Create object for Connection Class*/
		JDBCConnection jdbc=new JDBCConnection();	
		/*Creating object for Bean class*/
		FacultyDetails fd=new FacultyDetails();
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		try {
		/*Reuse the Connection from JDBC Connection Class*/
		Connection con=jdbc.getConnection();
		String sql="update faculty set facname=?, password=?, facmobile=?, facmail=?, address=? where facid=?";
		PreparedStatement ps=con.prepareStatement(sql);
		
		String facid=request.getParameter("facid");
		int fid=Integer.parseInt(facid);
		String facname=request.getParameter("facname");
		String pass=request.getParameter("pass");
		String confpass=request.getParameter("confpass");
		String mobile=request.getParameter("mobile");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		
		/*update using Set method of Bean class*/
		fd.setFacultyid(fid);
		fd.setFacultyname(facname);		
		if(pass.equals(confpass)) {
			fd.setPassword(pass);
		}else {
			out.println("Password doesn't match");	
		}
		fd.setFacmob(mobile);
		fd.setFacmail(email);
		fd.setAddress(address);
		
		/*Set the values to JDBC using PreparedStatement via Bean class Get method*/
		ps.setString(1, fd.getFacultyname());
		ps.setString(2, fd.getPassword());
		ps.setString(3, fd.getFacmob());
		ps.setString(4, fd.getFacmail());
		ps.setString(5, fd.getAddress());
		ps.setInt(6, fd.getFacultyid());
		
		int update=ps.executeUpdate();

		if(update>0) {
			System.out.println("Update Success");
			RequestDispatcher rd=request.getRequestDispatcher("facultylist.jsp");
			rd.forward(request, response);
		}else {
			System.out.println("Update Failed");
			RequestDispatcher rd=request.getRequestDispatcher("updatefaculty.html");
			rd.include(request, response);
		}
		con.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
