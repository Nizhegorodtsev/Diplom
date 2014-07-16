package business.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import business.abstraction.Model;
import business.model.insurance.FinanceEvent;
import business.model.insurance.FinanceStream;
import business.model.insurance.FinanceStreamBuilder;
import exception.CreateModelException;

public class InsuranceModel extends Model
{
    private double                        startCapital           = 0;
    private double                        capital                = 0;
    private double                        maxIncome              = 0;
    private double                        minIncome              = 0;
    private double                        maxPayment             = 0;
    private double                        minPayment             = 0;
    private ArrayList<Double>             capitalHistory;
    private FinanceStream                 incomeFlow;
    private FinanceStream                 paymentFlow;
    private TreeMap<Double, FinanceEvent> eventMap;

    public static final String            START_CAPITAL          = "Start_capital";
    public static final String            MAX_INCOME             = "Max_income";
    public static final String            MIN_INCOME             = "Min_income";
    public static final String            MAX_PAYMENT            = "Max_payment";
    public static final String            MIN_PAYMENT            = "Min_payment";
    public static final String            INCOME_FINANCE_STREAM  = "Income_finance_stream";
    public static final String            PAYMENT_FINANCE_STREAM = "Payment_finance_stream";

    public InsuranceModel()
    {
	capitalHistory = new ArrayList<>();
	eventMap = new TreeMap<Double, FinanceEvent>();
	incomeFlow = new FinanceStream();
	paymentFlow = new FinanceStream();
	paymentFlow.setIncome(false);
    }

    @Override
    public void restore(HashMap<String, String> modelTree) throws CreateModelException
    {
	try
	{
	    startCapital = Double.parseDouble(modelTree.get(START_CAPITAL));
	    maxIncome = Double.parseDouble(modelTree.get(MAX_INCOME));
	    minIncome = Double.parseDouble(modelTree.get(MIN_INCOME));
	    maxPayment = Double.parseDouble(modelTree.get(MAX_PAYMENT));
	    minPayment = Double.parseDouble(modelTree.get(MIN_PAYMENT));
	    incomeFlow = FinanceStreamBuilder.createFinanceStream(modelTree.get(INCOME_FINANCE_STREAM));
	    paymentFlow = FinanceStreamBuilder.createFinanceStream(modelTree.get(PAYMENT_FINANCE_STREAM));
	    paymentFlow.setIncome(false);
	}
	catch (Exception e)
	{
	    throw new CreateModelException(e.getMessage());
	}
    }

    public void init()
    {
	capital = startCapital;
	FinanceEvent event = incomeFlow.nextBusinessEvent();
	eventMap.put(event.getTime() + currentTime, event);
	event = paymentFlow.nextBusinessEvent();
	eventMap.put(event.getTime() + currentTime, event);
	capitalHistory.add(capital);
    }

    public void doStep()
    {
	FinanceEvent event = eventMap.remove(eventMap.firstKey());
	capital += event.getAmount();
	currentTime += event.getTime();
	System.out.print(capital);
	System.out.println("   " + event.getAmount());
	if (capital < 0)
	{
	    currentModelCicle++;
	    if (currentModelCicle > countOfModelCicle)
		stopRun();
	}
	else
	{
	    FinanceEvent newEvent = (FinanceEvent) event.getBusinessProcess().nextBusinessEvent();
	    eventMap.put(newEvent.getTime() + currentTime, newEvent);
	    capitalHistory.add(capital);
	}
    }

    public void finish()
    {
	System.out.println(capitalHistory.size());
    }

    @Override
    public boolean check(HashMap<String, String> paramsTree)
    {
	if (!paramsTree.containsKey(MODEL_NAME))
	    return false;
	if (!paramsTree.containsKey(COUNT_OF_MODEL_CICLE))
	    return false;
	if (!paramsTree.containsKey(START_CAPITAL))
	    return false;
	if (!paramsTree.containsKey(MAX_INCOME))
	    return false;
	if (!paramsTree.containsKey(MIN_INCOME))
	    return false;
	if (!paramsTree.containsKey(MAX_PAYMENT))
	    return false;
	if (!paramsTree.containsKey(MIN_PAYMENT))
	    return false;
	if (!paramsTree.containsKey(INCOME_FINANCE_STREAM))
	    return false;
	if (!paramsTree.containsKey(PAYMENT_FINANCE_STREAM))
	    return false;
	return true;
    }
}
