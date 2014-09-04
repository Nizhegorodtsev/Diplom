package business.model;

import java.util.ArrayList;
import java.util.TreeMap;

import org.json.JSONException;
import org.json.JSONObject;

import exception.CreateModelException;
import application.AbstractStorable;
import business.model.insurance.FinanceEvent;
import business.model.insurance.FinanceStream;

public class InsuranceModel extends AbstractModel {
	private double							startCapital			= 0;
	private double							capital					= 0;
	private double							maxIncome				= 0;
	private double							minIncome				= 0;
	private double							maxPayment				= 0;
	private double							minPayment				= 0;
	private ArrayList<Double>				capitalHistory;
	private FinanceStream					incomeFinanceStream;
	private FinanceStream					paymentFinanceStream;
	private TreeMap<Double, FinanceEvent>	eventMap;

	public static final String				START_CAPITAL			= "Start_capital";
	public static final String				MAX_INCOME				= "Max_income";
	public static final String				MIN_INCOME				= "Min_income";
	public static final String				MAX_PAYMENT				= "Max_payment";
	public static final String				MIN_PAYMENT				= "Min_payment";
	public static final String				INCOME_FINANCE_STREAM	= "Income_finance_stream";
	public static final String				PAYMENT_FINANCE_STREAM	= "Payment_finance_stream";

	public InsuranceModel() {
		init();
	}

	@Override
	public void init() {
		capitalHistory = new ArrayList<>();
		eventMap = new TreeMap<Double, FinanceEvent>();
		incomeFinanceStream = new FinanceStream();
		paymentFinanceStream = new FinanceStream();
		paymentFinanceStream.setIncome(false);

		capital = startCapital;
		FinanceEvent event = incomeFinanceStream.nextBusinessEvent();
		eventMap.put(event.getTime() + currentTime, event);
		event = paymentFinanceStream.nextBusinessEvent();
		eventMap.put(event.getTime() + currentTime, event);
		capitalHistory.add(capital);
	}

	@Override
	public void doStep() {
		FinanceEvent event = eventMap.remove(eventMap.firstKey());
		capital += event.getAmount();
		currentTime += event.getTime();
		System.out.print(capital);
		System.out.println("   " + event.getAmount());
		if (capital < 0) {
			currentModelCicle++;
			if (currentModelCicle > countOfModelCicle)
				stopRun();
		} else {
			FinanceEvent newEvent = (FinanceEvent) event.getBusinessProcess().nextBusinessEvent();
			eventMap.put(newEvent.getTime() + currentTime, newEvent);
			capitalHistory.add(capital);
		}
	}

	@Override
	public void finish() {
		System.out.println(capitalHistory.size());
	}

	@Override
	public JSONObject store() throws JSONException {
		JSONObject state = super.store();
		state.put(START_CAPITAL, startCapital);
		state.put(MAX_INCOME, maxIncome);
		state.put(MAX_PAYMENT, maxPayment);
		state.put(MIN_INCOME, minIncome);
		state.put(MIN_PAYMENT, minPayment);
		state.put(INCOME_FINANCE_STREAM, incomeFinanceStream.store());
		state.put(PAYMENT_FINANCE_STREAM, paymentFinanceStream.store());
		return state;
	}

	@Override
	public void restore(JSONObject state) throws JSONException {
		try {
			startCapital = state.getDouble(START_CAPITAL);
			maxIncome = state.getDouble(MAX_INCOME);
			maxPayment = state.getDouble(MAX_PAYMENT);
			minIncome = state.getDouble(MIN_INCOME);
			minPayment = state.getDouble(MIN_PAYMENT);
			incomeFinanceStream = (FinanceStream) AbstractStorable.newInstance(state.getJSONObject(INCOME_FINANCE_STREAM));
			paymentFinanceStream = (FinanceStream) AbstractStorable.newInstance(state.getJSONObject(PAYMENT_FINANCE_STREAM));
			paymentFinanceStream.setIncome(false);
		} catch (Exception e) {
			e.printStackTrace();
			throw new JSONException(getClassName() + " restore error");
		}
	}

	@Override
	public void validate() throws CreateModelException {
		// TODO Auto-generated method stub

	}
}
