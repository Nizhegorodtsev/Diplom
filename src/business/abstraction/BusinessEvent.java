package business.abstraction;

/**
 * ������-������� � ������
 * 
 * @author Aleksandr
 */
public class BusinessEvent
{
	/**
	 * ��� �������
	 */
	protected String typeOfEvent = "";

	/**
	 * ����� ����������� �������
	 */
	protected double eventTime = 0;

	protected IBusinessProcess businessProcess;

	public BusinessEvent()
	{
	}

	public BusinessEvent(String type, double time)
	{
		this.typeOfEvent = type;
		this.eventTime = time;
	}

	public String getType()
	{
		return typeOfEvent;
	}

	public double getTime()
	{
		return eventTime;
	}

	public IBusinessProcess getBusinessProcess()
	{
		return businessProcess;
	}

	public void setType(String type)
	{
		this.typeOfEvent = type;
	}

	public void setTime(double time)
	{
		this.eventTime = time;
	}

	public void setBusinessProcess(IBusinessProcess iBusinessProcess)
	{
		this.businessProcess = iBusinessProcess;
	}
}
