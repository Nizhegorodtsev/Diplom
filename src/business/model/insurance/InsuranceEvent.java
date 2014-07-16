package business.model.insurance;

import business.abstraction.BusinessEvent;

public class InsuranceEvent extends BusinessEvent
{
	public double getAmount()
	{
		return _amount;
	}

	public void setAmount(double amount)
	{
		_amount = amount;
	}

	private double _amount;

	public static String INCOME = "Income";
}
