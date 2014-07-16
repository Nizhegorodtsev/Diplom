package application;

public class SeedFabric
{

	public static SeedFabric getInstance()
	{
		if (_instance == null)
		{
			synchronized (SeedFabric.class)
			{
				if (_instance == null)
				{
					_instance = new SeedFabric();
				}
			}
		}
		return _instance;
	}

	private SeedFabric()
	{
		_offset = 1.23419876;
	}

	public long getSeed()
	{
		try
		{
			Thread.sleep(7);
			long seed = System.currentTimeMillis();
			_lastSeed = seed;
			return seed;
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
			System.out.println("������� �� �������");
			return _lastSeed + Double.doubleToLongBits(_offset++ / 1.00324);
		}
	}

	private static SeedFabric _instance;
	private long _lastSeed;
	private double _offset;
}
