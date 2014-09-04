package math.random;

import org.json.JSONException;
import org.json.JSONObject;

import application.Utils;
import exception.CreateModelException;

/**
 * Экспоненциально распределенная случайная величина с параметром "лямбда"
 * 
 * @author Aleksandr
 *
 */
public class RandomExponentialValue extends AbstractRandomValue {

	private double				lambda				= 1;

	private RandomBasicValue	basicRandomValue	= null;

	public static String		LAMBDA				= "Lambda";

	public RandomExponentialValue() {
		basicRandomValue = new RandomBasicValue();
	}

	public RandomExponentialValue(double lambda) {
		basicRandomValue = new RandomBasicValue();
		this.lambda = lambda;
	}

	@Override
	public double nextValue() {
		double u;
		while ((u = basicRandomValue.nextValue()) <= Utils.EPSILON)
			;
		return -1.0 / lambda * Math.log(u);
	}

	@Override
	public void restore(JSONObject state) throws JSONException {
		lambda = state.getDouble(LAMBDA);
	}

	@Override
	public JSONObject store() throws JSONException {
		JSONObject state = super.store();
		state.put(LAMBDA, lambda);
		return state;
	}

	@Override
	public void init() {
	}

	@Override
	public void validate() throws CreateModelException {
		if (lambda <= Utils.EPSILON)
			throw new CreateModelException("lambda is too small");
	}

	public double getLambda() {
		return lambda;
	}

	public void setLambda(double lambda) {
		this.lambda = lambda;
	}
}
