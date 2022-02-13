package payement.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import payment.model.dao.PaymentDao;
import payment.model.vo.Payment;

public class PaymentService {
	private PaymentDao paymentDao = null;
	private Connection conn = null;
	
	public PaymentService() {
		init();
	}
	
	public void init() {
		if(conn != null) {
			close(conn);
		}
		conn = getConnection();
		paymentDao = new PaymentDao(conn);
	}

	@Override
	protected void finalize() throws Throwable { // 객체 종료시 호출되는 메소드
		close(conn);
	}
	
	public List<Payment> selectAll(){
		paymentDao.setConn(conn);
		return paymentDao.selectAll();
	}
	
	public List<Payment> selectPSPT(String searchPSPT) {
		if(searchPSPT == null) {
			return null;
		}
		return paymentDao.selectPSPT(searchPSPT);
	}
	
	public List<String> searchPaymentNum(String searchPSPT) {
		if(searchPSPT == null) {
			return null;
		}
		return paymentDao.searchPaymentNum(searchPSPT);
	}
	
	public int updatePSPT(String pNum, String pspt) {
		int result = paymentDao.updatePSPT(pNum, pspt);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
	
	public Payment selectNum(String payNum) {
		if(payNum == null) {
			return null;
		}
		return paymentDao.selectNum(payNum);
	}
		
	public int insertPayment(Payment p) {
		
		int result = paymentDao.insertPayment(p);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
	
	public int deletePayment(String payNum) {
		Payment tmpPay = selectNum(payNum);
		if(tmpPay == null) {
			return ERROR_CODE_PAYMENT_DUPLE;
		}
		
		int result = paymentDao.deletePayment(payNum);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

}
