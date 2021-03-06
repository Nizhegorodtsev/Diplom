package math.random;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Нормально распределенная случайная величина с параметрами "альфа" и "сигма"
 * 
 * @author Aleksandr
 *
 */
public class RandomNormalValue extends AbstractRandomValue {
    private RandomBasicValue   brv1;
    private RandomBasicValue   brv2;
    private double	     alpha;
    private double	     sigma;

    public static final String ALPHA = "Alpha";
    public static final String SIGMA = "Sigma";

    public RandomNormalValue() {
	brv1 = new RandomBasicValue();
	brv2 = new RandomBasicValue();
    }

    private double nextGaus() {
	return Math.sqrt(-2 * Math.log(1 - brv1.nextValue())) * Math.cos(2 * Math.PI * brv2.nextValue());
    }

    @Override
    public double nextValue() {
	return alpha + nextGaus() * sigma;
    }

    @Override
    public void restore(JSONObject state) throws JSONException {
	alpha = state.getDouble(ALPHA);
	sigma = state.getDouble(SIGMA);
    }

    @Override
    public JSONObject store() throws JSONException {
	JSONObject state = super.store();
	state.put(ALPHA, alpha);
	state.put(SIGMA, sigma);
	return state;
    }

}
