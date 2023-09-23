<%@page import="com.silicon.model.StudentDetails"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.silicon.model.FacultyDetails"%>
<%@page import="com.silicon.core.JDBCConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Details</title>
</head>
<body>
		<form action="logout" method="post" align="right">
		<input type="submit" value="Logout">
		</form>
	<center>
		<h3>Student Details</h3>
		<%
			/*Create Object for JDBC Connection class*/		
			JDBCConnection jdbc=new JDBCConnection();
			/*Create object for BEan class*/
			StudentDetails std=new StudentDetails();
			String sql="select * from studentdetails";
			/*Reuse connection from JDBC connection class*/
			Connection con=jdbc.getConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
          	
			out.println("<html><body>");
			out.println("<table border='3' width='90%' height='35%'>"); 
            out.println("<tr><th>Rollno</th><th>Student Name</th><th>Father's Name</th><th>Mobile</th><th>E-Mail</th><th>DOB</th><th>Gender</th><th>Religion</th><th>STD</th><th>Address</th><th>City</th><th>Pincode</th></tr>"); 
 	
            /*Select and view using JDBC Resultset and Bean class Getter and Setter method*/
			while(rs.next()){
				std.setRno(rs.getInt(1));
				std.setSname(rs.getString(2));
				std.setFname(rs.getString(3));
				std.setMobile(rs.getString(4));
				std.setEmail(rs.getString(5));
				std.setDob(rs.getString(6));
				std.setGender(rs.getString(7));
				std.setRegion(rs.getString(8));
				std.setStd(rs.getInt(9));
				std.setAddress(rs.getString(10));
				std.setCity(rs.getString(11));
				std.setPincode(rs.getInt(12));

				int rno=std.getRno();
				String sname=std.getSname();
				String fname=std.getFname();
				String mobile=std.getMobile();
				String email=std.getEmail();
				String dob=std.getDob();
				String gender=std.getGender();
				String religion=std.getRegion();
				int stdd=std.getStd();
				String address=std.getAddress();
				String city=std.getCity();
				int pincode=std.getPincode();

				out.println("<tr><td>"+rno+"</td><td>"+sname+"</td><td>"+fname+"</td><td>"+mobile+"</td><td>"+email+"</td><td>"
				+dob+"</td><td>"+gender+"</td><td>"+religion+"</td><td>"+stdd+"</td><td>"+address+"</td><td>"+city+"</td><td>"+pincode+"</td></tr>");		
			}
			out.println("</table>");	
			con.close();
		%>
	</center>
</body>
</html>