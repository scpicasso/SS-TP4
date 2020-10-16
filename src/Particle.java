
public class Particle implements Comparable<Particle>{
	
    private int id;
    private double x;
    private double y;
    private double radius;
    private double mass;
    private double vx;
    private double vy;

    public Particle(int id, double x, double y, double vx, double vy, double mass, double radius) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.mass = mass;
        this.vx = vx;
        this.vy = vy;
    }   

    public int getId() {
        return id;
    }

    public double getVelocityX() {
        return vx;
    }

    public double getVelocityY() {
        return vy;
    }
    
    public double getRadius() {
        return radius;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getMass() {
        return mass;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public void setVelocityX(double vx){
        this.vx = vx;
    }

    public void setVelocityY(double vy){
        this.vy = vy;
    }

    public double getDistanceWithRadius(Particle p) {
        return Math.sqrt(Math.pow(x - p.getX(),2) + Math.pow(y - p.getY(),2)) - radius - p.getRadius();
    }

    public double getDistance(Particle p) {
        return Math.sqrt(Math.pow(x - p.getX(),2) + Math.pow(y - p.getY(),2));
    }

    public double getElasticAcceleration(double position, double k_constant, double g_constant, double velocity) {
        return -(k_constant*position + g_constant*velocity)/mass;
    }
    
    public double getVelocity() {
        return Math.sqrt(vy*vy + vx*vx);
    }
    
    public double getEnergy() {
    	
        return (mass/2)*Math.pow(getVelocity()*1000, 2); 
    }

	@Override
	public int compareTo(Particle p) {
		return this.id - p.id;
	}


}
