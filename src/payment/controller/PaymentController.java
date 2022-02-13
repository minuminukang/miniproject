package payment.controller;

import java.util.List;

import payement.service.PaymentService;
import payment.model.vo.Payment;

public class PaymentController {
	private PaymentService paymentService = new PaymentService();
	
	public List<Payment> selectAll(){
		return paymentService.selectAll();
	}
	
	public List<Payment> selectPSPT(String searchPSPT) {
		return paymentService.selectPSPT(searchPSPT);
	}
		
	public List<String> searchPaymentNum(String searchPSPT) {
		return paymentService.searchPaymentNum(searchPSPT);
	}
	
	public int updatePSPT(String pNum, String pspt) {
		return paymentService.updatePSPT(pNum, pspt);
	}
	
	public Payment selectNum(String payNum) {
		return paymentService.selectNum(payNum);
	}
	
	public int insertPayment(Payment p) {	
		return paymentService.insertPayment(p);
	}
	
	public int deletePayment(String payNum) {
		return paymentService.deletePayment(payNum);
	}
}
