package business.model.insurance;

import math.abstraction.Process;
import math.abstraction.RandomValue;

import org.json.JSONException;

import application.Builder;
import business.abstraction.IBusinessProcess;
import exception.CreateModelException;

/**
 * Абстракция потока денежных средств
 * 
 * @author Aleksandr
 *
 */
public class FinanceStream implements IBusinessProcess
{
    private Process     process     = null;

    private RandomValue randomValue = null;

    private int         flowType    = 1;

    public FinanceStream()
    {
    }

    public void setProcess(Process processName, String processParams)
    {
	this.process = processName;
    }

    public void setProcess(String process) throws CreateModelException,
	    ClassNotFoundException, InstantiationException,
	    IllegalAccessException, JSONException
    {
	this.process = Builder.createProcess(process);
    }

    public void setRandomValue(RandomValue value)
    {
	this.randomValue = value;
    }

    public void setRandomValue(String randomValue) throws CreateModelException,
	    InstantiationException, IllegalAccessException,
	    ClassNotFoundException, JSONException
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
    public FinanceEvent nextBusinessEvent()
    {
	FinanceEvent event = new FinanceEvent();
	event.setTime(process.nextValue());
	event.setAmount(flowType * randomValue.nextValue());
	event.setBusinessProcess(this);
	return event;
    }
}
