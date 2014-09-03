package math.process;

import math.abstraction.Process;
import math.abstraction.RandomValue;
import math.random.ExpRandomValue;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Простейший Пуассоновский поток событий
 * 
 * @author Aleksandr
 *
 */
public class PoissonProcess extends Process {
	private RandomValue randomValue;

	private double lambda;

	public static String LAMBDA = "Lambda";

	public PoissonProcess() {
	}

	public PoissonProcess(double lambda) {
		randomValue = new ExpRandomValue(lambda);
	}

	@Override
	public double nextValue() {
		return randomValue.nextValue();
	}

	@Override
	public void restore(JSONObject obj) throws JSONException {
		lambda = obj.getDouble(LAMBDA);
		randomValue = new ExpRandomValue(lambda);
	}

	@Override
	public JSONObject store() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put(LAMBDA, lambda);
		return obj;
	}

}
