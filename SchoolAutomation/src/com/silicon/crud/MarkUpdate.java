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
import com.silicon.model.StudentMark;

@WebServlet("/MarkUpdate")
public class MarkUpdate extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		/*Create object for Connection Class*/
		JDBCConnection jdbc=new JDBCConnection();
		/*Creating object for Bean class*/
		StudentMark sm=new StudentMark();
		
		try {
			String sql="update studentmark set stuname=?,tamil=?,english=?,maths=?,science=?,social=?,total=?,avg=? where rno=?";
			/*Reuse the Connection from JDBC Connection Class*/
			Connection con=jdbc.getConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			
			String rno=request.getParameter("rno");
			int rollno=Integer.parseInt(rno);
			String sname=request.getParameter("sname");
			String tam=request.getParameter("tam");
			int tamil=Integer.parseInt(tam);
			String eng=request.getParameter("eng");
			int english=Integer.parseInt(eng);
			String maths=request.getParameter("maths");
			int mat=Integer.parseInt(maths);
			String sci=request.getParameter("sci");
			int science=Integer.parseInt(sci);
			String soc=request.getParameter("soc");
			int social=Integer.parseInt(soc);			
			int tot=tamil+english+mat+science+social;
			int avg=tot/5;
			
			/*Insert the values using Bean class*/
			sm.setRollno(rollno);
			sm.setName(sname);
			sm.setTam(tamil);
			sm.setEng(english);
			sm.setMaths(mat);
			sm.setSci(science);
			sm.setSoc(social);
			sm.setTot(tot);
			sm.setAvg(avg);			
			
			ps.setString(1, sm.getName());
			ps.setInt(2, sm.getTam());
			ps.setInt(3, sm.getEng());
			ps.setInt(4, sm.getMaths());
			ps.setInt(5, sm.getSci());
			ps.setInt(6, sm.getSoc());
			ps.setInt(7, sm.getTot());
			ps.setInt(8, sm.getAvg());
			ps.setInt(9, sm.getRollno());
						
			int insert=ps.executeUpdate();			
			if(insert>0) {
				out.println("Mark Submitted");
				RequestDispatcher rd=request.getRequestDispatcher("viewmark.jsp");
				rd.forward(request, response);
			}else {
				out.println("Not Submitted");
				RequestDispatcher rd=request.getRequestDispatcher("updatemark.html");
				rd.include(request, response);
			con.close();
			}		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
