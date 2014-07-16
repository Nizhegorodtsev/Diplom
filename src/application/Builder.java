package application;

import java.util.HashMap;
import java.util.Set;

import math.abstraction.Process;
import math.abstraction.RandomValue;

import org.json.JSONException;
import org.json.JSONObject;

import business.abstraction.Model;
import exception.CreateModelException;

/**
 * Класс отвечает за создание объектов модели, случайной величины и потока
 * 
 * @author Aleksandr
 */
public class Builder
{
	public static Model createModel(String file) throws CreateModelException
	{
		try
		{
			JSONObject jsonObj = new JSONObject(file);
			String modelType = jsonObj.getString("modelType");
			Class<?> c = Class.forName("business.model." + modelType);
			Object obj = c.newInstance();
			Model model = (Model) obj;
			model.restore(getParamsMap(jsonObj.getJSONObject("modelParams")));
			return model;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new CreateModelException(e.getMessage());
		}
	}

	public static Process createProcess(String file) throws CreateModelException, JSONException,
			ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		JSONObject jsonObj = new JSONObject(file);
		String processType = jsonObj.getString("processType");
		Class<?> c = Class.forName("math.process." + processType);
		Object obj = c.newInstance();
		Process process = (Process) obj;
		process.restore(getParamsMap(jsonObj.getJSONObject("processParams")));
		return process;

	}

	public static RandomValue createRandomValue(String file) throws JSONException, InstantiationException,
			IllegalAccessException, CreateModelException, ClassNotFoundException
	{
		JSONObject jsonObj = new JSONObject(file);
		String randomValueType = jsonObj.getString("randomValueType");
		Class<?> c = Class.forName("math.random." + randomValueType);
		Object obj = c.newInstance();
		RandomValue randomValue = (RandomValue) obj;
		randomValue.restore(getParamsMap(jsonObj.getJSONObject("randomValueParams")));
		return randomValue;
	}

	public static double[] createVector(String file) throws CreateModelException
	{
		double[] vector = new double[1];
		return vector;
	}

	public static double[][] createMatrix(String file) throws CreateModelException
	{
		double[][] vector = new double[1][1];
		return vector;
	}

	private static HashMap<String, String> getParamsMap(JSONObject obj) throws JSONException
	{
		HashMap<String, String> map = new HashMap<String, String>();
		Set<String> keySey = map.keySet();
		for (String key : keySey)
			map.put(key, obj.getString(key));
		return map;
	}
}
