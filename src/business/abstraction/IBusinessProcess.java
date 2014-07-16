package business.abstraction;

/**
 * Абстракция бизнес-процесса, который протекает в предметной области
 * 
 * @author Aleksandr
 */
public interface IBusinessProcess
{
	/**
	 * Возвращается следующее бизнес-событие процесса
	 * 
	 * @return следующее бизнес-событие
	 */
	public BusinessEvent nextBusinessEvent();

}
