package business.model;

import application.AbstractStorable;

public abstract class AbstractModel extends AbstractStorable implements
		Runnable {
	protected boolean run = false;

	protected int countOfModelCicle = 0;

	protected int currentModelCicle = 0;

	protected double currentTime = 0;

	public static final String COUNT_OF_MODEL_CICLE = "Count_of_model_cicle";

	@Override
	public void run() {
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
