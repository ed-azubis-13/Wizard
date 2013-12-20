package de.ergodirekt.wizard.logic;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
/**
 * Diese Klasse enthält den Kartenstapel inkl. Karten und wählt eine zufällige Karte, die dann ausgeteilt wird.
 * @author Tobias
 *
 */
public class Kartenstapel {

	public static final String KEINE_FARBE = "weiss";
	public static final String[] FARBEN = { "rot","gelb", "gruen", "blau" };
	/**
	 *   0 - 12	= gruen 1 - 13
	 *  13 - 25	= gelb 1 - 13
	 *  26 - 38	= rot 1 - 13
	 *  39 - 51	= blau 1 - 13
	 *  52, 54, 56, 58 = narren
	 *  53, 55, 57, 59 = zauberer
	 */
	private List<Karte> karten;

	public Kartenstapel() {
		karten = new ArrayList<Karte>();
		zuruecksetzen();
	}

	public void zuruecksetzen() {
		karten.clear();
		// Für jede Farbe die Karten von 1-13
		for (int i = 0; i < FARBEN.length; i++) {	
			for (int j = 1; j <= 13; j++) {
				karten.add(new Karte(j, FARBEN[i], new ImageIcon("img/" + FARBEN[i] + j + ".png")));

			}
		}
		// Zauberer und Narren
		for (int i = 1; i <= FARBEN.length; i++) {
			karten.add(new Karte(0, KEINE_FARBE, new ImageIcon("img/n" + i + ".png")));
			karten.add(new Karte(14, KEINE_FARBE, new ImageIcon("img/z" + i + ".png")));
		}
	}

	/**
	 * Wählt eine zufällige Karte aus dem Kartenstapel, entfernt sie aus diesem
	 * und gibt sie zurück.
	 * Ist der Kartenstapel leer, so wird eine Karte mit wert:0,farbe:"weiss" und leerem Bild zurückgegeben.
	 * @return Die Trumpf-Karte (wenn Stapel leer mit wert:0,farbe:"weiss" und leerem Bild)
	 */
	public Karte getZufaelligeKarte() {
		int rnd = (int) (Math.random() * (karten.size()));
		Karte tempkarte;

		if (!karten.isEmpty()) {
			tempkarte = karten.get(rnd);
			karten.remove(rnd);
		} else {
			tempkarte = new Karte(0, KEINE_FARBE, new ImageIcon("img/leer.png"));
		}

		return tempkarte;
	}

	public List<Karte> getKarten() {
		return karten;
	}

	public void setKarten(List<Karte> karten) {
		this.karten = karten;
	}
}
