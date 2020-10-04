package src;

import java.util.*;

public class BeemanAlgorithm {

	private Particle particle;
	private double delta_t;
	private double r;
	private double v;
	private double a;
	private double prev_a;
	private double g_constant;
	private double k_constant;

	public BeemanAlgorithm(Particle particle, double delta_t, double g_constant, double k_constant){
		this.particle = particle;
		this.delta_t = delta_t;
		this.r = particle.getX();
		this.v = particle.getVelocityX();
		this.a = getAcceleration(r, v);
		this.prev_a = getAcceleration(r - delta_t*v, v - delta_t*a);
		this.g_constant = g_constant;
		this.k_constant = k_constant;

	}

	public void updateParticle() {
		
			double new_r = getNewPosition();
			double new_v = getNewVelocity(new_r);
			this.r = new_r;
			this.v = new_v;
			this.prev_a = a;
			this.a = getAcceleration(r, v);
			particle.setX(new_r);
			particle.setVelocityX(new_v);
		

	}

	public double getNewPosition() {
		return (r + v*delta_t + (1/6)*(4*a - prev_a)*Math.pow(delta_t,2));
	}

	public double getNewVelocity(double new_r) {
		double v_pred = v + (3/2)*a*delta_t - (1/2)*prev_a*delta_t;
		double a_pred = getAcceleration(new_r, v_pred);
		return (v + (1/6)*delta_t*(2*a_pred + 5*a - prev_a));
	}

	public double getAcceleration(double position, double velocity) {
		return -(k_constant*position + g_constant*velocity)/particle.getMass();
	}

}