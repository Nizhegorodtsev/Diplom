package math.abstraction;

import application.IStorable;

/**
 * Абстракция случайной величины, распределенной по некоторому закону
 * 
 * @author Aleksandr
 */
public abstract class RandomValue implements IStorable
{
    public abstract double nextValue();

}
