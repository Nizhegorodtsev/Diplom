package math.random;

import java.util.HashMap;

import math.abstraction.RandomValue;
import application.Utils;
import exception.CreateModelException;

/**
 * Экспоненциально распределенная случайная величина с параметром "лямбда"
 * 
 * @author Aleksandr
 *
 */
public class ExpRandomValue extends RandomValue
{
    private double           lambda;

    private BasicRandomValue basicRandomValue;

    public static String     LAMBDA = "Lambda";

    public ExpRandomValue()
    {
	basicRandomValue = new BasicRandomValue();
    }

    public ExpRandomValue(double lambda)
    {
	this.lambda = lambda;
	basicRandomValue = new BasicRandomValue();
    }

    public double nextValue()
    {
	double u;
	while ((u = basicRandomValue.nextValue()) <= Utils.EPSILON)
	    ;
	return -1.0 / lambda * Math.log(u);
    }

    @Override
    public void restore(HashMap<String, String> paramsTree) throws CreateModelException
    {
	lambda = Double.parseDouble(paramsTree.get(LAMBDA));
    }

    @Override
    public HashMap<String, String> store()
    {
	return null;
    }

    @Override
    public boolean check(HashMap<String, String> paramsTree)
    {
	if (!paramsTree.containsKey(LAMBDA))
	    return false;
	return true;
    }

}
