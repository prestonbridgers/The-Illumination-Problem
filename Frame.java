import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frame extends JFrame {
	
	private Main beamPanel;
	private JPanel pnlStartPoint, pnlBeamCount;
	private JButton btnStartPoint, btnBeamCount;
	private JTextField tfStartPointX, tfStartPointY, tfBeamCount;

	private EventHandler eventHandler;

	public Frame() {
		super("The Illumination Problem");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());

		eventHandler = new EventHandler();

		beamPanel = new Main();

		pnlStartPoint = new JPanel();
		tfStartPointX = new JTextField("X", 5);
		tfStartPointY = new JTextField("Y", 5);
		btnStartPoint = new JButton("Set Start Point");
		btnStartPoint.addActionListener(eventHandler);
		pnlStartPoint.add(tfStartPointX);
		pnlStartPoint.add(tfStartPointY);
		pnlStartPoint.add(btnStartPoint);

		pnlBeamCount = new JPanel();
		tfBeamCount = new JTextField("Beam Count", 10);
		btnBeamCount = new JButton("Set Beam Count");
		btnBeamCount.addActionListener(eventHandler);
		pnlBeamCount.add(tfBeamCount);
		pnlBeamCount.add(btnBeamCount);

		add(pnlStartPoint, BorderLayout.NORTH);
		add(beamPanel, BorderLayout.CENTER);
		add(pnlBeamCount, BorderLayout.SOUTH);
		pack();
		setVisible(true);
		beamPanel.go();
	}

	private class EventHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				switch( e.getActionCommand() ) {
					case "Set Start Point":
						beamPanel.setStartPoint(Integer.parseInt(tfStartPointX.getText()), Integer.parseInt(tfStartPointY.getText()));
					break;
					case "Set Beam Count":
						beamPanel.setBeamCount(Integer.parseInt(tfBeamCount.getText()));
					break;
				}
			} catch (Exception ex) { System.out.println("Invalid Input!"); }
		}
	}

	public static void main(String[] args) {
		new Frame();
	}
}