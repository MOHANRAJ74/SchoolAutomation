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
<title>Faculty Details</title>
</head>
<body>
		<form action="logout" method="post" align="right">
		<input type="submit" value="Logout">
		</form>
	
		<h2>Faculty Details</h2>
		<%
			JDBCConnection jdbc=new JDBCConnection();
			FacultyDetails fd=new FacultyDetails();
			String sql="select * from faculty";
			Connection con=jdbc.getConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
          	
			out.println("<html><body>");
			out.println("<form action='RemoveFacultyControl' method='post' align='right'>");
			out.println("<input type='text' name='deluname' placeholder='Faculty ID'>");
			out.println("<input type='submit' value='Delete'>");

			out.println("</center>");
			out.println("<table border='3' width='90%' height='35%'>"); 
            out.println("<tr><th>Faculty ID</th><th>Faculty Name</th><th>Password</th><th>Mobile</th><th>E-Mail</th><th>Address</th></tr>"); 
 
			while(rs.next()){
				fd.setFacultyid(rs.getInt(1));
				fd.setFacultyname(rs.getString(2));
				fd.setPassword(rs.getString(3));
				fd.setFacmob(rs.getString(4));
				fd.setFacmail(rs.getString(5));
				fd.setAddress(rs.getString(6));
				
				int fid=fd.getFacultyid();
				String name=fd.getFacultyname();
				String pass=fd.getPassword();
				String mob=fd.getFacmail();
				String mail=fd.getFacmail();
				String address=fd.getAddress();
	            out.println("<tr><td>"+fid+"</td><td>"+name+"</td><td>"+pass+"</td><td>"+mob+"</td><td>"+mail+"</td><td>"+address+"</td></tr>");		
			}
			out.println("</table></center>");				
			con.close();
		%>
	
</body>
</html>