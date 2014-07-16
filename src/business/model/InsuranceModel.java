package business.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import math.process.PoissonProcess;
import math.random.ExpRandomValue;
import business.abstraction.Model;
import business.model.insurance.FinanceFlow;
import business.model.insurance.InsuranceEvent;
import exception.CreateModelException;

public class InsuranceModel extends Model
{
	private double startCapital = 0;
	private double capital = 0;
	private double maxIncome = 0;
	private double minIncome = 0;
	private double maxPayment = 0;
	private double minPayment = 0;
	private ArrayList<Double> capitalHistory;
	private FinanceFlow incomeFlow;
	private FinanceFlow paymentFlow;
	private TreeMap<Double, InsuranceEvent> eventMap;

	public static final String MODEL_NAME = "Insurance_model";
	public static final String START_CAPITAL = "Start_capital";
	public static final String MAX_INCOME = "Max_income";
	public static final String MIN_INCOME = "Min_income";
	public static final String MAX_PAYMENT = "Max_payment";
	public static final String MIN_PAYMENT = "Min_payment";
	public static final String INCOME_PROCESS = "Income_process_type";
	public static final String INCOME_PROCESS_PARAMS = "Income_process_params";
	public static final String INCOME_AMOUNT = "Income_amount_type";
	public static final String INCOME_AMOUNT_PARAMS = "Income_amount_params";
	public static final String PAYMENT_PROCESS = "Payment_process_type";
	public static final String PAYMENT_PROCESS_PARAMS = "Income_process_params";
	public static final String PAYMENT_AMOUNT = "Payment_amount_type";
	public static final String PAYMENT_AMOUNT_PARAMS = "Payment_amount_params";

	public InsuranceModel()
	{
		capitalHistory = new ArrayList<>();
		eventMap = new TreeMap<Double, InsuranceEvent>();
		incomeFlow = new FinanceFlow();
		paymentFlow = new FinanceFlow();
		paymentFlow.setIncome(false);
	}

	@Override
	public void restore(HashMap<String, String> modelTree) throws CreateModelException
	{
		try
		{
			countOfModelCicle = Integer.parseInt(modelTree.get(COUNT_OF_MODEL_CICLE));
			startCapital = Double.parseDouble(modelTree.get(START_CAPITAL));
			maxIncome = Double.parseDouble(modelTree.get(MAX_INCOME));
			minIncome = Double.parseDouble(modelTree.get(MIN_INCOME));
			maxPayment = Double.parseDouble(modelTree.get(MAX_PAYMENT));
			minPayment = Double.parseDouble(modelTree.get(MIN_PAYMENT));
			incomeFlow.setProcess(modelTree.get(INCOME_PROCESS));
			incomeFlow.setRandomValue(modelTree.get(INCOME_AMOUNT));
			paymentFlow.setProcess(modelTree.get(PAYMENT_PROCESS));
			paymentFlow.setRandomValue(modelTree.get(PAYMENT_AMOUNT));
		}
		catch (Exception e)
		{
			throw new CreateModelException(e.getMessage());
		}
	}

	public void init()
	{
		capital = startCapital;
		InsuranceEvent event = incomeFlow.nextBusinessEvent();
		eventMap.put(event.getTime(), event);
		event = paymentFlow.nextBusinessEvent();
		eventMap.put(event.getTime(), event);
		capitalHistory.add(capital);
	}

	public void doStep()
	{
		InsuranceEvent event = eventMap.remove(eventMap.firstKey());
		capital += event.getAmount();
		System.out.println(capital);
		if (capital < 0)
		{
			currentModelCicle++;
			if (currentModelCicle > countOfModelCicle)
				stopRun();
		}
		else
		{
			InsuranceEvent newEvent = (InsuranceEvent) event.getBusinessProcess().nextBusinessEvent();
			eventMap.put(newEvent.getTime(), newEvent);
			capitalHistory.add(capital);
		}
	}

	public void finish()
	{
		System.out.print(capital);
		System.out.print(capitalHistory.size());
	}

	public static String createTemplate()
	{
		String template = new String();
		template += MODEL_NAME + " = " + MODEL_NAME + ";\n";
		template += START_CAPITAL + " = 0;\n";
		template += INCOME_PROCESS + " = ;\n";
		template += INCOME_PROCESS_PARAMS + " = \n";
		template += PoissonProcess.createTemplate();
		template += INCOME_AMOUNT + " = ;\n";
		template += ExpRandomValue.createTemplate();
		template += PAYMENT_PROCESS + " = ;\n";
		template += PoissonProcess.createTemplate();
		template += PAYMENT_AMOUNT + " = ;\n";
		template += ExpRandomValue.createTemplate();
		return template;
	}
}