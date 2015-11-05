
public class DatoAcelerometro {

	float x;
	float y;
	float z;
	float gx;
	float gy;
	float gz;
	float temperature;
	
	public DatoAcelerometro() {
		
	}

	public DatoAcelerometro(String datos) {

		this.parseString(datos);
	}

	public void parseString(String string) {

		string = string.replace("acc", "");
		string = string.replace("[", "");
		string = string.replace("]", "");
		string = string.replace(":", "");
		string = string.replace("X", "");
		string = string.replace("Y", "");
		string = string.replace("Z", "");
		string = string.replace("Gy", "");
		string = string.replace("T", "");

		String[] parts = string.split(";");

		this.x = Float.valueOf(parts[0]);
		this.y = Float.valueOf(parts[1]);
		this.z = Float.valueOf(parts[2]);
		this.gx = Float.valueOf(parts[3]);
		this.gy = Float.valueOf(parts[4]);
		this.gz = Float.valueOf(parts[5]);
		this.temperature = Float.valueOf(parts[6]);
	}

}
