package controlador;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import modelo.Empleados;
import utils.Utiles;

public class CRUD_Empleado_Xml {

	/**
	 * Genera el documento xml de empleados
	 * 
	 * @param empleados
	 */
	public void saveEmpleados(List<Empleados> empleados) {
		Document doc = null;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;

		try {
			builder = factory.newDocumentBuilder();

			DOMImplementation implementacion = builder.getDOMImplementation();
			doc = implementacion.createDocument(null, "empleados", null);
			Source source = new DOMSource(doc);
			Result result = new StreamResult(new java.io.File(Constantes.FILE_EMPLEADOS));

			for (Empleados empleado : empleados) {
				generarXmlEmpleado(empleado, doc);
			}

			guardarCambiosXML(source, result);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Genera los diferentes nodos del XML y los carga de contenido
	 * 
	 * @param empleados
	 * @param doc
	 */
	public void generarXmlEmpleado(Empleados empleados, Document doc) {

		Node numero = doc.createElement("numero");
		Node nEdadTexto = doc.createTextNode(empleados.getNumero().toString());
		numero.appendChild(nEdadTexto);

		Node apellido = doc.createElement("apellido");
		Node nApellidoTexto = doc.createTextNode(empleados.getApellido());
		apellido.appendChild(nApellidoTexto);

		Node jefe = null;
		if (empleados.getJefe() != null) {
			jefe = doc.createElement("jefe");
			Node nJefeTexto = doc.createTextNode(empleados.getJefe().toString());
			jefe.appendChild(nJefeTexto);
		}

		Node salario = doc.createElement("salario");
		Node nSalarioTexto = doc.createTextNode(empleados.getSalario().toString());
		salario.appendChild(nSalarioTexto);

		Node oficio = doc.createElement("oficio");
		Node nOficioTexto = doc.createTextNode(empleados.getOficio());
		oficio.appendChild(nOficioTexto);

		Node fechaAlta = doc.createElement("fechaAlta");
		Node nfechaAltaTexto = doc.createTextNode(Utiles.convertDateToString(empleados.getFechaAlta()));
		fechaAlta.appendChild(nfechaAltaTexto);

		Node comision = doc.createElement("comision");
		Node nComisionTexto = doc.createTextNode(empleados.getComision().toString());
		comision.appendChild(nComisionTexto);

		Node departamento = doc.createElement("departamento");

		Node nDepartamento = doc.createElement("departamentoNum");
		Node nDepartamentoTexto = doc.createTextNode(empleados.getDepartamento().getDepartamentoNum().toString());
		nDepartamento.appendChild(nDepartamentoTexto);

		Node nDepartamentoNombre = doc.createElement("dnombre");
		Node nDepartamentoNombreText = doc.createTextNode(empleados.getComision().toString());
		nDepartamentoNombre.appendChild(nDepartamentoNombreText);

		Node nDepartamentoLoc = doc.createElement("localidad");
		Node nDepartamentoLocText = doc.createTextNode(empleados.getComision().toString());
		nDepartamentoLoc.appendChild(nDepartamentoLocText);

		departamento.appendChild(nDepartamento);
		departamento.appendChild(nDepartamentoNombre);
		departamento.appendChild(nDepartamentoLoc);

		Node nPersona = doc.createElement("empleado");
		nPersona.appendChild(numero);
		nPersona.appendChild(apellido);

		if (jefe != null)
			nPersona.appendChild(jefe);

		nPersona.appendChild(salario);
		nPersona.appendChild(oficio);
		nPersona.appendChild(fechaAlta);
		nPersona.appendChild(comision);
		nPersona.appendChild(departamento);

		Node raiz = doc.getChildNodes().item(0);
		raiz.appendChild(nPersona);

	}

	/**
	 * Guarda los cambios dentro del xml
	 * 
	 * @param fuente
	 * @param salida
	 */
	public void guardarCambiosXML(Source fuente, Result salida) {
		try {
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.transform(fuente, salida);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
