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
import com.silicon.model.StudentDetails;

@WebServlet("/UpdateStudent")
public class UpdateStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		/*Create object for Connection Class*/
		JDBCConnection jdbc=new JDBCConnection();
		/*Create object for Bean class*/
		StudentDetails sd=new StudentDetails();
		
		try {
			/*Reuse connection from Connection class*/
			Connection con=jdbc.getConnection();
			String sql="update studentdetails set sname=?,fname=?,mobile=?,email=?,dob=?,gender=?,religion=?,std=?,address=?,city=?,pincode=? where rno=?";
			PreparedStatement ps=con.prepareStatement(sql);
		
			String rollno=request.getParameter("rno");
			int rno=Integer.parseInt(rollno);
			String sname=request.getParameter("sname");
			String fname=request.getParameter("fname");
			String mobile=request.getParameter("mobile");
			String email=request.getParameter("email");
			String dob=request.getParameter("dob");
			String gender=request.getParameter("gender");
			String region=request.getParameter("region");
			String stdd=request.getParameter("std");
			int std=Integer.parseInt(stdd);
			String address=request.getParameter("address");
			String city=request.getParameter("city");
			String pincode=request.getParameter("pincode");
			int pin=Integer.parseInt(pincode);
					
			sd.setRno(rno);
			sd.setSname(sname);
			sd.setFname(fname);
			sd.setMobile(mobile);
			sd.setEmail(email);
			sd.setDob(dob);
			sd.setGender(gender);
			sd.setRegion(region);
			sd.setStd(std);
			sd.setAddress(address);
			sd.setCity(city);
			sd.setPincode(pin);
			
			/*Set the values to JDBC using Prepared Statement via Bean class Get method*/
			ps.setString(1, sd.getSname());
			ps.setString(2, sd.getFname());
			ps.setString(3, sd.getMobile());
			ps.setString(4, sd.getEmail());
			ps.setString(5, sd.getDob());
			ps.setString(6, sd.getGender());
			ps.setString(7, sd.getRegion());
			ps.setInt(8, sd.getStd());
			ps.setString(9, sd.getAddress());
			ps.setString(10, sd.getCity());
			ps.setInt(11, sd.getPincode());
			ps.setInt(12, sd.getRno());
			
			int update=ps.executeUpdate();
			if(update>0) {
				RequestDispatcher rd=request.getRequestDispatcher("studentlist.jsp");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd=request.getRequestDispatcher("updatestudent.html");
				rd.include(request, response);
			}
		con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
