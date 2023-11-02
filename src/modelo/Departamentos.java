package modelo;

public class Departamentos {

	Integer departamentoNum;
	String dnombre;
	String localidad;
	
	
	//Constructor Departamentos
	public Departamentos(Integer departamentoNum, String dnombre, String localidad) {
		super();
		this.departamentoNum = departamentoNum;
		this.dnombre = dnombre;
		this.localidad = localidad;
	}


	//toString Departamentos
	@Override
	public String toString() {
		return "Departamentos [departamentoNum=" + departamentoNum + ", dnombre=" + dnombre + ", localidad=" + localidad + "]";
	}

	
	//Getter and setter Departamentos

	public Integer getDepartamentoNum() {
		return departamentoNum;
	}


	public void setDepartamentoNum(int departamentoNum) {
		this.departamentoNum = departamentoNum;
	}


	public String getDnombre() {
		return dnombre;
	}


	public void setDnombre(String dnombre) {
		this.dnombre = dnombre;
	}


	public String getLocalidad() {
		return localidad;
	}


	public void setLoc(String localidad) {
		this.localidad = localidad;
	}


	public boolean compareToNumero(Departamentos departamentos) {
		return this.getDepartamentoNum().equals(departamentos.getDepartamentoNum());
	}
	
	public boolean compareToNombre(Departamentos departamentos) {
		return this.dnombre.equalsIgnoreCase(departamentos.getDnombre());
	}
	
	public boolean compareToLocalidad(Departamentos departamentos) {
		return this.localidad.equalsIgnoreCase(departamentos.getLocalidad());
	}
	
}
