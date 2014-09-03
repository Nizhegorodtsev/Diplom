package math.random;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import math.abstraction.RandomValue;
import application.Utils;
import exception.CreateModelException;

/**
 * Экспоненциально распределенная случайная величина с параметром "лямбда"
 * 
 * @author Aleksandr
 *
 */
public class ExpRandomValue extends RandomValue {
	private double lambda;

	private BasicRandomValue basicRandomValue;

	public static String LAMBDA = "Lambda";

	public ExpRandomValue() {
		basicRandomValue = new BasicRandomValue();
	}

	public ExpRandomValue(double lambda) {
		this.lambda = lambda;
		basicRandomValue = new BasicRandomValue();
	}

	public double nextValue() {
		double u;
		while ((u = basicRandomValue.nextValue()) <= Utils.EPSILON)
			;
		return -1.0 / lambda * Math.log(u);
	}

	@Override
	public void restore(JSONObject obj) throws JSONException {
		lambda = obj.getDouble(LAMBDA);
	}

	@Override
	public JSONObject store() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put(LAMBDA, lambda);
		return obj;
	}
}
