package business.model.insurance;

import business.abstraction.BusinessEvent;

public class FinanceEvent extends BusinessEvent {
    private double       amount;

    public static String INCOME = "Income";

    public double getAmount() {
	return amount;
    }

    public void setAmount(double amount) {
	this.amount = amount;
    }
}
