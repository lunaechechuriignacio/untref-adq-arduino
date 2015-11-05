import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;

import javax.media.j3d.Canvas3D;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.universe.SimpleUniverse;

public class Ventana implements ActionListener {

	private SerialConnector serial;
	private Timer timer;
	private DatoAcelerometro acelerometro;
	private DatoAcelerometro acelerometroBase;
	private DatoMagnetometro magnetometro;
	private Brujula3D brujula;
	private Tracking3D tracking;
	private Panel panel;
	private Vector3f posicion;

	public static void main(String[] args) {

		Ventana panel = new Ventana();
		panel.start();
	}

	public void start() {

		serial.initialize();
		this.timer = new Timer();
		timer.schedule(new TareaDatos(this), 0, 10);
	}

	public Ventana() {

		serial = new SerialConnector();
		setupUI();
		this.acelerometro = new DatoAcelerometro();
		this.magnetometro = new DatoMagnetometro();
		this.posicion = new Vector3f();
	}

	private void setupUI() {

		JFrame frame = new JFrame();
		frame.setSize(500, 500);

		frame.getContentPane().setLayout(new GridBagLayout());

		panel = new Panel(this);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		frame.getContentPane().add(panel, generateConstraints(1, 0));

		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas = new Canvas3D(config);
		// brujula = new Brujula3D(canvas);
		tracking = new Tracking3D(canvas);

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

				this.magnetometro = new DatoMagnetometro(datos);
				// brujula.rotate(this.magnetometro);

			} else if (header.equals("acc")) {

				this.acelerometro = new DatoAcelerometro(datos);
			}
		}
	}

	public void calcularTracking() {

		if (acelerometroBase != null) {
			float diferencia;

			diferencia = acelerometro.x - acelerometroBase.x;
			if (Math.abs(diferencia) > 200) {
				posicion.y = posicion.y + diferencia / 500000;
				tracking.move(posicion);
			}

			diferencia = acelerometro.y - acelerometroBase.y;
			if (Math.abs(diferencia) > 200) {
				posicion.x = posicion.x + diferencia / 500000;
				tracking.move(posicion);
			}
//
//			diferencia = acelerometro.z - acelerometroBase.z;
//			if (Math.abs(diferencia) > 2000) {
//				posicion.z = posicion.z + diferencia / 1000000;
//				tracking.move(posicion);
//			}
		}
	}

	public void actualizarLabels() {

		this.panel.actualizarLabels(magnetometro, acelerometro);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "BASE") {
			this.acelerometroBase = acelerometro;
		}
	}

}
