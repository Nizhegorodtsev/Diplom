package business.abstraction;

import java.util.HashMap;

import application.IStorable;
import exception.CreateModelException;
import exception.ModelImplementationExeption;

public class Model implements IStorable
{
    protected boolean          run                  = false;

    protected int              countOfModelCicle    = 0;

    protected int              currentModelCicle    = 0;

    protected double           currentTime          = 0;

    public static final String MODEL_NAME           = "Model_name";

    public static final String COUNT_OF_MODEL_CICLE = "Count_of_model_cicle";

    public Model()
    {
    }

    /**
     * 
     * 
     * @param modelTree
     * @throws CreateModelException
     */
    @Override
    public void restore(HashMap<String, String> modelTree) throws CreateModelException
    {
    }

    @Override
    public boolean check(HashMap<String, String> paramsTree)
    {
	return false;
    }

    @Override
    public HashMap<String, String> store()
    {
	return null;
    }

    /**
     * 
     * 
     * @throws ModelImplementationExeption
     */
    public void startRun() throws ModelImplementationExeption
    {
	init();
	run = true;
	while (run)
	    doStep();
	finish();
	finishRun();
    }

    /**
	 * 
	 */
    public void stopRun()
    {
	run = false;
    }

    /**
     * 
     * @throws ModelImplementationExeption
     */
    public void init() throws ModelImplementationExeption
    {
	throw new ModelImplementationExeption();
    }

    /**
     * 
     * @throws ModelImplementationExeption
     */
    public void doStep() throws ModelImplementationExeption
    {
	throw new ModelImplementationExeption();
    }

    /**
     * 
     * @throws ModelImplementationExeption
     */
    public void finish() throws ModelImplementationExeption
    {
	throw new ModelImplementationExeption();
    }

    /**
     * 
     * @throws ModelImplementationExeption
     */
    public void finishRun() throws ModelImplementationExeption
    {
	throw new ModelImplementationExeption();
    }
}
