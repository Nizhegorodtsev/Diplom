package math.process;

import java.util.ArrayList;

import math.random.RandomBasicValue;
import math.random.RandomExponentialValue;

import org.json.JSONException;
import org.json.JSONObject;

import application.JSONUtils;
import exception.CreateModelException;

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
	private int									currentState				= 0;

	/**
	 * Число различных состояний
	 */
	private int									countOfState				= 1;

	/**
	 * Фиксируется время пребывания в каждом состоянии
	 */
	private double[]							timeInStateVector			= null;

	/**
	 * Вектор частот наступления событий в каждом состоянии
	 */
	private double[]							occurrenceFrequencyVector	= null;

	/**
	 * Матрица переходов
	 */
	private double[][]							changeStateMatrix			= null;

	/**
	 * Базовая случайная величина, отвечающая за смену состояния
	 */
	private RandomBasicValue					stateSelector				= null;

	/**
	 * Вектор случайных величин, отвечающих за генерацию времени пребывания в каждом состоянии
	 */
	private ArrayList<RandomExponentialValue>	timeInCurrentState			= null;

	/**
	 * Вектор случайных величин, отвечающих за генерацию времени наступления события. Каждая случайная величина генерирует время
	 * для соответствует конкретному состоянию
	 */
	private ArrayList<RandomExponentialValue>	eventGeneratorVector		= null;

	/**
	 * Текущее время
	 */
	private double								time						= 0;

	/**
	 * Время пребывания в текущем состоянии
	 */
	private double								timeinState					= 0;

	public final static String					COUNT_OF_STATES				= "Count_of_states";

	public final static String					CURRENT_STATE				= "Current_state";

	public final static String					INF_MARTIX					= "Inf_matrix";

	public final static String					TIME_IN_STATE_VECTOR		= "Time_in_state";

	public final static String					LAMBDA_VECTOR				= "Lambda";

	public MMPP() {

	}

	@Override
	public double nextValue() {
		return nextEventTime();
	}

	public double nextEventTime() {
		return eventGeneratorVector.get(currentState).nextValue();
	}

	/**
	 * Переход в новое состояние потока
	 */
	public void selectState() {
		double value = stateSelector.nextValue();
		for (int i = 0; i < countOfState; i++)
			if (value < timeInStateVector[i]) {
				currentState = i;

				return;
			}
		currentState = countOfState - 1;
	}

	@Override
	public void restore(JSONObject obj) throws JSONException {
		countOfState = obj.getInt(COUNT_OF_STATES);
		timeInStateVector = JSONUtils.parseVector(obj.getJSONArray(TIME_IN_STATE_VECTOR));
		occurrenceFrequencyVector = JSONUtils.parseVector(obj.getJSONArray(LAMBDA_VECTOR));
		changeStateMatrix = JSONUtils.parseMatrix(obj.getJSONArray(INF_MARTIX));
	}

	@Override
	public JSONObject store() throws JSONException {
		JSONObject state = super.store();
		state.put(COUNT_OF_STATES, countOfState);
		state.put(TIME_IN_STATE_VECTOR, JSONUtils.createJSONVector(timeInStateVector));
		state.put(LAMBDA_VECTOR, JSONUtils.createJSONVector(occurrenceFrequencyVector));
		state.put(INF_MARTIX, JSONUtils.createJSONMatrix(changeStateMatrix));
		return state;
	}

	@Override
	public void init() {
		stateSelector = new RandomBasicValue();
		eventGeneratorVector = new ArrayList<RandomExponentialValue>(countOfState);
		timeInCurrentState = new ArrayList<RandomExponentialValue>(countOfState);

		for (int i = 0; i < countOfState; i++) {
			// нормализация матрицы
			double summ = 0;
			for (int j = 0; j < countOfState; j++)
				if (i != j)
					summ += changeStateMatrix[i][j];
			for (int j = 0; j < countOfState; j++)
				if (i != j)
					changeStateMatrix[i][j] = changeStateMatrix[i][j] / summ;
		}

		for (int i = 0; i < countOfState; i++) {
			eventGeneratorVector.add(new RandomExponentialValue(occurrenceFrequencyVector[i]));
			timeInCurrentState.add(new RandomExponentialValue(timeInStateVector[i]));
		}

		double summ = 0;
		for (int i = 0; i < countOfState; i++)
			summ += timeInStateVector[i];
		for (int i = 0; i < countOfState; i++)
			timeInStateVector[i] = timeInStateVector[i] / summ;
		selectState();
	}

	@Override
	public void validate() throws CreateModelException {

	}
}
