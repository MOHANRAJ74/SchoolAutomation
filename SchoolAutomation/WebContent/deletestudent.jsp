<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.silicon.model.StudentDetails"%>
<%@page import="com.silicon.model.FacultyDetails"%>
<%@page import="com.silicon.core.JDBCConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Student</title>
</head>
<body>
		<form action="logout" method="post" align="right">
		<input type="submit" value="Logout">
		</form>
	
		<h2>Faculty Details</h2>
		<%
			JDBCConnection jdbc=new JDBCConnection();
			StudentDetails stdd=new StudentDetails();
			String sql="select * from studentdetails";
			Connection con=jdbc.getConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
          	
			out.println("<html><body>");
			out.println("<form action='StudentDeleteControl' method='post' align='right'>");
			out.println("<input type='text' name='rollno' placeholder='Roll No'>");
			out.println("<input type='submit' value='Delete'>");

			out.println("</center>");
			out.println("<table border='3' width='90%' height='35%'>"); 
            out.println("<tr><th>Rollno</th><th>Student Name</th><th>Father's Name</th><th>Mobile</th><th>E-Mail</th><th>DOB</th><th>Gender</th><th>Religion</th><th>Standard</th><th>Address</th><th>City</th><th>Pincode</th></tr>"); 
 
			while(rs.next()){
				stdd.setRno(rs.getInt(1));
				stdd.setSname(rs.getString(2));
				stdd.setFname(rs.getString(3));
				stdd.setMobile(rs.getString(4));
				stdd.setEmail(rs.getString(5));
				stdd.setDob(rs.getString(6));
				stdd.setGender(rs.getString(7));
				stdd.setRegion(rs.getString(8));
				stdd.setStd(rs.getInt(9));
				stdd.setAddress(rs.getString(10));
				stdd.setCity(rs.getString(11));
				stdd.setPincode(rs.getInt(12));
				
				int rno=stdd.getRno();
				String sname=stdd.getSname();
				String fname=stdd.getFname();
				String mobile=stdd.getMobile();
				String email=stdd.getEmail();
				String dob=stdd.getDob();
				String gender=stdd.getGender();
				String religion=stdd.getRegion();
				int std=stdd.getStd();
				String address=stdd.getAddress();
				String city=stdd.getCity();
				int pincode=stdd.getPincode();
				
				
	            out.println("<tr><td>"+rno+"</td><td>"+sname+"</td><td>"+fname+"</td><td>"+mobile+"</td><td>"+email+"</td><td>"+dob+"</td><td>"+gender+"</td><td>"+religion+"</td><td>"+std+"</td><td>"+address+"</td><td>"+city+"</td><td>"+pincode+"</td></tr>");		
			}
			out.println("</table></center>");
			con.close();
		%>
	
</body>
</html>