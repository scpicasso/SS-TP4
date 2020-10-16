
public class VelocityVerletAlgorithm {
	private Particle particle;
	private double delta_t;
	private double r;
	private double v;
	private double a;
	private double g_constant;
	private double k_constant;

	public VelocityVerletAlgorithm(Particle particle, double delta_t, double g_constant, double k_constant){
		this.particle = particle;
		this.delta_t = delta_t;
		this.r = particle.getX();
		this.v = particle.getVelocityX();
		this.a = particle.getElasticAcceleration(r, k_constant, g_constant, v);
		this.g_constant = g_constant;
		this.k_constant = k_constant;
	}

	public void updateParticle() {
			double new_r = getNewPosition();
			double new_v = getNewVelocity(new_r);
			this.r = new_r;
			this.v = new_v;
			this.a = particle.getElasticAcceleration(r, k_constant, g_constant, v);
			particle.setX(r);
			particle.setVelocityX(v);	
	}

	public double getNewPosition() {
		return (r + delta_t*v + delta_t*delta_t*a/2);
	}

	public double getNewVelocity(double new_r) {
	
		double vdt_2 = v + a*delta_t/2;
		double adt = particle.getElasticAcceleration(new_r, k_constant, g_constant, v);
		
		return (vdt_2 + delta_t*adt/2);
		
	}

}
