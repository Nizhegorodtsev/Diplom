package math.process;

import math.random.RandomExponentialValue;

import org.json.JSONException;
import org.json.JSONObject;

import application.Utils;
import exception.CreateModelException;

/**
 * Простейший Пуассоновский поток событий
 * 
 * @author Aleksandr
 *
 */
public class PoissonProcess extends AbstractProcess {

	private RandomExponentialValue	randomValue;

	private double					lambda;

	public static String			LAMBDA	= "Lambda";

	public PoissonProcess() {
		randomValue = new RandomExponentialValue();
	}

	@Override
	public double nextValue() {
		return randomValue.nextValue();
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

	@Override
	public void init() {
		randomValue.setLambda(lambda);
	}

	@Override
	public void validate() throws CreateModelException {
		if (lambda <= Utils.EPSILON)
			throw new CreateModelException("lambda is too small");
	}

}
