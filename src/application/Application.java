package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.JSONObject;

import business.model.AbstractModel;

public class Application {

	private ArrayList<Class<?>> modelList;

	private ArrayList<Class<?>> processList;

	private ArrayList<Class<?>> randomList;

	public static final String MODEL_DIRECRORY = "business.model";

	public static final String PROCESS_DIRECRORY = "math.process";

	public static final String RANDOM_DIRECRORY = "math.random";

	public Application() {
		modelList = MyClassLoader.getClassesByPackage(MODEL_DIRECRORY);
		processList = MyClassLoader.getClassesByPackage(PROCESS_DIRECRORY);
		randomList = MyClassLoader.getClassesByPackage(RANDOM_DIRECRORY);
	}

	public static Application getInstance() {
		if (instance == null) {
			synchronized (Application.class) {
				if (instance == null) {
					instance = new Application();
				}
			}
		}
		return instance;
	}

	public static String getModelFile() {
		String text = "";
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("models/model.txt"));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				// sb.append(System.lineSeparator());
				line = br.readLine();
			}
			text = sb.toString();
			br.close();
		} catch (Exception e) {

		}
		return text;
	}

	private static Application instance;

	public static void main(String args[]) {
		Application appData = getInstance();
		try {
			AbstractModel model = (AbstractModel) AbstractStorable
					.newInstance(new JSONObject(getModelFile()));
			Thread modelThread = new Thread(model);
			modelThread.setName(model.getClassName());
			modelThread.start();
			// MainFrame frame = new MainFrame();
			// frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
