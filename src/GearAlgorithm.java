
import java.util.*;

public class GearAlgorithm {

	private Particle particle;
	private double delta_t;
	private double g_constant;
	private double k_constant;
	private double values[] = new double[6];
	private double coefficients[] = {3/16, 251/360, 1, 11/18, 1/6, 1/60};

	//first derivative = position
	//second derivative = velocity
	//third derivative = acceleration
	public GearAlgorithm(Particle particle, double delta_t, double g_constant, double k_constant){
		this.particle = particle;
		this.delta_t = delta_t;
		this.g_constant = g_constant;
		this.k_constant = k_constant;
		values[0] = particle.getX();
		values[1] = particle.getVelocityX();
		for(int i = 2; i < 6; i++) {
			values[i] = getAcceleration(values[i-2], values[i-1]);
		}

	}

	public void updateParticle() {
		double new_values[] = makePredictions();
		double acceleration = getAcceleration(new_values[0], new_values[1]);
		double delta_R2 = (acceleration - new_values[2])*Math.pow(delta_t, 2)/2;

		for(int i=0; i<6; i++) {
			values[i] = new_values[i] + (delta_R2*coefficients[i]*factorial(i))/Math.pow(delta_t,i);
		}
		particle.setX(values[0]);
		particle.setVelocityX(values[1]);

	}

	public double factorial(int num) {
		int base = 1;
		for(int j = 2; j<=num; j++) {
			base *= j;
		}
		return base;
	}

	public double getAcceleration(double position, double velocity) {
		return -(k_constant*position + g_constant*velocity)/particle.getMass();
	}

	public double[] makePredictions() {
		double new_values[] = new double[6];

		new_values[0] = values[0] +
		values[1]*delta_t +
		values[2]*Math.pow(delta_t, 2)/2 +
		values[3]*Math.pow(delta_t, 3)/6 +
		values[4]*Math.pow(delta_t, 4)/24 +
		values[5]*Math.pow(delta_t, 5)/120;

		new_values[1] = values[1] +
		values[2]*delta_t +
		values[3]*Math.pow(delta_t, 2)/2 +
		values[4]*Math.pow(delta_t, 3)/6 +
		values[5]*Math.pow(delta_t, 4)/24;

		new_values[2] = values[2] +
		values[3]*delta_t +
		values[4]*Math.pow(delta_t, 2)/2 +
		values[5]*Math.pow(delta_t, 3)/6;

		new_values[3] = values[3] +
		values[4]*delta_t +
		values[5]*Math.pow(delta_t, 2)/2 ;

		new_values[4] = values[4] +
		values[5]*delta_t;

		new_values[5] = values[5];

		return new_values;
	}
}

