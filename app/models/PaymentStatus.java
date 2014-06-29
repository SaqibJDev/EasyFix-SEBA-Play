package models;

public enum PaymentStatus {
	PAID(1), PENDING(0);

    private int index;

    private PaymentStatus(int index) {
        this.index = index;
    }

    /**
     * @return rating value
     */
    public int getIndex() {
        return this.index;
    }
}
