package application;

import org.json.JSONException;
import org.json.JSONObject;

import exception.CreateModelException;

/**
 * @author Aleksandr
 */
public abstract class AbstractStorable {

    /**
     * Этому ключу соответствует название класса объекта
     */
    public static final String NAME      = "Name";

    /**
     * Этому ключу соответствует путь к директории, в которой хранится класс объекта
     */
    public static final String TYPE = "Type";

    /**
     * Создание объекта из сохраненного ранее состояния в JSON объекте. Сначала создается экземпляр класса, затем
     * восстанавливается его состояние методом restore(). Этот метод также за восстановление всех простых полей и сложных
     * объектов, которые принадлежат текущему объекту. Восстановление объекта можно представить в виде дерева. Далее проходит
     * валидация созданного объекта и вызввается метод init(), который подготовит объекты к работе. Метод init() вызывается ПОСЛЕ
     * того, как все объекты будут проинициализированны из-за специфики метода restore()
     * 
     * @param state
     *            сохраненное состояние объекта
     * @return востановленный объект
     * @throws JSONException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws CreateModelException
     */
    public static AbstractStorable newInstance(JSONObject state) throws JSONException, ClassNotFoundException,
	    InstantiationException, IllegalAccessException, CreateModelException {
	Class<?> c = Class.forName(state.getString(TYPE) + "." + state.getString(NAME));
	Object object = c.newInstance();
	AbstractStorable instance = (AbstractStorable) object;
	instance.restore(state);
	instance.validate();
	instance.init();
	return instance;
    }

    /**
     * Создание JSON объекта, в котором фиксируется состояние сохроняемой сущности.
     * 
     * @return - состояние объекта
     */
    public JSONObject store() throws JSONException {
	JSONObject state = new JSONObject();
	state.put(NAME, getClassName());
	state.put(TYPE, getDirectory());
	return state;
    }

    /**
     * Восстановление состояния объекта, которое было сохранено в JSON. Этот метод предполагает исключительно инициализацию полей,
     * информация которых была сохранена в JSON. Все дальнейшие мероприятия по инициализации объекта должны быть реализованы в
     * методе init()
     * 
     * @param state
     *            сохраренное состояние
     * @throws JSONException
     */
    public abstract void restore(JSONObject state) throws JSONException;

    /**
     * Инициализация компонент класса, необходимых для работы, выполнение всех мероприятий, которые надо провести после того, как
     * объект был востановлен из JSON
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
     * Получение имени класса
     * 
     * @return имя класса
     */
    protected String getClassName() {
	String className = this.getClass().getName();
	className = className.substring(className.lastIndexOf("."));
	return className;
    }

    /**
     * Получение директории, в которой находится класс
     * 
     * @return
     */
    protected String getDirectory() {
	String directory = this.getClass().getName();
	directory = directory.substring(0, directory.lastIndexOf("."));
	return directory;
    }

}
