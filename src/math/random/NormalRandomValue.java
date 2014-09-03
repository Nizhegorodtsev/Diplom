package math.random;

import math.abstraction.RandomValue;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Нормально распределенная случайная величина с параметрами "альфа" и "сигма"
 * 
 * @author Aleksandr
 *
 */
public class NormalRandomValue extends RandomValue {
	private BasicRandomValue brv1;
	private BasicRandomValue brv2;
	private double alpha;
	private double sigma;

	public static final String ALPHA = "Alpha";
	public static final String SIGMA = "Sigma";

	public NormalRandomValue() {
		brv1 = new BasicRandomValue();
		brv2 = new BasicRandomValue();
	}

	private double nextGaus() {
		return Math.sqrt(-2 * Math.log(1 - brv1.nextValue()))
				* Math.cos(2 * Math.PI * brv2.nextValue());
	}

	public double nextValue() {
		return alpha + nextGaus() * sigma;
	}

	@Override
	public void restore(JSONObject obj) throws JSONException {
		alpha = obj.getDouble(ALPHA);
		sigma = obj.getDouble(SIGMA);
	}

	@Override
	public JSONObject store() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put(ALPHA, alpha);
		obj.put(SIGMA, sigma);
		return obj;
	}

}
