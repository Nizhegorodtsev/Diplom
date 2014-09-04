package business.abstraction;

/**
 * Бизнес-событие предметной области
 * 
 * @author Aleksandr
 */
public class BusinessEvent {
    /**
     * Тип события
     */
    protected String		  typeOfEvent = "";

    /**
     * Время наступления события
     */
    protected double		  eventTime   = 0;

    /**
     * Бизнес-процесс, в котором наступило событие
     */
    protected AbstractBusinessProcess businessProcess;

    public BusinessEvent() {
    }

    public void setType(String type) {
	this.typeOfEvent = type;
    }

    public void setTime(double time) {
	this.eventTime = time;
    }

    public void setBusinessProcess(AbstractBusinessProcess iBusinessProcess) {
	this.businessProcess = iBusinessProcess;
    }

    public String getType() {
	return typeOfEvent;
    }

    public double getTime() {
	return eventTime;
    }

    public AbstractBusinessProcess getBusinessProcess() {
	return businessProcess;
    }
}
