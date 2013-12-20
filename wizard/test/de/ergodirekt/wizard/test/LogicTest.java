package de.ergodirekt.wizard.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import de.ergodirekt.wizard.logic.Karte;
import de.ergodirekt.wizard.logic.Kartenstapel;
import de.ergodirekt.wizard.logic.Spiel;
import de.ergodirekt.wizard.logic.Spieler;
import de.ergodirekt.wizard.logic.Spiellogik;
import de.ergodirekt.wizard.logic.Stich;

public class LogicTest {
	
	
	static Spiel spiel;

	//Für die alten Tests
	static List<Spieler> spielerListe;
	static Stich stich;
	static Spiellogik logik;

	static Spieler manu;
	static Spieler tobi;
	static Spieler phil;
	static Spieler konsti;

	/**
	 * 0 - 12 = gruen 1 - 13 13 - 25 = gelb 1 - 13 26 - 38 = rot 1 - 13 39 - 51
	 * = blau 1 - 13 52, 54, 56, 58 = narren 53, 55, 57, 59 = zauberer
	 */
	static Kartenstapel stapel;
	static Karte nixxer;
	static Karte z;
	static Karte rot13;
	static Karte rot12;
	static Karte gelb7;
	static Karte blau4;
	static Karte blau8;
	static Karte blau12;
	static Karte gruen2;
	static Karte gruen10;

	@BeforeClass
	public static void init() {
		List<Spieler> spielerListeNeu = new ArrayList<Spieler>();
		spielerListeNeu.add(new Spieler("Konstantin"));
		spielerListeNeu.add(new Spieler("Manuel"));
		spielerListeNeu.add(new Spieler("Philipp"));
		spielerListeNeu.add(new Spieler("Tobias"));
		spiel = new Spiel(spielerListeNeu);
		
		//Für die alten Tests
		spielerListe = new ArrayList<Spieler>();

		konsti = new Spieler("konstantin");
		manu = new Spieler("manuel");
		phil = new Spieler("philipp");
		tobi = new Spieler("tobias");

		spielerListe.add(konsti);
		spielerListe.add(manu);
		spielerListe.add(phil);
		spielerListe.add(tobi);
		
		stich = new Stich("rot");
		stapel = new Kartenstapel();
		logik = new Spiellogik();


		/**
		 * 0 - 12 = gruen 1 - 13 13 - 25 = gelb 1 - 13 26 - 38 = rot 1 - 13 39 -
		 * 51 = blau 1 - 13 52, 54, 56, 58 = narren 53, 55, 57, 59 = zauberer
		 */
		nixxer = stapel.getKarten().get(54);
		z = stapel.getKarten().get(57);
		rot13 = stapel.getKarten().get(38);
		rot12 = stapel.getKarten().get(37);
		gelb7 = stapel.getKarten().get(19);
		blau4 = stapel.getKarten().get(42);
		blau8 = stapel.getKarten().get(46);
		blau12 = stapel.getKarten().get(50);
		gruen2 = stapel.getKarten().get(1);
		gruen10 = stapel.getKarten().get(9);
	}

	// KARTENSTAPEL
	@Test
	public void testeZuruecksetzen() {
		Kartenstapel testStapel = new Kartenstapel();
		// Neuer Stapel -> 60 Karten
		assertEquals(60, testStapel.getKarten().size());

		int anzahlAusgeteilteKarten = 5;
		for (int i = 0; i < anzahlAusgeteilteKarten; i++) {
			testStapel.getZufaelligeKarte();
		}
		// 5 ausgeteilt -> 55 Karten
		assertEquals(60 - anzahlAusgeteilteKarten, testStapel.getKarten()
				.size());

		testStapel.zuruecksetzen();
		// Zurückgesetzter Stapel -> 60 Karten
		assertEquals(60, testStapel.getKarten().size());
	}

	@Test
	public void testeGetZufaelligeKarte() {
		Kartenstapel testStapel = new Kartenstapel();
		assertEquals(60, testStapel.getKarten().size());
		Karte testKarte = testStapel.getZufaelligeKarte();
		assertEquals(59, testStapel.getKarten().size());
		for (Karte karte : testStapel.getKarten()) {
			if (karte.getWert() == testKarte.getWert()
					&& karte.getFarbe().equals(testKarte.getFarbe())
					&& testKarte.getFarbe() != Kartenstapel.KEINE_FARBE) {
				fail("Die gezogene Karte befindet sich noch immer im Kartenstapel");
			}
		}
	}

	// SPIELER
	@Test
	public void testeIncreaseStiche() {
		Spieler s = new Spieler("blubb");
		assertEquals(0, s.getStiche());
		s.increaseStiche();
		assertEquals(1, s.getStiche());
	}

	@Test
	public void testeIncreasePunkte() {
		Spieler s = new Spieler("blubb");
		assertEquals(0, s.getPunkte());
		s.increasePunkte(10);
		assertEquals(10, s.getPunkte());
	}

	@Test
	public void testeDecreasepunkte() {
		Spieler s = new Spieler("blubb");
		assertEquals(0, s.getPunkte());
		s.decreasePunkte(10);
		assertEquals(-10, s.getPunkte());
	}

	// STICH

	// getSiegerPosition ANFANG
	@Test
	public void vierZauberer() {
		Stich vierZ = new Stich("rot");

		vierZ.addKarte(z);
		vierZ.addKarte(z);
		vierZ.addKarte(z);
		vierZ.addKarte(z);

		assertEquals(0, vierZ.getSiegerPosition());
	}

	@Test
	public void vierNixxer() {

		Stich nix = new Stich(null);
		nix.addKarte(nixxer);
		nix.addKarte(nixxer);
		nix.addKarte(nixxer);
		nix.addKarte(nixxer);

		assertEquals(0, nix.getSiegerPosition());

	}

	@Test
	public void posi1() {
		Stich p1 = new Stich("rot");
		p1.addKarte(nixxer);
		System.out.println(nixxer.getWert());
		p1.addKarte(gruen2);
		p1.addKarte(gelb7);
		p1.addKarte(blau4);

		assertEquals(1, p1.getSiegerPosition());
	}

	// getSiegerPosition ENDE

	@Test
	public void testeIstKarteGueltig() {
		Stich testrunde = new Stich("rot");

		List<Karte> hand = new ArrayList<Karte>();
		hand.add(blau4);
		hand.add(gruen10);

		assertEquals(true, testrunde.istKarteGueltig(rot12, hand));
		assertEquals(true, testrunde.istKarteGueltig(z, hand));

		testrunde.addKarte(blau12);

		assertEquals(false, testrunde.istKarteGueltig(rot12, hand));
		assertEquals(true, testrunde.istKarteGueltig(z, hand));
		assertEquals(true, testrunde.istKarteGueltig(nixxer, hand));
		assertEquals(true, testrunde.istKarteGueltig(blau8, hand));
	}

	@Test
	public void testeAddeKarte() {
		Stich stich = new Stich("rot");
		assertEquals(true, stich.getGespielteKarten().isEmpty());
		stich.addKarte(new Karte(5, "gruen", null));
		assertEquals(1, stich.getGespielteKarten().size());
		assertEquals(5, stich.getGespielteKarten().get(0).getWert());
		assertEquals("gruen", stich.getGespielteKarten().get(0).getFarbe());
	}

	// SPIELLOGIK
	@Test
	public void teileAusTest() {
		init();

		logik.teileAus(5, spielerListe, 1, stapel);

		assertEquals(5, spielerListe.get(2).getHand().size());
	}

	@Test
	public void sageStichAnTest() {
		init();

		logik.sageSticheAn(spielerListe, "philipp", 4, 10, 1);

		assertEquals(4, phil.getAnsage());
	}

	@Test
	public void spieleKarteTest() {
		init();
		phil.getHand().add(rot13);
		phil.getHand().add(rot12);
		phil.getHand().add(gruen10);
		phil.getHand().add(gelb7);
		phil.getHand().add(blau12);
		phil.getHand().add(nixxer);

		stich = new Stich("rot");
		stich.addKarte(blau4);

		//TODO
//		assertEquals(false,
//				logik.spieleKarte(spielerListe, "philipp", 3, stich));
	}

	@Test
	public void testeGetStichSieger() {
		Stich s = new Stich("rot");
		/**
		 * SpielerListe 
		 * 0 - Konstantin 
		 * 1 - Manuel 
		 * 2 - Philipp 
		 * 3 - Tobias
		 */
		s.addKarte(blau4);
		s.addKarte(blau12);
		s.addKarte(gelb7);
		s.addKarte(gruen2);

		Spiellogik sl = new Spiellogik();
		// Philipp(Index == 2) spielt an -> Tobias (Index == 3) müsste gewinnen
		assertEquals(3, sl.getStichSieger(s, 2));
		
		Stich s2 = new Stich("rot");
		
		s2.addKarte(blau4);
		s2.addKarte(rot12);
		s2.addKarte(gelb7);
		s2.addKarte(gruen2);
		
		// Manuel(Index == 1) spielt an -> Philipp (Index == 2) müsste gewinnen
		assertEquals(2, sl.getStichSieger(s, 1));
	}

	@Test
	public void berechnePunkteTest() {
		init();

		tobi.setPunkte(0);
		phil.setPunkte(0);
		manu.setPunkte(0);
		konsti.setPunkte(0);
		
		tobi.setAnsage(2);
		phil.setAnsage(2);
		manu.setAnsage(1);
		konsti.setAnsage(5);
		
		tobi.setStiche(2);
		phil.setStiche(1);
		manu.setStiche(7);
		konsti.setStiche(5);
		
		
		logik.berechnePunkte(spielerListe);
		
		assertEquals(30, tobi.getPunkte());
		assertEquals(-10, phil.getPunkte());
		assertEquals(-60, manu.getPunkte());
		assertEquals(60, konsti.getPunkte());

	}

	@Test
	public void getVerboteneAnsageTest() {
		init();

		phil.setAnsage(2);
		tobi.setAnsage(2);
		manu.setAnsage(1);
		konsti.setAnsage(0);

		assertEquals(1, logik.getVerboteneAnsage(6, spielerListe));

	}
	
	@Test
	public void testeGetPositionVonName(){
		init();
		
		assertEquals(3, logik.getPositionVonName("tobias", spielerListe));
		assertEquals(0, logik.getPositionVonName("konstantin", spielerListe));
		assertEquals(2, logik.getPositionVonName("philipp", spielerListe));
		assertEquals(1, logik.getPositionVonName("manuel", spielerListe));
	}
	
	//SPIEL
	
	@Test
	public void testeStarteNeueRunde(){
		init();
		int vorherigerunde = spiel.getRunde();
		int vorherigeKartenAnzahl = spiel.getStapel().getKarten().size();
		int vorherigerKartengeber = spiel.getKartengeber();
		
		spiel.starteNeueRunde();
		//runde++
		assertEquals(vorherigerunde + 1, spiel.getRunde());
		//4 Karten weniger
		assertEquals(vorherigeKartenAnzahl - 4, spiel.getStapel().getKarten().size());
		//kartengeber++
		assertEquals(vorherigerKartengeber + 1, spiel.getKartengeber());
	}

	// ALLGEMEIN
	@Test
	public void exceptionFinder() {
		Kartenstapel stapel = new Kartenstapel();

		for (int i = 0; i < 15; i++) {
			Stich stich = new Stich("rot");
			for (int j = 0; j < 4; j++) {
				stich.addKarte(stapel.getZufaelligeKarte());
			}
			stich.getSiegerPosition();
		}
	}
}
