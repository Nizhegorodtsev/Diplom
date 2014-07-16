package math.process;

import java.util.HashMap;

import math.abstraction.Process;
import math.abstraction.RandomValue;
import math.random.ExpRandomValue;
import exception.CreateModelException;

public class PoissonProcess extends Process
{
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

	public static String createTemplate()
	{
		String template = new String();
		template += TAG_P + LAMBDA + " = 1;\n";
		return template;
	}

	@Override
	public void restore(HashMap<String, String> tree) throws CreateModelException
	{
		lambda = Double.parseDouble(tree.get(LAMBDA));
		randomValue = new ExpRandomValue(lambda);
	}

	private RandomValue randomValue;

	private double lambda;

	public static String LAMBDA = "Lambda";

}
