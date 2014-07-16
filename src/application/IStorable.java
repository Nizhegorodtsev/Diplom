package application;

import java.util.HashMap;

import exception.CreateModelException;

/**
 * @author Aleksandr
 */
public interface IStorable
{
	public void restore(HashMap<String, String> paramsTree) throws CreateModelException;

	public HashMap<String, String> store();
}
