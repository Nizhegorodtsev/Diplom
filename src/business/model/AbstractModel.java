package business.model;

import application.AbstractStorable;
import exception.ModelImplementationExeption;

public abstract class AbstractModel extends AbstractStorable {
	protected boolean			run						= false;

	protected int				countOfModelCicle		= 0;

	protected int				currentModelCicle		= 0;

	protected double			currentTime				= 0;

	public static final String	COUNT_OF_MODEL_CICLE	= "Count_of_model_cicle";

	/**
	 * 
	 * 
	 * @throws ModelImplementationExeption
	 */
	public void startRun() {
		init();
		run = true;
		while (run)
			doStep();
		finish();
	}

	public void stopRun() {
		run = false;
	}

	public abstract void doStep();

	public abstract void finish();
}
