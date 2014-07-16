package math.process;

import java.util.HashMap;

import math.abstraction.Process;
import application.JSONParser;
import exception.CreateModelException;

public class ProcessBuilder
{
    public static Process createProcess(String file) throws CreateModelException
    {
	try
	{
	    HashMap<String, String> map = JSONParser.parseKeysMap(file);
	    String processName = map.get(Process.PROCESS_NAME);
	    Class<?> c = Class.forName("math.process." + processName);
	    Object obj = c.newInstance();
	    Process process = (Process) obj;
	    if (process.check(map))
		process.restore(map);
	    else
		throw new CreateModelException();
	    return process;
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	    throw new CreateModelException(e.getMessage());
	}

    }
}
