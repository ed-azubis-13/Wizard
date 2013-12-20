package de.ergodirekt.wizard.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse erzeugt eine neue Runde und startet sie.
 * @author Tobias
 * @author Konstantin Straub, Philipp Bruckner, Tobias 
 * 
 */
public class Spiel {

	private Kartenstapel stapel;
	private List<Spieler> spielerListe = new ArrayList<Spieler>();
	private int runde;
	private int starter;
	private Karte trumpf;
	private Spiellogik logik;
	private List<Stich> stichListe;
	private int kartengeber;

	/**
	 * Erzeugt ein neues Spiel. Dazu gehören eine neue Spiellogik, eine neue
	 * Stichliste und legt per Zufall einen Starter bzw. Kartengeber fest. Des
	 * Weiteren wird mir {@code starteNeueRunde()} bereits die erste Runde
	 * gestartet, d.h. die Runde wird hochgezählt, ein neuer Kartenstapel wird
	 * erzeugt und es werden mit {@code teileAus()} die ersten Karten ausgeteilt
	 * und der Trumpf festgelegt.
	 * 
	 * @param spielerListe
	 *            Die Liste der Spieler, welche am Spiel teilnehmen soll.
	 */
	public Spiel(List<Spieler> spielerListe) {
		stapel = new Kartenstapel();
		logik = new Spiellogik();
		this.spielerListe = spielerListe;
		stichListe = new ArrayList<Stich>();
		runde = 0;

		int rnd = (int) (Math.random() * spielerListe.size());
		kartengeber = rnd;

		starteNeueRunde();
	}

	public void starteNeueRunde() {
		runde++;
		stapel.zuruecksetzen();

		for(Spieler spieler : spielerListe){
			spieler.setAnsage(0);
			spieler.setStiche(0);
		}
		
		if (kartengeber + 1 >= spielerListe.size()) {
			kartengeber = 0;
		} else {
			kartengeber++;
		}
		
		if (kartengeber + 1 >= spielerListe.size()) {
			starter = 0;
		} else {
			starter = kartengeber + 1;
		}
		


		trumpf = logik.teileAus(runde, spielerListe, starter, stapel);
		if(trumpf.getFarbe().equals(Kartenstapel.KEINE_FARBE)){
			trumpf.setFarbe("nichts");
		}
	}

	/**
	 * 
	 * @param name
	 * @param anzahlStiche
	 * @return false, wenn Ansage nicht gültig
	 */
	public boolean ansagen(String name, int anzahlStiche) {
		boolean b = true;

		if (!logik.sageSticheAn(spielerListe, name, anzahlStiche, runde,
				starter)) {
			b = false;
		}

		return b;
	}

	/**
	 * 
	 * @param name
	 * @param kartenIndex
	 * @return false, wenn gewünschte Karte nicht gespielt werden darf
	 */
	public boolean spieleKarte(String name, Karte karte) {
		boolean b = true;
		if (stichListe.isEmpty()
				|| stichListe.get(stichListe.size() - 1).getGespielteKarten()
						.size() == spielerListe.size()) {
			stichListe.add(new Stich(trumpf.getFarbe()));
		}

		if (!logik.spieleKarte(spielerListe, name, karte,
				stichListe.get(stichListe.size() - 1))) {
			b = false;
		}

		return b;
	}

	public int getStichSieger() {
		int naechster = logik.getStichSieger(
				stichListe.get(stichListe.size() - 1), starter);
		spielerListe.get(naechster).increaseStiche();
		return naechster;
	}
	public int getStichSieger(int stichIndex) {
		int naechster = logik.getStichSieger(
				stichListe.get(stichIndex), starter);
		spielerListe.get(naechster).increaseStiche();
		return naechster;
	}

	public void beendeRunde() {
		getStichSieger();
		logik.beendeRunde(spielerListe);
	}

	public int getPositionVonName(String name) {
		int position = logik.getPositionVonName(name, spielerListe);

		return position;
	}

	public Kartenstapel getStapel() {
		return stapel;
	}

	public List<Spieler> getSpielerListe() {
		return spielerListe;
	}

	public int getRunde() {
		return runde;
	}

	public int getStarter() {
		return starter;
	}

	public Karte getTrumpf() {
		return trumpf;
	}

	public void setKartengeber(int kartengeber) {
		this.kartengeber = kartengeber;
	}

	public int getKartengeber() {
		return kartengeber;
	}

	public List<Stich> getStichListe() {
		return stichListe;
	}

	public void setStarter(int starter) {
		this.starter = starter;
	}

	public void setTrumpf(Karte trumpf) {
		this.trumpf = trumpf;
	}
	public void setTrumpf(String trumpfFarbe) {
		this.trumpf.setFarbe(trumpfFarbe);
	}

	public void setRunde(int runde) {
		this.runde = runde;
	}

}
