package math.process;

import java.util.ArrayList;

import math.random.RandomBasicValue;
import math.random.RandomExponentialValue;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Марковский модулированный поток
 * 
 * @author Aleksandr
 * 
 */
public class MMPP extends AbstractProcess {

    private int			       currentState    = 0;

    private int			       countOfState    = 1;

    private double[]			  timeInState;

    private double[]			  chastotaNastypleniya;

    private double[][]			changeStateMatrix;

    private RandomBasicValue		  stateSelector;

    private ArrayList<RandomExponentialValue> timeInCurrentState;

    private ArrayList<RandomExponentialValue> event;

    private double			    time	    = 0;

    private double			    timeinState     = 0;

    public final static String		COUNT_OF_STATES = "Count_of_state";

    public final static String		Inf_matrix      = "Inf_matrix";

    public final static String		Time_in_state   = "Time_in_state";

    public final static String		Lambda	  = "Lambda";

    public MMPP() {
    }

    @Override
    public double nextValue() {
	return nextEventTime();
    }

    public static String createTemplate() {
	String template = new String();

	return template;
    }

    public double nextEventTime() {
	return event.get(currentState).nextValue();
    }

    public void selectState() {
	double value = stateSelector.nextValue();
	for (int i = 0; i < countOfState; i++)
	    if (value < timeInState[i]) {
		currentState = i;

		return;
	    }
	currentState = countOfState - 1;
    }

    @Override
    public void restore(JSONObject obj) throws JSONException {

	countOfState = obj.getInt(COUNT_OF_STATES);
	// timeInState = JSONParser.parseVector(tree.get(Time_in_state));
	// chastotaNastypleniya = JSONParser.parseVector(tree.get(Lambda));
	// changeStateMatrix = JSONParser.parseMatrix(tree.get(Inf_matrix));

	for (int i = 0; i < countOfState; i++) {
	    double summ = 0;
	    for (int j = 0; j < countOfState; j++)
		if (i != j)
		    summ += changeStateMatrix[i][j];
	    for (int j = 0; j < countOfState; j++)
		if (i != j)
		    changeStateMatrix[i][j] = changeStateMatrix[i][j] / summ;
	}
	event = new ArrayList<RandomExponentialValue>(countOfState);
	timeInCurrentState = new ArrayList<RandomExponentialValue>(countOfState);
	for (int i = 0; i < countOfState; i++) {
	    event.add(new RandomExponentialValue(chastotaNastypleniya[i]));
	    timeInCurrentState.add(new RandomExponentialValue(timeInState[i]));
	}
	stateSelector = new RandomBasicValue();

	double summ = 0;
	for (int i = 0; i < countOfState; i++)
	    summ += timeInState[i];
	for (int i = 0; i < countOfState; i++)
	    timeInState[i] = timeInState[i] / summ;
	selectState();

    }

    @Override
    public JSONObject store() throws JSONException {
	return null;
    }
}
