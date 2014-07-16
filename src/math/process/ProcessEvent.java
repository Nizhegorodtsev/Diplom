package math.process;

public class ProcessEvent
{
	public ProcessEvent()
	{
		_eventTime = 0;
		_eventType = "";
	}

	public ProcessEvent(long time, String type)
	{
		_eventTime = time;
		_eventType = type;
	}

	public double getEventTime()
	{
		return _eventTime;
	}

	public String getEventType()
	{
		return _eventType;
	}

	private String _eventType;
	private double _eventTime;

	public final static String EVENT_OCCURED = "Event_occured";

	public final static String EVENT_STATE_CHANGED = "Event_state_changed";
}
