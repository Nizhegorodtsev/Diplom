package business.model;

import java.util.HashMap;

import application.JSONParser;
import business.abstraction.Model;
import exception.CreateModelException;

/**
 * Класс отвечает за создание объектов модели, случайной величины и потока
 * 
 * @author Aleksandr
 */
public class ModelBuilder
{
    public static Model createModel(String file) throws CreateModelException
    {
	try
	{
	    HashMap<String, String> map = JSONParser.parseKeysMap(file);
	    String modelName = map.get(Model.MODEL_NAME);
	    Class<?> c = Class.forName("business.model." + modelName);
	    Object obj = c.newInstance();
	    Model model = (Model) obj;
	    if (!model.check(map))
		throw new CreateModelException();
	    model.restore(map);
	    return model;
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	    throw new CreateModelException(e.getMessage());
	}
    }
}
