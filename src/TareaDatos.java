import java.util.TimerTask;

public class TareaDatos extends TimerTask {

	private Ventana ventana;

	public TareaDatos(Ventana panel) {

		this.ventana = panel;
	}

	@Override
	public void run() {

		ventana.recolectarDatos();
		ventana.actualizarLabels();
	}

}
