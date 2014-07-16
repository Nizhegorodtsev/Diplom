package math.process;

import java.util.HashMap;

import math.abstraction.Process;
import math.abstraction.RandomValue;
import math.random.ExpRandomValue;
import exception.CreateModelException;

/**
 * Простейший Пуассоновский поток событий
 * 
 * @author Aleksandr
 *
 */
public class PoissonProcess extends Process
{
    private RandomValue  randomValue;

    private double       lambda;

    public static String LAMBDA = "Lambda";

    public PoissonProcess()
    {
    }

    public PoissonProcess(double lambda)
    {
	randomValue = new ExpRandomValue(lambda);
    }

    @Override
    public double nextValue()
    {
	return randomValue.nextValue();
    }

    @Override
    public void restore(HashMap<String, String> tree)
	    throws CreateModelException
    {
	lambda = Double.parseDouble(tree.get(LAMBDA));
	randomValue = new ExpRandomValue(lambda);
    }

}
