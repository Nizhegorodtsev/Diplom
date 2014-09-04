package business.model.insurance;

import math.process.AbstractProcess;
import math.random.AbstractRandomValue;

import org.json.JSONException;
import org.json.JSONObject;

import application.AbstractStorable;
import business.abstraction.AbstractBusinessProcess;
import exception.CreateModelException;

/**
 * Абстракция потока денежных средств
 * 
 * @author Aleksandr
 *
 */
public class FinanceStream extends AbstractBusinessProcess {

	private AbstractProcess		process		= null;

	private AbstractRandomValue	randomValue	= null;

	private boolean				isIncome	= true;

	public static String		AMOUNT		= "Amount";

	public static String		PROCESS		= "Process";

	public void setIncome(boolean isIncome) {
		this.isIncome = isIncome;
	}

	@Override
	public FinanceEvent nextBusinessEvent() {
		FinanceEvent event = new FinanceEvent();
		event.setTime(process.nextValue());
		event.setAmount(isIncome ? randomValue.nextValue() : -randomValue.nextValue());
		event.setBusinessProcess(this);
		return event;
	}

	@Override
	public JSONObject store() throws JSONException {
		JSONObject state = super.store();
		state.put(PROCESS, process.store());
		state.put(AMOUNT, randomValue.store());
		return state;
	}

	@Override
	public void restore(JSONObject state) throws JSONException {
		try {
			process = (AbstractProcess) AbstractStorable.newInstance(state.getJSONObject(PROCESS));
			randomValue = (AbstractRandomValue) AbstractStorable.newInstance(state.getJSONObject(AMOUNT));
		} catch (Exception e) {
			e.printStackTrace();
			throw new JSONException(getClassName() + " restore error");
		}
	}

	@Override
	public void init() {
	}

	@Override
	public void validate() throws CreateModelException {
	}
}
