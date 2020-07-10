public class Beam {

	//Case variables
	public static final int CASE_TOP = 1;
	public static final int CASE_LEFT = 2;
	public static final int CASE_BOTTOM = 3;
	public static final int CASE_RIGHT = 4;

	double x0, y0; //start-point
	double theta; //angle from start-point
	double x1, y1; //end-point
	double angleOfReflection; //angle of reflection relative to theta

	//guidelines/guide-angles
	private int angleCase;
	private double theta1, theta2, theta3, theta4;
	private double xL, xR, yU, yD;

	public Beam(double x0, double y0, double theta) {
		this.x0 = x0;
		this.y0 = y0;
		this.theta = theta;

		calculateGuideAngles();
		calculateEndPoints();
		calculateAngleOfReflection();
	}

	public int getStartX() { return (int) x0; }
	public int getStartY() { return (int) y0; }
	public int getEndX() { return (int) x1; }
	public int getEndY() { return (int) y1; }
	public double getAngleOfReflection() { return angleOfReflection; }

	public void calculateGuideAngles() {
		xL = x0;
		yU = y0;
		xR = Main.WIDTH - x0;
		yD = Main.HEIGHT - y0;

		theta1 = Math.atan(yU / xR);
		theta2 = (Math.PI / 2) + (Math.PI / 2 - Math.atan(yU / xL));
		theta3 = Math.PI + Math.atan(yD / xL);
		theta4 = (3 * Math.PI / 2) + (Math.PI / 2 - Math.atan(yD / xR));
	}
	
	public void calculateEndPoints() {
		if(theta > theta1 && theta < theta2) {
			angleCase = 1;
			x1 = (int) (x0 + (yU / Math.tan(theta)));
			y1 = 0;
		} else if(theta > theta2 && theta < theta3) {
			angleCase = 2;
			x1 = 0;
			y1 = (int) (y0 + (Math.tan(theta) * xL));
		} else if(theta > theta3 && theta < theta4) {
			angleCase = 3;
			x1 = (int) (x0 - (yD / Math.tan(theta)));
			y1 = Main.HEIGHT;
		} else if(theta > theta4  && theta != 0 || theta < theta1 && theta != 0) {
			angleCase = 4;
			x1 = Main.WIDTH;
			y1 = (int) (y0 - (Math.tan(theta) * xR));
		} else if(theta == 0 || theta == 2 * Math.PI) {
			x1 = Main.WIDTH;
			y1 = Main.HEIGHT / 2;
		} else if(theta == Math.PI/2) {
			x1 = Main.WIDTH / 2;
			y1 = 0;
		} else if(theta == Math.PI) {
			x1 = 0;
			y1 = Main.HEIGHT / 2;
		} else if(theta == 3 * Math.PI / 2) {
			x1 = Main.WIDTH / 2;
			y1 = Main.HEIGHT;
		}
	}

	public void calculateAngleOfReflection() {
		if(angleCase == 1 || angleCase == 3) {
			angleOfReflection = negToPos(-theta);
		} else {
			if(theta > 0 && theta < Math.PI) {
				angleOfReflection = -theta + Math.PI;
			} else {
				angleOfReflection = -theta + 3*Math.PI;
			}
		}
	}

	public void update(double x0, double y0, double theta) {
		this.x0 = x0;
		this.y0 = y0;
		this.theta = theta;

		calculateGuideAngles();
		calculateEndPoints();
		calculateAngleOfReflection();
	}

	private double negToPos(double theta) {
		return (theta + 2 * Math.PI);
	}

	public void printData() {
		System.out.printf("Start Point = (%f, %f)\n", x0, y0);
		System.out.printf("End Point   = (%f, %f)\n", x1, y1);
		System.out.printf("xL=%f xR=%f\n", xL, xR);
		System.out.printf("yU=%f yD=%f\n", yU, yD);
		System.out.println("\nAngleID = radians || degrees");
		System.out.printf("theta  = %f || %f\n", theta, toDegrees(theta));
		System.out.printf("theta1 = %f || %f\n", theta1, toDegrees(theta1));
		System.out.printf("theta2 = %f || %f\n", theta2, toDegrees(theta2));
		System.out.printf("theta3 = %f || %f\n", theta3, toDegrees(theta3));
		System.out.printf("theta4 = %f || %f\n", theta4, toDegrees(theta4));
	}

	private double toDegrees(double radians) {
		return radians*(180/Math.PI);
	}

}