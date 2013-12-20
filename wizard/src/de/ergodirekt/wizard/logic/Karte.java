package de.ergodirekt.wizard.logic;

import java.io.Serializable;

import javax.swing.Icon;
/**
 * Diese Klasse enthält die Eigenschaften einer Karte inkl. Getter und Setter.
 * @author Tobias
 *
 */
public class Karte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2196815928158989694L;
	
	private int wert;
	private String farbe;
	private Icon bild;

	public Karte(int wert, String farbe, Icon bild) {
		this.wert = wert;
		this.farbe = farbe;
		this.bild = bild;

	}

	public int getWert() {
		return wert;
	}

	public void setWert(int wert) {
		this.wert = wert;
	}

	public String getFarbe() {
		return farbe;
	}

	public void setFarbe(String farbe) {
		this.farbe = farbe;
	}

	public Icon getBild() {
		return bild;
	}

	public void setBild(Icon bild) {
		this.bild = bild;
	}
}
