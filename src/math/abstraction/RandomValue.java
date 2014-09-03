package math.abstraction;

import org.json.JSONException;
import org.json.JSONObject;

import application.AbstractStorable;

/**
 * Абстракция случайной величины, распределенной по некоторому закону
 * 
 * @author Aleksandr
 */
public abstract class RandomValue extends AbstractStorable {

	public static final String RANDOM_VALUE_NAME = "Random_value_name";

	public static final String DIRECTORY = "math.random.";

	public abstract double nextValue();

	public static RandomValue newInstance(JSONObject obj) throws JSONException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		RandomValue randomValue = (RandomValue) AbstractStorable
				.newAbstractInstance(obj.getString(RANDOM_VALUE_NAME),
						DIRECTORY, obj);
		return randomValue;
	}
}
