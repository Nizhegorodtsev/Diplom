package math.random;

import application.AbstractStorable;

/**
 * Абстракция случайной величины, распределенной по некоторому закону
 * 
 * @author Aleksandr
 */
public abstract class AbstractRandomValue extends AbstractStorable {

    /**
     * Следующее значение случайной величины
     * 
     * @return
     */
    public abstract double nextValue();
}
