package business.model.insurance;

import java.util.HashMap;

import math.abstraction.Process;
import math.abstraction.RandomValue;
import application.IStorable;
import business.abstraction.IBusinessProcess;
import exception.CreateModelException;

/**
 * Абстракция потока денежных средств
 * 
 * @author Aleksandr
 *
 */
public class FinanceStream implements IBusinessProcess, IStorable
{
    private Process      process     = null;

    private RandomValue  randomValue = null;

    private int          flowType    = 1;

    public static String AMOUNT      = "Amount";

    public static String PROCESS     = "Process";

    public FinanceStream()
    {
    }

    public void setProcess(Process processName, String processParams)
    {
	this.process = processName;
    }

    public void setProcess(Process process)
    {
	this.process = process;
    }

    public void setRandomValue(RandomValue value)
    {
	this.randomValue = value;
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

    @Override
    public void restore(HashMap<String, String> paramsTree) throws CreateModelException
    {

    }

    @Override
    public HashMap<String, String> store()
    {
	return null;
    }

    @Override
    public boolean check(HashMap<String, String> paramsTree)
    {
	if (!paramsTree.containsKey(AMOUNT))
	    return false;
	if (!paramsTree.containsKey(PROCESS))
	    return false;
	return true;
    }
}
