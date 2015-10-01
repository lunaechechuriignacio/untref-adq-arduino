
public class DatoMagnetometro {

	float x;
	float y;
	float z;

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

		String[] parts = string.split(";");

		this.x = Float.valueOf(parts[0]);
		this.y = Float.valueOf(parts[1]);
		this.z = Float.valueOf(parts[2]);
	}

	@Override
	public String toString() {
		return "DatoMagnetometro [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

}
