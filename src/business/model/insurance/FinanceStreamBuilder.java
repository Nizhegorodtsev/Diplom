package business.model.insurance;

import java.util.HashMap;

import math.process.ProcessBuilder;
import math.random.RandomValueBuilder;

import org.json.JSONException;

import application.JSONParser;
import exception.CreateModelException;

public class FinanceStreamBuilder
{
    public static FinanceStream createFinanceStream(String file) throws CreateModelException
    {
	try
	{
	    FinanceStream stream = new FinanceStream();
	    HashMap<String, String> map = JSONParser.parseKeysMap(file);
	    stream.setProcess(ProcessBuilder.createProcess(map.get(FinanceStream.PROCESS)));
	    stream.setRandomValue(RandomValueBuilder.createRandomValue(map.get(FinanceStream.AMOUNT)));
	    return stream;
	}
	catch (JSONException e)
	{
	    e.printStackTrace();
	    throw new CreateModelException();
	}
    }
}
