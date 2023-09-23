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
import com.silicon.model.StudentDetails;
import com.silicon.model.StudentMark;

@WebServlet("/DeleteMarkControl")
/*This is Controller class for Deleting Mark*/
public class DeleteMarkControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Create object for JDBC Connection class*/
		JDBCConnection jdbc=new JDBCConnection();
		/*Creating object for Bean class*/
		StudentMark sm=new StudentMark();
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");	
		 try 
         {  
			 /*Reuse the connection from Connection class*/
             Connection con=jdbc.getConnection(); 
             String sql="delete from studentmark where rno=?";           
             PreparedStatement ps=con.prepareStatement(sql);
             String dele=request.getParameter("rollno");
             
            int i=Integer.valueOf(dele);
            ps.setInt(1,i);
                          
            int rinsert=ps.executeUpdate();
 			if(rinsert>0) {
 				out.println("Record Deleted");
 				RequestDispatcher rd=request.getRequestDispatcher("viewmark.jsp");
 				rd.forward(request, response);
 			}else {
 				out.println("Records Deleted Failed");
 				RequestDispatcher rd=request.getRequestDispatcher("deletemark.jsp");
 				rd.include(request, response);
 			}
 			con.close();
         }catch(Exception e) {
        	 out.println(e);
         }
}
}