package math.random;

import java.util.Random;

import application.SeedFabric;

/**
 * ������� ��������� ��������. ���������� ������������ �� ��������� (0, 1)
 * 
 * @author Aleksandr
 *
 */
public class BasicRandomValue
{
    private Random random;

    public BasicRandomValue()
    {
	random = new Random(SeedFabric.getInstance().getSeed());
    }

    public double nextValue()
    {
	return random.nextDouble();
    }
}
