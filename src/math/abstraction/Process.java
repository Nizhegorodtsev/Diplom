package math.abstraction;

import org.json.JSONException;
import org.json.JSONObject;

import application.AbstractStorable;

/**
 * Абстракция математического процесса, который генерирует время наступления
 * событий
 * 
 * @author Aleksandr
 *
 */
public abstract class Process extends AbstractStorable {

	public static String PROCESS_NAME = "Process_name";

	public static final String DIRECTORY = "math.process.";

	public double nextValue() {
		return 0;
	}

	public static Process newInstance(JSONObject obj) throws JSONException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		Process process = (Process) AbstractStorable.newAbstractInstance(
				obj.getString(PROCESS_NAME), DIRECTORY, obj);
		return process;
	}
}
