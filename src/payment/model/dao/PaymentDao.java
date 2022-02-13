package payment.model.dao;

import java.io.FileReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import payment.model.vo.Payment;

public class PaymentDao {
	
	private Connection conn = null;
	private Properties prop = null;

	public PaymentDao (Connection conn){
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
	
	
	public List<Payment> selectAll(){
		
		List <Payment> list = new ArrayList<Payment>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = prop.getProperty("PaymentDao_selectAll");
			// SELECT * FROM TABEL_PAYMENT;
			
			pstmt = conn.prepareStatement(sql); 
			rs = pstmt.executeQuery();
			
			while(rs.next() == true) {
				String paymentNum		= rs.getString("PAYMENT_NUM");    
				String cardInfo			= rs.getString("CARD_INFO");      
				int payment             = rs.getInt("PAYMENT");           
				String paymentEnd       = rs.getString("PAYMNET_END");    
				int mileage             = rs.getInt("MILEAGE");           
				String reservationNum   = rs.getString("RESERVATION_NUM");
				String psptNum			= rs.getString("PSPT_NUM");
				
				Payment p = new Payment(paymentNum,cardInfo,payment,paymentEnd,mileage,reservationNum,psptNum);
				list.add(p);
				
			}
			rs.close();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Payment> selectPSPT(String searchPSPT) {
		List<Payment> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = prop.getProperty("PaymentDao_selectPSPT");
			// SELECT * FROM TABLE_PAYMENT WHERE PSPT_NUM = ?
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchPSPT);
						
			rs = pstmt.executeQuery();
			
			while(rs.next() == true) {
				String paymentNum		= rs.getString("PAYMENT_NUM");    
				String cardInfo			= rs.getString("CARD_INFO");      
				int payment             = rs.getInt("PAYMENT");           
				String paymentEnd       = rs.getString("PAYMNET_END");    
				int mileage             = rs.getInt("MILEAGE");           
				String reservationNum   = rs.getString("RESERVATION_NUM");
				String psptNum			= rs.getString("PSPT_NUM");
				
				Payment p = new Payment(paymentNum,cardInfo,payment,paymentEnd,mileage,reservationNum,psptNum);
				
				list.add(p);
			}
			rs.close();
			pstmt.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<String> searchPaymentNum(String searchPSPT) {
		List<String> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = prop.getProperty("PaymentDao_searchPaymentNum");
			// SELECT PAYMENT_NUM FROM TABLE_PAYMENT WHERE PSPT_NUM = ?
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchPSPT);
						
			rs = pstmt.executeQuery();
			
			while(rs.next() == true) {
				String paymentNum		= rs.getString("PAYMENT_NUM");    
								
				list.add(paymentNum);
			}
			rs.close();
			pstmt.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int updatePSPT(String pNum, String pspt) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = prop.getProperty("PaymentDao_updatePSPT");
			// UPDATE TABLE_PAYMENT SET PSPT_NUM = ? WHERE PAYMENT_NUM = ?
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pspt);
			pstmt.setString(2, pNum);
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Payment selectNum(String payNum) {
		Payment p = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = prop.getProperty("PaymentDao_selectNum");
			// SELECT * FROM TABLE_PAMENT WHERE PAYMENT_NUM = ?
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, payNum);
			
			rs = pstmt.executeQuery();
			rs.next();
			
			String paymentNum		= rs.getString("PAYMENT_NUM");    
			String cardInfo			= rs.getString("CARD_INFO");      
			int payment             = rs.getInt("PAYMENT");           
			String paymentEnd       = rs.getString("PAYMNET_END");    
			int mileage             = rs.getInt("MILEAGE");           
			String reservationNum   = rs.getString("RESERVATION_NUM");
			String psptNum			= rs.getString("PSPT_NUM");       
			
			p = new Payment(paymentNum,cardInfo,payment,paymentEnd,mileage,reservationNum,psptNum);
			
			rs.close();
			pstmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	
	
	//insert
	public int insertPayment(Payment payment) {	
				
		PreparedStatement pstmt = null;
		
		try {
			String sql = prop.getProperty("PaymentDao_insertPayment");
			// INSERT INTO TABLE_PAYMENT VALUES(?, ?, ?, ?, ?, ?, ?)
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString	(1, payment.getPaymentNum());
			pstmt.setString	(2, payment.getCardInfo());
			pstmt.setInt	(3, payment.getPayment());
			pstmt.setString	(4, payment.getPaymentEnd());
			pstmt.setInt	(5, payment.getMileage());
			pstmt.setString	(6, payment.getReservationNum());
			pstmt.setString	(7, payment.getPsptNum());
			
			int result = pstmt.executeUpdate();
			
			return result;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//delete
	public int deletePayment(String payNum) {
		PreparedStatement pstmt = null;
		
		try {
			String sql = prop.getProperty("deletePayment");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, payNum);
			int result = pstmt.executeUpdate();
		
			pstmt.close();
			
			return result;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
}
