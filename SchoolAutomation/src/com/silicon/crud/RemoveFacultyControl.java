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

@WebServlet("/RemoveFacultyControl")

/*This Controller class for Delete the Faculty details*/
public class RemoveFacultyControl extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Create object for Connection Class*/
		JDBCConnection jdbc=new JDBCConnection();
		/*Creating object for Bean class*/
		FacultyDetails fd=new FacultyDetails();
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		try 
         {  
			 /*Reuse the Connection from JDBC Connection Class*/
             Connection con=jdbc.getConnection(); 
             String sql="delete from faculty where facid=?";           
             PreparedStatement ps=con.prepareStatement(sql);
             String dele=request.getParameter("deluname");
             
            int i=Integer.valueOf(dele);
            ps.setInt(1,i);
                          
            int rinsert=ps.executeUpdate();
 			if(rinsert>0) {
 				out.println("Record Deleted");
 				RequestDispatcher rd=request.getRequestDispatcher("facultylist.jsp");
 				rd.forward(request, response);
 			}else {
 				out.println("Records Deleted Failed");
 				RequestDispatcher rd=request.getRequestDispatcher("deletefaculty.jsp");
 				rd.include(request, response);
 			}
 			con.close();
         }catch(Exception e) {
        	 out.println(e);
         }
}
}