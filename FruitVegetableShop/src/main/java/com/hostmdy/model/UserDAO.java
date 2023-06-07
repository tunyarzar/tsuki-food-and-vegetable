package com.hostmdy.model;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.eclipse.tags.shaded.org.apache.bcel.generic.RETURN;

import com.hostmdy.crypto.PasswordEncoder;
import com.hostmdy.crypto.PasswordValidator;

public class UserDAO {

	private DataSource dataSource;
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;
	
	
	public UserDAO(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	private void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int createUser (User user) {
		int rowUpdated = 0;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("INSERT INTO `user`"
					+ " (`username`, `email`, `password`, `role`) VALUES "
					+ "(?, ?, ?, ?);"
					+ "");
			
			pStmt.setString(1, user.getUsername());
			pStmt.setString(2, user.getEmail());
			String storedPassword ="";
			try {
				 storedPassword = PasswordEncoder.encode(user.getPassword());
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pStmt.setString(3,storedPassword );
			pStmt.setString(4, user.getRole());
			
			rowUpdated = pStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
	
		return rowUpdated;
	}
	
	public User getUserByEmail(String email) {
		
		User user = null;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("select * from user where email=?");
			pStmt.setString(1,email);
			rs = pStmt.executeQuery();
			
			
			while(rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("username"),  rs.getString("email"),  rs.getString("password"),  rs.getString("role"));
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
		
	}
	
	public boolean isUserValidated(String email,String originalPassword) {
		
		boolean valid = false;
		
		User user = getUserByEmail(email);
		
		if (user != null) {
			String storedPassowrd = user.getPassword();
			
			try {
				if(PasswordValidator.validatePassword(originalPassword, storedPassowrd))
					valid = true;
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return valid;

	}
	
}
