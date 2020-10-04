package src;

import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class SimSystem {
	
	List<Particle> particles;
	List<BeemanAlgorithm> beeman;
	List<GearAlgorithm> gear;
	double delta_t;
	double k_constant;
	double g_constant;

	public SimSystem(List<Particle> particles, double delta_t, double k_constant, double g_constant) {
		this.particles = particles;
		this.delta_t = delta_t;
		this.k_constant = k_constant;
		this.g_constant = g_constant;
		this.beeman = new ArrayList<>();
		this.gear = new ArrayList<>();

	}

	public void updateParticles() {
		for(BeemanAlgorithm b : beeman){
			b.updateParticle();
		}
		for(GearAlgorithm g : gear){
			g.updateParticle();
		}
	}

	public void addAlgorithm(String algorithm) {
		switch(algorithm) {
			case "G":
				for(Particle p:particles) {
					gear.add(new GearAlgorithm(p, delta_t, g_constant, k_constant));
				}
				break;
			case "B":
				for(Particle p:particles) {
					beeman.add(new BeemanAlgorithm(p, delta_t, g_constant, k_constant));
				}
				break;
			case "V":
				return;
		}
	}

}

