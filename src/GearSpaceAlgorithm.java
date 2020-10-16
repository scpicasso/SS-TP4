
import java.util.*;

public class GearSpaceAlgorithm {

    private static final int X = 0;
    private static final int Y = 1;
    private double coefficients[] = {3.0/20.0, 251.0/360.0, 1.0, 11.0/18.0, 1.0/6.0, 1.0/60.0};
    private static final double G_constant = 6.674e-20;


    private double[][][] values;

    private List<Particle> particles;
    private double delta_t;
    

    public GearSpaceAlgorithm(List<Particle> particles, double delta_t, GearSpaceAlgorithm old_gs) {
        this.particles = particles;
        this.delta_t = delta_t;
        this.values = new double[particles.size()][2][6];

        if(old_gs == null) {
            for (Particle p : particles) {
                int index = p.getId() - 1;

                values[index][X][0] = p.getX();
                values[index][Y][0] = p.getY();
                values[index][X][1] = p.getVelocityX();
                values[index][Y][1] = p.getVelocityY();

                for(int i = 3; i < 6; i++) {
                    values[index][X][i] = 0;
                    values[index][Y][i] = 0;

                }

            }

            for (Particle p : particles) {
                int index = p.getId() - 1;
                values[index][X][2] = getAccelerationX(values, index);
                values[index][Y][2] = getAccelerationY(values, index);
            }
        }

        else {
            double [][][] old_values = old_gs.getValues();
            for(Particle p : particles) {
                int index = p.getId() - 1;
                if(index != particles.size() - 1) {
                    for(int i = 0; i<6; i++) {
                        values[index][X][i] = old_values[index][X][i];
                        values[index][Y][i] = old_values[index][Y][i];
                    }

                }
                else {
                    values[index][X][0] = p.getX();
                    values[index][Y][0] = p.getY();
                    values[index][X][1] = p.getVelocityX();
                    values[index][Y][1] = p.getVelocityY();
                    values[index][X][2] = getAccelerationX(values, index);
                    values[index][Y][2] = getAccelerationY(values, index);

                    for(int i = 3; i<6; i++) {
                        values[index][X][i] = 0;
                        values[index][Y][i] = 0;
                    }
                }
            }
        }
    }

    public double[][][] getValues() {
        return values;
    }

    public void updateParticles() {
        double[][][] new_values = makePredictions();

        for (Particle p : particles) {
            int index = p.getId() - 1;

            for (int i = 0; i<=Y; i++) {
                
                double a;

                if(i == X) {
                    a = getAccelerationX(values, index);
                }

                else {
                    a = getAccelerationY(values, index);
                }
                
                double deltaR2 = (a - new_values[index][i][2]) * Math.pow(delta_t, 2)*0.5;

                for(int j = 0; j < 6; j++) {
                    values[index][i][j] = new_values[index][i][j] + deltaR2*coefficients[j]*factorial(j)/Math.pow(delta_t, j);
                }
            }

            p.setX(values[index][X][0]);
            p.setY(values[index][Y][0]);
            
            p.setVelocityX(values[index][X][1]);
            p.setVelocityY(values[index][Y][1]);
        }
    }

    public double getAccelerationX(double[][][] matrices, int num) {
        double a = 0;

        for (Particle p : particles) {
            int index = p.getId() -1;

            if (index != num) {
                double d_x = matrices[index][X][0] - matrices[num][X][0];
                double d_y = matrices[index][Y][0] - matrices[num][Y][0];

                double dist = Math.sqrt(d_x*d_x + d_y*d_y);

                a += p.getMass() * d_x / Math.pow(dist, 3);

            }
        }

        return G_constant * a;
    }

    public double getAccelerationY(double[][][] matrices, int num) {
        double a = 0;

        for (Particle p : particles) {
            int index = p.getId() -1;

            if (index != num) {
                double d_x = matrices[index][X][0] - matrices[num][X][0];
                double d_y = matrices[index][Y][0] - matrices[num][Y][0];

                double dist = Math.sqrt(d_x*d_x + d_y*d_y);

                a += p.getMass() * d_y / Math.pow(dist, 3);
            }
        }

        return G_constant * a;
    }

    public double factorial(int num) {
        int base = 1;
        for(int i = 2; i <= num; i++) {
            base = base * i;
        }
        return base;
    }

    public double[][][] makePredictions() {
        double[][][] new_values = new double[particles.size()][2][6];

        for (Particle p : particles) {
            int index = p.getId() - 1;

            for (int j = 0; j<=Y; j++) {
                new_values[index][j][0] = 
                    values[index][j][0] +
                    values[index][j][1] * delta_t +
                    values[index][j][2] * Math.pow(delta_t, 2)/(2) +
                    values[index][j][3] * Math.pow(delta_t, 3)/factorial(3) +
                    values[index][j][4] * Math.pow(delta_t, 4)/factorial(4) +
                    values[index][j][5] * Math.pow(delta_t, 5)/factorial(5);

                new_values[index][j][1] = 
                    values[index][j][1] +
                    values[index][j][2] * delta_t +
                    values[index][j][3] * Math.pow(delta_t, 2)/(2) +
                    values[index][j][4] * Math.pow(delta_t, 3)/factorial(3) +
                    values[index][j][5] * Math.pow(delta_t, 4)/factorial(4);

                new_values[index][j][2] = 
                    values[index][j][2] +
                    values[index][j][3] * delta_t +
                    values[index][j][4] * Math.pow(delta_t, 2)/(2) +
                    values[index][j][5] * Math.pow(delta_t, 3)/factorial(3);

                new_values[index][j][3] = 
                    values[index][j][3] +
                    values[index][j][4] * delta_t +
                    values[index][j][5] * Math.pow(delta_t, 2)/(2);

                new_values[index][j][4] = 
                    values[index][j][4] +
                    values[index][j][5] * delta_t;

                new_values[index][j][5] = 
                    values[index][j][5];
            }
        }

        return new_values;
    }

}
