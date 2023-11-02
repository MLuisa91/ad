package utils;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

import modelo.Departamentos;

public class DepartamentosMapper {
	
	@XStreamImplicit
	List<Departamentos> departamento;

	public List<Departamentos> getDepartamento() {
		return departamento;
	}

}
