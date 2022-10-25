package com.chatapp.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.chatapp.dto.UserDTO;


public class UserDAO {
	CommonDAO commonDAO = CommonDAO.getInstance();
	public String doLogin(UserDTO userDTO) throws ClassNotFoundException, SQLException {
		System.out.println(userDTO.getUserid()+" "+userDTO.getPassword());
		Connection con = null;
		//Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = commonDAO.createConnection();
			//stmt = con.createStatement();
			pstmt = con.prepareStatement("select userid from user1 where userid=? and password=? and pincode=?");
			pstmt.setString(1, userDTO.getUserid());
			pstmt.setString(2, new String(userDTO.getPassword()));
			pstmt.setInt(3, Integer.parseInt(userDTO.getPincode()));
			// select userid from users where userid = 'amit' and password ='a1111'
			rs = pstmt.executeQuery();
//			 rs = stmt.executeQuery("select userid from users where"
//					+ " userid ='"+userDTO.getUserid()+"' and password ='"
//					 +new String(userDTO.getPassword())+"'"+" and pincode="+userDTO.getPincode());
		
			if(rs.next()) {
				return "Welcome "+rs.getString("userid");
//			if(con == null) {
//			System.out.println("Connection Fail");
//			return "Connection Fail ";
		}
		else {
			return "Invalid Userid or Password";
		}
		}
		finally {
			if(rs!=null) {
				rs.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
//			if(stmt!=null) {
//				stmt.close();
//			}
			if(con!=null) {
			con.close();
			}
		}
		
	}
	
	public String register(UserDTO userDTO) throws ClassNotFoundException, SQLException {
		Connection con = null;
		Statement stmt = null;
		try {
		 con = commonDAO.createConnection();
		 stmt = con.createStatement(); // Query
		// insert into users (userid, password) values ('amit','amit12',111);
		int count = stmt.executeUpdate("insert into user1 (userid, password,pincode) "
				+ "values('"+userDTO.getUserid()+"','"+new String(userDTO.getPassword())+"',"+
				userDTO.getPincode()+")");
		if(count>0) {
			return "Record Added ";
		}
		else {
			return "Record Not Added ";
		}
		}
		finally {
		if(stmt!=null) {	
		stmt.close();
		}
		if(con!=null) {
		con.close();
		}
		}
	}

}
