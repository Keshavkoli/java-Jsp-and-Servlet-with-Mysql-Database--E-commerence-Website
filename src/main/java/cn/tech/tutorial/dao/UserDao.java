package cn.tech.tutorial.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.tech.tutorial.model.User;

public class UserDao {

	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public UserDao() {

	}

	public UserDao(Connection con) {
		this.con = con;
	}

	public User userLogin(String email, String password) {
		User user = null;
		try {
			// checks user exits or not
			query = "select * from users where email=? and password=?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
			 user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				// we will not use the password here becz it is not good practice for the Security purpose
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return user;
	}
}