package application;

import org.json.JSONException;
import org.json.JSONObject;

import exception.CreateModelException;

/**
 * @author Aleksandr
 */
public abstract class AbstractStorable {

	public static final String NAME = "Name";

	/**
	 * Восстановление объекта
	 * 
	 * @param paramsTree
	 * @throws CreateModelException
	 */
	public abstract void restore(JSONObject obj) throws JSONException;

	/**
	 * Проверка наличия всех полей, необходимых для инициализации
	 * 
	 * @param paramsTree
	 * @throws JSONException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	// public boolean check(HashMap<String, String> paramsTree);

	/**
	 * 
	 * @param state
	 * @param classDirectory
	 * @return
	 * @throws JSONException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	protected static AbstractStorable newAbstractInstance(String className,
			String classDirectory, JSONObject state) throws JSONException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		Class<?> c = Class.forName(classDirectory + className);
		Object object = c.newInstance();
		AbstractStorable instance = (AbstractStorable) object;
		instance.restore(state);
		return instance;
	}

	/**
	 * Создание шаблона
	 * 
	 */
	// public JSONObject template();

	/**
	 * Создание контейнера, который хранит состояние объекта для дальнейшего
	 * сохранения в файл
	 * 
	 * @return - состояние объекта
	 */
	public abstract JSONObject store() throws JSONException;
}
