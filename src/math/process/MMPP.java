package math.process;

import java.util.ArrayList;

import math.random.RandomBasicValue;
import math.random.RandomExponentialValue;

import org.json.JSONException;
import org.json.JSONObject;

import exception.CreateModelException;
import application.JSONUtils;

/**
 * Марковский модулированный поток
 * 
 * @author Aleksandr
 * 
 */
public class MMPP extends AbstractProcess {

	/**
	 * Текущее состояние
	 */
	private int currentState = 0;

	/**
	 * Число различных состояний
	 */
	private int countOfState = 1;

	/**
	 * Фиксируется время пребывания в каждом состоянии
	 */
	private double[] timeInState;

	/**
	 * Вектор частот наступления событий в каждом состоянии
	 */
	private double[] chastotaNastypleniya;

	/**
	 * Матрица переходов
	 */
	private double[][] changeStateMatrix;

	/**
	 * Базовая случайная величина, отвечающая за смену состояния
	 */
	private RandomBasicValue stateSelector;

	/**
	 * Вектор случайных величин, отвечающих за генерацию времени пребывания в
	 * каждом состоянии
	 */
	private ArrayList<RandomExponentialValue> timeInCurrentState;

	/**
	 * Почему я решил сохранять события в массив элементов??
	 */
	private ArrayList<RandomExponentialValue> event;

	/**
	 * Текущее время
	 */
	private double time = 0;

	/**
	 * Время пребывания в текущем состоянии
	 */
	private double timeinState = 0;

	public final static String COUNT_OF_STATES = "Count_of_states";

	public final static String CURRENT_STATE = "Current_state";

	public final static String INF_MARTIX = "Inf_matrix";

	public final static String TIME_IN_STATE = "Time_in_state";

	public final static String LAMBDA = "Lambda";

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
		timeInState = JSONUtils.parseVector(obj.getJSONArray(TIME_IN_STATE));
		chastotaNastypleniya = JSONUtils.parseVector(obj.getJSONArray(LAMBDA));
		changeStateMatrix = JSONUtils.parseMatrix(obj.getJSONArray(INF_MARTIX));

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

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validate() throws CreateModelException {
		// TODO Auto-generated method stub
		
	}
}
