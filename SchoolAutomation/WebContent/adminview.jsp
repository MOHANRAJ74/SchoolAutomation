<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Portal</title>
</head>
<body>
		<form action="logout" method="post" align="right">
		<input type="submit" value="Logout">
		</form>
	<center>
		<h3>Admin Portal</h3>
		
		<form action="adminuse" method="post">
		<table>
			<tr><td><a href="FacultyRegister.html">Faculty Register</a></td></tr><tr></tr>
			<tr><td><a href="facultylist.jsp">Faculty List</a></td></tr><tr></tr>
			<tr><td><a href="updatefaculty.html">Update Faculty</a></td></tr><tr></tr>
			<tr><td><a href="deletefaculty.jsp">Delete Faculty</a></td></tr><tr></tr>
			
			<tr><td><a href="studentregister.html">Student Register</a></td></tr><tr></tr>
			<tr><td><a href="studentlist.jsp">Student List</a></td></tr><tr></tr>
			<tr><td><a href="updatestudent.html">Update Student</a></td></tr><tr></tr>
			<tr><td><a href="deletestudent.jsp">Delete Student</a></td></tr><tr></tr>
			
			<tr><td><a href="markregister.html">Mark Register</a></td></tr><tr></tr>
			<tr><td><a href='viewmark.jsp'>View Mark</a></td></tr><tr></tr>
			<tr><td><a href="updatemark.html">Update Mark</a></td></tr><tr></tr>
			<tr><td><a href="deletemark.jsp">Delete Mark</a></td></tr><tr></tr>
		</table>
		</form>
	</center>

</body>
</html>