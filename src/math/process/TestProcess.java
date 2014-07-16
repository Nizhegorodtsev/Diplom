package math.process;

import java.util.HashMap;

import math.abstraction.Process;
import exception.CreateModelException;

public class TestProcess extends Process
{
	public double nextValue()
	{
		return 0;
	}

	public void restore(HashMap<String, String> modelTree) throws CreateModelException
	{

	}
}
