package src;

import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class SpaceSystem {
	
	List<Particle> particles;
	GearSpaceAlgorithm gs;
	double delta_t;
	

	public SpaceSystem(List<Particle> particles, double delta_t) {
		this.particles = particles;
		this.delta_t = delta_t;
		this.gs = new GearSpaceAlgorithm(particles, delta_t);
	}

	public void updateParticles() {
		gs.updateParticles();
	}

	public void addSpaceship(Particle p) {
		particles.add(p);
		this.gs = new GearSpaceAlgorithm(particles, delta_t);

	}

}

