package math.random;

import org.json.JSONException;
import org.json.JSONObject;

import exception.CreateModelException;

/**
 * Равномерно распределенная случайная величина
 * 
 * @author Aleksandr
 */
public class RandomUniformValue extends AbstractRandomValue {

    private RandomBasicValue brv;

    private double	   begin;

    private double	   end;

    public static String     BEGIN = "Begin";

    public static String     END   = "End";

    public RandomUniformValue() {
	init();
    }

    @Override
    public double nextValue() {
	return begin + (end - begin) * brv.nextValue();
    }

    @Override
    public void restore(JSONObject state) throws JSONException {
	begin = state.getDouble(BEGIN);
	end = state.getDouble(END);
	if (begin >= end)
	    throw new JSONException("UniformRandomValue: begin >= end");
    }

    @Override
    public JSONObject store() throws JSONException {
	JSONObject state = super.store();
	state.put(BEGIN, begin);
	state.put(END, end);
	return state;
    }

    @Override
    public void init() throws CreateModelException {
	brv = new RandomBasicValue();
    }
}
