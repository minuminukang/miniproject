package payment.model.vo;

public class Payment {
	private String paymentNum;
	private String cardInfo;
	private int payment;
	private String paymentEnd;
	private int mileage;
	private String reservationNum;
	private String psptNum;
	
	public Payment() {
		super();
	}

	public Payment(String paymentNum, String cardInfo, int payment, String paymentEnd, int mileage,
			String reservationNum, String psptNum) {
		super();
		this.paymentNum = paymentNum;
		this.cardInfo = cardInfo;
		this.payment = payment;
		this.paymentEnd = paymentEnd;
		this.mileage = mileage;
		this.reservationNum = reservationNum;
		this.psptNum = psptNum;
	}
	
	

	public String getPaymentNum() {
		return paymentNum;
	}

	public void setPaymentNum(String paymentNum) {
		this.paymentNum = paymentNum;
	}

	public String getCardInfo() {
		return cardInfo;
	}

	public void setCardInfo(String cardInfo) {
		this.cardInfo = cardInfo;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public String getPaymentEnd() {
		return paymentEnd;
	}

	public void setPaymentEnd(String paymentEnd) {
		this.paymentEnd = paymentEnd;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public String getReservationNum() {
		return reservationNum;
	}

	public void setReservationNum(String reservationNum) {
		this.reservationNum = reservationNum;
	}

	public String getPsptNum() {
		return psptNum;
	}

	public void setPsptNum(String psptNum) {
		this.psptNum = psptNum;
	}

	@Override
	public String toString() {
		return "Payment [paymentNum=" + paymentNum + ", cardInfo=" + cardInfo + ", payment=" + payment + ", paymentEnd="
				+ paymentEnd + ", mileage=" + mileage + ", reservationNum=" + reservationNum + ", psptNum=" + psptNum
				+ "]";
	}
}
