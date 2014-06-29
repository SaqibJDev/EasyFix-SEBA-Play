package models;

import javax.persistence.Embeddable;

@Embeddable
public enum PaymentOption {
VISA, MASTERCARD, PAYPAL;
}
