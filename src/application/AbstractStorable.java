package application;

import org.json.JSONException;
import org.json.JSONObject;

import exception.CreateModelException;

/**
 * @author Aleksandr
 */
public abstract class AbstractStorable {

    public AbstractStorable() {
	// init();
    }

    /**
     * Класс объекта
     */
    public static final String NAME      = "Name";

    /**
     * Путь к директории, в которой хранится класс объекта
     */
    public static final String DIRECTORY = "Directory";

    /**
     * Создание JSON объекта, в котором фиксируется состояние сохроняемой сущности.
     * 
     * @return - состояние объекта
     */
    public JSONObject store() throws JSONException {
	JSONObject state = new JSONObject();
	state.put(NAME, getClassName());
	state.put(DIRECTORY, getDirectory());
	return state;
    }

    /**
     * Восстановление состояния объекта, которое было сохранено в JSON
     * 
     * @param state
     *            сохраренное состояние
     * @throws JSONException
     */
    public abstract void restore(JSONObject state) throws JSONException;

    /**
     * 
     * @param state
     * @return
     * @throws JSONException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws CreateModelException
     */
    public static AbstractStorable newInstance(JSONObject state) throws JSONException, ClassNotFoundException,
	    InstantiationException, IllegalAccessException, CreateModelException {
	Class<?> c = Class.forName(state.getString(DIRECTORY) + "." + state.getString(NAME));
	Object object = c.newInstance();
	AbstractStorable instance = (AbstractStorable) object;
	instance.restore(state);
	instance.validate();
	instance.init();
	return instance;
    }

    /**
     * Инициализация компонент класса, необходимых для работы
     */
    public abstract void init();

    /**
     * Валидация данных, переданных для инициализации
     * 
     * @throws CreateModelException
     *             если варидация не прошла успешно. Это может быть вызвано тем, что были переданы недопустимые параметры
     */
    public abstract void validate() throws CreateModelException;

    /**
     * Инициализация компонент
     */
    // protected void init() {}

    protected String getClassName() {
	String className = this.getClass().getName();
	className = className.substring(className.lastIndexOf("."));
	return className;
    }

    protected String getDirectory() {
	String directory = this.getClass().getName();
	directory = directory.substring(0, directory.lastIndexOf("."));
	return directory;
    }

}
