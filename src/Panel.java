import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panel extends JPanel {

	private JLabel labelMagnetometro = new JLabel();
	private JLabel labelMagnetometroX = new JLabel();
	private JLabel labelMagnetometroXValue = new JLabel();
	private JLabel labelMagnetometroY = new JLabel();
	private JLabel labelMagnetometroYValue = new JLabel();
	private JLabel labelMagnetometroZ = new JLabel();
	private JLabel labelMagnetometroZValue = new JLabel();
	private JLabel labelAcelerometro = new JLabel();
	private JLabel labelAcelerometroX = new JLabel();
	private JLabel labelAcelerometroXValue = new JLabel();
	private JLabel labelAcelerometroY = new JLabel();
	private JLabel labelAcelerometroYValue = new JLabel();
	private JLabel labelAcelerometroZ = new JLabel();
	private JLabel labelAcelerometroZValue = new JLabel();
	private JLabel labelGiroscopo = new JLabel();
	private JLabel labelGiroscopoX = new JLabel();
	private JLabel labelGiroscopoXValue = new JLabel();
	private JLabel labelGiroscopoY = new JLabel();
	private JLabel labelGiroscopoYValue = new JLabel();
	private JLabel labelGiroscopoZ = new JLabel();
	private JLabel labelGiroscopoZValue = new JLabel();
	private JLabel labelBrujula = new JLabel();
	private JLabel labelBrujulaValue = new JLabel();
	private JLabel labelTemperatura = new JLabel();
	private JLabel labelTemperaturaValue = new JLabel();
	private JButton buttonBase = new JButton();

	public Panel(Ventana ventana) {

		this.setLayout(new GridBagLayout());

		this.setElementsText();
		this.addElements();

		buttonBase.addActionListener(ventana);
		buttonBase.setActionCommand("BASE");
	}

	private void setElementsText() {

		labelMagnetometro.setText("Magnetómetro");
		labelMagnetometroX.setText("X:");
		labelMagnetometroXValue.setText("0");
		labelMagnetometroY.setText("Y:");
		labelMagnetometroYValue.setText("0");
		labelMagnetometroZ.setText("Z:");
		labelMagnetometroZValue.setText("0");
		labelAcelerometro.setText("Acelerómetro");
		labelAcelerometroX.setText("X:");
		labelAcelerometroXValue.setText("0");
		labelAcelerometroY.setText("Y:");
		labelAcelerometroYValue.setText("0");
		labelAcelerometroZ.setText("Z:");
		labelAcelerometroZValue.setText("0");
		labelGiroscopo.setText("Giróscopo");
		labelGiroscopoX.setText("X:");
		labelGiroscopoXValue.setText("0");
		labelGiroscopoY.setText("Y:");
		labelGiroscopoYValue.setText("0");
		labelGiroscopoZ.setText("Z:");
		labelGiroscopoZValue.setText("0");
		labelBrujula.setText("Brujula:");
		labelBrujulaValue.setText("0");
		labelTemperatura.setText("Temperatura:");
		labelTemperaturaValue.setText("0 °C");
		buttonBase.setText("Base");
	}

	private void addElements() {

		this.add(labelMagnetometro, generateConstraints(0, 0));
		this.add(labelMagnetometroX, generateConstraints(0, 1));
		this.add(labelMagnetometroXValue, generateConstraints(1, 1));
		this.add(labelMagnetometroY, generateConstraints(0, 2));
		this.add(labelMagnetometroYValue, generateConstraints(1, 2));
		this.add(labelMagnetometroZ, generateConstraints(0, 3));
		this.add(labelMagnetometroZValue, generateConstraints(1, 3));
		this.add(labelAcelerometro, generateConstraints(0, 4));
		this.add(labelAcelerometroX, generateConstraints(0, 5));
		this.add(labelAcelerometroXValue, generateConstraints(1, 5));
		this.add(labelAcelerometroY, generateConstraints(0, 6));
		this.add(labelAcelerometroYValue, generateConstraints(1, 6));
		this.add(labelAcelerometroZ, generateConstraints(0, 7));
		this.add(labelAcelerometroZValue, generateConstraints(1, 7));
		this.add(labelGiroscopo, generateConstraints(0, 9));
		this.add(labelGiroscopoX, generateConstraints(0, 10));
		this.add(labelGiroscopoXValue, generateConstraints(1, 10));
		this.add(labelGiroscopoY, generateConstraints(0, 11));
		this.add(labelGiroscopoYValue, generateConstraints(1, 11));
		this.add(labelGiroscopoZ, generateConstraints(0, 12));
		this.add(labelGiroscopoZValue, generateConstraints(1, 12));
		this.add(labelBrujula, generateConstraints(0, 13));
		this.add(labelBrujulaValue, generateConstraints(1, 13));
		this.add(labelTemperatura, generateConstraints(0, 14));
		this.add(labelTemperaturaValue, generateConstraints(1, 14));
		this.add(buttonBase, generateConstraints(0, 15));
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

	public void actualizarLabels(DatoMagnetometro magnetometro, DatoAcelerometro acelerometro) {

		labelMagnetometroXValue.setText(String.valueOf(magnetometro.x));
		labelMagnetometroYValue.setText(String.valueOf(magnetometro.y));
		labelMagnetometroZValue.setText(String.valueOf(magnetometro.z));
		labelAcelerometroXValue.setText(String.valueOf(acelerometro.x));
		labelAcelerometroYValue.setText(String.valueOf(acelerometro.y));
		labelAcelerometroZValue.setText(String.valueOf(acelerometro.z));
		labelGiroscopoXValue.setText(String.valueOf(acelerometro.gx));
		labelGiroscopoYValue.setText(String.valueOf(acelerometro.gy));
		labelGiroscopoZValue.setText(String.valueOf(acelerometro.gz));
		labelBrujulaValue.setText(String.valueOf(magnetometro.heading));
		labelTemperaturaValue.setText(String.valueOf(acelerometro.temperature));
	}

}
