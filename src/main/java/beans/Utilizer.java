package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Utilizer {
	
	private String firstName;
	private String lastName;
	private String birthday;
	private String email;
	private String phoneNumber;
	private String password;
	private String status = "user";
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {//from string input
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYS as SYSDBA", "q");
    }
	
	public void registration()
	{
		String sql ="INSERT INTO utilizer (first_name, second_name, birthay, email, phone_number, password, status)" + " VALUES (?, ?, ?, ?, ?, ?, ?)";
		Connection conn;
		PreparedStatement pstmt;
		try {
			conn = connect();
			 pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		} catch (SQLException e) {
			throw new Error("Problem", e);
		}

		//set values to the request
		try
		{
		    pstmt.setString(1, this.firstName);
		    pstmt.setString(2, this.lastName);
		    pstmt.setString(3, this.birthday);
		    pstmt.setString(4, this.email);
		    pstmt.setString(5, this.phoneNumber);
		    pstmt.setString(6, this.password);
		    pstmt.setString(7, this.status);
		    pstmt.executeUpdate();
		}
		catch(SQLException ex) {
		    throw new Error("Problem", ex);
		}
		
	}
	
	    public String authentication()
	    {
	    	 String sql = "SELECT password FROM Users WHERE login = '" + email +"'";

	    	 Connection conn;
				PreparedStatement pstmt;
				try {
					conn = connect();
					 pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

				} catch (SQLException e) {
					throw new Error("Problem", e);
				}
	    	 
				String user_password = "";
				try {
					ResultSet res = pstmt.executeQuery();
					res.next();
					user_password = res.getString("password");
				} catch (SQLException e) {
					
				}
 	            return user_password;
	    }

}
