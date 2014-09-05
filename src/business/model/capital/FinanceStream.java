package business.model.capital;

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

	private AbstractProcess		eventTimeGenerator	= null;

	private AbstractRandomValue	amountGenerator		= null;

	private boolean				isIncome			= true;

	public static String		AMOUNT				= "Amount";

	public static String		PROCESS				= "Process";

	@Override
	public FinanceEvent nextBusinessEvent() {
		FinanceEvent event = new FinanceEvent();
		event.setTime(eventTimeGenerator.nextValue());
		event.setAmount(isIncome ? amountGenerator.nextValue() : -amountGenerator.nextValue());
		event.setBusinessProcess(this);
		return event;
	}

	@Override
	public JSONObject store() throws JSONException {
		JSONObject state = super.store();
		state.put(PROCESS, eventTimeGenerator.store());
		state.put(AMOUNT, amountGenerator.store());
		return state;
	}

	@Override
	public void restore(JSONObject state) throws JSONException {
		try {
			eventTimeGenerator = (AbstractProcess) AbstractStorable.newInstance(state.getJSONObject(PROCESS));
			amountGenerator = (AbstractRandomValue) AbstractStorable.newInstance(state.getJSONObject(AMOUNT));
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

	public AbstractProcess getEventTimeGenerator() {
		return eventTimeGenerator;
	}

	public void setEventTimeGenerator(AbstractProcess eventTimeGenerator) {
		this.eventTimeGenerator = eventTimeGenerator;
	}

	public AbstractRandomValue getAmountGenerator() {
		return amountGenerator;
	}

	public void setAmountGenerator(AbstractRandomValue amountGenerator) {
		this.amountGenerator = amountGenerator;
	}

	public boolean isIncome() {
		return isIncome;
	}

	public void setIncome(boolean isIncome) {
		this.isIncome = isIncome;
	}
}
