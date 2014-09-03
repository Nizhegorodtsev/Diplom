package math.random;

import math.abstraction.RandomValue;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Равномерно распределенная случайная величина
 * 
 * @author Aleksandr
 */
public class UniformRandomValue extends RandomValue {
	private BasicRandomValue brv;

	private double begin;

	private double end;

	public static String BEGIN = "Begin";

	public static String END = "End";

	public UniformRandomValue() {
		brv = new BasicRandomValue();
	}

	public double nextValue() {
		return begin + (end - begin) * brv.nextValue();
	}

	@Override
	public void restore(JSONObject obj) throws JSONException {
		begin = obj.getDouble(BEGIN);
		end = obj.getDouble(END);
		if (begin >= end)
			throw new JSONException("UniformRandomValue: begin >= end");
	}

	@Override
	public JSONObject store() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put(BEGIN, begin);
		obj.put(END, end);
		return obj;
	}
}
