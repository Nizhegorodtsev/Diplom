package math.abstraction;

import java.util.HashMap;

import application.IStorable;
import exception.CreateModelException;

public class Process implements IStorable
{
	public double nextValue()
	{
		return 0;
	}

	@Override
	public void restore(HashMap<String, String> modelTree) throws CreateModelException
	{

	}

	@Override
	public HashMap<String, String> store()
	{
		return null;
	}

	public static String createTemplate()
	{
		return "";
	}

	public static String TAG_P = "p#";

}
