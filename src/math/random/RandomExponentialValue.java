package math.random;

import org.json.JSONException;
import org.json.JSONObject;

import application.Utils;

/**
 * Экспоненциально распределенная случайная величина с параметром "лямбда"
 * 
 * @author Aleksandr
 *
 */
public class RandomExponentialValue extends AbstractRandomValue {
    private double	   lambda;

    private RandomBasicValue basicRandomValue;

    public static String     LAMBDA = "Lambda";

    public RandomExponentialValue() {
	basicRandomValue = new RandomBasicValue();
    }

    public RandomExponentialValue(double lambda) {
	this.lambda = lambda;
	basicRandomValue = new RandomBasicValue();
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
}
