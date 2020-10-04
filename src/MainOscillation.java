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

public class MainOscillation  {

	public static void main(String[] args) throws IOException {
		String algorithm = args[1];
		double g_constant = 100;
		double k_constant = Math.pow(10,4);
		double tf = 5;
		double amplitude = 1;
		double mass = 70;
		double vi = -(amplitude*g_constant)/(2*mass);
		double delta_t = Math.pow(10, -1*Integer.valueOf(args[0]));
		List<Particle> particles = new ArrayList<>();
		Particle ball = new Particle(1, amplitude, 0, vi, 0, mass, 0);
		double current_time = 0.0;
		particles.add(ball);

		ArrayList<Double>[] results = new ArrayList[2];
		results[0] = new ArrayList<Double>(); 
		results[1] = new ArrayList<Double>();

		SimSystem system = new SimSystem(particles, delta_t, k_constant, g_constant);
		system.addAlgorithm(algorithm);

		int index = 0;
		int round = 0;
		double print_time = 0.0025;

		while(current_time < tf) {
			if(current_time >= print_time*index) {
				results[1].add(particles.get(0).getX()); 
				results[0].add(print_time*index);
				index ++;
			}

			system.updateParticles();
			round ++;
			current_time = delta_t*round;
		}

		printOutput(results, index, args[0], args[1]);
	}

	public static void printOutput(ArrayList<Double>[]  results, int index, String delta, String al) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		
		try (Writer writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("output" + al + delta + ".txt"), "utf-8"))) {
			for(int i = 0; i<index;i++) {
				writer.write(String.valueOf(results[0].get(i)) + " " + String.valueOf(results[1].get(i)) + "\n");
			}  		
		}
		return;			    	
	}

}