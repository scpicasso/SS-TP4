
import java.util.*;

public class GearSpaceAlgorithm {

	private double delta_t;
	private double G_constant = 6.674*Math.pow(10,-20);
	private List<Double[][]> values = new ArrayList<Double[][]>();
	private List<Particle> ps;
	private double coefficients[] = {3/16, 251/360, 1, 11/18, 1/6, 1/60};

	//first derivative = position
	//second derivative = velocity
	//third derivative = acceleration
	public GearSpaceAlgorithm(List<Particle> ps, double delta_t){
		this.delta_t = delta_t;
		this.ps = ps;
		int j = 0;
		for(Particle p:ps) {
			values.add(new Double[2][6]);
			values.get(j)[0][0] = p.getX();
			values.get(j)[0][1] = p.getVelocityX();
			values.get(j)[0][2] = getAccelerationX(j, values);
			for(int i = 3; i < 6; i++) {
				values.get(j)[0][i] = 0.0;
			}
			values.get(j)[1][0] = p.getY();
			values.get(j)[1][1] = p.getVelocityY();
			values.get(j)[1][2] = getAccelerationY(j, values);
			for(int i = 3; i < 6; i++) {
				values.get(j)[1][i] = 0.0;
			}
			j++;
		}

	}

	public void updateParticles() {
		List<Double[][]> new_values = makePredictions();
		
		for(int j = 0; j < ps.size(); j++) {
			for(int i = 0; i<2; i++) {
				double acceleration;

				if(i == 0) 
					acceleration = getAccelerationX(j, new_values);
				else
					acceleration = getAccelerationY(j, new_values);
				
				double delta_R2 = (acceleration - new_values.get(j)[i][2])*Math.pow(delta_t, 2)/2;

				for(int k=0; k<6; k++) {
					values.get(j)[i][k] = new_values.get(j)[i][k] + (delta_R2*coefficients[k]*factorial(k))/Math.pow(delta_t,k);
				}
			}

			ps.get(j).setX(values.get(j)[0][0]);
			ps.get(j).setY(values.get(j)[1][0]);
			ps.get(j).setVelocityX(values.get(j)[0][1]);
			ps.get(j).setVelocityY(values.get(j)[1][1]);
		}
	}


	public double factorial(int num) {
		int base = 1;
		for(int j = 2; j<=num; j++) {
			base *= j;
		}
		return base;
	}

	public double getAccelerationX(int id, List<Double[][]> matrix) {
		double total = 0;

		for(int j = 0; j<ps.size(); j++) {
			if(j != id) {
				double d_x = matrix.get(id)[0][0] - matrix.get(j)[0][0];
				double d_y = matrix.get(id)[1][0] - matrix.get(j)[1][0];

				double dist = Math.pow(d_x,2) + Math.pow(d_y,2);

				total += (ps.get(j).getMass()*d_x) / Math.pow(dist, 1.5);
			}
		}

		return total*G_constant;

	}

	public double getAccelerationY(int id, List<Double[][]> matrix) {
		double total = 0;

		for(int j = 0; j<ps.size(); j++) {
			if(j != id) {
				double d_x = matrix.get(id)[0][0] - matrix.get(j)[0][0];
				double d_y = matrix.get(id)[1][0] - matrix.get(j)[1][0];

				double dist = Math.pow(d_x,2) + Math.pow(d_y,2);

				total += (ps.get(j).getMass()*d_y) / Math.pow(dist, 1.5);
			}
		}

		return total*G_constant;

	}

	public List<Double[][]> makePredictions() {
		List<Double[][]> new_values = new ArrayList<Double[][]>();

		for(int j = 0; j<ps.size(); j++) {
			new_values.add(new Double[2][6]);

			for(int i = 0; i<2; i++) {
				new_values.get(j)[i][0] = values.get(j)[i][0] +
				values.get(j)[i][1]*delta_t +
				values.get(j)[i][2]*Math.pow(delta_t, 2)/2 +
				values.get(j)[i][3]*Math.pow(delta_t, 3)/6 +
				values.get(j)[i][4]*Math.pow(delta_t, 4)/24 +
				values.get(j)[i][5]*Math.pow(delta_t, 5)/120;

				new_values.get(j)[i][1] = values.get(j)[i][1] +
				values.get(j)[i][2]*delta_t +
				values.get(j)[i][3]*Math.pow(delta_t, 2)/2 +
				values.get(j)[i][4]*Math.pow(delta_t, 3)/6 +
				values.get(j)[i][5]*Math.pow(delta_t, 4)/24;

				new_values.get(j)[i][2] = values.get(j)[i][2] +
				values.get(j)[i][3]*delta_t +
				values.get(j)[i][4]*Math.pow(delta_t, 2)/2 +
				values.get(j)[i][5]*Math.pow(delta_t, 3)/6;

				new_values.get(j)[i][3] = values.get(j)[i][3] +
				values.get(j)[i][4]*delta_t +
				values.get(j)[i][5]*Math.pow(delta_t, 2)/2 ;

				new_values.get(j)[i][4] = values.get(j)[i][4] +
				values.get(j)[i][5]*delta_t;

				new_values.get(j)[i][5] = values.get(j)[i][5];
			}
		}
		return new_values;
	}

}

