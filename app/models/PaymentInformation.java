package models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import models.customer.Customer;
import play.db.jpa.Model;

@Entity
public class PaymentInformation extends Model {

	public PaymentInformation() {
		this.cardMonthExp = "12";
		this.cardNumber = "";
		this.cvv = "";
		this.holderFirstName = "";
		this.holderLastName = "";
		this.paymentOption = PaymentOption.MASTERCARD;
		this.cardYearExp = "14";
	}

	public String cardNumber;
	public PaymentOption paymentOption;
	public String cardMonthExp;
	public String cardYearExp;
	public String cvv;
	public String holderFirstName;
	public String holderLastName;

	public String getFirstFourDigits() {
		return this.cardNumber.substring(0, 4);
	}

	public String getSecondFourDigits() {
		return this.cardNumber.substring(4, 8);
	}

	public String getThirdFourDigits() {
		return this.cardNumber.substring(8, 12);
	}

	public String getForthFourDigits() {
		return this.cardNumber.substring(12, 16);
	}

}
