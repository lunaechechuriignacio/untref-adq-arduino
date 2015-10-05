import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;

import javax.media.j3d.Canvas3D;
import javax.swing.JFrame;

import com.sun.j3d.utils.universe.SimpleUniverse;

public class Panel {

	private SerialConnector serial;
	private Timer timer;

	private DatoAcelerometro acelerometro;
	private DatoMagnetometro magnetometro;

	private Brujula3D brujula;

	public static void main(String[] args) {

		Panel panel = new Panel();
		panel.start();
	}

	public void start() {

		serial.initialize();
		this.timer = new Timer();
		timer.schedule(new TareaDatos(this), 0, 1);
	}

	public Panel() {

		serial = new SerialConnector();
		setupUI();
	}

	private void setupUI() {

		JFrame frame = new JFrame();
		frame.setSize(500, 500);

		frame.getContentPane().setLayout(new GridBagLayout());

		GridBagConstraints constraints;

		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 1;
		// constraints.gridwidth = 1;
		// constraints.gridheight = 1;
		// frame.getContentPane().add(mag, constraints);

		// constraints = new GridBagConstraints();
		// constraints.fill = GridBagConstraints.BOTH;
		// constraints.gridx = 0;
		// constraints.gridy = 1;
		// constraints.gridwidth = 1;
		// constraints.gridheight = 1;
		// frame.getContentPane().add(acc, constraints);

		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas = new Canvas3D(config);

		brujula = new Brujula3D(canvas);

		frame.getContentPane().add(canvas, constraints);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				serial.close();
				System.exit(0);
			}
		});

		frame.setVisible(true);
	}

	public void recolectarDatos() {

		String datos = serial.getLine();

		if (datos != null && datos.length() > 3) {

			String header = datos.substring(0, 3);

			if (header.equals("mag")) {

				System.out.println(datos);
				this.magnetometro = new DatoMagnetometro(datos);
				brujula.rotate(this.magnetometro);

			} else if (header.equals("acc")) {

				this.acelerometro = new DatoAcelerometro(datos);
			}
		}

	}

}
