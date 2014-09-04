package math.process;

import math.random.RandomExponentialValue;
import math.random.AbstractRandomValue;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Простейший Пуассоновский поток событий
 * 
 * @author Aleksandr
 *
 */
public class PoissonProcess extends AbstractProcess {
	private AbstractRandomValue randomValue;

	private double lambda;

	public static String LAMBDA = "Lambda";

	public PoissonProcess() {
	}

	public PoissonProcess(double lambda) {
		randomValue = new RandomExponentialValue(lambda);
	}

	@Override
	public double nextValue() {
		return randomValue.nextValue();
	}

	@Override
	public void restore(JSONObject obj) throws JSONException {
		lambda = obj.getDouble(LAMBDA);
		randomValue = new RandomExponentialValue(lambda);
	}

	@Override
	public JSONObject store() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put(LAMBDA, lambda);
		return obj;
	}

}
