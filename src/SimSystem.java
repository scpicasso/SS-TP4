package src;

import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class SimSystem {
	
	List<Particle> particles;
	List<BeemanAlgorithm> beeman;
	List<GearAlgorithm> gear;
	List<OscillationAlgorithm> osc;
	double delta_t;
	double k_constant;
	double g_constant;
	double amp;

	public SimSystem(List<Particle> particles, double delta_t, double k_constant, double g_constant, double amp) {
		this.particles = particles;
		this.delta_t = delta_t;
		this.k_constant = k_constant;
		this.g_constant = g_constant;
		this.amp = amp;
		this.beeman = new ArrayList<>();
		this.gear = new ArrayList<>();
		this.osc = new ArrayList<>();

	}

	public void updateParticles() {
		for(BeemanAlgorithm b : beeman) {
			b.updateParticle();
		}
		for(GearAlgorithm g : gear) {
			g.updateParticle();
		}
		for(OscillationAlgorithm o : osc) {
			o.updateParticle();
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
			case "O":
				for(Particle p:particles) {
					osc.add(new OscillationAlgorithm(p, delta_t, g_constant, k_constant, amp));
				}
				break;
			case "V":
				return;
		}
	}

}

