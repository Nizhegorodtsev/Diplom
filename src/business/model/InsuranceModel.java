package business.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import business.abstraction.Model;
import business.model.insurance.FinanceEvent;
import business.model.insurance.FinanceStream;
import business.model.insurance.FinanceStreamBuilder;
import exception.CreateModelException;

public class InsuranceModel extends Model {
	private double startCapital = 0;
	private double capital = 0;
	private double maxIncome = 0;
	private double minIncome = 0;
	private double maxPayment = 0;
	private double minPayment = 0;
	private ArrayList<Double> capitalHistory;
	private FinanceStream incomeFlow;
	private FinanceStream paymentFlow;
	private TreeMap<Double, FinanceEvent> eventMap;

	public static final String START_CAPITAL = "Start_capital";
	public static final String MAX_INCOME = "Max_income";
	public static final String MIN_INCOME = "Min_income";
	public static final String MAX_PAYMENT = "Max_payment";
	public static final String MIN_PAYMENT = "Min_payment";
	public static final String INCOME_FINANCE_STREAM = "Income_finance_stream";
	public static final String PAYMENT_FINANCE_STREAM = "Payment_finance_stream";

	public InsuranceModel() {
		capitalHistory = new ArrayList<>();
		eventMap = new TreeMap<Double, FinanceEvent>();
		incomeFlow = new FinanceStream();
		paymentFlow = new FinanceStream();
		paymentFlow.setIncome(false);
	}

	public void init() {
		capital = startCapital;
		FinanceEvent event = incomeFlow.nextBusinessEvent();
		eventMap.put(event.getTime() + currentTime, event);
		event = paymentFlow.nextBusinessEvent();
		eventMap.put(event.getTime() + currentTime, event);
		capitalHistory.add(capital);
	}

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
			FinanceEvent newEvent = (FinanceEvent) event.getBusinessProcess()
					.nextBusinessEvent();
			eventMap.put(newEvent.getTime() + currentTime, newEvent);
			capitalHistory.add(capital);
		}
	}

	public void finish() {
		System.out.println(capitalHistory.size());
	}

}
