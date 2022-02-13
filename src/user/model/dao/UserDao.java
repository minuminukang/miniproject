package user.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import user.model.vo.User;


public class UserDao {

	private Connection conn = null;
	private Properties prop = null;

	public UserDao(Connection conn) {
		this.conn = conn;
		prop = new Properties();
		FileReader fr;
		FileReader fr2;
		try {
			fr = new FileReader("./resources/data-source.properties");
			fr2 = new FileReader("./resources/query.properties");
			prop.load(fr);
			prop.load(fr2);
			fr.close();
			fr2.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public List<User> selectAll() {
		List <User> list = new ArrayList<User>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = prop.getProperty("UserDao_selectAll"); //유저id순으로

			pstmt = conn.prepareStatement(sql); 
			rs = pstmt.executeQuery();

			while(rs.next() == true) {
				String userId      = rs.getString("USER_ID"); 	
				String userPw	     = rs.getString("USER_PW");
				String name			 = rs.getString("USER_NAME");
				String userSSN 	 	 = rs.getString("USER_SSN");
				String phone 	 	 = rs.getString("PHONE");
				String email 	 	 = rs.getString("EMAIL");
				String psptNum 	     = rs.getString("PSPT_NUM");
				String airlineMemberNum  = rs.getString("AIRLINE_MEMBERNUM");
				int mileage		     = rs.getInt("MILEAGE");

				User user = new User(userId, userPw, name, userSSN, phone, email, psptNum, airlineMemberNum, mileage);
				list.add(user);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list; 
	}

	public User selectId(String id) {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = prop.getProperty("UserDao_selectId");
			pstmt = conn.prepareStatement(sql); 

			pstmt.setString(1, id); 

			rs = pstmt.executeQuery();
			rs.next();

			String userId      = rs.getString("USER_ID"); 	
			String userPw	     = rs.getString("USER_PW");
			String name			 = rs.getString("USER_NAME");
			String userSSN 	 	 = rs.getString("USER_SSN");
			String phone 	 	 = rs.getString("PHONE");
			String email 	 	 = rs.getString("EMAIL");
			String psptNum 	     = rs.getString("PSPT_NUM");
			String airlineMemberNum  = rs.getString("AIRLINE_MEMBERNUM");
			int mileage		     = rs.getInt("MILEAGE");

			user = new User(userId, userPw, name, userSSN, phone, email, psptNum, airlineMemberNum, mileage);
						
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return user;
	}

	public List<User> selectName(String name2) {

		List <User> list = new ArrayList<User>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = prop.getProperty("UserDao_selectName");

			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, name2);

			rs = pstmt.executeQuery();

			while(true) {
				if(rs.next() == true) {
					String userId      = rs.getString("USER_ID"); 	
					String userPw	     = rs.getString("USER_PW");
					String name			 = rs.getString("USER_NAME");
					String userSSN 	 	 = rs.getString("USER_SSN");
					String phone 	 	 = rs.getString("PHONE");
					String email 	 	 = rs.getString("EMAIL");
					String psptNum 	     = rs.getString("PSPT_NUM");
					String airlineMemberNum  = rs.getString("AIRLINE_MEMBERNUM");
					int mileage		     = rs.getInt("MILEAGE");

					User user = new User(userId, userPw, name, userSSN, phone, email, psptNum, airlineMemberNum, mileage);
					list.add(user);
				} else {
					break;
				}
				rs.close();
				pstmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list; 
	}

	public List<String> checkId() {

		List <String> list = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = prop.getProperty("UserDao_checkId");

			pstmt = conn.prepareStatement(sql); 
			
			rs = pstmt.executeQuery();

			while(rs.next() == true) {

				String userId = rs.getString("USER_ID"); 	

				list.add(userId);
			}
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list; 
	}
	
	public int insertUser(User user) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = prop.getProperty("UserDao_insertUser");

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getUserSSN());
			pstmt.setString(5, user.getPhone());
			pstmt.setString(6, user.getEmail());
			pstmt.setString(7, user.getPsptNum());
			pstmt.setString(8, user.getAirlineMemberNum());
			pstmt.setInt(9, user.getMilage());		

			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result; 
	}

	public int updateUser(String id, String userPw, String phone, String email) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = prop.getProperty("UserDao_updateUser");
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userPw);
			pstmt.setString(2, phone);
			pstmt.setString(3, email);
			pstmt.setString(4, id);
			
			result = pstmt.executeUpdate();
		
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	
	public int updateAirlineNum(String id, String num) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = prop.getProperty("UserDao_updateAirlineNum");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, num);
			pstmt.setString(2, id);
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public List<String> readPSPT() {

		List <String> list = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = prop.getProperty("UserDao_readPSPT");
			// SELECT PSPT_NUM FROM TABLE_USER

			pstmt = conn.prepareStatement(sql); 

			rs = pstmt.executeQuery();

			while(rs.next() == true) {

				String pspt = rs.getString("PSPT_NUM"); 	

				list.add(pspt);
			}
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int updatePSPT(String id, String num) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = prop.getProperty("UserDao_updatePSPT");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.setString(2, id);
			
			result = pstmt.executeUpdate();
				
			pstmt.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}

	public int deleteUser(String id) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = prop.getProperty("UserDao_deleteUser");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}
}
