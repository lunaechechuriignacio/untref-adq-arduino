import java.util.TimerTask;

public class TareaDatos extends TimerTask {

	private Panel panel;

	public TareaDatos(Panel panel) {

		this.panel = panel;
	}

	@Override
	public void run() {

		panel.recolectarDatos();
	}

}
