package de.ergodirekt.wizard.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Diese Klasse enthält die Eigenschaften eines Kommandos
 * @author Tobias
 *
 */
public class Kommando implements Serializable {

	private String kommando;

	private List<Serializable> parameter;

	private Serializable returnWert;

	public void addParameter(Serializable p) {
		if (parameter == null) {
			parameter = new ArrayList<Serializable>();
		}
		parameter.add(p);
	}

	public String getKommando() {
		return kommando;
	}

	public void setKommando(String kommando) {
		this.kommando = kommando;
	}

	public List<Serializable> getParameter() {
		return parameter;
	}

	public void setParameter(List<Serializable> parameter) {
		this.parameter = parameter;
	}

	public Serializable getReturnWert() {
		return returnWert;
	}

	public void setReturnWert(Serializable returnWert) {
		this.returnWert = returnWert;
	}

	// Setter und Getter

}
