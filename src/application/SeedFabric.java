package application;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SeedFabric
{
    private static SeedFabric instance;
    private long              lastSeed;
    private Random            random;
    private Set<Long>         seedSet;

    public static SeedFabric getInstance()
    {
	if (instance == null)
	{
	    synchronized (SeedFabric.class)
	    {
		if (instance == null)
		{
		    instance = new SeedFabric();
		}
	    }
	}
	return instance;
    }

    private SeedFabric()
    {
	random = new Random();
	seedSet = new HashSet<Long>();
    }

    public long getSeed()
    {
	try
	{
	    Thread.sleep(17);
	    long seed = System.currentTimeMillis();
	    lastSeed = seed;
	    return seed;
	}
	catch (InterruptedException e)
	{
	    e.printStackTrace();
	    return lastSeed + System.currentTimeMillis();
	}
    }

    public long getFastSeed()
    {
	long seed = 0;
	do
	{
	    seed = random.nextLong();
	}
	while (seedSet.contains(seed));
	return seed;
    }
}
