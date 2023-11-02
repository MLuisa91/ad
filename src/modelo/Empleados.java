package modelo;

import java.util.Date;

public class Empleados {

	// Variables empleados
	private Integer numero;
	private String apellido;
	private Integer jefe;
	private Double salario;
	private String oficio;
	private Date fechaAlta;
	private Double comision;
	private Departamentos departamento;

	// Constructor Empleados
	public Empleados(Integer emp_no, String apellido, Integer dir, Double salario, String oficio, Date fecha_alt,
			Double comision, Departamentos departamento) {
		super();
		this.numero = emp_no;
		this.apellido = apellido;
		this.jefe = dir;
		this.salario = salario;
		this.oficio = oficio;
		this.fechaAlta = fecha_alt;
		this.comision = comision;
		this.departamento = departamento;

	}

	public Empleados() {
		// TODO Auto-generated constructor stub
	}

	// ToString Empleados
	@Override
	public String toString() {
		return "empleados [emp_no=" + numero + ", apellido=" + apellido + ", dir=" + jefe + ", salario=" + salario
				+ ", oficio=" + oficio + ", fecha_alt=" + fechaAlta + ", comision=" + comision + ", departamento="
				+ departamento + "]";
	}


	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getJefe() {
		return jefe;
	}

	public void setJefe(Integer jefe) {
		this.jefe = jefe;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getOficio() {
		return oficio;
	}

	public void setOficio(String oficio) {
		this.oficio = oficio;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Double getComision() {
		return comision;
	}

	public void setComision(Double comision) {
		this.comision = comision;
	}

	public Departamentos getDepartamento() {
		return departamento;
	}

	public void setNumDepartamento(Departamentos departamento) {
		this.departamento = departamento;
	}

	public boolean compareToNo(Empleados empleados) {
		return this.getNumero().equals(empleados.getNumero());
	}

	public boolean compareToApellido(Empleados empleados) {
		return this.getApellido().equalsIgnoreCase(empleados.getApellido());
	}

	public boolean compareToSalario(Empleados empleados) {
		return this.salario.equals(empleados.getSalario());
	}

	public boolean compareToOficio(Empleados empleados) {
		return this.oficio.equalsIgnoreCase(empleados.getOficio());
	}

	public boolean compareToComision(Empleados empleados) {
		return this.comision.equals(empleados.getComision());
	}

	public boolean compareToDepartamento(Empleados empleados) {
		return this.departamento.equals(empleados.getDepartamento());
	}

	public boolean compareToJefe(Empleados empleados) {
		return this.jefe.equals(empleados.getJefe());
	}
	
	public boolean compareToFechaAlta(Empleados empleados) {
		return this.fechaAlta.equals(empleados.getFechaAlta());
	}

}
