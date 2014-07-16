package math.random;

import java.util.HashMap;
import java.util.Random;

import math.abstraction.RandomValue;

public class NormRandomValue extends RandomValue
{

	private Random random;

	double alpha;
	double sigma;

	public NormRandomValue(double alpha, double sigma)
	{
	}

	public double nextValue()
	{
		return sigma * random.nextGaussian() + alpha;
	}

	@Override
	public void restore(HashMap<String, String> tree)
	{
	}

	@Override
	public HashMap<String, String> store()
	{
		return null;
	}

}
