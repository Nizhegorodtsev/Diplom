package application;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileManager {

	public static String readFile(String filePath) {
		String text = "";
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filePath));
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

	public static void writeFile(String file, String filePath) {

	}
}
