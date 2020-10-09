package src;

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
	static final double week = 7*24*60*60;

	//Particle: int id, double x, double y, double vx, double vy, double mass, double radius
	//in km/s

	public static void main(String[] args) throws IOException {
		double G_constant = 100;
		double delta_t = Math.pow(10, Integer.valueOf(args[0]));
		List<Particle> particles = new ArrayList<>();
		
		Particle sun = new Particle(1, 0, 0, 0, 0, 1.989e+30, 696340);
		Particle earth = new Particle(2, 1.493188929636662E+08, 1.318936357931255E+07, -3.113279917782445, 2.955205189256462E+01, 5.972e+24, 6371);
		Particle mars = new Particle(3, 2.059448551842169E+08, 4.023977946528339E+07, -3.717406842095575, 2.584914078301731E+01, 6.39e+23, 3389.5);
		Particle ship = new Particle(4, 0, 0, 0, 0, 5e+05, 0);
		particles.add(sun);
		particles.add(earth);
		particles.add(mars);

		double current_time = 0.0;

		SpaceSystem system = new SpaceSystem(particles, delta_t);

		int round = 0;
		int printed = 0;
		double print_time = week;

		while(current_time <= year) {
			if(printed*print_time <= current_time) {
				printOutput(particles, printed, current_time);
				printed ++;
			}

			system.updateParticles();
			round ++;
			current_time = delta_t*round;
		}

		printDOutput(particles, printed, current_time);
	}

	public static void printOutput(List<Particle> particles, int index, double time) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		
		try (Writer writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("output" + index + ".txt"), "utf-8"))) {
			writer.write("3\n");
			writer.write(String.valueOf(time) + "\n");
			for(Particle p : particles) {
				if(p.getId() != 1)
					writer.write(String.valueOf(p.getX()) + " " + String.valueOf(p.getY()) + " " + String.valueOf(p.getRadius()*100) + "\n");
				else
					writer.write(String.valueOf(p.getX()) + " " + String.valueOf(p.getY()) + " " + String.valueOf(p.getRadius()) + "\n");
			}  		
		}
		return;			    	
	}

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

}