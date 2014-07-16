package business.abstraction;

/**
 * ���������� ������-��������, ������� ��������� � ���������� �������
 * 
 * @author Aleksandr
 */
public interface IBusinessProcess
{
	/**
	 * ������������ ��������� ������-������� ��������
	 * 
	 * @return ��������� ������-�������
	 */
	public BusinessEvent nextBusinessEvent();

}
