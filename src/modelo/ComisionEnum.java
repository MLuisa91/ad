package modelo;

public enum ComisionEnum {
	BAJA(1), MEDIA(1.5), ALTA(2);
	
	private double id;

	ComisionEnum(double i) {
		this.id = i;
	}
	
	public static ComisionEnum getById(double i) {
		for (ComisionEnum acces : values()) {
			if (acces.id == i)
				return acces;
		}
		return null;
	}

}
