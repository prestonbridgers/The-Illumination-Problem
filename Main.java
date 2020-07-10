import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Main extends JPanel {

	public static final int WIDTH = 400;
	public static final int HEIGHT = 400;

	private int numOfBeams = 25;
	private ArrayList<Beam> beams;


	private double startX = WIDTH/2;
	private double startY = HEIGHT/2;
	private double startTheta = Math.PI/6;
	private double thetaInc = 0.0001;

	public Main() {
		Dimension dim = new Dimension(WIDTH, HEIGHT);
		setSize(dim);
		setPreferredSize(dim);

		beams = new ArrayList<Beam>(numOfBeams);
		initBeams();
	}

	public void initBeams() {
			beams.clear();
			beams.add(new Beam(startX, startY, startTheta));
		for(int i = 1; i < numOfBeams; i++) {
			beams.add(new Beam(beams.get(i-1).getEndX(), beams.get(i-1).getEndY(), beams.get(i-1).getAngleOfReflection()));
		}
	}

	public void setStartPoint(int x, int y) {
		startX = x;
		startY = y;
		initBeams();
	}

	public void setBeamCount(int n) {
		numOfBeams = n;
		initBeams();
	}

	public void go() {
		while(true) {
			updateBeams();

			if(startTheta >= Math.PI*2)
				startTheta = 0;
			startTheta += thetaInc;

			repaint();

			try {
				Thread.sleep(1);
			} catch(Exception e) {}
		}
	}

	private void updateBeams() {
		beams.get(0).update(startX, startY, startTheta);
		for(int i = 1; i < numOfBeams; i++) {
			beams.get(i).update(beams.get(i-1).getEndX(), beams.get(i-1).getEndY(), beams.get(i-1).getAngleOfReflection());
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		//Background
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		//Cartesian plane with origin at (startX, startY)
		g.setColor(Color.YELLOW);
		g.drawLine(0, (int) startY, this.getWidth(), (int) startY);
		g.drawLine((int) startX, 0, (int) startX, this.getHeight());
		//Drawing beams
		g.setColor(Color.GREEN);
		for(int i = 0; i < beams.size(); i++) {
			g.drawLine(beams.get(i).getStartX(), beams.get(i).getStartY(), beams.get(i).getEndX(), beams.get(i).getEndY());
		}

	}
}