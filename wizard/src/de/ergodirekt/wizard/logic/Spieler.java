package de.ergodirekt.wizard.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse erzeugt einen neuen Spieler und enthält seine Eigenschaften.
 * 
 * @author Tobias
 * @author Konstantin Straub, Philipp Bruckner
 * 
 */
public class Spieler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4914182854135424641L;

	private String name;
	private List<Karte> hand;
	private int punkte;
	private int ansage;
	private int stiche;

	/**
	 * Erzeugt einen Spieler, weist ihm einen Namen zu und erzeugt für ihn eine
	 * leere Kartenliste.
	 * 
	 * @param name
	 *            Namen des zu erzeugenden Spielers.
	 */
	public Spieler(String name) {
		this.name = name;
		hand = new ArrayList<Karte>();
	}

	/**
	 * Copy-Constructor
	 * 
	 * @param name
	 * @param hand
	 * @param punkte
	 * @param ansage
	 * @param stiche
	 */
	public Spieler(String name, List<Karte> hand, int punkte, int ansage,
			int stiche) {
		super();
		this.name = name;
		this.hand = hand;
		this.punkte = punkte;
		this.ansage = ansage;
		this.stiche = stiche;
	}

	public void sortiereHand(String trumpfFarbe) {
		List<Karte> sortierteListe = new ArrayList<Karte>();
		Karte tempKarte = null;

		while (!hand.isEmpty()) {
			tempKarte = null;
			// 1. Zauberer
			for (Karte karteNachFarbe : hand) {
				if (karteNachFarbe.getWert() == 14) {
					tempKarte = karteNachFarbe;
					sortierteListe.add(tempKarte);
					break;
				}
			}

			// 2. Narren
			if (tempKarte == null) {
				for (Karte karteNachFarbe : hand) {
					if (karteNachFarbe.getWert() == 0) {
						tempKarte = karteNachFarbe;
						sortierteListe.add(tempKarte);
						break;
					}
					if (tempKarte != null) {
						hand.remove(tempKarte);
					}
				}
			}
			//3. Trumpf
			if (tempKarte == null) {
				for (Karte karteNachFarbe : hand) {
					if (karteNachFarbe.getFarbe().equals(
							trumpfFarbe)) {
						tempKarte = karteNachFarbe;
						for (Karte karteNachWert : hand) {
							if (karteNachWert.getFarbe().equals(
									tempKarte.getFarbe())
									&& karteNachWert.getWert() > tempKarte
											.getWert()) {
								tempKarte = karteNachWert;
							}
						}

						sortierteListe.add(tempKarte);
						break;
					}
				}

			}
			
			// 4. farbige Karten
			for (int i = 0; i < 4; i++) {
				if (tempKarte == null) {
					for (Karte karteNachFarbe : hand) {
						if (karteNachFarbe.getFarbe().equals(
								Kartenstapel.FARBEN[i])) {
							tempKarte = karteNachFarbe;
							// 4.1. farben nach Wert
							for (Karte karteNachWert : hand) {
								if (karteNachWert.getFarbe().equals(
										tempKarte.getFarbe())
										&& karteNachWert.getWert() > tempKarte
												.getWert()) {
									tempKarte = karteNachWert;
								}
							}

							sortierteListe.add(tempKarte);
							break;
						}
					}

				}

			}
			hand.remove(tempKarte);
		}
		hand = sortierteListe;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Karte> getHand() {
		return hand;
	}

	public void setHand(List<Karte> hand) {
		this.hand = hand;
	}

	public int getPunkte() {
		return punkte;
	}

	public void increasePunkte(int punkte) {
		this.punkte += punkte;
	}

	public void decreasePunkte(int punkte) {
		this.punkte -= punkte;
	}

	public void setPunkte(int punkte) {
		this.punkte = punkte;
	}

	public int getAnsage() {
		return ansage;
	}

	public void setAnsage(int ansage) {
		this.ansage = ansage;
	}

	public int getStiche() {
		return stiche;
	}

	public void setStiche(int stiche) {
		this.stiche = stiche;
	}

	public void increaseStiche() {
		this.stiche++;
	}

}
