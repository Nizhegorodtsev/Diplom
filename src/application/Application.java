package application;

import gui.MainFrame;

import java.io.BufferedReader;
import java.io.FileReader;

import math.random.RandomExponentialValue;
import math.random.RandomNormalValue;

import org.json.JSONObject;

import business.model.AbstractModel;

public class Application {
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

	public Application() {

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
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			text = sb.toString();
			br.close();
		} catch (Exception e) {

		}
		return text;
	}

	private static Application	instance;

	public static void main(String args[]) {
		Application appData = getInstance();
		RandomNormalValue v = new RandomNormalValue();
		Class<?> c = RandomNormalValue.class;
		Object object;
		try {
			object = c.newInstance();
			AbstractStorable instance = (AbstractStorable) object;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * try { AbstractModel model = (AbstractModel) AbstractStorable .newInstance(new JSONObject(getModelFile()));
		 * model.startRun(); MainFrame frame = new MainFrame(); frame.setVisible(true); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
	}
}
