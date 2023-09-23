<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.silicon.model.StudentMark"%>
<%@page import="com.silicon.core.JDBCConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Mark</title>
</head>
<body>
		
		<form action="logout" method="post" align="right">
		<input type="submit" value="Logout">
		</form>
	
	<center>
		<h3>Student Mark</h3>
		<%
			/*Create Object for Connectionc class*/
			JDBCConnection jdbc=new JDBCConnection();
			/*Create object for Bean class*/
			StudentMark sm=new StudentMark();
			String sql="select * from studentmark";
			/*Reuse connection from JDBC Connection Class*/
			Connection con=jdbc.getConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			out.println("<html><body>");
			out.println("<table border='3' width='90%' height='35%'>"); 
            out.println("<tr><th>Rollno</th><th>Student Name</th><th>Tamil</th><th>English</th><th>Maths</th><th>Science</th><th>Social</th><th>Total</th><th>Average</th></tr>"); 
			
            /*Select and view values from database using JDBC Resultset*/
			while(rs.next()){
				sm.setRollno(rs.getInt(1));
				sm.setName(rs.getString(2));
				sm.setTam(rs.getInt(3));
				sm.setEng(rs.getInt(4));
				sm.setMaths(rs.getInt(5));
				sm.setSci(rs.getInt(6));
				sm.setSoc(rs.getInt(7));
				sm.setTot(rs.getInt(8));
				sm.setAvg(rs.getInt(9));
			
				int rno=sm.getRollno();
				String name=sm.getName();
				int tamil=sm.getTam();
				int eng=sm.getEng();
				int mat=sm.getMaths();
				int sci=sm.getSci();
				int soc=sm.getSoc();
				int tot=sm.getTot();
				int avg=sm.getAvg();
			
				out.println("<tr><td>"+rno+"</td><td>"+name+"</td><td>"+tamil+"</td><td>"+eng+"</td><td>"+mat+"</td><td>"+sci+"</td><td>"+soc+"</td><td>"+tot+"</td><td>"+avg+"</td></tr>");	
			}
			out.println("</table>");	
			con.close();
		%>
	</center>
</body>
</html>