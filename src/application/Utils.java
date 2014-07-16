package application;

import java.util.HashMap;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

public class Utils
{
    public static final double EPSILON = 1E-6;

    public static HashMap<String, String> getParamsMap(JSONObject obj) throws JSONException
    {
	HashMap<String, String> map = new HashMap<String, String>();
	Set<String> keySey = map.keySet();
	for (String key : keySey)
	    map.put(key, obj.getString(key));
	return map;
    }
}
