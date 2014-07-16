package math.random;

import java.util.Random;

import application.SeedFabric;

public class BasicRandomValue
{
	public BasicRandomValue()
	{
		random = new Random(SeedFabric.getInstance().getSeed());
	}

	public double nextDouble()
	{
		return random.nextDouble();
	}

	protected Random random;
}
