package math.random;

import java.util.HashMap;

import math.abstraction.RandomValue;

/**
 * Нормально распределенная случайная величина с параметрами "альфа" и "сигма"
 * 
 * @author Aleksandr
 *
 */
public class NormalRandomValue extends RandomValue
{
    private BasicRandomValue   brv1;
    private BasicRandomValue   brv2;
    private double             alpha;
    private double             sigma;

    public static final String ALPHA = "Alpha";
    public static final String SIGMA = "Sigma";

    public NormalRandomValue()
    {
	brv1 = new BasicRandomValue();
	brv2 = new BasicRandomValue();
    }

    private double nextGaus()
    {
	return Math.sqrt(-2 * Math.log(1 - brv1.nextValue())) * Math.cos(2 * Math.PI * brv2.nextValue());
    }

    public double nextValue()
    {
	return alpha + nextGaus() * sigma;
    }

    @Override
    public void restore(HashMap<String, String> tree)
    {
	alpha = Double.valueOf(tree.get(ALPHA));
	sigma = Double.valueOf(tree.get(SIGMA));
    }

    @Override
    public HashMap<String, String> store()
    {
	return null;
    }

    @Override
    public boolean check(HashMap<String, String> paramsTree)
    {
	if (!paramsTree.containsKey(ALPHA))
	    return false;
	if (!paramsTree.containsKey(SIGMA))
	    return false;
	return true;
    }
}
