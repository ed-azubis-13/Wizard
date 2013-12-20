package de.ergodirekt.wizard.logic;

import java.util.List;

import de.ergodirekt.wizard.shared.WizardLogger;

/**
 * Diese Klasse teilt Karten aus und implementiert die komplette Spiellogik.
 * @author Tobias
 * @author Konstantin Straub, Philipp Bruckner
 * 
 */
public class Spiellogik {

	/**
	 * Teilt sämtlichen teilnehmenden Spielern Karten entsprechend der aktuellen
	 * Runde aus.
	 * 
	 * @param runde
	 *            Die aktuelle Runde und dementsprechend die Anzahl der
	 *            auszuteilenden Karten.
	 * @param spielerListe
	 *            Die Liste mit den am Spiel teilnehmenden Spielern.
	 * @param starter
	 *            Die Position des Spielers, welcher die erste Karte bekommen
	 *            soll.
	 * @param stapel
	 *            Der zu verwendende Kartenstapel.
	 * @return Die Karte, welche als Trumpf dient. Wenn der Stapel keine Karten
	 *         mehr beinhaltet, so kommt eine Karte mit wert:0,farbe:"weiss" und
	 *         leerem Bild zurück.
	 */
	public Karte teileAus(int runde, List<Spieler> spielerListe, int starter,
			Kartenstapel stapel) {

		int aktuellePosition = starter;

		for (int i = 0; i < runde * spielerListe.size(); i++) {
			spielerListe.get(aktuellePosition).getHand()
					.add(stapel.getZufaelligeKarte());
			aktuellePosition++;
			if (aktuellePosition == spielerListe.size()) {
				aktuellePosition = 0;
			}
		}
		
		Karte trumpf = stapel.getZufaelligeKarte();
		
		for(Spieler spieler : spielerListe){
			spieler.sortiereHand(trumpf.getFarbe());
		}
		return trumpf;
	}

	/**
	 * Setzt die Stiche des Spielers auf die gewünschte Anzahl.
	 * 
	 * @param spielerListe
	 *            Die Liste mit den am Spiel teilnehmenden Spielern.
	 * @param name
	 *            Der Name des Spielers, welcher aktuell den Stich ansagt.
	 * @param anzahlStiche
	 *            Die Anzahl der Stiche, welche der Spieler machen möchte.
	 * @param runde
	 *            Die aktuelle Runde.
	 * @param starter
	 *            Die Position desjenigen Spielers, welcher als erstes den Stich
	 *            ansagt/e.
	 * @return true, wenn die Ansage gültig ist. Andernfalls false.
	 */
	public boolean sageSticheAn(List<Spieler> spielerListe, String name,
			int anzahlStiche, int runde, int starter) {
		boolean b = true;

		int positionVonName = getPositionVonName(name, spielerListe);

		int letzter;
		if (starter == 0) {
			letzter = spielerListe.size() - 1;
		} else {
			letzter = starter - 1;
		}
		if (positionVonName == -1) {
			WizardLogger.error("Es konnte kein Spieler mit dem Namen " + name
					+ " gefunden werden!");
		} else if (positionVonName == letzter
				&& anzahlStiche == getVerboteneAnsage(runde, spielerListe)) {
			b = false;
		} else {
			spielerListe.get(positionVonName).setAnsage(anzahlStiche);
		}

		return b;
	}

	/**
	 * Spielt eine Karte aus der Hand eines Spielers.
	 * 
	 * @param spielerListe
	 *            Liste aller teilnehmenden Spieler.
	 * @param name
	 *            Name des Spielers, welcher die Karte ausspielen möchte.
	 * @param kartenIndex
	 *            Der Index der gewünschten Karte aus der Hand des Spielers.
	 * @param stich
	 *            Der aktuell auszuspielende Stich.
	 * @return true, wenn der Spieler die gewünschte Karte spielen darf.
	 *         Andernfalls false.
	 */
	public boolean spieleKarte(List<Spieler> spielerListe, String name,
			Karte karte, Stich stich) {
		boolean b = true;

		int positionVonName = getPositionVonName(name, spielerListe);
		List<Karte> aktuelleHand = spielerListe.get(positionVonName).getHand();
		Karte tempKarte = null;
		
		if (stich.istKarteGueltig(karte, aktuelleHand)) {
			stich.addKarte(karte);
			for (Karte handkarte : aktuelleHand) {
				if (handkarte.getBild().toString().equals(karte.getBild().toString())) {
					tempKarte = handkarte;
				}
			}
			aktuelleHand.remove(tempKarte);
		} else {
			b = false;
		}

		return b;
	}

	/**
	 * Gibt den Sieger des aktuellen Stiches zurück.
	 * 
	 * @param stich
	 *            Der zu beendende Stich.
	 * @param starter
	 *            Position des Spielers, welcher die erste Karte in diesem Stich
	 *            ausspielte.
	 * @return Position des Spielers, welcher den Stich gewonnen hat und die
	 *         nächste Karte anspielen darf.
	 */
	public int getStichSieger(Stich stich, int starter) {
		int starterPosition = stich.getSiegerPosition() + starter;
		if (starterPosition >= stich.getGespielteKarten().size()) {
			starterPosition -= stich.getGespielteKarten().size();
		}
		return starterPosition;
	}

	public void beendeRunde(List<Spieler> spielerListe) {	
		berechnePunkte(spielerListe);
	}

	/**
	 * Berechnet und aktualisiert die Punkte aller teilnehmenden Spieler anhand
	 * ihrer Ansagen und gemachten Stiche.
	 * 
	 * @param spielerListe
	 *            Liste aller teilnehmenden Spieler.
	 */
	public void berechnePunkte(List<Spieler> spielerListe) {
		for (Spieler spieler : spielerListe) {
			if (spieler.getAnsage() == spieler.getStiche()) {
				spieler.increasePunkte(spieler.getAnsage() * 10 + 10);
			} else {
				int falsch = spieler.getAnsage() - spieler.getStiche();
				if (falsch < 0) {
					falsch *= (-1);
				}
				spieler.decreasePunkte(falsch * 10);
			}
		}

	}

	/**
	 * Errechnet die Anzahl an Stichen, welche der letzte Spieler NICHT sagen
	 * darf.
	 * 
	 * @param runde
	 *            Die aktuelle Runde.
	 * @param spielerListe
	 *            Liste aller teilnehmenden Spieler.
	 * @return Die Anzahl an Stichen, welche der letzte Spieler NICHT sagen
	 *         darf.
	 */
	public int getVerboteneAnsage(int runde, List<Spieler> spielerListe) {
		int verboteneAnsage = runde;
		for (Spieler spieler : spielerListe) {
			verboteneAnsage -= spieler.getAnsage();
		}
		return verboteneAnsage;
	}

	/**
	 * Gibt den Index eines Spielers anhand seines Namens zurück.
	 * 
	 * @param name
	 *            Der Name des Spielers.
	 * @param spielerListe
	 *            Die Liste aller teilnehmenden Spieler.
	 * @return Den Index eines Spielers anhand seines Namens.
	 */
	public int getPositionVonName(String name, List<Spieler> spielerListe) {
		int positionVonName = -1;
		for (int i = 0; i < spielerListe.size(); i++) {
			if (spielerListe.get(i).getName().equals(name)) {
				positionVonName = i;
				i = spielerListe.size();
			}
		}
		return positionVonName;
	}
}
