package math.process;

import application.AbstractStorable;

/**
 * Абстракция математического процесса, который генерирует время наступления
 * событий
 * 
 * @author Aleksandr
 *
 */
public abstract class AbstractProcess extends AbstractStorable {

	public double nextValue() {
		return 0;
	}
}
