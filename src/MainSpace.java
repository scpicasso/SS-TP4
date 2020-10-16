
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;
import java.util.ArrayList;

public class MainSpace  {

	static final double year = 365*24*60*60;
	static final double minute = 60;
	static final double hour = minute*60;
	static final double day = 24*hour;
	static final double week = 7*day;
	static final double month = 30*day;
	
	//Particle: int id, double x, double y, double vx, double vy, double mass, double radius
	//in km/s

	public static void main(String[] args) throws IOException {
		double delta_t = Math.pow(10, Integer.valueOf(args[0]));
		List<Particle> particles = new ArrayList<>();
		boolean blast_off = false;
		double ship_v = Double.valueOf(args[1]);
		ship_v = 8.0;
		
		Particle sun = new Particle(1, 0, 0, 0, 0, 1.989e+30, 696340);
		Particle earth = new Particle(2, 1.493188929636662E+08, 1.318936357931255E+07, -3.113279917782445, 2.955205189256462E+01, 5.972e+24, 6371);
		Particle mars = new Particle(3, 2.059448551842169E+08, 4.023977946528339E+07, -3.717406842095575, 2.584914078301731E+01, 6.39e+23, 3389.5);
		particles.add(sun);
		particles.add(earth);
		particles.add(mars);

		double current_time = 0.0;

		SpaceSystem system = new SpaceSystem(particles, delta_t);

		int round = 0;
		int printed = 0;
		double print_time = day;
		double max_sim_time = year;
		double min_distance = Integer.MAX_VALUE;
		double min_time = Integer.MAX_VALUE;
		double last_distance = 0.0;
		double ship_velocity = 0.0;
		
		try (Writer writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("journeyV=8.txt"), "utf-8"))) {
			
			for (int departure_time = 0; departure_time < year*4; departure_time += day) {
				
				particles.clear();
				sun = new Particle(1, 0, 0, 0, 0, 1.989e+30, 696340);
				earth = new Particle(2, 1.493188929636662E+08, 1.318936357931255E+07, -3.113279917782445, 2.955205189256462E+01, 5.972e+24, 6371);
				mars = new Particle(3, 2.059448551842169E+08, 4.023977946528339E+07, -3.717406842095575, 2.584914078301731E+01, 6.39e+23, 3389.5);
				particles.add(sun);
				particles.add(earth);
				particles.add(mars);

				current_time = 0.0;
				round = 0;
								
				min_distance = Integer.MAX_VALUE;
				min_time = Integer.MAX_VALUE;
				blast_off = false;
				
				system = new SpaceSystem(particles, delta_t);
			
				while(current_time <= departure_time + year/2) {

					if(current_time >= departure_time && !blast_off) {
						system.addSpaceship(ship_v);
						blast_off = true;
					}
		
					system.updateParticles();
					
					if (blast_off) {
					
						if (min_distance >= system.getShipToMarsDistance()) {
							min_distance = system.getShipToMarsDistance();
							min_time = current_time - departure_time;						
						}
						if (system.getShipToMarsDistance() <= 0 ) {
							ship_velocity = system.getShipVelocity();
						}

					}
					
					round ++;
					current_time = delta_t*round;
				}
				System.out.println(departure_time + " " + min_time + " " + min_distance + " " + ship_velocity);
				
				printTimeToArribeOutput(particles, min_distance, departure_time, writer); 
			}
		}		
		
//		double last = Integer.MAX_VALUE;
//		
//		try (Writer writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("velTime.txt"), "utf-8"))) {
//		
//			while(current_time <= year + 704*day) {
//				
//				if(printed*print_time <= current_time) {
//					printOutput(particles, printed, current_time);
//					printed ++;
//				}
//				
//				if(current_time >= 704*day + 16*hour + 41*minute && !blast_off) {
//					system.addSpaceship(ship_v);
//					blast_off = true;
//				}
//				
//				system.updateParticles();
//						
//				if (blast_off) {
//					System.out.println(current_time - 704*day + 16*hour + 41*minute + " " + system.getShipVelocity());
//					
//					printTimeToArribeOutput(particles, system.getShipVelocity(), current_time - 704*day + 16*hour + 41*minute, writer); 
//		
//					
//					if (system.getShipToMarsDistance() > last) {
//						break;
//					}
//					
//					last = system.getShipToMarsDistance();
////					break;
//				}
//				
//
//				
//				round ++;
//				current_time = delta_t*round;
//				
//				
//			}
//		
//		}

		
		
//		for (int k = 4; k > -2; k--) {			
//			delta_t = Math.pow(10, k);
//			
//			particles.clear();
//			sun = new Particle(1, 0, 0, 0, 0, 1.989e+30, 696340);
//			earth = new Particle(2, 1.493188929636662E+08, 1.318936357931255E+07, -3.113279917782445, 2.955205189256462E+01, 5.972e+24, 6371);
//			mars = new Particle(3, 2.059448551842169E+08, 4.023977946528339E+07, -3.717406842095575, 2.584914078301731E+01, 6.39e+23, 3389.5);
//			particles.add(sun);
//			particles.add(earth);
//			particles.add(mars);
//
//			current_time = 0.0;
//			round = 0;
//			
//			system = new SpaceSystem(particles, delta_t);
//			
//			double totalEnergy = system.getSystemEnergy();
//			
//			double d_earth = sun.getDistanceWithRadius(earth);
//			double error = 0.0;
//			
//			while(current_time <= week) {					
//				system.updateParticles();				
//				round ++;
//				current_time = delta_t*round;
//			}	
//			
//			error = Math.abs(totalEnergy - system.getSystemEnergy());
//			
////			error = Math.abs(d_earth - sun.getDistanceWithRadius(earth));
//			
//			System.out.println(k + " " + error);
//		}
		
	}

	public static void printOutput(List<Particle> particles, int index, double time) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		
		try (Writer writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("output" + index + ".txt"), "utf-8"))) {
			writer.write(String.valueOf(particles.size()) + "\n");
			writer.write(String.valueOf(time) + "\n");
			for(Particle p : particles) {
				if(p.getId() != 1)
					writer.write(String.valueOf(p.getX()) + " " + String.valueOf(p.getY()) + " " + String.valueOf(100000000) + "\n");
				else
					writer.write(String.valueOf(p.getX()) + " " + String.valueOf(p.getY()) + " " + String.valueOf(100000000) + "\n");
			}  		
		}
		return;			    	
	}

//	public static void printDOutput(List<Particle> particles, int index, double time) throws UnsupportedEncodingException, FileNotFoundException, IOException {
//		
//		try (Writer writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("a" + index + ".txt"), "utf-8"))) {
//			writer.write("3\n");
//			writer.write(String.valueOf(time) + "\n");
//			Particle sun = particles.get(0);
//			double d_earth = sun.getDistanceWithRadius(particles.get(1));
//			double d_mars = sun.getDistanceWithRadius(particles.get(2));
//			writer.write(String.valueOf(d_earth) + " " + String.valueOf(d_mars) + "\n");  		
//		}
//		return;			    	
//	}
	
	public static void printDOutput(List<Particle> particles, int index, double time) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		
		try (Writer writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("a" + index + ".txt"), "utf-8"))) {
			writer.write("3\n");
			writer.write(String.valueOf(time) + "\n");
			Particle sun = particles.get(0);
			double d_earth = sun.getDistanceWithRadius(particles.get(1));
			double d_mars = sun.getDistanceWithRadius(particles.get(2));
			writer.write(String.valueOf(d_earth) + " " + String.valueOf(d_mars) + "\n");  		
		}
		return;			    	
	}
	
	public static void printTimeToArribeOutput(List<Particle> particles, double journey_time, double departure_time, Writer writer) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		writer.write(String.valueOf(departure_time) + " " + String.valueOf(journey_time) + "\n");
		return;			    	
	}

}