package math.random;

import java.util.HashMap;

import math.abstraction.RandomValue;
import exception.CreateModelException;

public class ExpRandomValue extends RandomValue
{
	private double lambda;

	private BasicRandomValue basicRandomValue;

	public static String LAMBDA = "Lambda";

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
		while ((u = basicRandomValue.nextDouble()) <= 0.00001)
			;
		return -1.0 / lambda * Math.log(u);
	}

	public static String createTemplate()
	{
		String template = new String();
		template += LAMBDA + " = 1;\n";
		return template;
	}

	@Override
	public void restore(HashMap<String, String> paramsTree) throws CreateModelException
	{
		lambda = Double.parseDouble(paramsTree.get(LAMBDA));
	}

	@Override
	public HashMap<String, String> store()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
