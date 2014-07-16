package application;

import java.util.HashMap;

import exception.CreateModelException;

/**
 * @author Aleksandr
 */
public interface IStorable
{
	/**
	 * �������������� �������
	 * 
	 * @param paramsTree
	 * @throws CreateModelException
	 */
	public void restore(HashMap<String, String> paramsTree) throws CreateModelException;

	/**
	 * �������� ����������, ������� ������ ��������� ������� ��� �����������
	 * ���������� � ����
	 * 
	 * @return - ��������� �������
	 */
	public HashMap<String, String> store();
}
