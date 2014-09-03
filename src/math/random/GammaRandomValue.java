package math.random;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import math.abstraction.RandomValue;
import exception.CreateModelException;

/**
 * Гамма распределенная случайная величина
 * 
 * @author Aleksandr
 *
 */
public class GammaRandomValue extends RandomValue {

	@Override
	public void restore(JSONObject obj) throws JSONException {

	}

	@Override
	public JSONObject store() throws JSONException {
		return null;
	}

	@Override
	public double nextValue() {
		return 0;
	}
}
