package business.model.insurance;

import math.abstraction.Process;
import math.abstraction.RandomValue;

import org.json.JSONException;

import application.Builder;
import business.abstraction.IBusinessProcess;
import exception.CreateModelException;

public class FinanceFlow implements IBusinessProcess
{
	private Process process = null;

	private RandomValue randomValue = null;

	private int flowType = 1;

	public FinanceFlow()
	{
	}

	public void setProcess(Process processName, String processParams)
	{
		this.process = processName;
	}

	public void setProcess(String process) throws CreateModelException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, JSONException
	{
		this.process = Builder.createProcess(process);
	}

	public void setRandomValue(RandomValue value)
	{
		this.randomValue = value;
	}

	public void setRandomValue(String randomValue) throws CreateModelException, InstantiationException,
			IllegalAccessException, ClassNotFoundException, JSONException
	{
		this.randomValue = Builder.createRandomValue(randomValue);
	}

	public void setIncome(boolean isIncome)
	{
		if (isIncome)
			flowType = 1;
		else
			flowType = -1;
	}

	@Override
	public InsuranceEvent nextBusinessEvent()
	{
		InsuranceEvent event = new InsuranceEvent();
		event.setTime(process.nextValue());
		event.setAmount(flowType * randomValue.nextValue());
		event.setBusinessProcess(this);
		return event;
	}
}
