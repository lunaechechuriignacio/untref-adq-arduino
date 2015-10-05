
public class DatoMagnetometro {

	float x;
	float y;
	float z;
	float heading;

	public DatoMagnetometro(String datos) {

		this.parseString(datos);
	}

	public void parseString(String string) {

		string = string.replace("mag", "");
		string = string.replace("[", "");
		string = string.replace("]", "");
		string = string.replace(":", "");
		string = string.replace("X", "");
		string = string.replace("Y", "");
		string = string.replace("Z", "");
		string = string.replace("H", "");

		String[] parts = string.split(";");

		this.x = Float.valueOf(parts[0]);
		this.y = Float.valueOf(parts[1]);
		this.z = Float.valueOf(parts[2]);
		this.heading = Float.valueOf(parts[3]);
	}

}
