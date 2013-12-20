package de.ergodirekt.wizard.logic;

import java.util.ArrayList;
import java.util.List;
/**
 * Diese Klasse implementiert den Ablauf eines Stichs.
 * @author Tobias
 *
 */
public class Stich {
	private String trumpfFarbe;
	private String angespielteFarbe;
	private Karte hoechsteKarte;
	private List<Karte> gespielteKarten;

	/**
	 * Erzeugt einen "leeren" Stich.
	 * 
	 * @param trumpfFarbe
	 *            Die Farbe der aktuellen Trumpfkarte
	 */
	public Stich(String trumpfFarbe) {
		gespielteKarten = new ArrayList<Karte>();
		this.trumpfFarbe = trumpfFarbe;
		angespielteFarbe = "nichts";
	}

	/**
	 * Fügt dem Stich eine Karte hinzu und legt die angespielteFarbe fest, falls
	 * es die erste Karte in diesem Stich ist.
	 * 
	 * @param karte
	 *            Die Karte, welche dem Stich hinzugefügt werden soll.
	 */
	public void addKarte(Karte karte) {
		if (angespielteFarbe.equals("nichts")) {
			if (!karte.getFarbe().equals(Kartenstapel.KEINE_FARBE)){
				angespielteFarbe = karte.getFarbe();				
			}
		}
		gespielteKarten.add(karte);
	}

	/**
	 * Vergleicht die gespielten Karten und gibt die Position der höchsten Karte
	 * zurück.
	 * 
	 * @return Die Position der Karte, welchen den Stich gemacht hat.
	 */
	public int getSiegerPosition() {
		int zaehler = 0;
		int position = 0;

		for (Karte karte : gespielteKarten) {
			// erste Karte automatisch höchste Karte
			if (zaehler == 0) {
				hoechsteKarte = karte;
				position = zaehler;
				// prüfung zauberer
			} else if (karte.getWert() != 0) {
				if (!(hoechsteKarte.getWert() == 0 && karte.getWert() > 0)) {
					if (hoechsteKarte.getWert() != 14) {
						if (karte.getWert() != 14) {
							// prüfung trumpf
							if (!hoechsteKarte.getFarbe().equals(trumpfFarbe)) {
								if (!karte.getFarbe().equals(trumpfFarbe)) {
									// prüfung auf angespielte farbe
									if (karte.getFarbe().equals(
											hoechsteKarte.getFarbe())) {

										if (karte.getWert() > hoechsteKarte
												.getWert()) {
											hoechsteKarte = karte;
											position = zaehler;
										}
									}

								} else {
									hoechsteKarte = karte;
									position = zaehler;
								}
							} else if ((karte.getFarbe().equals(trumpfFarbe) && karte
									.getWert() > hoechsteKarte.getWert())
									|| karte.getWert() == 14) {
								hoechsteKarte = karte;
								position = zaehler;
							}

						} else {
							hoechsteKarte = karte;
							position = zaehler;
						}
					}
				} else {
					hoechsteKarte = karte;
					position = zaehler;
				}
			}
			zaehler++;
		}

		return position;
	}

	/**
	 * Prüft, ob der Spieler die gewünschte Karte spielen darf.
	 * 
	 * @param karte
	 *            Die Karte, welche der Spieler ausspielen will.
	 * @param hand
	 *            Die Hand des Spielers, welcher die Karte ausspielt.
	 * @return true, wenn der Spieler die gewünschte Karte spielen darf.
	 *         Ansonsten false
	 */
	public boolean istKarteGueltig(Karte karte, List<Karte> hand) {
		boolean b = true;

		if (angespielteFarbe != Kartenstapel.KEINE_FARBE) {
			if (karte.getFarbe() != Kartenstapel.KEINE_FARBE) {
				if (!karte.getFarbe().equals(angespielteFarbe)) {
					for (Karte handkarte : hand) {
						if (handkarte.getFarbe() == angespielteFarbe) {
							b = false;
							break;
						}
					}

				}
			}
		}
		return b;
	}

	public List<Karte> getGespielteKarten() {
		return gespielteKarten;
	}

	public String getTrumpfFarbe() {
		return trumpfFarbe;
	}

	public void setTrumpfFarbe(String trumpfFarbe) {
		this.trumpfFarbe = trumpfFarbe;
	}
}
