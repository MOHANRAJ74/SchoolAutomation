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

@WebServlet(name = "RegisterFaculty", urlPatterns = { "/RegisterFaculty" })

/*This Class is used for Register the faculty*/
public class FacultyRegister extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Create object for Connection Class*/
		JDBCConnection jdbc=new JDBCConnection();
		/*Creating object for Bean class*/
		FacultyDetails fd=new FacultyDetails();
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		try {
		/*Reuse the connection*/
		Connection con=jdbc.getConnection();
		String sql="insert into faculty (facid,facname,password,facmobile,facmail,address) values(?,?,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		
		String facid=request.getParameter("fid");
		int fid=Integer.parseInt(facid);
		String facname=request.getParameter("fname");
		String pass=request.getParameter("pass");
		String mobile=request.getParameter("mobile");
		String email=request.getParameter("mail");
		String address=request.getParameter("address");
		
		/*Insert the values using java Bean class Setter and Getter method*/
		fd.setFacultyid(fid);
		fd.setFacultyname(facname);		
		fd.setPassword(pass);
		fd.setFacmob(mobile);
		fd.setFacmail(email);
		fd.setAddress(address);
		
		/*Insert the valus using JDBC PreparedStatement using Bean class*/
		ps.setInt(1, fd.getFacultyid());
		ps.setString(2, fd.getFacultyname());
		ps.setString(3, fd.getPassword());
		ps.setString(4, fd.getFacmob());
		ps.setString(5, fd.getFacmail());
		ps.setString(6, fd.getAddress());
		
		int update=ps.executeUpdate();

		if(update>0) {
			System.out.println("Registration Success");
			RequestDispatcher rd=request.getRequestDispatcher("facultylist.jsp");
			rd.forward(request, response);
		}else {
			System.out.println("Registration Failed");
			RequestDispatcher rd=request.getRequestDispatcher("FacultyRegister.html");
			rd.include(request, response);
		}
		con.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
