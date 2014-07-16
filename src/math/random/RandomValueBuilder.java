package math.random;

import java.util.HashMap;

import math.abstraction.RandomValue;
import application.JSONParser;
import exception.CreateModelException;

public class RandomValueBuilder
{
    public static RandomValue createRandomValue(String file) throws CreateModelException
    {
	try
	{
	    HashMap<String, String> map = JSONParser.parseKeysMap(file);
	    String randomValueName = map.get(RandomValue.RANDOM_VALUE_NAME);
	    Class<?> c = Class.forName("math.random." + randomValueName);
	    Object obj = c.newInstance();
	    RandomValue randomValue = (RandomValue) obj;
	    if (randomValue.check(map))
		randomValue.restore(map);
	    else
		throw new CreateModelException();
	    return randomValue;
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	    throw new CreateModelException(e.getMessage());
	}
    }
}
