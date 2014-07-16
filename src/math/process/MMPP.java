package math.process;

import java.util.ArrayList;
import java.util.HashMap;

import math.abstraction.Process;
import math.random.BasicRandomValue;
import math.random.ExpRandomValue;
import application.Builder;
import exception.CreateModelException;

/**
 * Марковский модулированный поток
 * 
 * @author Aleksandr
 * 
 */
public class MMPP extends Process
{
    private int                       currentState    = 0;

    private int                       countOfState    = 1;

    private double[]                  timeInState;

    private double[]                  chastotaNastypleniya;

    private ArrayList<ExpRandomValue> timeInCurrentState;

    private double[][]                changeStateMatrix;

    private BasicRandomValue          stateSelector;

    private ArrayList<ExpRandomValue> event;

    private double                    time            = 0;

    private double                    timeinState     = 0;

    public final static String        COUNT_OF_STATES = "Count_of_state";

    public final static String        Inf_matrix      = "Inf_matrix";

    public final static String        Time_in_state   = "Time_in_state";

    public final static String        Lambda          = "Lambda";

    public MMPP()
    {
    }

    @Override
    public double nextValue()
    {
	return nextEventTime();
    }

    public static String createTemplate()
    {
	String template = new String();

	return template;
    }

    @Override
    public void restore(HashMap<String, String> tree)
	    throws CreateModelException
    {
	countOfState = Integer.parseInt(tree.get(COUNT_OF_STATES));
	timeInState = Builder.createVector(tree.get(Time_in_state));
	chastotaNastypleniya = Builder.createVector(tree.get(Lambda));
	changeStateMatrix = Builder.createMatrix(tree.get(Inf_matrix));

	for (int i = 0; i < countOfState; i++)
	{
	    double summ = 0;
	    for (int j = 0; j < countOfState; j++)
		if (i != j)
		    summ += changeStateMatrix[i][j];
	    for (int j = 0; j < countOfState; j++)
		if (i != j)
		    changeStateMatrix[i][j] = changeStateMatrix[i][j] / summ;
	}
	event = new ArrayList<ExpRandomValue>(countOfState);
	timeInCurrentState = new ArrayList<ExpRandomValue>(countOfState);
	for (int i = 0; i < countOfState; i++)
	{
	    event.add(new ExpRandomValue(chastotaNastypleniya[i]));
	    timeInCurrentState.add(new ExpRandomValue(timeInState[i]));
	}
	stateSelector = new BasicRandomValue();

	double summ = 0;
	for (int i = 0; i < countOfState; i++)
	    summ += timeInState[i];
	for (int i = 0; i < countOfState; i++)
	    timeInState[i] = timeInState[i] / summ;
	selectState();

    }

    public double nextEventTime()
    {
	return event.get(currentState).nextValue();
    }

    public void selectState()
    {
	double value = stateSelector.nextValue();
	for (int i = 0; i < countOfState; i++)
	    if (value < timeInState[i])
	    {
		currentState = i;

		return;
	    }
	currentState = countOfState - 1;
    }
}
