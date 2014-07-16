package business.abstraction;

/**
 * Некоторый бизнес-процесс предметной
 * области
 * 
 * @author Aleksandr
 */
public interface IBusinessProcess
{
	/**
	 * Возвращается следующее бизнес-событие
	 * процесса
	 * 
	 * @return - следующее бизнес-событие
	 */
	public BusinessEvent nextBusinessEvent();

}
