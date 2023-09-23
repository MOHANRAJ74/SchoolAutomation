package com.silicon.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.silicon.core.JDBCConnection;


/*This is used to check and validate the Credentials*/
public class LoginValidate {
	
	public static boolean validate(String loginname,String loginpass){  
		/*Create object for Connection Class*/
		JDBCConnection jdbc=new JDBCConnection();
		boolean status=false;  
		
		try {
			/*Reuse the Connection from JDBC Connection Class*/
			Connection con=jdbc.getConnection();
		
			PreparedStatement ps=con.prepareStatement("select * from faculty where facmail=? and password=?");  
				ps.setString(1,loginname);  
				ps.setString(2,loginpass);  
				      
				ResultSet rs=ps.executeQuery();  
		
				status=rs.next();
				con.close();
		} catch (SQLException | ClassNotFoundException e) {
					e.printStackTrace();
				}  
				          
				return status;  				
	}  				
}
