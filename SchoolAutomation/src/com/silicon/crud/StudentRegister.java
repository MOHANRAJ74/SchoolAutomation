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

@WebServlet("/StudentRegister")

/*This is used for Register the Student Details*/
public class StudentRegister extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		/*Create object for Connection Class*/
		JDBCConnection jdbc=new JDBCConnection();
		/*Creating object for Bean class*/
		StudentDetails sd=new StudentDetails();
		
		try {
			/*Reuse the Connection from JDBC Connection Class*/
			Connection con=jdbc.getConnection();
			String sql="insert into studentdetails(rno,sname,fname,mobile,email,dob,gender,religion,std,address,city,pincode)values(?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
		
			/*Get the values from HTML and store the data.*/
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
			
			/*Insert the values in database using Bean Class*/
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
			ps.setInt(1, sd.getRno());
			ps.setString(2, sd.getSname());
			ps.setString(3, sd.getFname());
			ps.setString(4, sd.getMobile());
			ps.setString(5, sd.getEmail());
			ps.setString(6, sd.getDob());
			ps.setString(7, sd.getGender());
			ps.setString(8, sd.getRegion());
			ps.setInt(9, sd.getStd());
			ps.setString(10, sd.getAddress());
			ps.setString(11, sd.getCity());
			ps.setInt(12, sd.getPincode());
			
			int update=ps.executeUpdate();
			if(update>0) {
				RequestDispatcher rd=request.getRequestDispatcher("studentlist.jsp");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd=request.getRequestDispatcher("studentregister.html");
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
