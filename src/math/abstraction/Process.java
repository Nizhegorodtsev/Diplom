package math.abstraction;

import java.util.HashMap;

import application.IStorable;
import exception.CreateModelException;

/**
 * ���������� ��������������� ��������, ������� ���������� ����� �����������
 * �������
 * 
 * @author Aleksandr
 *
 */
public class Process implements IStorable
{
    public double nextValue()
    {
	return 0;
    }

    @Override
    public void restore(HashMap<String, String> modelTree)
	    throws CreateModelException
    {
    }

    @Override
    public HashMap<String, String> store()
    {
	return null;
    }
}
