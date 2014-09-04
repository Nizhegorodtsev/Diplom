package math.random;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Гамма распределенная случайная величина
 * 
 * @author Aleksandr
 *
 */
public class RandomGammaValue extends AbstractRandomValue {

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
}
