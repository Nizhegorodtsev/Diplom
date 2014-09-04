package application;

import org.json.JSONArray;
import org.json.JSONException;

public class JSONUtils {

	/**
	 * Распарсить вектор
	 * 
	 * @param array
	 * @return
	 * @throws JSONException
	 */
	public static double[] parseVector(JSONArray array) throws JSONException {
		double[] vector = new double[array.length()];
		for (int i = 0; i < array.length(); i++)
			vector[i] = array.getDouble(i);
		return vector;
	}

	/**
	 * Распарсить матрицу элементов
	 * 
	 * @param array
	 * @return
	 * @throws JSONException
	 */
	public static double[][] parseMatrix(JSONArray array) throws JSONException {
		double[][] matrix = new double[array.length()][];
		for (int i = 0; i < array.length(); i++)
			matrix[i] = parseVector(array.getJSONArray(i));
		return matrix;
	}

	public static JSONArray createJSONMatrix(double[][] matrix) throws JSONException {
		JSONArray array = new JSONArray();
		for (int i = 0; i < matrix.length; i++)
			array.put(createJSONVector(matrix[i]));
		return array;
	}

	public static JSONArray createJSONVector(double[] vector) throws JSONException {
		JSONArray array = new JSONArray();
		for (int i = 0; i < vector.length; i++)
			array.put(vector[i]);
		return array;
	}
}
