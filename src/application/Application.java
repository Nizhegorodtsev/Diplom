package application;

import java.io.BufferedReader;
import java.io.FileReader;

import business.abstraction.Model;

public class Application
{
	public static Application getInstance()
	{
		if (instance == null)
		{
			synchronized (Application.class)
			{
				if (instance == null)
				{
					instance = new Application();
				}
			}
		}
		return instance;
	}

	public Application()
	{

	}

	public static String start()
	{
		String text = "";
		BufferedReader br;
		try
		{
			br = new BufferedReader(new FileReader("model.txt"));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null)
			{
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			text = sb.toString();
			br.close();
		}
		catch (Exception e)
		{

		}
		return text;
	}

	private static Application instance;

	public static void main(String args[])
	{
		Application appData = getInstance();
		try
		{
			Class<?> c = Class.forName("business.model.InsuranceModel");
			Object obj = c.newInstance();

			Model model = Builder.createModel(start());
			model.startRun();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}