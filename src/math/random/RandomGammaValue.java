package math.random;

import org.json.JSONException;
import org.json.JSONObject;

import exception.CreateModelException;

/**
 * Гамма распределенная случайная величина
 * 
 * @author Aleksandr
 *
 */
public class RandomGammaValue extends AbstractRandomValue {

	public RandomGammaValue() {
	}

	@Override
	public void restore(JSONObject state) throws JSONException {
	}

	@Override
	public JSONObject store() throws JSONException {
		JSONObject state = super.store();
		return state;
	}

	@Override
	public double nextValue() {
		return 0;
	}

	@Override
	public void init() {
	}

	@Override
	public void validate() throws CreateModelException {
		// TODO Auto-generated method stub

	}
}
