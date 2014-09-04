package business.abstraction;

import application.AbstractStorable;

/**
 * Абстракция бизнес-процесса, который протекает в предметной области
 * 
 * @author Aleksandr
 */
public abstract class AbstractBusinessProcess extends AbstractStorable {

    /**
     * Возвращается следующее бизнес-событие процесса
     * 
     * @return следующее бизнес-событие
     */
    public abstract BusinessEvent nextBusinessEvent();

}
