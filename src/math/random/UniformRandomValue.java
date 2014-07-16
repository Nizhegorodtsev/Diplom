package math.random;

import java.util.HashMap;

import math.abstraction.RandomValue;
import exception.CreateModelException;

/**
 * Равномерно распределенная случайная величина
 * 
 * @author Aleksandr
 */
public class UniformRandomValue extends RandomValue
{
    private BasicRandomValue brv;

    private double           begin;

    private double           end;

    public static String     BEGIN = "Begin";

    public static String     END   = "End";

    public UniformRandomValue()
    {
	brv = new BasicRandomValue();
    }

    public double nextValue()
    {
	return begin + (end - begin) * brv.nextValue();
    }

    public void restore(HashMap<String, String> tree) throws CreateModelException
    {
	begin = Double.parseDouble(tree.get(BEGIN));
	end = Double.parseDouble(tree.get(END));
	if (begin >= end)
	    throw new CreateModelException("UniformRandomValue: begin >= end");
    }

    @Override
    public HashMap<String, String> store()
    {
	return null;
    }

    @Override
    public boolean check(HashMap<String, String> paramsTree)
    {
	if (!paramsTree.containsKey(BEGIN))
	    return false;
	if (!paramsTree.containsKey(END))
	    return false;
	return true;
    }
}
