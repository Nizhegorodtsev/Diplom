package math.abstraction;

import application.IStorable;

/**
 * Абстракция случайной величины, распределенной по некоторому закону
 * 
 * @author Aleksandr
 */
public abstract class RandomValue implements IStorable
{
    public static String RANDOM_VALUE_NAME = "Random_value_name";

    public abstract double nextValue();
}
