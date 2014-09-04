package application;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import exception.CreateModelException;

public class JSONParser {
    public static HashMap<String, String> parseKeysMap(String file) throws JSONException {
	JSONObject obj = new JSONObject(file);
	HashMap<String, String> map = new HashMap<String, String>();
	Iterator<?> iterator = obj.keys();
	while (iterator.hasNext()) {
	    String key = (String) iterator.next();
	    map.put(key, obj.get(key).toString());
	}

	return map;
    }

    public static double[] parseVector(String file) throws CreateModelException {
	double[] vector = new double[1];
	return vector;
    }

    public static double[][] parseMatrix(String file) throws CreateModelException {
	double[][] vector = new double[1][1];
	return vector;
    }
}
