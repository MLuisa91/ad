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

import modelo.Departamentos;

public class CRUD_XmlDepartamentos {
	/**
	 * Genera el documento xml de departamentos
	 * 
	 * @param departamentos
	 */
	public void saveDepartamentos(List<Departamentos> departamentos) {
		Document doc = null;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;

		try {
			builder = factory.newDocumentBuilder();

			DOMImplementation implementacion = builder.getDOMImplementation();
			doc = implementacion.createDocument(null, "departamentos", null);			
			Source source = new DOMSource(doc);
			Result result = new StreamResult(new java.io.File(Constantes.FILE_DEPARTAMENTOS));

			for (Departamentos departamento : departamentos) {
				anadirDepartamento(departamento, doc);
			}

			guardarCambiosXML(source, result);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Guarda los cambios dentro del xml
	 * 
	 * @param fuente
	 * @param salida
	 */

	private void guardarCambiosXML(Source fuente, Result salida) {
		try {
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.transform(fuente, salida);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Genera los diferentes nodos del XML y los carga de contenido
	 * 
	 * @param departamentos
	 * @param doc
	 */
	private void anadirDepartamento(Departamentos departamento, Document doc) {
		Node numero = doc.createElement("departamentoNum");
		Node nDepartamentoNumero = doc.createTextNode(departamento.getDepartamentoNum().toString());
		numero.appendChild(nDepartamentoNumero);
		
		Node nombre = doc.createElement("dnombre");
		Node nDepartamentoNombre = doc.createTextNode(departamento.getDnombre());
		nombre.appendChild(nDepartamentoNombre);
		
		Node localidad = doc.createElement("localidad");
		Node nDepartamentoLocalidad = doc.createTextNode(departamento.getLocalidad());
		localidad.appendChild(nDepartamentoLocalidad);
		
		Node nDepartamento = doc.createElement("departamento");
		nDepartamento.appendChild(numero);
		nDepartamento.appendChild(nombre);
		nDepartamento.appendChild(localidad);
		
		Node raiz = doc.getChildNodes().item(0);
		raiz.appendChild(nDepartamento);
		
	}

}
