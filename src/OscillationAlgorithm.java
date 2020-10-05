package src;

import java.util.*;

public class OscillationAlgorithm {

	private Particle particle;
	private double delta_t;
	private double mass;
	private double time;
	private double amp;
	private double g_constant;
	private double k_constant;

	public OscillationAlgorithm(Particle particle, double delta_t, double g_constant, double k_constant, double amplitude){
		this.particle = particle;
		this.delta_t = delta_t;
		this.g_constant = g_constant;
		this.k_constant = k_constant;
		this.time = 0;
		this.mass = particle.getMass();
		this.amp = amplitude;

	}

	public void updateParticle() {
			double new_r = getNewPosition();
			particle.setX(new_r);
			time += delta_t;
	}

	public double getNewPosition() {
		double first_term = amp*Math.exp(-(g_constant*time)/(2*mass));
		double second_term = Math.cos(Math.sqrt(k_constant/mass - Math.pow(g_constant/(2*mass),2))*time);
		return first_term*second_term;
	}


}