package business.model.capital;

import business.abstraction.BusinessEvent;

/**
 * Событие класса FinanceStream. Оповещает об изменении капитала
 * 
 * @author anizhegorodtsev
 *
 */
public class FinanceEvent extends BusinessEvent {

	/**
	 * Величина, на которую изменятеся капитал
	 */
	private double	amount;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
