import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Panel {

	private SerialConnector serial;
	private Timer timer;
	private JLabel mag;
	private JLabel acc;

	private DatoAcelerometro acelerometro;
	private DatoMagnetometro magnetometro;

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
		mag = new JLabel();
		acc = new JLabel();
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
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		frame.getContentPane().add(mag, constraints);

		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		frame.getContentPane().add(acc, constraints);

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

				this.magnetometro = new DatoMagnetometro(datos);
				this.mag.setText(this.magnetometro.toString());

			} else if (header.equals("acc")) {

				this.acelerometro = new DatoAcelerometro(datos);
				this.acc.setText(this.acelerometro.toString());
			}
		}
	}

}
