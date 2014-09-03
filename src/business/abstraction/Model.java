package business.abstraction;

import java.util.HashMap;

import application.AbstractStorable;
import exception.CreateModelException;
import exception.ModelImplementationExeption;

public abstract class Model extends AbstractStorable {
	protected boolean run = false;

	protected int countOfModelCicle = 0;

	protected int currentModelCicle = 0;

	protected double currentTime = 0;

	public static final String MODEL_NAME = "Model_name";

	public static final String COUNT_OF_MODEL_CICLE = "Count_of_model_cicle";

	public Model() {
	}

	/**
	 * 
	 * 
	 * @throws ModelImplementationExeption
	 */
	public void startRun() throws ModelImplementationExeption {
		init();
		run = true;
		while (run)
			doStep();
		finish();
	}

	/**
	 * 
	 */
	public void stopRun() {
		run = false;
	}

	/**
	 * 
	 * @throws ModelImplementationExeption
	 */
	public void init() throws ModelImplementationExeption {
		throw new ModelImplementationExeption();
	}

	/**
	 * 
	 * @throws ModelImplementationExeption
	 */
	public void doStep() throws ModelImplementationExeption {
		throw new ModelImplementationExeption();
	}

	/**
	 * 
	 * @throws ModelImplementationExeption
	 */
	public void finish() throws ModelImplementationExeption {
		throw new ModelImplementationExeption();
	}
}
