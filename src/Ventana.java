import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;

import javax.media.j3d.Canvas3D;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.j3d.utils.universe.SimpleUniverse;

public class Ventana {

	private SerialConnector serial;
	private Timer timer;
	private DatoAcelerometro acelerometro;
	private DatoMagnetometro magnetometro;
	private Brujula3D brujula;
	private Panel panel;

	public static void main(String[] args) {

		Ventana panel = new Ventana();
		panel.start();
	}

	public void start() {

		serial.initialize();
		this.timer = new Timer();
		timer.schedule(new TareaDatos(this), 0, 1);
	}

	public Ventana() {

		serial = new SerialConnector();
		setupUI();
	}

	private void setupUI() {

		JFrame frame = new JFrame();
		frame.setSize(500, 500);

		frame.getContentPane().setLayout(new GridBagLayout());

		panel = new Panel();
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		frame.getContentPane().add(panel, generateConstraints(1, 0));

		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas = new Canvas3D(config);
		brujula = new Brujula3D(canvas);

		frame.getContentPane().add(canvas, generateConstraints(0, 0));

		frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				serial.close();
				System.exit(0);
			}
		});

		frame.setVisible(true);
	}

	private GridBagConstraints generateConstraints(int x, int y) {

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(10, 10, 10, 10);

		return constraints;
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

	public void actualizarLabels() {

		this.panel.actualizarLabels(magnetometro, acelerometro);
	}

}
