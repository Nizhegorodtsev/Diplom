package math.random;

import org.json.JSONException;
import org.json.JSONObject;

import application.SeedFabric;

/**
 * Базовая случайная величина, распределенная на интервале [0, 1]
 * 
 * @author Aleksandr
 *
 */
public class RandomBasicValue extends AbstractRandomValue {

    private java.util.Random random;

    public RandomBasicValue() {
	init();
    }

    @Override
    public double nextValue() {
	return random.nextDouble();
    }

    @Override
    public void restore(JSONObject state) throws JSONException {
    }

    @Override
    public void init() {
	random = new java.util.Random(SeedFabric.getInstance().getSeed());
    }
}
