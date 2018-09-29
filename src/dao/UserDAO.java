package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;
import util.DBConn;

public class UserDAO {
	
	public static final int SUCCESS = 0;
	public static final int ACCOUNT_ALREADY_EXISTS = 1;
	public static final int ACCOUNT_DOESNT_EXISTS = 2;
	public static final int PASSWORD_INCORRECT = 3;
	public static final int UNKNOWN_EXCEPTION = 4;
	
	public int insert(User u) {
		int code = this.SUCCESS;
		String sql = "insert into user values(null,?,?,?)";
		try(Connection c = new DBConn().getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, u.getEmail());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getAvatar());
			
			ps.execute();
			//根据执行结果返回自增的id
			ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                u.setId(id);;
            } 
			
		}catch(Exception e) {
			e.printStackTrace();
			code = this.UNKNOWN_EXCEPTION;
		}
		return code;
	}
	
	public User getUserByEmail(String email) {
		User user = null;
		String sql = "select * from user where email = ?";
		try (Connection c = new DBConn().getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user = new User();
				int id = rs.getInt(1);
				String password = rs.getString(3);
				String avatar = rs.getString(4);
				user.setId(id);
				user.setEmail(email);
				user.setPassword(password);
				user.setAvatar(avatar);
			}					
		}catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public Boolean checkPassword(String email, String password) {
		Boolean flag = false;
		String sql = "select * from user where email = ? and password = ?";
		try(Connection c = new DBConn().getConnection();PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				flag = true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return flag;
		
	}

}
