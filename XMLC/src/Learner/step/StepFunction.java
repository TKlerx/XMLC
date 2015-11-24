package Learner.step;

import jsat.linear.Vec;

/**
 *
 * @author Karlson Pfannschmidt
 */
public interface StepFunction extends Cloneable {
	/**
	 * Applies a gradient descent step to the weight vector {@code w}.
	 * @param w weight vector
	 * @param grad vector of gradients
	 * @return adjustment to bias:
	 * {@code bias = bias - returnValue}
	 */
	public void step(Vec w, Vec grad);

	/**
	 * Applies a gradient descent step to the weight vector {@code w}.
	 * The bias is handled separately.
	 * @param w weight vector
	 * @param grad vector of gradients
	 * @param bias
	 * @param biasGrad
	 * @return adjustment to bias:
	 * {@code bias = bias - returnValue}
	 */
	public double step(Vec w, Vec grad, double bias, double biasGrad);
	public StepFunction clone();
}
