package math.random;

import java.util.HashMap;

import math.abstraction.RandomValue;
import exception.CreateModelException;

/**
 * Гамма распределенная случайная величина
 * 
 * @author Aleksandr
 *
 */
public class GammaRandomValue extends RandomValue
{
    @Override
    public void restore(HashMap<String, String> paramsTree)
	    throws CreateModelException
    {
    }

    @Override
    public HashMap<String, String> store()
    {
	return null;
    }

    @Override
    public double nextValue()
    {
	return 0;
    }
}
