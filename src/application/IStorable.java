package application;

import java.util.HashMap;

import exception.CreateModelException;

/**
 * @author Aleksandr
 */
public interface IStorable
{
	/**
	 * Восстановление объекта
	 * 
	 * @param paramsTree
	 * @throws CreateModelException
	 */
	public void restore(HashMap<String, String> paramsTree) throws CreateModelException;

	/**
	 * Создание контейнера, который хранит состояние объекта для дальнейшего
	 * сохранения в файл
	 * 
	 * @return - состояние объекта
	 */
	public HashMap<String, String> store();
}
