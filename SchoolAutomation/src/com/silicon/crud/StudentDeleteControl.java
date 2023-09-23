package com.silicon.crud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.silicon.core.JDBCConnection;
import com.silicon.model.FacultyDetails;
import com.silicon.model.StudentDetails;

@WebServlet("/StudentDeleteControl")

/*This is Controller class for Delete Student Details using Bean Class setter and getter method and JDBC*/
public class StudentDeleteControl extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Create object for Connection Class*/
		JDBCConnection jdbc=new JDBCConnection();
		/*Creating object for Bean class*/
		StudentDetails sd=new StudentDetails();
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");	
		 try 
         { 
			 /*Reuse the Connection from JDBC Connection Class*/
             Connection con=jdbc.getConnection(); 
             String sql="delete from studentdetails where rno=?";           
             PreparedStatement ps=con.prepareStatement(sql);
             String dele=request.getParameter("rollno");
             
            int i=Integer.valueOf(dele);
            ps.setInt(1,i);
                          
            int rinsert=ps.executeUpdate();
 			if(rinsert>0) {
 				out.println("Record Deleted");
 				RequestDispatcher rd=request.getRequestDispatcher("studentlist.jsp");
 				rd.forward(request, response);
 			}else {
 				out.println("Records Deleted Failed");
 				RequestDispatcher rd=request.getRequestDispatcher("deletestudent.jsp");
 				rd.include(request, response);
 			}
 			con.close();
         }catch(Exception e) {
        	 out.println(e);
         }
}
}