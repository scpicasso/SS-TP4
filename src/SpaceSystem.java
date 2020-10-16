
import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class SpaceSystem {
	
	List<Particle> particles;
	GearSpaceAlgorithm gs;
	double delta_t;
	private static final double G_constant = 6.674e-11;
	

	public SpaceSystem(List<Particle> particles, double delta_t) {
		this.particles = particles;
		this.delta_t = delta_t;
		this.gs = new GearSpaceAlgorithm(particles, delta_t, null);
	}

	public void updateParticles() {
		gs.updateParticles();
	}

	public double getSystemEnergy() {
		double kineticEnergy = 0.0;
		double potentialEnergy = 0.0;
		
		double m1 = particles.get(0).getMass();
		double m2 = particles.get(1).getMass();
		double m3 = particles.get(2).getMass();
		double r12 = particles.get(0).getDistance(particles.get(1))*1000;
		double r13 = particles.get(0).getDistance(particles.get(2))*1000;
		double r23 = particles.get(1).getDistance(particles.get(2))*1000;
		
		potentialEnergy = G_constant*( (m1*m2)/r12 + (m1*m3)/r13 + (m2*m3)/r23 ); 
		
		for (Particle p: particles) {
			kineticEnergy += p.getEnergy();
		}
		
		return kineticEnergy - potentialEnergy;
		
	}
	
	
	public double getShipToMarsDistance() {
		
		return particles.get(3).getDistanceWithRadius(particles.get(2));
		
	}
	
	public double getShipVelocity() {
		
		return particles.get(3).getVelocity();
		
	}
	
//Space station height = 1500 km, orbital velocity = 7,12 km/s

	public void addSpaceship(double ship_v) {
		Particle sun = particles.get(0);
		Particle earth = particles.get(1);

		double srad = 0.1;

		double dx = earth.getX() - sun.getX();
		double dy = earth.getY() - sun.getY();
		double dist = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

		double ex = dx/dist;
		double ey = dy/dist;

		double x = ex*(1500 + earth.getRadius()) + earth.getX();
		double y = ey*(1500 + earth.getRadius()) + earth.getY();

		double vx = -ey*(7.12 + ship_v) + earth.getVelocityX();
		double vy = ex*(7.12 +ship_v) + earth.getVelocityY();

		Particle ship = new Particle(4, x, y, vx, vy, 5e+05, srad);

		particles.add(ship);

		this.gs = new GearSpaceAlgorithm(particles, delta_t, gs);

	}

}

